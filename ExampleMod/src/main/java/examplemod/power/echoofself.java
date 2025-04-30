package examplemod.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import examplemod.helpers.Modhelper;

public class echoofself extends AbstractPower {
    public static final String ECHO_ID = Modhelper.makePath("echoofself");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ECHO_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int triggeredThisTurn = 0;
    private boolean isInnate;

    public echoofself(AbstractCreature owner, int Amount){
        this.name = NAME;
        this.ID = ECHO_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "ExampleResources/img/powers/echo84.png";
        String path48 = "ExampleResources/img/powers/echo32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        AbstractPlayer p = AbstractDungeon.player;
        if (triggeredThisTurn < this.amount) {
            triggeredThisTurn++;
/*
            AbstractCard copy = card.makeStatEquivalentCopy();
            copy.exhaust = true; // 设置虚无
            copy.isEthereal = true;
            copy.setCostForTurn(card.cost);
            AbstractDungeon.player.hand.addToTop(copy);
 */
            if (p != null && p.stance != null && "WakabaStance".equals(p.stance.ID)) {
                if (card.cost > 0) {
                    addToTop(new GainEnergyAction(card.cost));
                }
            }
        }
    }
    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = 0;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
