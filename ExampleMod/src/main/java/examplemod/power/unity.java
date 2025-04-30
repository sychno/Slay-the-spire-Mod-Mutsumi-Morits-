package examplemod.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import examplemod.helpers.Modhelper;

public class unity extends AbstractPower {
    public static final String UNITY_ID = Modhelper.makePath("unity");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(UNITY_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public unity(AbstractCreature owner){
        this.name = NAME;
        this.ID = UNITY_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;  //非堆叠

        // 添加一大一小两张能力图
        String path128 = "ExampleResources/img/powers/Hurt84.png";
        String path48 = "ExampleResources/img/powers/Hurt32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


}
