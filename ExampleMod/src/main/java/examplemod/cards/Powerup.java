package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import examplemod.helpers.Modhelper;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Powerup extends CustomCard {
    public static final String ID = Modhelper.makePath("Powerup");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Powerup.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    public Powerup() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(Modhelper.makePath("unity")))
        {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber+1), this.magicNumber+1));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber+1), this.magicNumber+1));
        }
        else if ("WakabaStance".equals(p.stance.ID)) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber+1), this.magicNumber+1));
        }
        else if("MoritsStance".equals(p.stance.ID)){
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber+1), this.magicNumber+1));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}