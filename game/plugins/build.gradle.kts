import io.github.mikeysasse.gitclone.gitclone

val rootPluginDir = projectDir
val rootPluginBuildDir = buildDir

subprojects {
	group = pluginPackage
}

allprojects {
    val relative = projectDir.relativeTo(rootPluginDir)
    buildDir = rootPluginBuildDir.resolve(relative)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.github.mikeysasse:git-clone-plugin:1.06")
    }
}

apply(plugin = "io.github.mikeysasse.gitclone")

gitclone {
    git {
        url = "https://github.com/OldschoolPKTeam/api"
        path = "/game/plugins/core/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/altar"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/banks"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/wilderness-ditch"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/equipment"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/npc-walk"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/run"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/shops"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/starter"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/prayer"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/restoration"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/login"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/options"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/emotes"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/skill-guides"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/attack-tab"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/price-guide"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/spell-filter"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/logout"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/inventory"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/equipment-stats"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/levelup"
        path = "/game/plugins/content/skills/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/skillcapes"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/banker"
        path = "/game/plugins/content/npcs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/restore-pools"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/magic-altar"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/chest"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/hay"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/levers"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/ladders"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/doors"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/webs"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/cabbage"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/deposit-box"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/crate"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/bookcase"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/sack"
        path = "/game/plugins/content/objs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/combat"
        path = "/game/plugins/content/combat/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/npc-combat"
        path = "/game/plugins/content/combat/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/effects"
        path = "/game/plugins/content/combat/effects/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/magic"
        path = "/game/plugins/content/magic/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/spell-dump"
        path = "/game/plugins/content/magic/spells/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/vengeance"
        path = "/game/plugins/content/magic/spells/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/spellbook-swap"
        path = "/game/plugins/content/magic/spells/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/dungeons"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/toxins"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/player-following"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/skull-removal"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/trading"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/npc-drops"
        path = "/game/plugins/content/mechanics/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/items-kept-on-death"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/bank"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/xpdrops"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/follower"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/worldmap"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/scroll-clickable"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/tournament-supplies"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/quest-tab"
        path = "/game/plugins/content/inter/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/high-risk-zone"
        path = "/game/plugins/content/areas/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/edgeville"
        path = "/game/plugins/content/areas/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/map-locations"
        path = "/game/plugins/content/areas/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/mythical-guild"
        path = "/game/plugins/content/areas/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/wilderness"
        path = "/game/plugins/content/areas/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/home-test-npcs"
        path = "/game/plugins/content/dev/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/woodcutting"
        path = "/game/plugins/content/skills/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/thieving"
        path = "/game/plugins/content/skills/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/slayer"
        path = "/game/plugins/content/skills/slayer/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/slayer-helmet"
        path = "/game/plugins/content/skills/slayer/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/runecrafting"
        path = "/game/plugins/content/skills/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/smithing"
        path = "/game/plugins/content/skills/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/test-bank"
        path = "/game/plugins/content/test/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/presets"
        path = "/game/plugins/content/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/cmd"
        path = "/game/plugins/content/cmd/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/bounty-hunter"
        path = "/game/plugins/content/pvp/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/wyvern-shield"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/mythical-cape"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/amulet-of-glory"
        path = "/game/plugins/content/items/teleports/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/teletabs"
        path = "/game/plugins/content/items/teleports/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/ring-of-dueling"
        path = "/game/plugins/content/items/teleports/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/looting-bag"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/consumables"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/avas-device"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/dragonfire-shield"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/prayer-scrolls"
        path = "/game/plugins/content/items/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/sheep"
        path = "/game/plugins/content/npcs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/rockcrab"
        path = "/game/plugins/content/npcs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/man"
        path = "/game/plugins/content/npcs/"
    }

    git {
        url = "https://github.com/OldschoolPKTeam/kbd"
        path = "/game/plugins/content/npcs/bosses/"
    }
}

