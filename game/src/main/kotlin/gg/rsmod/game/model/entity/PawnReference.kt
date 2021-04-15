package gg.rsmod.game.model.entity

import gg.rsmod.game.model.EntityType
import gg.rsmod.game.model.World

/**
 * A reference to a [Pawn] that may or may not still exist inside the [World].
 */
class PawnReference(pawn: Pawn) {

    val hash: Int
    val entityType: EntityType

    init {
        check(pawn.entityType == EntityType.PLAYER
                || pawn.entityType == EntityType.CLIENT
                || pawn.entityType == EntityType.NPC) { "Unsupported entity type ${pawn.entityType}" }
        hash = pawn.hashCode()
        entityType = if (pawn.entityType == EntityType.CLIENT) EntityType.PLAYER else pawn.entityType
    }

    fun get(world: World): Pawn? {
        return when (entityType) {
            EntityType.PLAYER -> world.players.getFromHash(hash)
            EntityType.NPC -> world.npcs.getFromHash(hash)
            else -> throw UnsupportedOperationException()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PawnReference

        if (hash != other.hash) return false
        if (entityType != other.entityType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hash
        result = 31 * result + entityType.hashCode()
        return result
    }


}