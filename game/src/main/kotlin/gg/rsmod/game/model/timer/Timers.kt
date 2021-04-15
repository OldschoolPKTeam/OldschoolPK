package gg.rsmod.game.model.timer

/**
 * A decoupled file that holds TimerKeys that require read-access from our
 * game module. Any timer keys that can be stored on the plugin classes themselves,
 * should do so. When storing them in a class, remember the TimerKey must be
 * a singleton, meaning it should only have a single state.
 *
 * @author Tom <rspsmods@gmail.com>
 */

/**
 * A timer for npcs to reset their pawn face attribute.
 */
internal val RESET_PAWN_FACING_TIMER = TimerKey()

/**
 * A timer for removing a skull icon.
 */
val SKULL_ICON_DURATION_TIMER = TimerKey()

/**
 * Timer key set when a pawn is attacked either in PvP or in PvM.
 */
val ACTIVE_COMBAT_TIMER = TimerKey()

/**
 * Timer key used to force a player disconnect, usually used so that if a
 * player's channel has been inactive (disconnected) for X amount of time,
 * we disconnect them so that they can play again.
 */
val FORCE_DISCONNECTION_TIMER = TimerKey()

/**
 * Timer key set when frozen.
 */
val FROZEN_TIMER = TimerKey()

/**
 * Timer key set when stunned.
 */
val STUN_TIMER = TimerKey()

/**
 * Timer key for dragonfire protection ticking down.
 */
val ANTIFIRE_TIMER = TimerKey()

/**
 * Timer key for the delay in between a pawn's attack.
 */
val ATTACK_DELAY = TimerKey()

/**
 * Timer key for delay in between drinking potions.
 */
val POTION_DELAY = TimerKey()

/**
 * Timer key for delay in between eating food.
 */
val FOOD_DELAY = TimerKey()

/**
 * Timer key for delay in between eating "combo" food.
 */
val COMBO_FOOD_DELAY = TimerKey()

/**
 * Timer key set when Teleblocked
 */
val TELEBLOCK_TIMER = TimerKey(resetOnDeath = true)

/**
 * Timer key set when [Player] has recently been in combat with a Player
 *
 * For special cases like Energy transfer spell.
 */
val LAST_COMBAT_TIMER = TimerKey(persistenceKey = "LAST_COMBAT_TIMER", tickOffline = true)

/**
 * Timer key set when [Player] drinks an antipoison
 */
val POISON_IMMUNITY_TIMER = TimerKey(persistenceKey = "POISON_IMMUNITY_TIMER", resetOnDeath = true)

/**
 * Timer key set when [Player] drinks an antivenom
 */
val VENOM_IMMUNITY_TIMER = TimerKey(persistenceKey = "VENOM_IMMUNITY_TIMER", resetOnDeath = true)

/**
 * Timer key set when [Player] sips an antifire potion
 */
val ANTIFIRE_IMMUNITY_TIMER = TimerKey(persistenceKey = "ANTIFIRE_IMMUNITY_TIMER", resetOnDeath = true)

/**
 * Timer key set when [Player] sips an antifire potion
 */
val ANTIFIRE_WARNING_TIMER = TimerKey(persistenceKey = "ANTIFIRE_WARNING_TIMER", resetOnDeath = true)