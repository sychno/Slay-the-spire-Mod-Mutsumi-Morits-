package examplemod.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import examplemod.helpers.Modhelper;

public class PersonalityCollapsePower extends AbstractPower {
    public static final String POWER_ID = Modhelper.makePath("PersonalityCollapsePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public PersonalityCollapsePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount += amount;


        String path128 = "ExampleResources/img/powers/PersonalityCollapse84.png";
        String path48 = "ExampleResources/img/powers/PersonalityCollapse32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasPower(unity.UNITY_ID)) {
                this.flash();
                this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount)));
                this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, this.amount)));
        }
        else if (p != null && p.stance != null && "WakabaStance".equals(p.stance.ID)){
            this.flash();
            this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, this.amount)));
        }
        else if (p != null && p.stance != null && "MoritsStance".equals(p.stance.ID)){
            this.flash();
            this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount)));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

    }
}
