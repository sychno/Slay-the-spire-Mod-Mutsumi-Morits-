package examplemod.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import examplemod.helpers.Modhelper;
public class live extends AbstractPower{
    public static final String LIVE_ID = Modhelper.makePath("live");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(LIVE_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public live(AbstractCreature owner){
        this.name = NAME;
        this.ID = LIVE_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "ExampleResources/img/powers/live84.png";
        String path48 = "ExampleResources/img/powers/live32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    //该状态的效果，当为Morits时，消耗一张牌时，往手牌添加相应的副本(非虚无)
    public void onExhaust(AbstractCard card){
        AbstractPlayer p = (AbstractPlayer) this.owner;
        if (p != null && p.stance != null && "MoritsStance".equals(p.stance.ID)) {
            AbstractCard copy = card.makeStatEquivalentCopy();
            copy.setCostForTurn(card.cost);
            AbstractDungeon.player.hand.addToTop(copy);
        }
    }
}
