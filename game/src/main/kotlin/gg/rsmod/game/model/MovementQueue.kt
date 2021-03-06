package gg.rsmod.game.model

import gg.rsmod.game.model.MovementQueue.Step
import gg.rsmod.game.model.entity.Pawn
import gg.rsmod.game.sync.block.UpdateBlockType
import java.util.*

/**
 * Responsible for handling a queue of [Step]s for a [Pawn].
 *
 * @author Tom <rspsmods@gmail.com>
 */
class MovementQueue(val pawn: Pawn) {

    /**
     * A [Deque] of steps.
     */
    private val steps: Deque<Step> = ArrayDeque()

    /**
     * If any step is queued.
     */
    fun hasDestination(): Boolean = steps.isNotEmpty()

    /**
     * Get the last tile in our [steps] without removing it.
     */
    fun peekLast(): Tile? = peekLastStep()?.tile

    fun peekLastStep(): Step? = if (steps.isNotEmpty()) steps.peekLast() else null

    fun clear() {
        steps.clear()
    }

    fun addStep(step: Tile, type: StepType, detectCollision: Boolean) {
        val current = if (steps.any()) steps.peekLast().tile else pawn.tile
        addStep(current, step, type, detectCollision)
    }

    /**
     * Peeks into the next movement cycle to determine the ending tile.
     * Remember if you update [cycle] to update this method!
     */
    fun peekNextMovement(): Tile {
        val collision = pawn.world.collision

        val iterator = steps.iterator()
        var next = if (iterator.hasNext()) iterator.next() else null
        if (next != null) {
            var tile = pawn.tile

            var walkDirection: Direction?
            val runDirection: Direction?

            walkDirection = Direction.between(tile, next.tile)

            if (walkDirection != Direction.NONE && (!next.detectCollision || collision.canTraverse(tile, walkDirection, projectile = false))) {
                tile = Tile(next.tile)

                val running = when (next.type) {
                    StepType.NORMAL -> pawn.isRunning()
                    StepType.FORCED_RUN -> true
                    StepType.FORCED_WALK -> false
                }
                if (running) {
                    next = if (iterator.hasNext()) iterator.next() else null
                    if (next != null) {
                        runDirection = Direction.between(tile, next.tile)

                        if (!next.detectCollision || collision.canTraverse(tile, runDirection, projectile = false)) {
                            tile = Tile(next.tile)
                        }
                    }
                }
            } else {
                walkDirection = null
            }

            if (walkDirection != null && walkDirection != Direction.NONE) {
                return Tile(tile)
            }
        }

        return pawn.tile
    }

    /**
     * Cycle movement.
     * If you updated this function remember to update [peekNextMovement].
     */
    fun cycle() {
        val collision = pawn.world.collision

        var next = steps.poll()
        if (next != null) {
            var tile = pawn.tile

            var walkDirection: Direction?
            var runDirection: Direction? = null

            walkDirection = Direction.between(tile, next.tile)

            if (walkDirection != Direction.NONE && (!next.detectCollision || collision.canTraverse(tile, walkDirection, projectile = false))) {
                tile = Tile(next.tile)
                pawn.lastFacingDirection = walkDirection

                val running = when (next.type) {
                    StepType.NORMAL -> pawn.isRunning()
                    StepType.FORCED_RUN -> true
                    StepType.FORCED_WALK -> false
                }
                if (running) {
                    next = steps.poll()
                    if (next != null) {
                        runDirection = Direction.between(tile, next.tile)

                        if (!next.detectCollision || collision.canTraverse(tile, runDirection, projectile = false)) {
                            tile = Tile(next.tile)
                            pawn.lastFacingDirection = runDirection
                        } else {
                            clear()
                            runDirection = null
                        }
                    }
                }
            } else {
                walkDirection = null
                clear()
            }

            if (walkDirection != null && walkDirection != Direction.NONE) {
                pawn.steps = StepDirection(walkDirection, runDirection)
                pawn.updateTile(tile)
                if (runDirection != null) {
                    pawn.addBlock(UpdateBlockType.MOVEMENT)
                }
            }
        }
    }

    private fun addStep(current: Tile, next: Tile, type: StepType, detectCollision: Boolean) {
        var dx = next.x - current.x
        var dz = next.z - current.z
        val delta = Math.max(Math.abs(dx), Math.abs(dz))

        for (i in 0 until delta) {
            if (dx < 0) {
                dx++
            } else if (dx > 0) {
                dx--
            }

            if (dz < 0) {
                dz++
            } else if (dz > 0) {
                dz--
            }

            val step = next.transform(-dx, -dz)
            steps.add(Step(step, type, detectCollision))
        }
    }

    data class StepDirection(val walkDirection: Direction?, val runDirection: Direction?)

    data class Step(val tile: Tile, val type: StepType, val detectCollision: Boolean)

    enum class StepType {
        NORMAL,
        FORCED_WALK,
        FORCED_RUN
    }
}