package examplemod.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import examplemod.helpers.Modhelper;

public class SpiritPulse extends CustomCard {
    public static final String ID = Modhelper.makePath("SpiritPulse");
    public static final String IMG = "ExampleResources/img/cards/SpiritPulse.png";
    private static final int COST = 2;
    private static final int DAMAGE = 10;

    public SpiritPulse() {
        super(ID, "Spirit Pulse", IMG, COST, "造成 !D! 点伤害。如果目标已虚弱，重复一次。",
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        if (m.hasPower("Weakened")) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

}
