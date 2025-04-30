package examplemod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ExhaustSelectedCardsAction extends AbstractGameAction {
    private AbstractPlayer p;
    private final int maxCards;
    private final boolean gainEnergyOnExhaust;
    private boolean openedSelection = false;

    /**
     * @param maxCards 要选择最多几张卡
     * @param gainEnergyOnExhaust 是否每消耗一张卡恢复1点能量
     */
    public ExhaustSelectedCardsAction(int maxCards, boolean gainEnergyOnExhaust) {
        this.p = AbstractDungeon.player;
        this.maxCards = maxCards;

        this.gainEnergyOnExhaust = gainEnergyOnExhaust;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (!openedSelection) {
            // 只在第一次调用时打开选牌界面
            ArrayList<AbstractCard> validCards = new ArrayList<>();
            for (AbstractCard c : p.hand.group) {
                    validCards.add(c);
            }

            if (validCards.isEmpty()) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(
                    "选择要消耗的牌",
                    Math.min(maxCards, validCards.size()),
                    true, true, false, false
            );
            openedSelection = true;
            return;
        }

        // 等待玩家选完
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int exhaustedCount = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToExhaustPile(c);
                exhaustedCount++;
            }
            if (gainEnergyOnExhaust && exhaustedCount > 0) {
                addToTop(new GainEnergyAction(exhaustedCount));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
    }
}
