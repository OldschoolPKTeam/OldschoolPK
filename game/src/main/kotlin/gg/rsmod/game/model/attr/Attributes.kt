package gg.rsmod.game.model.attr

import com.google.common.collect.EvictingQueue
import gg.rsmod.game.model.combat.PawnHit
import gg.rsmod.game.model.container.ItemTransaction
import gg.rsmod.game.model.container.key.ContainerKey
import gg.rsmod.game.model.entity.*
import gg.rsmod.game.model.item.Item
import gg.rsmod.game.model.shop.Shop
import java.lang.ref.WeakReference

/**
 * A decoupled file that holds AttributeKeys that require read-access from our
 * game module. Any attributes that can be stored on the plugin classes themselves,
 * should do so. When storing them in a class, remember the AttributeKey must be
 * a singleton, meaning it should only have a single state.
 *
 * @author Tom <rspsmods@gmail.com>
 */

/**
 * A flag which indicates if the player's account was just created/logged in for
 * the first time.
 */
val NEW_ACCOUNT_ATTR = AttributeKey<Boolean>()

/**
 * A flag which indicates that the player will not take collision into account
 * when walking.
 */
val NO_CLIP_ATTR = AttributeKey<Boolean>()

/**
 * A flag that indicates whether or not this player has protect-item
 * prayer active.
 */
val PROTECT_ITEM_ATTR = AttributeKey<Boolean>()

/**
 * The display mode that the player has submitted as a message.
 */
val DISPLAY_MODE_CHANGE_ATTR = AttributeKey<Int>()

/**
 * The [Pawn] which another pawn is facing.
 */
val FACING_PAWN_ATTR = AttributeKey<WeakReference<Pawn>>()

/**
 * An [Npc] that has us as their [FACING_PAWN_ATTR].
 */
val NPC_FACING_US_ATTR = AttributeKey<WeakReference<Npc>>()

/**
 * The current viewed shop.
 */
val CURRENT_SHOP_ATTR = AttributeKey<Shop>()

/**
 * The [Pawn] which another pawn wants to initiate combat with, whether they meet
 * the criteria to attack or not (including being in attack range).
 */
val COMBAT_TARGET_FOCUS_ATTR = AttributeKey<WeakReference<Pawn>>()

/**
 * The [Pawn] that killed another pawn.
 */
val KILLER_ATTR = AttributeKey<WeakReference<Pawn>>()

/**
 * The [EvictingQueue] of recent kills for a [Player]
 * that tracks the IP address of the killed [Player]s
 */
val RECENT_KILLS = AttributeKey<EvictingQueue<String>>(persistenceKey = "RECENT_KILLS")

/**
 * The last [Pawn] that the owner of this attribute has hit.
 */
val LAST_HIT_ATTR = AttributeKey<WeakReference<Pawn>>()

/**
 * The last [Pawn] who has hit the owner of this attribute.
 */
val LAST_HIT_BY_ATTR = AttributeKey<WeakReference<Pawn>>()

/**
 * The last [PawnHit] the [Pawn] did
 */
val LAST_PAWNHIT = AttributeKey<WeakReference<PawnHit>>()

/**
 * The last [PawnHit] that was received
 */
val LAST_PAWNHIT_RECEIVED = AttributeKey<WeakReference<PawnHit>>()

/**
 * The amount of antifire potion charges left.
 */
val ANTIFIRE_POTION_CHARGES_ATTR = AttributeKey<Int>(persistenceKey = "antifire_potion_charges", resetOnDeath = true)

/**
 * If full dragonfire immunity is enabled.
 */
val DRAGONFIRE_IMMUNITY_ATTR = AttributeKey<Boolean>(persistenceKey = "dragonfire_immunity", resetOnDeath = true)

/**
 * The command that the player has submitted to the server using the '::' prefix.
 */
val COMMAND_ATTR = AttributeKey<String>()

/**
 * The arguments to the last command that was submitted by the player. This does
 * not include the command itself, if you want the command itself, use the
 * [COMMAND_ATTR] attribute.
 */
val COMMAND_ARGS_ATTR = AttributeKey<Array<String>>()

/**
 * The option that was last selected on any entity message.
 * For example: object action one will set this attribute to [1].
 */
val INTERACTING_OPT_ATTR = AttributeKey<Int>()

/**
 * The slot that was selected on any entity message.
 */
val INTERACTING_SLOT_ATTR = AttributeKey<Int>()

/**
 * The slot that was last selected on any entity message.
 */
val LAST_INTERACTING_SLOT_ATTR = AttributeKey<Int>()

/**
 * The [GroundItem] that was last clicked on.
 */
val INTERACTING_GROUNDITEM_ATTR = AttributeKey<WeakReference<GroundItem>>()

/**
 * The last [ItemTransaction] to occur when a ground item is picked up
 * from the ground.
 */
val GROUNDITEM_PICKUP_TRANSACTION = AttributeKey<WeakReference<ItemTransaction>>()

/**
 * The [GameObject] that was last clicked on.
 */
val INTERACTING_OBJ_ATTR = AttributeKey<WeakReference<out GameObject>>()

/**
 * The [Npc] that was last clicked on.
 */
val INTERACTING_NPC_ATTR = AttributeKey<WeakReference<Npc>>()

/**
 * The [Player] that was last clicked on.
 */
val INTERACTING_PLAYER_ATTR = AttributeKey<WeakReference<Player>>()

/**
 * The slot of the interacting item in its item container.
 */
val INTERACTING_ITEM_SLOT = AttributeKey<Int>()

/**
 * The id of the interacting item.
 */
val INTERACTING_ITEM_ID = AttributeKey<Int>()

/**
 * The item pointer of the interacting item.
 */
val INTERACTING_ITEM = AttributeKey<WeakReference<Item>>()

/**
 * The container key the player is interacting with
 */
val INTERACTING_ITEM_CONTAINER_KEY = AttributeKey<ContainerKey>()

/**
 * The slot index of any 'secondary' item being interacted with.
 */
val OTHER_ITEM_SLOT_ATTR = AttributeKey<Int>()

/**
 * The item id of any 'secondary' item being interacted with.
 */
val OTHER_ITEM_ID_ATTR = AttributeKey<Int>()

/**
 * The item pointer of any 'secondary' item being interacted with.
 */
val OTHER_ITEM_ATTR = AttributeKey<WeakReference<Item>>()

/**
 * Interacting interface parent id.
 */
val INTERACTING_COMPONENT_PARENT = AttributeKey<Int>()

/**
 * Interacting interface child id.
 */
val INTERACTING_COMPONENT_CHILD = AttributeKey<Int>()

/**
 * The skill id of the latest level up.
 */
val LEVEL_UP_SKILL_ID = AttributeKey<Int>()

/**
 * The amount of levels that have incremented in a skill level up.
 */
val LEVEL_UP_INCREMENT = AttributeKey<Int>()

/**
 * The previous skill XP of the latest level up.
 */
val LEVEL_UP_OLD_XP = AttributeKey<Double>()

/**
 * The bounty hunter points for a player
 */
val BOUNTY_HUNTER_POINTS = AttributeKey<Int>(persistenceKey = "BOUNTY_HUNTER_POINTS")

/**
 * A flag that determines whether the player should have unlimited special attack energy
 */
val UNLIMITED_SPECIAL_ATTACK = AttributeKey<Boolean>(persistenceKey = "UNLIMITED_SPECIAL_ATTACK")

/**
 * The last fired projectile by the pawn
 */
val LAST_FIRED_PROJECTILE = AttributeKey<Projectile>()