package gg.rsmod.game.model.interf

enum class InterfaceEvent(val value: Int) {
    NONE(0),
    CONTINUE(1 shl 0),
    CLICK_OP1(1 shl 1),
    CLICK_OP2(1 shl 2),
    CLICK_OP3(1 shl 3),
    CLICK_OP4(1 shl 4),
    CLICK_OP5(1 shl 5),
    CLICK_OP6(1 shl 6),
    CLICK_OP7(1 shl 7),
    CLICK_OP8(1 shl 8),
    CLICK_OP9(1 shl 9),
    CLICK_OP10(1 shl 10),
    USE_ON_GROUND_ITEMS(1 shl 11),
    USE_ON_NPCS(1 shl 12),
    USE_ON_OBJECTS(1 shl 13),
    USE_ON_PLAYERS(1 shl 14),
    USE_ON_INVENTORY(1 shl 15),
    USE_ON_COMPONENT(1 shl 16),
    DRAG_DEPTH1(1 shl 17),
    DRAG_DEPTH2(2 shl 17),
    DRAG_DEPTH3(3 shl 17),
    DRAG_DEPTH4(4 shl 17),
    DRAG_DEPTH5(5 shl 17),
    DRAG_DEPTH6(6 shl 17),
    DRAG_DEPTH7(7 shl 17),
    DRAG_TARGETABLE(1 shl 20),
    COMPONENT_TARGETABLE(1 shl 21);

    companion object {
        fun combine(vararg values: InterfaceEvent): Int = values.map { it.value }.reduce { acc, i -> acc or i }
    }
}