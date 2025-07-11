package eatyourbeets.effects;

import basemod.BaseMod;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import patches.MainMusicPatches;

public class SFX
{
    public static void Initialize()
    {
        BaseMod.addAudio(ATTACK_REAPER, "audio/sound/STS_SFX_Reaper_v1.ogg");
        BaseMod.addAudio(ATTACK_AXE, "audio/sound/STS_SFX_EnemyAtk_Axe_v1.ogg");
        BaseMod.addAudio(ATTACK_BUTCHER, "audio/sound/STS_SFX_EnemyAtk_Butcher_v1.ogg");
        BaseMod.addAudio(ATTACK_DAGGER, "audio/sound/STS_SFX_EnemyAtk_Dagger_v1.ogg");
        BaseMod.addAudio(ATTACK_KNIFE, "audio/sound/STS_SFX_EnemyAtk_Knife_v1.ogg");
        BaseMod.addAudio(ATTACK_SCYTHE, "audio/sound/STS_SFX_EnemyAtk_Scythe_v1.ogg");
        BaseMod.addAudio(ATTACK_SCIMITAR, "audio/sound/STS_SFX_EnemyAtk_Scimitar_v1.ogg");
        BaseMod.addAudio(ATTACK_SWORD, "audio/sound/STS_SFX_EnemyAtk_Sword_v1.ogg");
        BaseMod.addAudio(RELIC_ACTIVATION, "audio/sound/SOTE_SFX_RelicActivation_v1.ogg");

        BaseMod.addAudio(ANIMATOR_ARROW, "audio/animator/sound/FIRING_ARROW.ogg");
        BaseMod.addAudio(ANIMATOR_GUNSHOT, "audio/animator/sound/FIRING_BULLET.ogg");
        BaseMod.addAudio(ANIMATOR_SPEAR_1, "audio/animator/sound/SPEAR_1.ogg");
        BaseMod.addAudio(ANIMATOR_SPEAR_2, "audio/animator/sound/SPEAR_2.ogg");
        BaseMod.addAudio(ANIMATOR_PUNCH, "audio/animator/sound/PUNCH.ogg");
        BaseMod.addAudio(ANIMATOR_KIRA_POWER, "audio/animator/sound/KIRA_POWER.ogg");
        BaseMod.addAudio(ANIMATOR_MEGUMIN_CHARGE, "audio/animator/sound/MEGUMIN_CHARGE.ogg");
        BaseMod.addAudio(ANIMATOR_ORB_EARTH_CHANNEL, "audio/animator/sound/ORB_EARTH_CHANNEL.ogg");
        BaseMod.addAudio(ANIMATOR_ORB_EARTH_EVOKE, "audio/animator/sound/ORB_EARTH_EVOKE.ogg");
        BaseMod.addAudio(ANIMATOR_ORB_WATER_EVOKE, "audio/animator/sound/ORB_WATER_EVOKE.ogg");
        BaseMod.addAudio(ANIMATOR_ORB_WATER_CHANNEL, "audio/animator/sound/ORB_WATER_CHANNEL.ogg");
        BaseMod.addAudio(ANIMATOR_THE_ULTIMATE_CRYSTAL, "audio/animator/sound/THE_ULTIMATE_CRYSTAL.ogg");

        MainMusicPatches.SetFolderPath("audio/animator/music/");
        MainMusicPatches.AddMusic(ANIMATOR_THE_CREATURE);
        MainMusicPatches.AddMusic(ANIMATOR_THE_HAUNT);
        //BaseMod.addAudio(Audio_TheUnnamed, "audio/music/ANIMATOR_THE_UNNAMED.ogg");
    }

    public static String GetVoiceString(String cardName) {
        return "VOICE_"+cardName;
    }
    public static final String ANIMATOR_ARROW = "ANIMATOR_ARROW";
    public static final String ANIMATOR_GUNSHOT = "ANIMATOR_GUNSHOT";
    public static final String ANIMATOR_SPEAR_1 = "ANIMATOR_SPEAR_1";
    public static final String ANIMATOR_SPEAR_2 = "ANIMATOR_SPEAR_2";
    public static final String ANIMATOR_PUNCH = "ANIMATOR_PUNCH";
    public static final String ANIMATOR_KIRA_POWER = "ANIMATOR_KIRA_POWER";
    public static final String ANIMATOR_MEGUMIN_CHARGE = "ANIMATOR_MEGUMIN_CHARGE";
    public static final String ANIMATOR_ORB_EARTH_CHANNEL = "ANIMATOR_ORB_EARTH_CHANNEL";
    public static final String ANIMATOR_ORB_EARTH_EVOKE = "ANIMATOR_ORB_EARTH_EVOKE";
    public static final String ANIMATOR_ORB_WATER_CHANNEL = "ANIMATOR_ORB_WATER_CHANNEL";
    public static final String ANIMATOR_ORB_WATER_EVOKE = "ANIMATOR_ORB_WATER_EVOKE";
    public static final String ANIMATOR_THE_ULTIMATE_CRYSTAL = "ANIMATOR_THE_ULTIMATE_CRYSTAL";
    public static final String ANIMATOR_THE_CREATURE = "THE_CREATURE.ogg";
    public static final String ANIMATOR_THE_HAUNT = "THE_HAUNT.ogg";

    public static final String AMBIANCE_BEYOND = "AMBIANCE_BEYOND";
    public static final String AMBIANCE_BOTTOM = "AMBIANCE_BOTTOM";
    public static final String AMBIANCE_CITY = "AMBIANCE_CITY";
    public static final String APPEAR = "APPEAR";
    public static final String ATTACK_AXE = "ATTACK_AXE";
    public static final String ATTACK_BOWLING = "ATTACK_BOWLING";
    public static final String ATTACK_BUTCHER = "ATTACK_BUTCHER";
    public static final String ATTACK_DAGGER = "ATTACK_DAGGER";
    public static final String ATTACK_DAGGER_1 = "ATTACK_DAGGER_1";
    public static final String ATTACK_DAGGER_2 = "ATTACK_DAGGER_2";
    public static final String ATTACK_DAGGER_3 = "ATTACK_DAGGER_3";
    public static final String ATTACK_DAGGER_4 = "ATTACK_DAGGER_4";
    public static final String ATTACK_DAGGER_5 = "ATTACK_DAGGER_5";
    public static final String ATTACK_DAGGER_6 = "ATTACK_DAGGER_6";
    public static final String ATTACK_DEFECT_BEAM = "ATTACK_DEFECT_BEAM";
    public static final String ATTACK_FAST = "ATTACK_FAST";
    public static final String ATTACK_FIRE = "ATTACK_FIRE";
    public static final String ATTACK_FLAME_BARRIER = "ATTACK_FLAME_BARRIER";
    public static final String ATTACK_KNIFE = "ATTACK_KNIFE";
    public static final String ATTACK_HEAVY = "ATTACK_HEAVY";
    public static final String ATTACK_IRON_1 = "ATTACK_IRON_1";
    public static final String ATTACK_IRON_2 = "ATTACK_IRON_2";
    public static final String ATTACK_IRON_3 = "ATTACK_IRON_3";
    public static final String ATTACK_MAGIC_BEAM = "ATTACK_MAGIC_BEAM";
    public static final String ATTACK_MAGIC_BEAM_SHORT = "ATTACK_MAGIC_BEAM_SHORT";
    public static final String ATTACK_MAGIC_FAST_1 = "ATTACK_MAGIC_FAST_1";
    public static final String ATTACK_MAGIC_FAST_2 = "ATTACK_MAGIC_FAST_2";
    public static final String ATTACK_MAGIC_FAST_3 = "ATTACK_MAGIC_FAST_3";
    public static final String ATTACK_MAGIC_SLOW_1 = "ATTACK_MAGIC_SLOW_1";
    public static final String ATTACK_MAGIC_SLOW_2 = "ATTACK_MAGIC_SLOW_2";
    public static final String ATTACK_PIERCING_WAIL = "ATTACK_PIERCING_WAIL";
    public static final String ATTACK_POISON = "ATTACK_POISON";
    public static final String ATTACK_POISON2 = "ATTACK_POISON2";
    public static final String ATTACK_REAPER = "ATTACK_REAPER";
    public static final String ATTACK_SCIMITAR = "ATTACK_SCIMITAR";
    public static final String ATTACK_SCYTHE = "ATTACK_SCYTHE";
    public static final String ATTACK_SWORD = "ATTACK_SWORD";
    public static final String ATTACK_WHIFF_1 = "ATTACK_WHIFF_1";
    public static final String ATTACK_WHIFF_2 = "ATTACK_WHIFF_2";
    public static final String ATTACK_WHIRLWIND = "ATTACK_WHIRLWIND";
    public static final String AUTOMATON_ORB_SPAWN = "AUTOMATON_ORB_SPAWN";
    public static final String BATTLE_START_1 = "BATTLE_START_1";
    public static final String BATTLE_START_2 = "BATTLE_START_2";
    public static final String BATTLE_START_BOSS = "BATTLE_START_BOSS";
    public static final String BELL = "BELL";
    public static final String BLOCK_ATTACK = "BLOCK_ATTACK";
    public static final String BLOCK_BREAK = "BLOCK_BREAK";
    public static final String BLOCK_GAIN_1 = "BLOCK_GAIN_1";
    public static final String BLOCK_GAIN_2 = "BLOCK_GAIN_2";
    public static final String BLOCK_GAIN_3 = "BLOCK_GAIN_3";
    public static final String BLOOD_SPLAT = "BLOOD_SPLAT";
    public static final String BLOOD_SWISH = "BLOOD_SWISH";
    public static final String BLUNT_FAST = "BLUNT_FAST";
    public static final String BLUNT_HEAVY = "BLUNT_HEAVY";
    public static final String BOSS_VICTORY_STINGER = "BOSS_VICTORY_STINGER";
    public static final String BUFF_1 = "BUFF_1";
    public static final String BUFF_2 = "BUFF_2";
    public static final String BUFF_3 = "BUFF_3";
    public static final String BYRD_DEATH = "BYRD_DEATH";
    public static final String CARD_BURN = "CARD_BURN";
    public static final String CARD_DRAW_8 = "CARD_DRAW_8";
    public static final String CARD_EXHAUST = "CARD_EXHAUST";
    public static final String CARD_OBTAIN = "CARD_OBTAIN";
    public static final String CARD_POWER_IMPACT = "CARD_POWER_IMPACT";
    public static final String CARD_POWER_WOOSH = "CARD_POWER_WOOSH";
    public static final String CARD_REJECT = "CARD_REJECT";
    public static final String CARD_SELECT = "CARD_SELECT";
    public static final String CARD_UPGRADE = "CARD_UPGRADE";
    public static final String CEILING_BOOM_1 = "CEILING_BOOM_1";
    public static final String CEILING_BOOM_2 = "CEILING_BOOM_2";
    public static final String CEILING_BOOM_3 = "CEILING_BOOM_3";
    public static final String CEILING_DUST_1 = "CEILING_DUST_1";
    public static final String CEILING_DUST_2 = "CEILING_DUST_2";
    public static final String CEILING_DUST_3 = "CEILING_DUST_3";
    public static final String CHEST_OPEN = "CHEST_OPEN";
    public static final String CHOSEN_DEATH = "CHOSEN_DEATH";
    public static final String DAMARU = "DAMARU";
    public static final String DARKLING_REGROW_1 = "DARKLING_REGROW_1";
    public static final String DARKLING_REGROW_2 = "DARKLING_REGROW_2";
    public static final String DEATH_STINGER = "DEATH_STINGER";
    public static final String DEBUFF_1 = "DEBUFF_1";
    public static final String DEBUFF_2 = "DEBUFF_2";
    public static final String DEBUFF_3 = "DEBUFF_3";
    public static final String DECK_CLOSE = "DECK_CLOSE";
    public static final String DECK_OPEN = "DECK_OPEN";
    public static final String DUNGEON_TRANSITION = "DUNGEON_TRANSITION";
    public static final String END_TURN = "END_TURN";
    public static final String ENEMY_TURN = "ENEMY_TURN";
    public static final String EVENT_ANCIENT = "EVENT_ANCIENT";
    public static final String EVENT_FALLING = "EVENT_FALLING";
    public static final String EVENT_FORGE = "EVENT_FORGE";
    public static final String EVENT_FORGOTTEN = "EVENT_FORGOTTEN";
    public static final String EVENT_FOUNTAIN = "EVENT_FOUNTAIN";
    public static final String EVENT_GHOSTS = "EVENT_GHOSTS";
    public static final String EVENT_GOLDEN = "EVENT_GOLDEN";
    public static final String EVENT_GOOP = "EVENT_GOOP";
    public static final String EVENT_LAB = "EVENT_LAB";
    public static final String EVENT_LIVING_WALL = "EVENT_LIVING_WALL";
    public static final String EVENT_NLOTH = "EVENT_NLOTH";
    public static final String EVENT_OOZE = "EVENT_OOZE";
    public static final String EVENT_PORTAL = "EVENT_PORTAL";
    public static final String EVENT_PURCHASE = "EVENT_PURCHASE";
    public static final String EVENT_SENSORY = "EVENT_SENSORY";
    public static final String EVENT_SERPENT = "EVENT_SERPENT";
    public static final String EVENT_SHINING = "EVENT_SHINING";
    public static final String EVENT_SKULL = "EVENT_SKULL";
    public static final String EVENT_SPIRITS = "EVENT_SPIRITS";
    public static final String EVENT_TOME = "EVENT_TOME";
    public static final String EVENT_VAMP_BITE = "EVENT_VAMP_BITE";
    public static final String EVENT_WINDING = "EVENT_WINDING";
    public static final String GHOST_FLAMES = "GHOST_FLAMES";
    public static final String GHOST_ORB_IGNITE_1 = "GHOST_ORB_IGNITE_1";
    public static final String GHOST_ORB_IGNITE_2 = "GHOST_ORB_IGNITE_2";
    public static final String GOLD_GAIN = "GOLD_GAIN";
    public static final String GOLD_GAIN_2 = "GOLD_GAIN_2";
    public static final String GOLD_GAIN_3 = "GOLD_GAIN_3";
    public static final String GOLD_GAIN_4 = "GOLD_GAIN_4";
    public static final String GOLD_GAIN_5 = "GOLD_GAIN_5";
    public static final String GOLD_JINGLE = "GOLD_JINGLE";
    public static final String GUARDIAN_ROLL_UP = "GUARDIAN_ROLL_UP";
    public static final String HEAL_1 = "HEAL_1";
    public static final String HEAL_2 = "HEAL_2";
    public static final String HEAL_3 = "HEAL_3";
    public static final String HEART_BEAT = "HEART_BEAT";
    public static final String HEART_SIMPLE = "HEART_SIMPLE";
    public static final String HOVER_CHARACTER = "HOVER_CHARACTER";
    public static final String INTIMIDATE = "INTIMIDATE";
    public static final String JAW_WORM_DEATH = "JAW_WORM_DEATH";
    public static final String KEY_OBTAIN = "KEY_OBTAIN";
    public static final String MAP_CLOSE = "MAP_CLOSE";
    public static final String MAP_HOVER_1 = "MAP_HOVER_1";
    public static final String MAP_HOVER_2 = "MAP_HOVER_2";
    public static final String MAP_HOVER_3 = "MAP_HOVER_3";
    public static final String MAP_HOVER_4 = "MAP_HOVER_4";
    public static final String MAP_OPEN = "MAP_OPEN";
    public static final String MAP_OPEN_2 = "MAP_OPEN_2";
    public static final String MAP_SELECT_1 = "MAP_SELECT_1";
    public static final String MAP_SELECT_2 = "MAP_SELECT_2";
    public static final String MAP_SELECT_3 = "MAP_SELECT_3";
    public static final String MAP_SELECT_4 = "MAP_SELECT_4";
    public static final String MAW_DEATH = "MAW_DEATH";
    public static final String MONSTER_AUTOMATON_SUMMON = "MONSTER_AUTOMATON_SUMMON";
    public static final String MONSTER_AWAKENED_ATTACK = "MONSTER_AWAKENED_ATTACK";
    public static final String MONSTER_AWAKENED_POUNCE = "MONSTER_AWAKENED_POUNCE";
    public static final String MONSTER_BOOK_STAB_0 = "MONSTER_BOOK_STAB_0";
    public static final String MONSTER_BOOK_STAB_1 = "MONSTER_BOOK_STAB_1";
    public static final String MONSTER_BOOK_STAB_2 = "MONSTER_BOOK_STAB_2";
    public static final String MONSTER_BOOK_STAB_3 = "MONSTER_BOOK_STAB_3";
    public static final String MONSTER_BYRD_ATTACK_0 = "MONSTER_BYRD_ATTACK_0";
    public static final String MONSTER_BYRD_ATTACK_1 = "MONSTER_BYRD_ATTACK_1";
    public static final String MONSTER_BYRD_ATTACK_2 = "MONSTER_BYRD_ATTACK_2";
    public static final String MONSTER_BYRD_ATTACK_3 = "MONSTER_BYRD_ATTACK_3";
    public static final String MONSTER_BYRD_ATTACK_4 = "MONSTER_BYRD_ATTACK_4";
    public static final String MONSTER_BYRD_ATTACK_5 = "MONSTER_BYRD_ATTACK_5";
    public static final String MONSTER_CHAMP_CHARGE = "MONSTER_CHAMP_CHARGE";
    public static final String MONSTER_CHAMP_SLAP = "MONSTER_CHAMP_SLAP";
    public static final String MONSTER_COLLECTOR_DEBUFF = "MONSTER_COLLECTOR_DEBUFF";
    public static final String MONSTER_COLLECTOR_SUMMON = "MONSTER_COLLECTOR_SUMMON";
    public static final String MONSTER_DONU_DEFENSE = "MONSTER_DONU_DEFENSE";
    public static final String MONSTER_GUARDIAN_DESTROY = "MONSTER_GUARDIAN_DESTROY";
    public static final String MONSTER_JAW_WORM_BELLOW = "MONSTER_JAW_WORM_BELLOW";
    public static final String MONSTER_SLIME_ATTACK = "MONSTER_SLIME_ATTACK";
    public static final String MONSTER_SNECKO_GLARE = "MONSTER_SNECKO_GLARE";
    public static final String NECRONOMICON = "NECRONOMICON";
    public static final String NULLIFY_SFX = "NULLIFY_SFX";
    public static final String ORB_DARK_CHANNEL = "ORB_DARK_CHANNEL";
    public static final String ORB_DARK_EVOKE = "ORB_DARK_EVOKE";
    public static final String ORB_FROST_CHANNEL = "ORB_FROST_CHANNEL";
    public static final String ORB_FROST_DEFEND_1 = "ORB_FROST_DEFEND_1";
    public static final String ORB_FROST_DEFEND_2 = "ORB_FROST_DEFEND_2";
    public static final String ORB_FROST_DEFEND_3 = "ORB_FROST_DEFEND_3";
    public static final String ORB_FROST_EVOKE = "ORB_FROST_EVOKE";
    public static final String ORB_LIGHTNING_CHANNEL = "ORB_LIGHTNING_CHANNEL";
    public static final String ORB_LIGHTNING_EVOKE = "ORB_LIGHTNING_EVOKE";
    public static final String ORB_LIGHTNING_PASSIVE = "ORB_LIGHTNING_PASSIVE";
    public static final String ORB_PLASMA_CHANNEL = "ORB_PLASMA_CHANNEL";
    public static final String ORB_PLASMA_EVOKE = "ORB_PLASMA_EVOKE";
    public static final String ORB_SLOT_GAIN = "ORB_SLOT_GAIN";
    public static final String POTION_1 = "POTION_1";
    public static final String POTION_2 = "POTION_2";
    public static final String POTION_3 = "POTION_3";
    public static final String POTION_DROP_1 = "POTION_DROP_1";
    public static final String POTION_DROP_2 = "POTION_DROP_2";
    public static final String POWER_CONFUSION = "POWER_CONFUSION";
    public static final String POWER_CONSTRICTED = "POWER_CONSTRICTED";
    public static final String POWER_DEXTERITY = "POWER_DEXTERITY";
    public static final String POWER_ENTANGLED = "POWER_ENTANGLED";
    public static final String POWER_FLIGHT = "POWER_FLIGHT";
    public static final String POWER_FOCUS = "POWER_FOCUS";
    public static final String POWER_INTANGIBLE = "POWER_INTANGIBLE";
    public static final String POWER_MANTRA = "POWER_MANTRA";
    public static final String POWER_METALLICIZE = "POWER_METALLICIZE";
    public static final String POWER_PLATED = "POWER_PLATED";
    public static final String POWER_POISON = "POWER_POISON";
    public static final String POWER_SHACKLE = "POWER_SHACKLE";
    public static final String POWER_STRENGTH = "POWER_STRENGTH";
    public static final String POWER_TIME_WARP = "POWER_TIME_WARP";
    public static final String RAGE = "RAGE";
    public static final String RELIC_DROP_CLINK = "RELIC_DROP_CLINK";
    public static final String RELIC_DROP_FLAT = "RELIC_DROP_FLAT";
    public static final String RELIC_DROP_HEAVY = "RELIC_DROP_HEAVY";
    public static final String RELIC_DROP_MAGICAL = "RELIC_DROP_MAGICAL";
    public static final String RELIC_DROP_ROCKY = "RELIC_DROP_ROCKY";
    public static final String RELIC_ACTIVATION = "RELIC_ACTIVATION";
    public static final String REST_FIRE_DRY = "REST_FIRE_DRY";
    public static final String REST_FIRE_WET = "REST_FIRE_WET";
    public static final String SCENE_TORCH_EXTINGUISH = "SCENE_TORCH_EXTINGUISH";
    public static final String SELECT_WATCHER = "SELECT_WATCHER";
    public static final String SHOP_CLOSE = "SHOP_CLOSE";
    public static final String SHOP_OPEN = "SHOP_OPEN";
    public static final String SHOP_PURCHASE = "SHOP_PURCHASE";
    public static final String SHOVEL = "SHOVEL";
    public static final String SINGING_BOWL = "SINGING_BOWL";
    public static final String SLEEP_1_1 = "SLEEP_1-1";
    public static final String SLEEP_1_2 = "SLEEP_1-2";
    public static final String SLEEP_1_3 = "SLEEP_1-3";
    public static final String SLEEP_2_1 = "SLEEP_2-1";
    public static final String SLEEP_2_2 = "SLEEP_2-2";
    public static final String SLEEP_2_3 = "SLEEP_2-3";
    public static final String SLEEP_3_1 = "SLEEP_3-1";
    public static final String SLEEP_3_2 = "SLEEP_3-2";
    public static final String SLEEP_3_3 = "SLEEP_3-3";
    public static final String SLEEP_BLANKET = "SLEEP_BLANKET";
    public static final String SLIME_ATTACK = "SLIME_ATTACK";
    public static final String SLIME_ATTACK_2 = "SLIME_ATTACK_2";
    public static final String SLIME_BLINK_1 = "SLIME_BLINK_1";
    public static final String SLIME_BLINK_2 = "SLIME_BLINK_2";
    public static final String SLIME_BLINK_3 = "SLIME_BLINK_3";
    public static final String SLIME_BLINK_4 = "SLIME_BLINK_4";
    public static final String SLIME_SPLIT = "SLIME_SPLIT";
    public static final String SNECKO_DEATH = "SNECKO_DEATH";
    public static final String SPHERE_DETECT_VO_1 = "SPHERE_DETECT_VO_1";
    public static final String SPHERE_DETECT_VO_2 = "SPHERE_DETECT_VO_2";
    public static final String SPLASH = "SPLASH";
    public static final String SPORE_CLOUD_RELEASE = "SPORE_CLOUD_RELEASE";
    public static final String STAB_BOOK_DEATH = "STAB_BOOK_DEATH";
    public static final String STANCE_ENTER_CALM = "STANCE_ENTER_CALM";
    public static final String STANCE_ENTER_DIVINITY = "STANCE_ENTER_DIVINITY";
    public static final String STANCE_ENTER_WRATH = "STANCE_ENTER_WRATH";
    public static final String STANCE_LOOP_CALM = "STANCE_LOOP_CALM";
    public static final String STANCE_LOOP_DIVINITY = "STANCE_LOOP_DIVINITY";
    public static final String STANCE_LOOP_WRATH = "STANCE_LOOP_WRATH";
    public static final String THUNDERCLAP = "THUNDERCLAP";
    public static final String TINGSHA = "TINGSHA";
    public static final String TURN_EFFECT = "TURN_EFFECT";
    public static final String UI_CLICK_1 = "UI_CLICK_1";
    public static final String UI_CLICK_2 = "UI_CLICK_2";
    public static final String UI_HOVER = "UI_HOVER";
    public static final String UNLOCK_PING = "UNLOCK_PING";
    public static final String UNLOCK_SCREEN = "UNLOCK_SCREEN";
    public static final String UNLOCK_WHIR = "UNLOCK_WHIR";
    public static final String VICTORY = "VICTORY";
    public static final String VO_AWAKENEDONE_1 = "VO_AWAKENEDONE_1";
    public static final String VO_AWAKENEDONE_2 = "VO_AWAKENEDONE_2";
    public static final String VO_AWAKENEDONE_3 = "VO_AWAKENEDONE_3";
    public static final String VO_CHAMP_1A = "VO_CHAMP_1A";
    public static final String VO_CHAMP_2A = "VO_CHAMP_2A";
    public static final String VO_CHAMP_3A = "VO_CHAMP_3A";
    public static final String VO_CHAMP_3B = "VO_CHAMP_3B";
    public static final String VO_CULTIST_1A = "VO_CULTIST_1A";
    public static final String VO_CULTIST_1B = "VO_CULTIST_1B";
    public static final String VO_CULTIST_1C = "VO_CULTIST_1C";
    public static final String VO_CULTIST_2A = "VO_CULTIST_2A";
    public static final String VO_CULTIST_2B = "VO_CULTIST_2B";
    public static final String VO_CULTIST_2C = "VO_CULTIST_2C";
    public static final String VO_FLAMEBRUISER_1 = "VO_FLAMEBRUISER_1";
    public static final String VO_FLAMEBRUISER_2 = "VO_FLAMEBRUISER_2";
    public static final String VO_GIANTHEAD_1A = "VO_GIANTHEAD_1A";
    public static final String VO_GIANTHEAD_1B = "VO_GIANTHEAD_1B";
    public static final String VO_GIANTHEAD_1C = "VO_GIANTHEAD_1C";
    public static final String VO_GIANTHEAD_2A = "VO_GIANTHEAD_2A";
    public static final String VO_GIANTHEAD_2B = "VO_GIANTHEAD_2B";
    public static final String VO_GIANTHEAD_2C = "VO_GIANTHEAD_2C";
    public static final String VO_GREMLINANGRY_1A = "VO_GREMLINANGRY_1A";
    public static final String VO_GREMLINANGRY_1B = "VO_GREMLINANGRY_1B";
    public static final String VO_GREMLINANGRY_1C = "VO_GREMLINANGRY_1C";
    public static final String VO_GREMLINANGRY_2A = "VO_GREMLINANGRY_2A";
    public static final String VO_GREMLINANGRY_2B = "VO_GREMLINANGRY_2B";
    public static final String VO_GREMLINCALM_1A = "VO_GREMLINCALM_1A";
    public static final String VO_GREMLINCALM_1B = "VO_GREMLINCALM_1B";
    public static final String VO_GREMLINCALM_2A = "VO_GREMLINCALM_2A";
    public static final String VO_GREMLINCALM_2B = "VO_GREMLINCALM_2B";
    public static final String VO_GREMLINDOPEY_1A = "VO_GREMLINDOPEY_1A";
    public static final String VO_GREMLINDOPEY_1B = "VO_GREMLINDOPEY_1B";
    public static final String VO_GREMLINDOPEY_2A = "VO_GREMLINDOPEY_2A";
    public static final String VO_GREMLINDOPEY_2B = "VO_GREMLINDOPEY_2B";
    public static final String VO_GREMLINDOPEY_2C = "VO_GREMLINDOPEY_2C";
    public static final String VO_GREMLINFAT_1A = "VO_GREMLINFAT_1A";
    public static final String VO_GREMLINFAT_1B = "VO_GREMLINFAT_1B";
    public static final String VO_GREMLINFAT_1C = "VO_GREMLINFAT_1C";
    public static final String VO_GREMLINFAT_2A = "VO_GREMLINFAT_2A";
    public static final String VO_GREMLINFAT_2B = "VO_GREMLINFAT_2B";
    public static final String VO_GREMLINFAT_2C = "VO_GREMLINFAT_2C";
    public static final String VO_GREMLINNOB_1A = "VO_GREMLINNOB_1A";
    public static final String VO_GREMLINNOB_1B = "VO_GREMLINNOB_1B";
    public static final String VO_GREMLINNOB_1C = "VO_GREMLINNOB_1C";
    public static final String VO_GREMLINNOB_2A = "VO_GREMLINNOB_2A";
    public static final String VO_GREMLINSPAZZY_1A = "VO_GREMLINSPAZZY_1A";
    public static final String VO_GREMLINSPAZZY_1B = "VO_GREMLINSPAZZY_1B";
    public static final String VO_GREMLINSPAZZY_2A = "VO_GREMLINSPAZZY_2A";
    public static final String VO_GREMLINSPAZZY_2B = "VO_GREMLINSPAZZY_2B";
    public static final String VO_GREMLINSPAZZY_2C = "VO_GREMLINSPAZZY_2C";
    public static final String VO_HEALER_1A = "VO_HEALER_1A";
    public static final String VO_HEALER_1B = "VO_HEALER_1B";
    public static final String VO_HEALER_2A = "VO_HEALER_2A";
    public static final String VO_HEALER_2B = "VO_HEALER_2B";
    public static final String VO_HEALER_2C = "VO_HEALER_2C";
    public static final String VO_IRONCLAD_1A = "VO_IRONCLAD_1A";
    public static final String VO_IRONCLAD_1B = "VO_IRONCLAD_1B";
    public static final String VO_IRONCLAD_1C = "VO_IRONCLAD_1C";
    public static final String VO_IRONCLAD_2A = "VO_IRONCLAD_2A";
    public static final String VO_IRONCLAD_2B = "VO_IRONCLAD_2B";
    public static final String VO_IRONCLAD_2C = "VO_IRONCLAD_2C";
    public static final String VO_LOOTER_1A = "VO_LOOTER_1A";
    public static final String VO_LOOTER_1B = "VO_LOOTER_1B";
    public static final String VO_LOOTER_1C = "VO_LOOTER_1C";
    public static final String VO_LOOTER_2A = "VO_LOOTER_2A";
    public static final String VO_LOOTER_2B = "VO_LOOTER_2B";
    public static final String VO_LOOTER_2C = "VO_LOOTER_2C";
    public static final String VO_MERCENARY_1A = "VO_MERCENARY_1A";
    public static final String VO_MERCENARY_1B = "VO_MERCENARY_1B";
    public static final String VO_MERCENARY_2A = "VO_MERCENARY_2A";
    public static final String VO_MERCENARY_3A = "VO_MERCENARY_3A";
    public static final String VO_MERCENARY_3B = "VO_MERCENARY_3B";
    public static final String VO_MERCHANT_2A = "VO_MERCHANT_2A";
    public static final String VO_MERCHANT_2B = "VO_MERCHANT_2B";
    public static final String VO_MERCHANT_2C = "VO_MERCHANT_2C";
    public static final String VO_MERCHANT_3A = "VO_MERCHANT_3A";
    public static final String VO_MERCHANT_3B = "VO_MERCHANT_3B";
    public static final String VO_MERCHANT_3C = "VO_MERCHANT_3C";
    public static final String VO_MERCHANT_KA = "VO_MERCHANT_KA";
    public static final String VO_MERCHANT_KB = "VO_MERCHANT_KB";
    public static final String VO_MERCHANT_KC = "VO_MERCHANT_KC";
    public static final String VO_MERCHANT_MA = "VO_MERCHANT_MA";
    public static final String VO_MERCHANT_MB = "VO_MERCHANT_MB";
    public static final String VO_MERCHANT_MC = "VO_MERCHANT_MC";
    public static final String VO_MUGGER_1A = "VO_MUGGER_1A";
    public static final String VO_MUGGER_1B = "VO_MUGGER_1B";
    public static final String VO_MUGGER_2A = "VO_MUGGER_2A";
    public static final String VO_MUGGER_2B = "VO_MUGGER_2B";
    public static final String VO_NEMESIS_1A = "VO_NEMESIS_1A";
    public static final String VO_NEMESIS_1B = "VO_NEMESIS_1B";
    public static final String VO_NEMESIS_1C = "VO_NEMESIS_1C";
    public static final String VO_NEMESIS_2A = "VO_NEMESIS_2A";
    public static final String VO_NEMESIS_2B = "VO_NEMESIS_2B";
    public static final String VO_NEOW_1A = "VO_NEOW_1A";
    public static final String VO_NEOW_1B = "VO_NEOW_1B";
    public static final String VO_NEOW_2A = "VO_NEOW_2A";
    public static final String VO_NEOW_2B = "VO_NEOW_2B";
    public static final String VO_NEOW_3A = "VO_NEOW_3A";
    public static final String VO_NEOW_3B = "VO_NEOW_3B";
    public static final String VO_SILENT_1A = "VO_SILENT_1A";
    public static final String VO_SILENT_1B = "VO_SILENT_1B";
    public static final String VO_SILENT_2A = "VO_SILENT_2A";
    public static final String VO_SILENT_2B = "VO_SILENT_2B";
    public static final String VO_SLAVERBLUE_1A = "VO_SLAVERBLUE_1A";
    public static final String VO_SLAVERBLUE_1B = "VO_SLAVERBLUE_1B";
    public static final String VO_SLAVERBLUE_2A = "VO_SLAVERBLUE_2A";
    public static final String VO_SLAVERBLUE_2B = "VO_SLAVERBLUE_2B";
    public static final String VO_SLAVERLEADER_1A = "VO_SLAVERLEADER_1A";
    public static final String VO_SLAVERLEADER_1B = "VO_SLAVERLEADER_1B";
    public static final String VO_SLAVERLEADER_2A = "VO_SLAVERLEADER_2A";
    public static final String VO_SLAVERLEADER_2B = "VO_SLAVERLEADER_2B";
    public static final String VO_SLAVERRED_1A = "VO_SLAVERRED_1A";
    public static final String VO_SLAVERRED_1B = "VO_SLAVERRED_1B";
    public static final String VO_SLAVERRED_2A = "VO_SLAVERRED_2A";
    public static final String VO_SLAVERRED_2B = "VO_SLAVERRED_2B";
    public static final String VO_SLIMEBOSS_1A = "VO_SLIMEBOSS_1A";
    public static final String VO_SLIMEBOSS_1B = "VO_SLIMEBOSS_1B";
    public static final String VO_SLIMEBOSS_1C = "VO_SLIMEBOSS_1C";
    public static final String VO_SLIMEBOSS_2A = "VO_SLIMEBOSS_2A";
    public static final String VO_TANK_1A = "VO_TANK_1A";
    public static final String VO_TANK_1B = "VO_TANK_1B";
    public static final String VO_TANK_1C = "VO_TANK_1C";
    public static final String WATCHER_HEART_PUNCH = "WATCHER_HEART_PUNCH";
    public static final String WHEEL = "WHEEL";
    public static final String WIND = "WIND";

    public static float Play(String key)
    {
        return Play(key, 1, 1);
    }

    public static float Play(String key, float pitch)
    {
        return Play(key, pitch, pitch, 1);
    }

    public static float Play(String key, float pitchMin, float pitchMax)
    {
        return Play(key, pitchMin, pitchMax, 1);
    }

    public static float Play(String key, float pitchMin, float pitchMax, float volume)
    {
        if (pitchMin > pitchMax)
        {
            throw new RuntimeException("Min can't be greater than max");
        }
        if (key == null || pitchMin <= 0 || volume <= 0)
        {
            return 0f;
        }

        return CardCrawlGame.sound.playAV(key, ((pitchMin == pitchMax) ? pitchMin : MathUtils.random(pitchMin, pitchMax)) - 1, volume) / 1000f;
    }

    public static String GetRandom(String... keys)
    {
        return keys[MathUtils.random(keys.length -1)];
    }

    // INSTANCE:
    public final String key;
    public final float pitchMin;
    public final float pitchMax;
    public final float volume;

    public SFX(String key)
    {
        this(key, 1, 1, 1);
    }

    public SFX(String key, float pitchMin, float pitchMax)
    {
        this(key, pitchMin, pitchMax, 1);
    }

    public SFX(String key, float pitchMin, float pitchMax, float volume)
    {
        this.key = key;
        this.pitchMin = pitchMin;
        this.pitchMax = pitchMax;
        this.volume = volume;
    }

    public void Play()
    {
        Play(key, pitchMin, pitchMax, volume);
    }
}
