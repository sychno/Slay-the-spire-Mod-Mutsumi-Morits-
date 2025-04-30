package examplemod.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import examplemod.helpers.Modhelper;

public class RotationPlanPower extends AbstractPower {
    public static final String Rotation_ID = Modhelper.makePath("RotationPlanPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(Rotation_ID);
    public static final String NAME = powerStrings.NAME;
    public static final PowerType POWER_TYPE = PowerType.BUFF;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int TRIGGERS_PER_TURN = 2;
 //   private int triggerCount = 0;

    public RotationPlanPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = Rotation_ID;
        this.owner = owner;
        //this.amount = amount; // 可堆叠层数
        this.type = POWER_TYPE;
        this.isTurnBased = false;
       // this.loadRegion("energized"); // 可使用已有贴图资源
        //自定义贴图资源
        String path128 = "ExampleResources/img/powers/RotationPlanPower84.png";
        String path48 = "ExampleResources/img/powers/RotationPlanPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }
/*
    @Override
    public void atStartOfTurn() {
        triggerCount = 0; // 每回合重置计数器
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
            flash(); // 动画提示
            addToTop(new GainEnergyAction(1));
            System.out.println("Stance changed from " + oldStance.ID + " to " + newStance.ID);
            System.out.println("Trigger count: " + triggerCount);
    }
*/
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + TRIGGERS_PER_TURN * this.amount + DESCRIPTIONS[1];
    }
}
