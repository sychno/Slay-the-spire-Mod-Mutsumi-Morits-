package examplemod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import examplemod.cards.UnityForm;
import examplemod.helpers.Modhelper;
import examplemod.stances.morits;
import examplemod.stances.wakaba;

// 继承CustomRelic
public class MyRelic extends CustomRelic {
    // 遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = "ExampleMod:MyRelic";
    // 图片路径（大小128x128，可参考同目录的图片）
    private static final String IMG_PATH = "ExampleResources/img/relics/MyRelic.png";
    // 遗物未解锁时的轮廓。可以不使用。如果要使用，取消注释
    // private static final String OUTLINE_PATH = "ExampleModResources/img/relics/MyRelic_Outline.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public MyRelic() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        // 如果你需要轮廓图，取消注释下面一行并注释上面一行，不需要就删除
        // super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



    @Override
    public void atBattleStart() {
        super.atBattleStart();
        AbstractDungeon.player.stance.onExitStance();
        AbstractDungeon.player.stance = new morits();
        AbstractDungeon.player.stance.onEnterStance();
    }
    @Override
    public void onExhaust(AbstractCard card) {
        this.flash(); // 遗物闪光提示
        if ((AbstractDungeon.player.stance != null && "WakabaStance".equals(AbstractDungeon.player.stance.ID)) || AbstractDungeon.player.hasPower(Modhelper.makePath("unity")))
        {addToBot(new DamageRandomEnemyAction(
                new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public AbstractRelic makeCopy() {
        return new MyRelic();
    }
}