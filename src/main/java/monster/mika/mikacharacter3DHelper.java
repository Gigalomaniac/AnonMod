package monster.mika; ///*     */ package monster.mika;
/*     */

import Mod.AnonMod;
import characters.AnimaItem;
import characters.MikaAnimaItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.lwjgl.opengl.EXTFramebufferObject;
import ui.SkinSelectScreen;

import java.util.ArrayList;
import java.util.List;


public class mikacharacter3DHelper
{
  public static final int SSAA = 4;
  private OrthographicCamera camera;
  private FrameBuffer frameBuffer;
  private TextureRegion region;
  private Environment environment;
  private boolean done = false;
    private float currentX;
  public mikacharacter3DHelper(float v) {
    this.inited = false;
     this.skinType = SkinSelectScreen.Inst.index;
      currentX = v;
    init();
  }



  private PolygonSpriteBatch psb;

  private boolean inited;

  private mikaModelController modelController;

  private float drawX;

  private float drawY;

  private int skinType;


    private void init() {
    this.camera = new OrthographicCamera((Gdx.graphics.getWidth() * 4), (Gdx.graphics.getHeight() * 4));
    this.camera.position.set(0.0F, 0.0F, 0.0F);
    this.camera.near = -1000.0F;
    this.camera.far = 1000.0F;
    this.camera.rotate(new Vector3(0.0F, 1.0F, 0.0F), 90.0F);

    this.camera.update();
    System.out.println("this.frameBuffer");
    this.frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth() * 4, Gdx.graphics.getHeight() * 4, true);

    this.environment = new Environment();
    this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1.0F, 1.0F, 1.0F, 1.0F));




    this.psb = new PolygonSpriteBatch();

        this.modelController = new mikaModelController("img/mika/mika.g3dj", 0.0F, 0.0F, 0.0F, "bone_root|CH0069_Normal_Idle");


    for (Material material : (this.modelController.getInstance()).materials) {
      material.set(ColorAttribute.createDiffuse(Color.WHITE));
      if (material.id.contains("Month")) {
        material.set(new BlendingAttribute(770, 771));
      }
    }



    this.modelController.getTransForm().rotate(0.0F, 1.0F, 0.0F, 180.0F);
    this.modelController.getTransForm().rotate(0.0F, 0.0F, 1.0F, 5.0F);
      this.modelController.getTransForm().rotate(0, 1, 0, 300);
    this.modelController.resetPosition(0.0F, 0.0F);

    this.modelController.setDefaultState();
    this.inited = true;
  }

  public void render(SpriteBatch spriteBatch, boolean flipHorizontal) {
   if(this.modelController == null)
    System.out.println("!!!!!!!!!!!!!!!!!");
   else {

   }
    this.modelController.flip(flipHorizontal);
    spriteBatch.end();

    this.frameBuffer.begin();
    Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
    Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth() * 4, Gdx.graphics.getHeight() * 4);
    Gdx.gl.glClear(16640);
    Gdx.gl.glEnable(3042);
    Gdx.gl.glBlendFunc(770, 771);

    this.modelController.render(this.camera, this.environment);

    this.frameBuffer.end();

    this.region = new TextureRegion((Texture)this.frameBuffer.getColorBufferTexture());
    this.region.flip(false, true);


    try {
      Texture texture = (Texture)this.frameBuffer.getColorBufferTexture();

      texture.bind(0);
      EXTFramebufferObject.glGenerateMipmapEXT(texture.glTarget);
      texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
    } catch (Throwable ex) {
         System.out.println(ex);
    }

    this.psb.begin();

    this.psb.draw(this.region, this.currentX*0.7f, AbstractDungeon.player.hb.y*0.2f,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
            1.5F, 1.5F, 0.0F);
//      this.psb.draw(this.region, AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, Gdx.graphics
//
//              .getWidth(), Gdx.graphics.getHeight(), Gdx.graphics
//              .getWidth(), Gdx.graphics.getHeight(), 1.0F, 1.0F, 0.0F);
//
//    this.psb.setShader(null);

    this.psb.setBlendFunction(770, 771);
    this.psb.end();
    spriteBatch.begin();
  }

  public void update(float current_x, float current_y) {
    if(!AnonMod.done){
        System.out.println(AnonMod.done);
    AnonMod.done= true;
      init();
    }

    this.modelController.update();
    this.drawX = current_x;
    this.drawY = current_y;
    this.modelController.resetPosition(0.0F, 0.0F);
  }




public void queueAnimaItem(List<AnimaItem> items, boolean clearQueue) { queueAnimaItem(items, clearQueue, 1); }


    public void queueAnimaItem(AnimaItem item, boolean clearQueue) {
        List<AnimaItem> list = new ArrayList<AnimaItem>();
        list.add(item);
        queueAnimaItem(list, clearQueue, 1);
    }

    public void queueAnimaItem(List<AnimaItem> items, boolean clearQueue, int loop) {
        if (clearQueue) {
            this.modelController.clearQueue();
        }
        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                String id = this.modelController.getAnimaId(((AnimaItem)items.get(i)).get());
                this.modelController.queueAnimation(modelController ->
                        modelController.animate(id, loop, 1.0F, null, 0.2F));
            }
        }
    }



//    public void queueTransItem(List<ModelController.TransItem> items) { Objects.requireNonNull(this.modelController); items.forEach(this.modelController::queueTrans); }



    public void rotateModel(int x, int y, int z, int degree) { this.modelController.getTransForm().rotate(x, y, z, degree); }








    public void clearAnimation() {
        this.modelController.clearQueue();
        this.modelController.setDefaultState();
    }

    public ArrayList<String> getCommandList() {
        ArrayList<String> commands = new ArrayList<String>();
        for (Animation a : this.modelController.getAnimaMap()) {
            commands.add(a.id);
        }
        return commands;
    }


    public String getCurrentAnima() { return this.modelController.getCurrentAnima(); }


//    public boolean isAttacking() {
//        String anima = getCurrentAnima();
//        return ((SkinSelectScreen.Inst.index == 0 && anima.equals(this.modelController.getAnimaId(MikaAnimaItem.ATTACK_START.get()))));
//    }
}
