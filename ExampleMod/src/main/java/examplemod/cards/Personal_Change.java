package examplemod.cards;
import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import examplemod.helpers.Modhelper;
import examplemod.stances.morits;
import examplemod.stances.wakaba;

public class Personal_Change extends CustomCard {
    public static final String ID = Modhelper.makePath("Personal_Change");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Personal_Change.png";
    private static final int COST = 0;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Personal_Change() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
       // this.baseDamage = 6;
    //    this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       // addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
       // AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(morits.STANCE_ID));
        if ("WakabaStance".equals(p.stance.ID)) {
            AbstractDungeon.player.stance = new morits();
            AbstractDungeon.player.stance.onEnterStance();
        }
        else{
            AbstractDungeon.player.stance = new wakaba();
            AbstractDungeon.player.stance.onEnterStance();
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
         //   upgradeDamage(3);
            this.selfRetain = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION; // 加载升级后的文本
            initializeDescription(); // 刷新显示文本
        }
    }


}
