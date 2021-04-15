package gg.rsmod.game.model.combat

import gg.rsmod.game.model.Hit
import gg.rsmod.game.model.entity.Pawn
import java.lang.ref.WeakReference

/**
 * Represents a [Hit] dealt by a [gg.rsmod.game.model.entity.Pawn].
 *
 * @param landed
 * If the hit past the accuracy formula check (hit should land a random number
 * based on max hit)
 *
 * @author Tom <rspsmods@gmail.com>
 */
data class PawnHit(val hit: Hit, val landed: Boolean) {

    val mainDamageSource: WeakReference<Pawn>? = hit.hitmarks.maxBy { it.damage }?.attacker?.get()

    /**
     * A flag that indicates if the [Hit] produced damage
     */
    val didDamage = hit.hitmarks.sumBy { it.damage } > 0

    /**
     * The total damage of the [Hit]
     */
    val totalDamage = hit.hitmarks.sumBy { it.damage }

    /**
     * A flag that indicates if the [Hit] did damage and was accurate
     */
    fun successfulHit(): Boolean = didDamage && landed

}