package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.action.ExhaustSelectedCardsAction;
import examplemod.helpers.Modhelper;
import examplemod.power.unity;
import examplemod.stances.morits;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Personal_Kill extends CustomCard {
    public static final String ID = Modhelper.makePath("Personal_Kill");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Personal_Kill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Personal_Kill() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET); // 1点能量消耗
        this.baseMagicNumber = 2; // 初始抽2张牌或给敌人1层虚弱
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 选择并消耗一张卡牌
        this.addToBot(new ExhaustSelectedCardsAction(1, false));
       // if (p.hasPower(Modhelper.makePath("unity")))
        if(p.hasPower(unity.UNITY_ID)) {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber - 1, false), 1));
                }
            }
        else if (p.stance.ID.equals("WakabaStance")) {
                // 若在若叶睦姿态，抽2张牌
                this.addToBot(new DrawCardAction(p, this.magicNumber));
        }
        else if (p.stance.ID.equals("MoritsStance")) {
                // 若在Morits姿态，给所有敌人1层虚弱
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber - 1, false), 1));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.upgradeMagicNumber(1);


        }
    }
}
