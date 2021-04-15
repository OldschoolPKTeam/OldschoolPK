package gg.rsmod.game.model

import kotlin.test.Test
import kotlin.test.assertTrue

class AreaTest {

    @Test
    fun areaTest() {
        val area = Area(3200, 3200, 3199, 3199)
        assertTrue(area.bottomLeft.x <= area.topRight.x)
        assertTrue(area.bottomLeft.z <= area.topRight.z)
    }

}