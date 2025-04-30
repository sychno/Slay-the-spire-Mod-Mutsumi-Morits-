package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.helpers.Modhelper;

import java.util.ArrayList;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class PersonalDestory extends CustomCard {
    public static final String ID = Modhelper.makePath("PersonalDestory");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/PersonalDestory.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_GREEN;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

    public PersonalDestory() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 7;
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        // 获取手牌中除自身外的卡牌
        for (AbstractCard c : p.hand.group) {
            if (c != this) {
                cardsToExhaust.add(c);
            }
        }

        // 每张被消耗的牌都会触发一次伤害
        for (AbstractCard c : cardsToExhaust) {
            addToBot(new ExhaustSpecificCardAction(c, p.hand));
            addToBot(new DamageAllEnemiesAction(
                    p,
                    DamageInfo.createDamageMatrix(damage, true),
                    DamageInfo.DamageType.NORMAL,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
            ));
            }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }
}