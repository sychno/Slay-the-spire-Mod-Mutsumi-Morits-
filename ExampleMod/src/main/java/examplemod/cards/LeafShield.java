package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.helpers.Modhelper;

public class LeafShield extends CustomCard {
    public static final String ID = Modhelper.makePath("LeafShield");
    public static final String IMG = "ExampleResources/img/cards/LeafShield.png";
    private static final int COST = 1;
    private static final int BLOCK = 7;

    public LeafShield() {
        super(ID, "Leaf Shield", IMG, COST, "获得 !B! 点格挡。",
                CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

}
