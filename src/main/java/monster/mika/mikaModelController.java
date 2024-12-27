package monster.mika;//package monster.mika;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.megacrit.cardcrawl.core.Settings;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;


public class mikaModelController
{
  private static final float SCALE = 2.2F * Settings.scale * 4.0F;
  private static final float MOVESCALE = Settings.scale * 4.0F * 4.0F; private G3dModelLoader loader; private Model model; private ModelInstance instance; private ModelBatch modelBatch;
  private AnimationController animationController;
  private float current_x;
  private float current_y;

  public mikaModelController(String modelPath, float x, float y, float z, String StandAnima) {
    this.current_x = 0.0F; this.current_y = 0.0F; this.target_x = 0.0F; this.target_y = 0.0F;
    this.fliped = false;
    this.loader = new G3dModelLoader(new JsonReader());
    this.model = this.loader.loadModel(Gdx.files.internal(modelPath));
    this.instance = new ModelInstance(this.model, -500.0F, 0.0F, 0.0F);
    this.animationController = new AnimationController(this.instance);
    this.animationController.allowSameAnimation = true;
    this.modelBatch = new ModelBatch();
    this.instance.transform.setTranslation(z, y, x);
    this.instance.transform.scale(SCALE, SCALE, SCALE);
    this.StandAnima = StandAnima;

    this.animaQueue = new LinkedList();
    this.transQueue = new LinkedList();
    for(Material m:this.instance.materials){
        if(m.id.contains("Mouth")){
            m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        }
    }

  }
  private float target_x; private float target_y; private String StandAnima; private Queue<Consumer<AnimationController>> animaQueue; private Queue<TransItem> transQueue; private Matrix4 DefaultState; private boolean fliped;

  public void setDefaultState() { this.DefaultState = this.instance.transform.cpy(); }


  public void update() {
    if (this.animationController.current == null || this.animationController.current.time >= this.animationController.current.duration) {
      if (!this.animaQueue.isEmpty()) {
        ((Consumer)Objects.requireNonNull((Consumer)this.animaQueue.poll())).accept(this.animationController);
      } else {

        this.animationController.queue(this.StandAnima, 1, 1.0F, null, 0.0F);
      }
    }
    if (!this.transQueue.isEmpty()) {
      TransItem item = (TransItem)this.transQueue.peek();
      item.time -= Gdx.graphics.getDeltaTime();
      if (item.time < 0.0F) {
        item.accept(this.instance);
        this.transQueue.poll();
      }
    }
    this.animationController.update(Gdx.graphics.getDeltaTime());

    if (Math.abs(this.current_x - this.target_x) > MOVESCALE) {
      this.current_x += Math.signum(this.target_x - this.current_x) * MOVESCALE;
    } else {
      this.current_x = this.target_x;
    }
    if (Math.abs(this.current_y - this.target_y) > MOVESCALE) {
      this.current_y += Math.signum(this.target_y - this.current_y) * MOVESCALE;
    } else {

      this.current_y = this.target_y;
    }
    this.instance.transform.setTranslation(0.0F, this.current_y, this.current_x);
  }

  public void render(OrthographicCamera camera, Environment environment) {
    this.modelBatch.begin(camera);
    this.modelBatch.render(this.instance, environment);
    this.modelBatch.end();
  }

  public void resetPosition(float x, float y) {
    this.current_x = this.target_x = x;
    this.current_y = this.target_y = y;
    this.instance.transform.setTranslation(-500.0F, this.current_y, this.current_x);
  }

  public void setCurrentPosition(float x, float y) {
    this.current_x = x;
    this.current_y = y;
  }

  public void moveCurrentPosition(float x, float y) {
    this.current_x += x;
    this.current_y += y;
  }

  public void clearQueue(AnimationController controller) {
    while (controller.current != null) {
      controller.setAnimation(null);
      this.animaQueue.clear();
      this.transQueue.clear();
      resetModel();
    }
  }


  private void resetModel() { this.instance.transform = this.DefaultState.cpy(); }


  public void clearQueue() {
    while (this.animationController.current != null) {
      this.animationController.setAnimation(null);
      this.animaQueue.clear();
      this.transQueue.clear();
      resetModel();
    }
  }

  public void setAnimation(Consumer<AnimationController> animation) {
    clearQueue(this.animationController);
    resetPosition(this.target_x, this.target_y);
    this.animaQueue.add(animation);
  }


  public void queueAnimation(Consumer<AnimationController> animation) { this.animaQueue.add(animation); }


  public void setStandAnima(String anima) {
    if (this.animationController.current != null && this.animationController.current.animation.id.equals(this.StandAnima)) {
      clearQueue(this.animationController);
    }
    this.StandAnima = anima;
  }

  public String getCurrentAnima() {
    if (this.animationController.current == null) {
      return "";
    }
    return this.animationController.current.animation.id;
  }


  public void resetDefaultAnima() { clearQueue(this.animationController); }



  public Matrix4 getTransForm() { return this.instance.transform; }



  public Array<Animation> getAnimaMap() { return this.instance.animations; }


  public String getAnimaId(int i) {
    if (i < this.instance.animations.size) {
      return ((Animation)this.instance.animations.get(i)).id;
    }
    return ((Animation)this.instance.animations.get(0)).id;
  }


  public ModelInstance getInstance() { return this.instance; }


  public boolean flip(boolean flip) {
    if (flip != this.fliped) {
      this.instance.transform.rotate(0.0F, 1.0F, 0.0F, 180.0F);
      this.fliped = flip;
      setDefaultState();
    }
    return this.fliped;
  }


  public void queueTrans(TransItem item) { this.transQueue.add(item); }

  public static class TransItem
  {
    float time;
    Consumer<ModelInstance> trans;

    public TransItem(float time, Consumer<ModelInstance> trans) {
      this.time = time;
      this.trans = trans;
    }


    public void accept(ModelInstance instance) { this.trans.accept(instance); }
  }
}
