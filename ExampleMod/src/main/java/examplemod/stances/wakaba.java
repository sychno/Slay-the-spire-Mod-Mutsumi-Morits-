package examplemod.stances;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import examplemod.Characters.MyCharacter;
import examplemod.power.RotationPlanPower;

// WakabaStance: 若叶睦姿态
public class wakaba extends AbstractStance {
    public static final String STANCE_ID = "WakabaStance";
    private static final int BLOCK_AMOUNT = 5;
    private static final int DRAWCARD_AMOUNT = 1;
    public wakaba() {
        this.ID = STANCE_ID;
        this.name = "Wakaba Form";
        this.updateDescription();
    }
    @Override
    public void updateDescription() {
        this.description = "睦头人登场！获得5点格挡。抽一张牌";
    }

    @Override
    public void onEnterStance() {
        AbstractPlayer player = AbstractDungeon.player;

        if (player instanceof MyCharacter) {
            MyCharacter myChar = (MyCharacter) player;
            //替换角色立绘
            myChar.img = ImageMaster.loadImage("ExampleResources/img/char/wakaba.png");

        }
        if(player.hasPower(RotationPlanPower.Rotation_ID)){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMOUNT)
        );
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,DRAWCARD_AMOUNT));
    }
}

