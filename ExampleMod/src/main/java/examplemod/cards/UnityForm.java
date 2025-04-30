package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import examplemod.helpers.Modhelper;
import examplemod.power.unity;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class UnityForm extends CustomCard {
    public static final String ID = Modhelper.makePath("UnityForm");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/UnityForm.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    public UnityForm() {
       // super(ID, "Verdant Growth", IMG, 1, "每回合开始时获得 1 点能量。",
         //       CardType.POWER, EXAMPLE_GREEN, CardRarity.RARE, CardTarget.SELF);

        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new unity(p), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true; // 升级后成为固有卡
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION; // 加载升级后的文本
            initializeDescription(); // 刷新显示文本
        }
    }
}