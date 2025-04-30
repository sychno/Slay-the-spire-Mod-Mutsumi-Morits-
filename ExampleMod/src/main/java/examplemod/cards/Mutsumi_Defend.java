package examplemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import examplemod.helpers.Modhelper;
import examplemod.modcore.IHelper;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Mutsumi_Defend extends CustomCard implements IHelper {
    public static final String ID = Modhelper.makePath("Mutsumi_Defend");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Mutsumi_Defend.png";
    private static final int COST = 2;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Mutsumi_Defend() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 20;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // AbstractCard中实现了addToBot方法，它的效果和AbstractDungeon.actionManager.addToBottom相同
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new MakeTempCardInDrawPileAction(new Dazed(), this.magicNumber , true, true));

    }

}