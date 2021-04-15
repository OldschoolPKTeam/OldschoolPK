package gg.rsmod.game

import gg.rsmod.game.model.Tile

/**
 * Holds vital information that the game needs in order to run (properly).
 * @param name the game name.
 *
 * @param revision
 * The cache revision the server is currently loading.
 *
 * @param cycleTime the time, in milliseconds, in between each full game
 * cycle/tick.
 *
 * @param playerLimit the max amount of players that can be online in the
 * world at once.
 *
 * @param home the [Tile] that will be used as the home area and tile where
 * new players will start off.
 *
 * @param skillCount the max amount of skills for players.
 *
 * @param npcStatCount the stat count for npcs.
 *
 * @param runEnergy if players' run energy will be deducted whilst running.
 *
 * @param gItemPublicDelay the amount of cycles for a [gg.rsmod.game.model.entity.GroundItem]
 * to become public if it's owned by a player.
 *
 * @param gItemDespawnDelay the amount of cycles for a [gg.rsmod.game.model.entity.GroundItem]
 * to despawn.
 *
 * @param preloadMaps if true, all map data will be be loaded on start-up instead
 * of on-demand.
 *
 * @param beta A flag that marks this world as a beta world.
 * @param allowCombatSkillSet Allow players to set combat skills.
 * @param allowNonCombatSkillSet Allow players to set non-combat skills.
 * @param allowItemSpawning Allow players to spawn items.
 * @param assertScriptBinding When scripts are initializing and an error is encountered
 *                            (such as an object doesn't have the option being bound to)
 *                            will the server thrown an exception (otherwise a warning will be thrown).
 * @param devWorld Defines whether this world is intended for development.
 *
 * @author Tom <rspsmods@gmail.com>
 */
data class GameContext(
        val name: String,
        val revision: Int,
        val cycleTime: Int,
        val playerLimit: Int,
        val home: Tile,
        val skillCount: Int,
        val npcStatCount: Int,
        val runEnergy: Boolean,
        val gItemPublicDelay: Int,
        val gItemDespawnDelay: Int,
        val preloadMaps: Boolean,
        val pluginExtensions: List<String>,
        val beta: Boolean,
        val allowCombatSkillSet: Boolean,
        val allowNonCombatSkillSet: Boolean,
        val allowItemSpawning: Boolean,
        val assertScriptBinding: Boolean,
        val devWorld: Boolean
)