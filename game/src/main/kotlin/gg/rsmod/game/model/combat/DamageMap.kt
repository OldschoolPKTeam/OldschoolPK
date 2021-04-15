package gg.rsmod.game.model.combat

import gg.rsmod.game.model.EntityType
import gg.rsmod.game.model.entity.Pawn
import gg.rsmod.game.model.entity.PawnReference
import java.util.*

/**
 * Represents a map of hits from different [Pawn]s and their information.
 *
 * @author Tom <rspsmods@gmail.com>
 */
class DamageMap {

    private val map = WeakHashMap<PawnReference, DamageStack>(0)

    operator fun get(pawn: Pawn): DamageStack? {
        return map[PawnReference(pawn)]
    }

    fun add(pawn: Pawn, damage: Int) {
        val total = (get(pawn)?.totalDamage ?: 0) + damage
        map[PawnReference(pawn)] = DamageStack(total, System.currentTimeMillis())
    }

    /**
     * Get all [DamageStack]s dealt by [Pawn]s whom meets the criteria
     * [Pawn.entityType] == [type].
     */
    fun getAll(type: EntityType, timeFrameMs: Long? = null): Collection<DamageStack> =
            map.filter { it.key.entityType == type && (timeFrameMs == null || System.currentTimeMillis() - it.value.lastHit < timeFrameMs) }.values


    /**
     * Get the total damage from a [pawn].
     *
     * @return
     * 0 if [pawn] has not dealt any damage.
     */
    fun getDamageFrom(pawn: Pawn): Int = map[PawnReference(pawn)]?.totalDamage ?: 0

    /**
     * Gets the [Pawn] that has dealt the most damage in this map.
     */
    fun getMostDamage(): PawnReference? = map.maxBy { it.value.totalDamage }?.key

    /**
     * Gets the most damage dealt by a [Pawn] in our map whom meets the criteria
     * [Pawn.entityType] == [type].
     */
    fun getMostDamage(type: EntityType, timeFrameMs: Long? = null): PawnReference? =
            map.filter { it.key.entityType == type && (timeFrameMs == null || System.currentTimeMillis() - it.value.lastHit < timeFrameMs) }
                    .maxBy { it.value.totalDamage }?.key

    /**
     * Clear the damage map.
     */
    fun clear() {
        map.clear()
    }

    data class DamageStack(val totalDamage: Int, val lastHit: Long)
}