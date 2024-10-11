package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author Keeper
 * @example： At Class test, define:
 * private final int integer0 = 1;
 * private static final int integer1 = 1;
 * private final Integer integer2 = 1;
 * private static final Integer integer3 = 1;
 * private Integer integer4 = 1;
 * private static Integer integer5 = 1;
 * private final String string0 = "1";
 * private static final String string1 = "1";
 * <p>
 * <p>
 * At Class A, run codes:
 * public static void main(String[] args) {
 * test t = new test();
 * Invoker.setField(t, "integer0", 2);
 * Invoker.setField(t, "integer1", 2);//或者Invoker.setField(test.class, "integer1", 2);
 * Invoker.setField(t, "integer2", 2);
 * Invoker.setField(t, "integer3", 2);//或者Invoker.setField(test.class, "integer3", 2);
 * Invoker.setField(t, "integer4", 2);
 * Invoker.setField(t, "integer5", 2);//或者Invoker.setField(test.class, "integer5", 2);
 * Invoker.setField(t, "string0", "2");
 * Invoker.setField(t, "string1", "2");//或者Invoker.setField(test.class, "string1", 2);
 * System.out.println(t.integer0);
 * System.out.println(t.integer1);
 * System.out.println(t.integer2);
 * System.out.println(t.integer3);
 * System.out.println(t.integer4);
 * System.out.println(t.integer5);
 * System.out.println(t.string0);
 * System.out.println(t.string1);
 * }
 * <p>
 * result:
 * <p>
 * 1
 * 1
 * 2
 * 2
 * 2
 * 2
 * 1
 * 1
 * <p>
 * 说明：
 * 只有final类型的8个基本类型（int, char, long, float, double, byte, boolean, short）和String不能改变值，原因是编译时这些属性在使用的地方被替换成具体值，后续修改了值也不能改变那些位置的值。
 * 例子中只有改变值，这里说明一下：
 * 获取值使用 Invoker.getField(对象/类名.class, 对应属性名称)
 * 比如获取上面integer1的值就用 Invoker.setField(t或者test.class, "integer1") 如果泛型匹配有歧义 可以使用强制类型转换
 * 要调用private或protected的方法，使用 Invoker.invoke(对象或者类名.class, 方法名, 0个或几个参数); public的方法也能用但是没必要。
 * 当例子用的话（因为常用的函数不太能想到private的）：
 * 一：integer0.toString()可以用Invoker.invoke(integer0, "toString")替换
 * 二：Integer.parseInt("1")可以用Invoker.invoke(Integer.class, "parseInt", "1")替换
 * 三：map.put("list", new ArrayList()) 可以用Invoker.invoke(map, "put", "list", new ArrayList())替换
 * 如果要使用函数的返回值 直接用 类型 名称 = Invoker.invoke(...); 例如：一：String s = Invoker.invoke(integer0, "toString");就可以使用了。
 * 类名.class的调用当然只能使用静态变量或者静态方法，请注意。
 */

@SuppressWarnings("ALL")
public class Invoker {

    private static ConcurrentHashMap<Class, HashMap<String, Field>> fields = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Class, HashMap<String, HashMap<Class[], Method>>> methods = new ConcurrentHashMap<>();

    public static final Logger LOGGER = Logger.getLogger(Invoker.class.toString());

    private static final HashMap<Class, Class> basics = new HashMap<>();

    private static final String NOT_MATCH = "NOT_MATCH";

    static {
        basics.put(Integer.class, int.class);
        basics.put(Character.class, char.class);
        basics.put(Boolean.class, boolean.class);
        basics.put(Byte.class, byte.class);
        basics.put(Float.class, float.class);
        basics.put(Double.class, double.class);
        basics.put(Short.class, short.class);
        basics.put(Long.class, long.class);
    }


    public static <T> T getField(Object target, String name) {
        try {
            return (T) getField0(target, name).get(target);
        } catch (IllegalAccessException | RuntimeException ignore) {
        }
        return null;
    }

    private static Field getField0(Object target, String name) {
        Class clazz = getClass(target);
        Field field = getFieldInCache(clazz, name);
        if (field != null) {
            return field;
        }
        Class c = clazz;
        while (c != Object.class && field == null) {
            try {
                field = c.getDeclaredField(name);
                setAccessible(field);
                save(clazz, name, field);
                return field;
            } catch (NoSuchFieldException | RuntimeException ignore) {
            }
            c = c.getSuperclass();
        }
        LOGGER.severe("No field '" + name + "' found in target class '" + clazz.getName() + "' and super classes.");
        return null;
    }

    public static void setField(Object target, String name, Object val) {
        Field field = getField0(target, name);
        try {
            if (field != null) {
                field.set(target, val);
            }
        } catch (IllegalAccessException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private static Method getMethod(Object target, String name, Class... paramTypes) {
        Class clazz = getClass(target);
        Method method = getMethodInCache(clazz, name, paramTypes);
        if (method != null) {
            return method;
        }
        Class c = clazz;
        while (c != Object.class && method == null) {
            try {
                method = c.getDeclaredMethod(name, paramTypes);
                setAccessible(method);
                save(clazz, name, method, paramTypes);
            } catch (NoSuchMethodException | RuntimeException ignore) {
            }
            c = c.getSuperclass();
        }
        if (method == null) {
            method = getSameNameMethodInCache(clazz, name, paramTypes);
            return method;
        }
        return method;
    }

    public static <T> T invoke(Object target, String name, Object... args) {
        Method method;
        try {
            Class[] params = getParamTypes(0, args);
            method = getMethod(target, name, params);
            if (method != null) {
                return (T) method.invoke(target, args);
            } else {
                long typesCount = typesCount(args);
                for (long i = typesCount - 1; i > 0; i--) {
                    try {
                        method = getMethod(target, name, getParamTypes(i, args));
                        if (method != null) {
                            save(getClass(target), name, method, params);
                            return (T) method.invoke(target, args);
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
            if (method == null) {
                List<Class[]> types = getSuperParamTypes(args);
                allSuperClass.clear();
                for (Class[] type : types) {
                    try {
                        method = getMethod(target, name, type);
                        if (method != null) {
                            save(getClass(target), name, method, params);
                            return (T) method.invoke(target, args);
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T invoke(Method method, Object target, Object... args) {
        try {
            return (T) method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void save(Class clazz, String name, Field field) {
        if (!fields.containsKey(clazz) || fields.get(clazz) == null) {
            HashMap<String, Field> map = new HashMap<>();
            map.put(name, field);
            fields.put(clazz, map);
        } else {
            HashMap<String, Field> map = fields.get(clazz);
            if (map.get(name) == null) {
                map.put(name, field);
            }
        }
    }

    private static void save(Class clazz, String name, Method method, Class... paramTypes) {
        if (methods.get(clazz) == null) {
            HashMap<String, HashMap<Class[], Method>> map = new HashMap<>();
            HashMap<Class[], Method> map2 = new HashMap<>();
            map.put(name, map2);
            map2.put(paramTypes, method);
            methods.put(clazz, map);
        } else {
            HashMap<String, HashMap<Class[], Method>> map = methods.get(clazz);
            if (map.get(name) == null) {
                HashMap<Class[], Method> map2 = new HashMap<>();
                map.put(name, map2);
                map2.put(paramTypes, method);
            }
        }
    }

    private static Field getFieldInCache(Class clazz, String name) {
        if (fields.get(clazz) == null) {
            return null;
        }
        return fields.get(clazz).get(name);
    }

    private static Method getMethodInCache(Class clazz, String name, Class... paramTypes) {
        if (methods.get(clazz) == null || methods.get(clazz).get(name) == null) {
            return null;
        }
        return methods.get(clazz).get(name).get(paramTypes);
    }

    private static Method getSameNameMethodInCache(Class clazz, String name, Class... paramTypes) {
        if (methods.get(clazz) == null || methods.get(clazz).get(name) == null) {
            return null;
        }
        HashMap<Class[], Method> map = methods.get(clazz).get(name);
        for (Class[] classes : map.keySet()) {
            if (isImplClassArray(classes, paramTypes)) {
                return map.get(classes);
            }
        }
        return null;
    }

    private static boolean isImplClassArray(Class[] c1, Class[] c2) {
        if (c1.length == c2.length) {
            for (int i = 0; i < c1.length; i++) {
                if (!c2[i].isAssignableFrom(c1[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Class getClass(Object target) {
        return target instanceof Class ? (Class) target : target.getClass();
    }

    public static void setAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }

        if (Modifier.isFinal(field.getModifiers())) {
            Field modifiersField = null;
            try {
                modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            } catch (NoSuchFieldException | IllegalAccessException e) {
            }
        }
    }

    private static void setAccessible(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            method.setAccessible(true);
        }
    }

    private static long typesCount(Object... args) {
        int cnt = 0;
        for (Object o : args) {
            if (basics.containsKey(o.getClass())) {
                cnt++;
            }
        }
        return 1 << cnt;
    }

    private static Class[] getParamTypes(long seed, Object... args) {
        Class[] params = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            params[i] = args[i].getClass();
            if (basics.containsKey(params[i])) {
                if ((seed & 1) == 0) {
                    params[i] = basics.get(params[i]);
                }
                seed >>>= 1;
            }
        }
        return params;
    }

    private static List<Class[]> allSuperClass = new ArrayList<>();
    private static Object[] theArgs = null;

    //非线程安全
    private static List<Class[]> getSuperParamTypes(Object... args) {
        allSuperClass.clear();
        theArgs = args;
        getSuperParamTypes(new Class[args.length], 0);
        return allSuperClass;
    }

    private static void getSuperParamTypes(Class[] params, int i) {
        if (i == theArgs.length) {
            allSuperClass.add(params);
            return;
        }
        params[i] = theArgs[i].getClass();
        if (basics.containsKey(params[i])) {
            getSuperParamTypes(params, i + 1);
            Class[] newArr = params.clone();
            newArr[i] = basics.get(params[i]);
            getSuperParamTypes(newArr, i + 1);
        } else {
            List<Class> supers = getSuperClasses(params[i]);
            for (Class c : supers) {
                Class[] newArr = params.clone();
                newArr[i] = c;
                getSuperParamTypes(newArr, i + 1);
            }
        }
    }

    private static List<Class> getSuperClasses(Class c) {
        Class[] interfaces = c.getInterfaces();
        List<Class> classes = new ArrayList<>();
        while (c != Object.class) {
            c = c.getSuperclass();
            classes.add(c);
        }
        for (Class cc : interfaces) {
            classes.add(cc);
        }
        return classes;
    }

}

