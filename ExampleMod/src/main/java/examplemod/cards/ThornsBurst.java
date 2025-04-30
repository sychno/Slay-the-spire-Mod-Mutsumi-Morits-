package examplemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import examplemod.helpers.Modhelper;


import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class ThornsBurst extends CustomCard {
    public static final String ID = Modhelper.makePath("ThornsBurst");
    public static final String IMG = "ExampleResources/img/cards/ThornsBurst.png";
    private static final int COST = 1;
    private static final int DAMAGE = 5;

    public ThornsBurst() {
        super(ID, "Thorns Burst", IMG, COST, "对所有敌人造成 !D! 点伤害。",
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

}
