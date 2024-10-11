
// Heartfelt - by Martijn Steinrucken aka BigWings - 2017
// Email:countfrolic@gmail.com Twitter:@The_ArtOfCode
// License Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.

// Eric Bartusch modified to only include the droplets falling


varying vec2 v_texCoord;

uniform vec2 u_screenSize;
uniform float u_time;
uniform sampler2D u_texture;
uniform float u_dripAmount1;
uniform float u_dripAmount2;
uniform float u_dripSpeed;
uniform float u_dripStrength;

#define S(a, b, t) smoothstep(a, b, t)

vec3 N13(float p) {
    //  from DAVE HOSKINS
   vec3 p3 = fract(vec3(p) * vec3(.1031,.11369,.13787));
   p3 += dot(p3, p3.yzx + 19.19);
   return fract(vec3((p3.x + p3.y)*p3.z, (p3.x+p3.z)*p3.y, (p3.y+p3.z)*p3.x));
}

float N(float t) {
    return fract(sin(t*12345.564)*7658.76);
}

float Saw(float b, float t) {
	return S(0., b, t)*S(1., b, t);
}

vec2 DropLayer2(vec2 uv, float t) {
    vec2 UV = uv;

    uv.y += t*0.75;
    vec2 a = vec2(6., 1.);
    vec2 grid = a*2.;
    vec2 id = floor(uv*grid);

    float colShift = N(id.x);
    uv.y += colShift;

    id = floor(uv*grid);
    vec3 n = N13(id.x*35.2+id.y*2376.1);
    vec2 st = fract(uv*grid)-vec2(.5, 0);

    float x = n.x-.5;

    float y = UV.y*20.;
    float wiggle = sin(y+sin(y));
    x += wiggle*(.5-abs(x))*(n.z-.5);
    x *= .7;
    float ti = fract(t+n.z);
    y = (Saw(.85, ti)-.5)*.9+.5;
    vec2 p = vec2(x, y);

    float d = length((st-p)*a.yx);

    float mainDrop = S(.4, .0, d);

    float r = sqrt(S(1., y, st.y));
    float cd = abs(st.x-x);
    float trail = S(.23*r, .15*r*r, cd);
    float trailFront = S(-.02, .02, st.y-y);
    trail *= trailFront*r*r;

    y = UV.y;
    float trail2 = S(.2*r, .0, cd);
    float droplets = max(0., (sin(y*(1.-y)*120.)-st.y))*trail2*trailFront*n.z;
    y = fract(y*10.)+(st.y-.5);
    float dd = length(st-vec2(x, y));
    droplets = S(.3, 0., dd);
    float m = mainDrop+droplets*r*trailFront;

    return vec2(m, trail);
}


vec2 Drops(vec2 uv, float t, float l0, float l1, float l2) {
    float s = 0.;
    vec2 m1 = DropLayer2(uv, t)*l1;
    vec2 m2 = DropLayer2(uv*1.85, t)*l2;

    float c = s+m1.x+m2.x;
    c = S(.3, 1., c);

    return vec2(c, max(m1.y*l0, m2.y*l1));
}

void main()
{
	vec2 uv = (gl_FragCoord.xy-.5*u_screenSize.xy) / u_screenSize.y;
    vec2 UV = gl_FragCoord.xy/u_screenSize.xy;

    vec4 col = texture2D(u_texture, v_texCoord);

    if (col.r > 0.0 || col.g > 0.0 || col.b > 0.0) {
        float t = u_time*u_dripSpeed;

        UV = (UV-.5)*1.+.5;

        float layer1 = S(.25, .75, u_dripAmount1);
        float layer2 = S(.0, .5, u_dripAmount2);


        vec2 c = Drops(uv, t, 0.0, layer1, layer2);
        vec2 e = vec2(.001, 0.);
        float cx = Drops(uv+e, t, 0.0, layer1, layer2).x;
        float cy = Drops(uv+e.yx, t, 0.0, layer1, layer2).x;
        vec2 n = vec2(cx-c.x, cy-c.x);

        col.rgb = texture2D(u_texture, UV+n * u_dripStrength, 1.0).rgb;
    }

    gl_FragColor = col;
}