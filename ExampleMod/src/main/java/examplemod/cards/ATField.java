package examplemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import examplemod.helpers.Modhelper;
import examplemod.modcore.IHelper;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ATField extends CustomCard implements IHelper {
    public static final String ID = Modhelper.makePath("ATField");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/ATField.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int LIFE_AMOUNT = 3;
    private static final int UPGRADE_PLUS_DRAW = 2;

    public ATField() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = LIFE_AMOUNT;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // AbstractCard中实现了addToBot方法，它的效果和AbstractDungeon.actionManager.addToBottom相同
        addToBot(new ApplyPowerAction(p , p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
    }

}