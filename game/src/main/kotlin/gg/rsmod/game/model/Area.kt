package gg.rsmod.game.model

import java.awt.Rectangle

/**
 * Represents a quad area in the world.
 *
 * @author Tom <rspsmods@gmail.com>
 */
class Area(bottomLeftX: Int, bottomLeftZ: Int, topRightX: Int, topRightZ: Int) {

    val bottomLeftX: Int
    val bottomLeftZ: Int
    val topRightX: Int
    val topRightZ: Int
    val tiles: HashSet<Tile>

    /**
     * Calculates the 'middle' tile of the area. The result is just an estimate
     * of what the middle tile should be, as it's possible for the area to not
     * be even in tiles.
     *
     * Example of when the tile is not perfectly centered:
     * [topRightX - bottomLeftZ % 2 != 0] or [topRightZ - bottomLeft % 2 != 0]
     */
    val centre: Tile get() = Tile(bottomLeftX + (topRightX - bottomLeftX), bottomLeftZ + (topRightZ - bottomLeftZ))

    val bottomLeft: Tile get() = Tile(bottomLeftX, bottomLeftZ)

    val topRight: Tile get() = Tile(topRightX, topRightZ)

    fun contains(x: Int, z: Int): Boolean = x in bottomLeftX..topRightX && z in bottomLeftZ..topRightZ

    fun contains(t: Tile): Boolean = t.x in bottomLeftX..topRightX && t.z in bottomLeftZ..topRightZ

    fun toRectangle(): Rectangle {
        return Rectangle(bottomLeftX, bottomLeftZ, topRightX - bottomLeftX, topRightZ - bottomLeftZ)
    }

    init {
        if (bottomLeftX > topRightX) {
            this.bottomLeftX = topRightX
            this.topRightX = bottomLeftX
        } else {
            this.bottomLeftX = bottomLeftX
            this.topRightX = topRightX
        }
        if (bottomLeftZ > topRightZ) {
            this.bottomLeftZ = topRightZ
            this.topRightZ = bottomLeftZ
        } else {
            this.bottomLeftZ = bottomLeftZ
            this.topRightZ = topRightZ
        }
        tiles = HashSet<Tile>().apply {
            for(x in bottomLeftX..topRightX) {
                for(z in bottomLeftZ..topRightZ) {
                    this.add(Tile(x, z, 0))
                }
            }
        }
    }
}