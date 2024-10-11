package relics.test;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomMonster;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Defect;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.characters.TheSilent;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.shop.Merchant;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackgroundMonster extends AbstractGameEffect {
    private AbstractCreature ac;
    private Skeleton skeleton;
    private Texture img;
    private AbstractAnimation animation;
    private float rotationVelocity;
    private Vector2 direction;

    public BackgroundMonster(boolean isfast) {
        this.ac = RandomEntity();
        this.ac.flipHorizontal = MathUtils.randomBoolean();


        this.direction = new Vector2(MathUtils.random(100, 200), MathUtils.random(-40, 40));
        this.direction = direction.nor();
        this.renderBehind = true;
        this.color = Color.WHITE.cpy().sub(0, 0, 0, MathUtils.random(0.4F, 0.7F));
        if(MathUtils.randomBoolean(0.2F)) {
            this.direction.x *= -1;
            this.renderBehind = false;
            this.color.a -= 0.1F;
        }
        this.direction.x *= Settings.scale * 50F;
        this.direction.y *= Settings.scale * 50F;

        float multiplier = Settings.WIDTH / 2F / Math.abs(direction.x) * 1.3F;
        this.ac.drawX = Settings.WIDTH / 2F - direction.x * multiplier;
        this.ac.drawY = Settings.HEIGHT * 2F / 3F + MathUtils.random(-Settings.HEIGHT, Settings.HEIGHT) / 3F - direction.y * multiplier;

        this.rotation = MathUtils.random(-360F, 360F);
        this.rotationVelocity = MathUtils.random(30F, 50F);
        if(MathUtils.randomBoolean()) {
            this.rotationVelocity *= -1;
        }

        boolean isPlayer = ac instanceof AbstractPlayer;

        this.skeleton = (Skeleton) ReflectionHacks.getPrivate(ac, AbstractCreature.class, "skeleton");
        if(this.skeleton != null) {
            this.ac.state.update(Gdx.graphics.getDeltaTime());
            this.ac.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();

            String[] shadows = new String[]{"shadow", "Shadow"};
            Bone shadow;
            for(final String s : shadows) {
                shadow = this.skeleton.findBone(s);
                if (shadow != null) {
                    shadow.setScale(0F);
                }
            }
        }
        this.img = isPlayer ? ((AbstractPlayer) ac).img : ((Texture) ReflectionHacks.getPrivate(ac, AbstractMonster.class, "img"));

        if(this.skeleton == null && this.img == null && (isPlayer && ac instanceof CustomPlayer || (!isPlayer && ac instanceof CustomMonster))) {
            this.animation = (AbstractAnimation) ReflectionHacks.getPrivate(ac, isPlayer ? CustomPlayer.class : CustomMonster.class, "animation");
        }

        if(isfast) {
            this.accelerate();
        }
    }
    public void accelerate() {
        this.direction.x *= MathUtils.random(7F, 8F);
        this.direction.y *= MathUtils.random(7F, 8F);
    }

    @Override
    public void update() {
        this.ac.drawX += direction.x * Gdx.graphics.getDeltaTime();
        this.ac.drawY += direction.y * Gdx.graphics.getDeltaTime();
        this.rotation += this.rotationVelocity * Gdx.graphics.getDeltaTime();

        this.ac.updateAnimations();

        if((direction.x < 0 && ac.drawX < -Settings.WIDTH / 3) || (direction.x > 0 && ac.drawX > Settings.WIDTH * 1.3F)) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(this.animation != null) {
            sb.setColor(this.color);
            this.animation.setFlip(ac.flipHorizontal, ac.flipVertical);
            this.animation.renderSprite(sb, ac.drawX, ac.drawY);
            sb.setColor(Color.WHITE.cpy());
        } else if(this.skeleton != null) {
            float angle = (float)Math.toRadians(this.rotation);

            this.skeleton.getRootBone().setRotation(this.rotation);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(
                    (float)(Math.cos(angle) * (this.ac.drawX - this.ac.hb.cX) - Math.sin(angle) * (this.ac.drawY - this.ac.hb.cY) + ac.hb.cX),
                    (float)(Math.sin(angle) * (this.ac.drawX - this.ac.hb.cX) + Math.cos(angle) * (this.ac.drawY - this.ac.hb.cY) + ac.hb.cY));
            this.skeleton.setColor(this.color);
            this.skeleton.setFlip(false, ac.flipVertical);
            if(ac.flipHorizontal) {
                this.skeleton.getRootBone().setScaleX(-Math.abs(this.skeleton.getRootBone().getScaleX()));
            }
            sb.end();
            CardCrawlGame.psb.begin();
            AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
            sb.setBlendFunction(770, 771);
        } else {
            sb.setColor(this.color);
            sb.draw(this.img, ac.drawX, ac.drawY, this.img.getWidth() * Settings.scale / 2F, this.img.getHeight() * Settings.scale, this.img.getWidth() * Settings.scale, this.img.getHeight() * Settings.scale, 1F, 1F, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), ac.flipHorizontal, ac.flipVertical);
            sb.setColor(Color.WHITE.cpy());
        }
    }

    @Override
    public void dispose() {}

    public static void masterDispose() {
        for(final AbstractGameEffect age : AbstractDungeon.effectList) {
            if(age instanceof BackgroundMonster) {
                age.isDone = true;
            }
        }
        for(final AbstractCreature ac : data.values()) {
            if(ac instanceof AbstractMonster) {
                ((AbstractMonster) ac).dispose();
            }
        }
        data.clear();
    }


    private static AbstractCreature RandomEntity() {
        ArrayList<Class> possibilities = new ArrayList<>();


        possibilities.add(LouseNormal.class);
        possibilities.add(LouseDefensive.class);
        possibilities.add(Lagavulin.class);
        possibilities.add(GremlinNob.class);
        possibilities.add(Sentry.class);
        possibilities.add(GremlinFat.class);
        possibilities.add(GremlinThief.class);
        possibilities.add(GremlinWarrior.class);
        possibilities.add(GremlinWizard.class);
        possibilities.add(GremlinTsundere.class);
        possibilities.add(Cultist.class);
        possibilities.add(AcidSlime_L.class);
        possibilities.add(AcidSlime_M.class);
        possibilities.add(AcidSlime_S.class);
        possibilities.add(SpikeSlime_L.class);
        possibilities.add(SpikeSlime_M.class);
        possibilities.add(SpikeSlime_S.class);
        possibilities.add(FungiBeast.class);
        possibilities.add(JawWorm.class);
        possibilities.add(Looter.class);
        possibilities.add(SlaverBlue.class);
        possibilities.add(SlaverRed.class);
        possibilities.add(TheGuardian.class);
        possibilities.add(SlimeBoss.class);

        possibilities.add(BanditBear.class);
        possibilities.add(BanditLeader.class);
        possibilities.add(BanditPointy.class);
        possibilities.add(BookOfStabbing.class);
        possibilities.add(BronzeAutomaton.class);
        possibilities.add(BronzeOrb.class);
        possibilities.add(Byrd.class);
        possibilities.add(Centurion.class);
        possibilities.add(Champ.class);
        possibilities.add(Chosen.class);
        possibilities.add(GremlinLeader.class);
        possibilities.add(Healer.class);
        possibilities.add(Mugger.class);
        possibilities.add(ShelledParasite.class);
        possibilities.add(SnakePlant.class);
        possibilities.add(Snecko.class);
        possibilities.add(SphericGuardian.class);
        possibilities.add(Taskmaster.class);
        possibilities.add(TheCollector.class);
        possibilities.add(TorchHead.class);

        possibilities.add(AwakenedOne.class);
        possibilities.add(Darkling.class);
        possibilities.add(Deca.class);
        possibilities.add(Donu.class);
        possibilities.add(Exploder.class);
        possibilities.add(GiantHead.class);
        possibilities.add(Maw.class);
        possibilities.add(Nemesis.class);
        possibilities.add(OrbWalker.class);
        possibilities.add(Reptomancer.class);
        possibilities.add(Repulsor.class);
        possibilities.add(SnakeDagger.class);
        possibilities.add(Spiker.class);
        possibilities.add(SpireGrowth.class);
        possibilities.add(TimeEater.class);
        possibilities.add(Transient.class);
        possibilities.add(WrithingMass.class);

        possibilities.add(SpireShield.class);
        possibilities.add(SpireSpear.class);



        possibilities.add(Ironclad.class);
        possibilities.add(TheSilent.class);
        possibilities.add(Defect.class);

        possibilities.add(Merchant.class);


        return getSpineEntity(possibilities.get(MathUtils.random(possibilities.size() - 1)));
    }

    static class SpineAnimationEntity extends AbstractMonster {
        public SpineAnimationEntity(String atlas, String skeleton, float scale, float width, float height) {
            super("", "", 0, 0, 0, width, height, null, 0, 0);
            this.loadAnimation(atlas, skeleton, scale);
        }
        public SpineAnimationEntity(AbstractMonster ac) {
            super("", "", 0, 0, 0, 0, 0, null, 0, 0);
            this.atlas = (TextureAtlas)ReflectionHacks.getPrivate(ac, AbstractCreature.class, "atlas");
            this.skeleton = (Skeleton)ReflectionHacks.getPrivate(ac, AbstractCreature.class, "skeleton");
            this.stateData = (AnimationStateData) ReflectionHacks.getPrivate(ac, AbstractCreature.class, "stateData");
            this.state = (AnimationState) ReflectionHacks.getPrivate(ac, AbstractCreature.class, "state");
            this.hb.width = ac.hb.width;
            this.hb.height = ac.hb.height;
            this.img = (Texture) ReflectionHacks.getPrivate(ac, AbstractMonster.class, "img");
        }

        @Override
        public void takeTurn() {}

        @Override
        protected void getMove(int i) {}
    }

    private static Map<Class, AbstractMonster> data = new HashMap<>();
    private static SpineAnimationEntity getSpineEntity(Class clz) {
        if(!data.containsKey(clz)) {
            if (LouseNormal.class.equals(clz)) {
                data.put(clz, new LouseNormal(0, 0));
            } else if (LouseDefensive.class.equals(clz)) {
                data.put(clz, new LouseDefensive(0, 0));
            } else if (Lagavulin.class.equals(clz)) {
                data.put(clz, new Lagavulin(false));
            } else if (GremlinNob.class.equals(clz)) {
                data.put(clz, new GremlinNob(0, 0));
            } else if (Sentry.class.equals(clz)) {
                data.put(clz, new Sentry(0, 0));
            } else if (GremlinFat.class.equals(clz)) {
                data.put(clz, new GremlinFat(0, 0));
            } else if (GremlinThief.class.equals(clz)) {
                data.put(clz, new GremlinThief(0, 0));
            } else if (GremlinWarrior.class.equals(clz)) {
                data.put(clz, new GremlinWarrior(0, 0));
            } else if (GremlinWizard.class.equals(clz)) {
                data.put(clz, new GremlinWizard(0, 0));
            } else if (GremlinTsundere.class.equals(clz)) {
                data.put(clz, new GremlinTsundere(0, 0));
            } else if (Cultist.class.equals(clz)) {
                data.put(clz, new Cultist(0, 0));
            } else if (AcidSlime_L.class.equals(clz)) {
                data.put(clz, new AcidSlime_L(0, 0));
            } else if (AcidSlime_M.class.equals(clz)) {
                data.put(clz, new AcidSlime_M(0, 0));
            } else if (AcidSlime_S.class.equals(clz)) {
                data.put(clz, new AcidSlime_S(0, 0, 0));
            } else if (SpikeSlime_L.class.equals(clz)) {
                data.put(clz, new SpikeSlime_L(0, 0));
            } else if (SpikeSlime_M.class.equals(clz)) {
                data.put(clz, new SpikeSlime_M(0, 0));
            } else if (SpikeSlime_S.class.equals(clz)) {
                data.put(clz, new SpikeSlime_S(0, 0, 0));
            } else if (FungiBeast.class.equals(clz)) {
                data.put(clz, new FungiBeast(0, 0));
            } else if (JawWorm.class.equals(clz)) {
                data.put(clz, new JawWorm(0, 0));
            } else if (Looter.class.equals(clz)) {
                data.put(clz, new Looter(0, 0));
            } else if (SlaverBlue.class.equals(clz)) {
                data.put(clz, new SlaverBlue(0, 0));
            } else if (SlaverRed.class.equals(clz)) {
                data.put(clz, new SlaverRed(0, 0));
            } else if (TheGuardian.class.equals(clz)) {
                data.put(clz, new TheGuardian());
            } else if (SlimeBoss.class.equals(clz)) {
                data.put(clz, new SlimeBoss());
            } else if (BanditBear.class.equals(clz)) {
                data.put(clz, new BanditBear(0, 0));
            } else if (BanditLeader.class.equals(clz)) {
                data.put(clz, new BanditLeader(0, 0));
            } else if (BanditPointy.class.equals(clz)) {
                data.put(clz, new BanditPointy(0, 0));
            } else if (BookOfStabbing.class.equals(clz)) {
                data.put(clz, new BookOfStabbing());
            } else if (BronzeAutomaton.class.equals(clz)) {
                data.put(clz, new BronzeAutomaton());
            } else if (BronzeOrb.class.equals(clz)) {
                data.put(clz, new BronzeOrb(0, 0, 0));
            } else if (Byrd.class.equals(clz)) {
                data.put(clz, new Byrd(0, 0));
            } else if (Centurion.class.equals(clz)) {
                data.put(clz, new Centurion(0, 0));
            } else if (Champ.class.equals(clz)) {
                data.put(clz, new Champ());
            } else if (Chosen.class.equals(clz)) {
                data.put(clz, new Chosen(0, 0));
            } else if (GremlinLeader.class.equals(clz)) {
                data.put(clz, new GremlinLeader());
            } else if (Healer.class.equals(clz)) {
                data.put(clz, new Healer(0, 0));
            } else if (Mugger.class.equals(clz)) {
                data.put(clz, new Mugger(0, 0));
            } else if (ShelledParasite.class.equals(clz)) {
                data.put(clz, new ShelledParasite(0, 0));
            } else if (SnakePlant.class.equals(clz)) {
                data.put(clz, new SnakePlant(0, 0));
            } else if (Snecko.class.equals(clz)) {
                data.put(clz, new Snecko(0, 0));
            } else if (SphericGuardian.class.equals(clz)) {
                data.put(clz, new SphericGuardian(0, 0));
            } else if (Taskmaster.class.equals(clz)) {
                data.put(clz, new Taskmaster(0, 0));
            } else if (TheCollector.class.equals(clz)) {
                data.put(clz, new TheCollector());
            } else if (TorchHead.class.equals(clz)) {
                data.put(clz, new TorchHead(0, 0));
            } else if (AwakenedOne.class.equals(clz)) {
                data.put(clz, new AwakenedOne(0, 0));
            } else if (Darkling.class.equals(clz)) {
                data.put(clz, new Darkling(0, 0));
            } else if (Deca.class.equals(clz)) {
                data.put(clz, new Deca());
            } else if (Donu.class.equals(clz)) {
                data.put(clz, new Donu());
            } else if (Exploder.class.equals(clz)) {
                data.put(clz, new Exploder(0, 0));
            } else if (GiantHead.class.equals(clz)) {
                data.put(clz, new GiantHead());
            } else if (Maw.class.equals(clz)) {
                data.put(clz, new Maw(0, 0));
            } else if (Nemesis.class.equals(clz)) {
                data.put(clz, new Nemesis());
            } else if (OrbWalker.class.equals(clz)) {
                data.put(clz, new OrbWalker(0, 0));
            } else if (Reptomancer.class.equals(clz)) {
                data.put(clz, new Reptomancer());
            } else if (Repulsor.class.equals(clz)) {
                data.put(clz, new Repulsor(0, 0));
            } else if (SnakeDagger.class.equals(clz)) {
                data.put(clz, new SnakeDagger(0, 0));
            } else if (Spiker.class.equals(clz)) {
                data.put(clz, new Spiker(0, 0));
            } else if (SpireGrowth.class.equals(clz)) {
                data.put(clz, new SpireGrowth());
            } else if (TimeEater.class.equals(clz)) {
                data.put(clz, new TimeEater());
            } else if (Transient.class.equals(clz)) {
                data.put(clz, new Transient());
            } else if (WrithingMass.class.equals(clz)) {
                data.put(clz, new WrithingMass());
            } else if (SpireShield.class.equals(clz)) {
                data.put(clz, new SpireShield());
            } else if (SpireSpear.class.equals(clz)) {
                data.put(clz, new SpireSpear());
            } else if (Ironclad.class.equals(clz)) {
                data.put(Ironclad.class, new SpineAnimationEntity("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0F, 220.0F, 290.0F));
            } else if (TheSilent.class.equals(clz)) {
                data.put(TheSilent.class, new SpineAnimationEntity("images/characters/theSilent/idle/skeleton.atlas", "images/characters/theSilent/idle/skeleton.json", 1.0F, 240.0F, 240.0F));
            } else if (Defect.class.equals(clz)) {
                data.put(Defect.class, new SpineAnimationEntity("images/characters/defect/idle/skeleton.atlas", "images/characters/defect/idle/skeleton.json", 1.0F, 240.0F, 244.0F));
            } else if (Merchant.class.equals(clz)) {
                data.put(Merchant.class, new SpineAnimationEntity("images/npcs/merchant/skeleton.atlas", "images/npcs/merchant/skeleton.json", 1.0F, 240.0F, 244.0F));
            }
        }
        return new SpineAnimationEntity(data.get(clz));
    }
}
