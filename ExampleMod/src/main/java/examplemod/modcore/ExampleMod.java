package examplemod.modcore;

import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;


import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.megacrit.cardcrawl.localization.RelicStrings;
import examplemod.cards.*;
import examplemod.Characters.MyCharacter;
import examplemod.relics.MyRelic;

import static examplemod.Characters.MyCharacter.PlayerColorEnum.MY_CHARACTER;

@SpireInitializer
public class ExampleMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {
    private static final String MY_CHARACTER_BUTTON = "ExampleResources/img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "ExampleResources/img/char/Character_Portrait.png";
    private static final String BG_ATTACK_512 = "ExampleResources/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "ExampleResources/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "ExampleResources/img/512/bg_skill_512.png";
    private static final String SMALL_ORB = "ExampleResources/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "ExampleResources/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "ExampleResources/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "ExampleResources/img/1024/bg_skill.png";
    private static final String BIG_ORB = "ExampleResources/img/char/card_orb.png";
    private static final String ENERGY_ORB = "ExampleResources/img/char/cost_orb.png";
    
    public static final Color MY_COLOR = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);

    public ExampleMod() {
        BaseMod.subscribe(this);

        // 这里的EXAMPLE_GREEN是人物类里的，应写成MyCharacter.PlayerColorEnum.EXAMPLE_GREEN
        BaseMod.addColor(MyCharacter.PlayerColorEnum.EXAMPLE_GREEN, ExampleMod.MY_COLOR, MY_COLOR, MY_COLOR,
            MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512,
            BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024,
            BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB
        );
    }

    public static void initialize() {
        new ExampleMod();
    }

    @Override
    public void receiveEditCards() {

        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Personal_Change());
        BaseMod.addCard(new UnityForm());
        BaseMod.addCard(new PersonalDestory());
        BaseMod.addCard(new Morits_Attack());
        BaseMod.addCard(new ATField());
        BaseMod.addCard(new Mutsumi_Defend());
        BaseMod.addCard(new Powerup());
        BaseMod.addCard(new wagabapeace());
        BaseMod.addCard(new ThornsBurst());
        BaseMod.addCard(new LeafShield());
        BaseMod.addCard(new healing());
        BaseMod.addCard(new SpiritPulse());
        BaseMod.addCard(new Special_Attack());
        BaseMod.addCard(new Exchange());
        BaseMod.addCard(new Personal_Attack());
        BaseMod.addCard(new Echo());
        BaseMod.addCard(new Personal_Produce());
        BaseMod.addCard(new Impulse_Night());
        BaseMod.addCard(new Awaken_Mutsumi());
        BaseMod.addCard(new Personal_Burning());
        BaseMod.addCard(new Personal_Kill());
        BaseMod.addCard(new Live_Power());
        BaseMod.addCard(new PersonalityCollapse());
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new MyRelic(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "ExampleResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "ExampleResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "ExampleResources/localization/" + lang + "/relics.json");
    }

}