package gg.rsmod.game.model.interf

/**
 * Represents a display-mode that the client can use.
 *
 * @author Tom <rspsmods@gmail.com>
 */
enum class DisplayMode(val id: Int) {
    /**
     * The game client is in fixed display-mode.
     */
    FIXED(id = 0),

    /**
     * The game client is in resizable display-mode.
     */
    RESIZABLE_CLASSIC(id = 1),

    /**
     * The game client is in resizable display-mode, with the list view for its
     * gameframe.
     */
    RESIZABLE_MODERN(id = 2),

    /**
     * The game client is in mobile display-mode.
     */
    MOBILE(id = 3),

    /**
     * The game client is in resizable display-mode. This is not supported for
     * lower revisions.
     */
    FULLSCREEN(id = 4);

    fun isResizable(): Boolean = this == RESIZABLE_CLASSIC || this == RESIZABLE_MODERN

    companion object {
        val values = enumValues<DisplayMode>()
    }
}