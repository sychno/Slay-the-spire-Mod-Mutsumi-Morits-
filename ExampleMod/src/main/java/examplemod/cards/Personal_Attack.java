package examplemod.cards;
import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import examplemod.helpers.Modhelper;
import examplemod.stances.morits;
import examplemod.stances.wakaba;

public class Personal_Attack extends CustomCard {
    public static final String ID = Modhelper.makePath("Personal_Attack");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Personal_Attack.png";
    private static final int COST = 1;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int BASE_DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int PER_EXHAUSTED_BONUS = 2;
    private static final int UPGRADED_BONUS = 3;

    public Personal_Attack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DAMAGE;
    }


    @Override
    public void applyPowers() {
        int bonus = AbstractDungeon.player.exhaustPile.size() * (this.upgraded ? UPGRADED_BONUS : PER_EXHAUSTED_BONUS);
        this.baseDamage = BASE_DAMAGE + bonus;
        super.applyPowers(); // 让卡牌文本正确显示计算后的最终伤害
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int bonus = AbstractDungeon.player.exhaustPile.size() * (this.upgraded ? UPGRADED_BONUS : PER_EXHAUSTED_BONUS);
        this.baseDamage = BASE_DAMAGE + bonus;
        super.calculateCardDamage(mo); // 同理用于对单体敌人计算
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


}
