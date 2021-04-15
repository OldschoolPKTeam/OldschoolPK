package gg.rsmod.game.model.path.strategy

import gg.rsmod.game.model.Coordinate
import gg.rsmod.game.model.Direction
import gg.rsmod.game.model.Tile
import gg.rsmod.game.model.collision.CollisionManager
import gg.rsmod.game.model.path.PathFindingStrategy
import gg.rsmod.game.model.path.PathRequest
import gg.rsmod.game.model.path.Route
import gg.rsmod.util.AabbUtil
import java.util.*

/**
 * @author Tom <rspsmods@gmail.com>
 */
class SimplePathFindingStrategy(collision: CollisionManager) : PathFindingStrategy(collision) {

    override fun calculateRoute(request: PathRequest): Route {
        val start = request.start
        val end = request.end
        val deltaX = start.x - end.x
        val deltaZ = start.z - end.z
        val sourceWidth = request.sourceWidth
        val sourceLength = request.sourceLength
        val targetWidth = request.targetWidth
        val targetLength = request.targetLength
        val projectile = request.projectilePath

        val sourceTiles = start.getTiles(sourceWidth, sourceLength)
        val targetTiles = end.getTiles(targetWidth, targetLength)
        val distance = Tile.getShortestDistance(sourceTiles, targetTiles)

        if (distance >= 1) {
            val moveX = if (deltaX == 0) 0 else if (deltaX >= 1) -1 else 1
            val moveZ = if (deltaZ == 0) 0 else if (deltaZ >= 1) -1 else 1
            var moveTile = Coordinate(moveX, moveZ)
            val direction = Direction.fromDeltas(moveX, moveZ)
            val movingToTargetTile = Tile.getShortestDistance(start.transform(direction).getTiles(sourceWidth, sourceLength), targetTiles) < 1

            if (!canTraverse(collision, start, sourceWidth, sourceLength, direction, projectile) || movingToTargetTile) {
                moveTile = getApproximateDirection(collision, start, sourceWidth, sourceLength, direction, projectile, prioritizeXAxis = movingToTargetTile)
            }

            // Finalize the movement
            val movementDirection = Direction.fromDeltas(moveTile.x, moveTile.z)
            if (canTraverse(collision, start, sourceWidth, sourceLength, movementDirection, projectile)) {
                val tile = Tile(start.x + movementDirection.getDeltaX(), start.z + movementDirection.getDeltaZ())
                return Route(ArrayDeque<Tile>(listOf(tile)), true, tail = tile)
            }

            return fail(start)
        } else {
            // On top of each other
            val directions = listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
            val walkable = directions.filter { canTraverse(collision, start, sourceWidth, sourceLength, it, request.projectilePath) }

            if (walkable.isNotEmpty()) {
                val tile = start.transform(walkable.random())
                return Route(ArrayDeque<Tile>(listOf(tile)), success = true, tail = tile)
            }

            return fail(start)
        }
    }

    private fun getApproximateDirection(
            collision: CollisionManager,
            tile: Tile,
            width: Int,
            length: Int,
            direction: Direction,
            projectile: Boolean,
            prioritizeXAxis: Boolean = false
    ): Coordinate {
        return when {
            prioritizeXAxis || canTraverse(collision, tile, width, length, Direction.fromDeltas(direction.getDeltaX(), 0), projectile) -> {
                Coordinate(direction.getDeltaX(), 0)
            }
            canTraverse(collision, tile, width, length, Direction.fromDeltas(0, direction.getDeltaZ()), projectile) -> {
                Coordinate(0, direction.getDeltaZ())
            }
            else -> {
                Coordinate(0, 0)
            }
        }
    }

    private fun canTraverse(collision: CollisionManager, tile: Tile, width: Int, length: Int, direction: Direction, projectile: Boolean): Boolean {
        if (direction == Direction.NONE) {
            return false
        }

        for (x in 0 until width) {
            for (z in 0 until length) {
                val transform = tile.transform(x, z)
                if (!collision.canTraverse(transform, direction, projectile) || !collision.canTraverse(transform.step(direction), direction.getOpposite(), projectile)) {
                    return false
                }
            }
        }
        return true
    }

    private fun areBordering(tile1: Tile, size1: Int, tile2: Tile, size2: Int): Boolean = AabbUtil.areBordering(tile1.x, tile1.z, size1, size1, tile2.x, tile2.z, size2, size2)

    private fun areDiagonal(tile1: Tile, size1: Int, tile2: Tile, size2: Int): Boolean = AabbUtil.areDiagonal(tile1.x, tile1.z, size1, size1, tile2.x, tile2.z, size2, size2)

    private fun areOverlapping(tile1: Tile, size1: Int, tile2: Tile, size2: Int): Boolean = AabbUtil.areOverlapping(tile1.x, tile1.z, size1, size1, tile2.x, tile2.z, size2, size2)

    private fun areCoordinatesInRange(coord1: Int, size1: Int, coord2: Int, size2: Int): Boolean {
        val a = Pair(coord1, coord1 + size1)
        val b = Pair(coord2, coord2 + size2)

        if (a.second < b.first) {
            return false
        }

        if (a.first > b.second) {
            return false
        }

        return true
    }

    private fun fail(tile: Tile) = Route(ArrayDeque<Tile>(), success = false, tail = tile)
}