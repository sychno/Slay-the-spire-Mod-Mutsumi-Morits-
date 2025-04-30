package examplemod.stances;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import examplemod.Characters.MyCharacter;
import examplemod.power.RotationPlanPower;

// MoritsStance: Morits形态
public class morits extends AbstractStance {
    public static final String STANCE_ID = "MoritsStance";
    private static final int DAMAGE_AMOUNT = 6;

    public morits() {
        this.ID = STANCE_ID;
        this.name = "Morits Form";
        this.updateDescription();
    }
    @Override
    public void updateDescription() {
        this.description = "Morits登场！对随机敌人造成6点伤害";
    }


    @Override
    public void onEnterStance() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player instanceof MyCharacter) {
            MyCharacter myChar = (MyCharacter) player;
         //替换角色立绘
            myChar.img = ImageMaster.loadImage("ExampleResources/img/char/morits.png");
        }
        if(player.hasPower(RotationPlanPower.Rotation_ID)){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageRandomEnemyAction(
                        new DamageInfo(AbstractDungeon.player, DAMAGE_AMOUNT, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                )
        );

    }

}
