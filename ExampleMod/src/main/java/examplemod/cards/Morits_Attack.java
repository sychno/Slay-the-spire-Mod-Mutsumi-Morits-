package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.helpers.Modhelper;
import examplemod.stances.morits;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Morits_Attack extends CustomCard {
    public static final String ID = Modhelper.makePath("Morits_Attack");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleResources/img/cards/Morits_Attack.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_GREEN;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final int DAMAGE = 7;
    private static final int ATTACK_TIMES = 3;
    private static final int UPGRADE_PLUS_TIMES = 1;

    public Morits_Attack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = ATTACK_TIMES;
    }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
                        new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                if ((p.stance != null && morits.STANCE_ID.equals(p.stance.ID)) || p.hasPower(Modhelper.makePath("unity"))) {
                    AbstractMonster target = AbstractDungeon.getRandomMonster();
                    if (target != null) {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(target, p, new VulnerablePower(target, 1, false), 1)
                        );
                    }
                }
            }
        }
        @Override
        public void upgrade() {
            if (!this.upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_TIMES);
            }
        }
    }

