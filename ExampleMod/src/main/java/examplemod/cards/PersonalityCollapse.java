package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.helpers.Modhelper;
import examplemod.power.PersonalityCollapsePower; // 等下也会帮你写Power
import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class PersonalityCollapse extends CustomCard {
    public static final String ID = Modhelper.makePath("PersonalityCollapse");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/PersonalityCollapse.png";
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PersonalityCollapse() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PersonalityCollapsePower(p, 1)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0); // 升级后费用0
            initializeDescription();
        }
    }
}
