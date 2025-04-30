package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.helpers.Modhelper;
import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Personal_Burning extends CustomCard {
    public static final String ID = Modhelper.makePath("Personal_Burning");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Personal_Burning.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Personal_Burning() {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, TYPE, COLOR, RARITY, TARGET); // -1 表示消耗X能量
        this.exhaust = true; // 打出后消耗
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        int cardsToExhaust = this.energyOnUse + (this.upgraded ? 1 : 0);
        if((p.stance.ID.equals("WakabaStance")))
        {
            cardsToExhaust ++;
        }
        if (cardsToExhaust > p.hand.size()) {
            cardsToExhaust = p.hand.size();
        }
        p.energy.use(energyOnUse);
        if (cardsToExhaust > 0) {
            this.addToBot(new examplemod.action.ExhaustSelectedCardsAction(
                    cardsToExhaust,
                    true // 消耗回能量
            ));
        }


    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
