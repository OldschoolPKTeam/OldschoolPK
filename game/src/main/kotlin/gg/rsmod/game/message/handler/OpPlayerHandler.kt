package gg.rsmod.game.message.handler

import gg.rsmod.game.action.PawnPathAction
import gg.rsmod.game.message.Message
import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.model.World
import gg.rsmod.game.model.attr.INTERACTING_OPT_ATTR
import gg.rsmod.game.model.attr.INTERACTING_PLAYER_ATTR
import gg.rsmod.game.model.entity.Client
import java.lang.ref.WeakReference

object OpPlayerHandler {
    /**
     * Handles a clicked [gg.rsmod.game.model.entity.Player] option.
     * @param client The [Client].
     * @param world The [Client]'s world.
     * @param index The index of clicked [gg.rsmod.game.model.entity.Player].
     * @param option The interaction option id.
     * @para messageHandler The message handler that received this clicked option.
     */
    fun handle(client: Client, world: World, index: Int, option: Int, messageHandler: MessageHandler<out Message>) {
        val optionIndex = option - 1

        if (!client.lock.canPlayerInteract()) {
            return
        }

        val other = world.players[index] ?: return

        if (client.options[optionIndex] == null || other == client) {
            return
        }

        messageHandler.log(client, "Player option: name=%s, opt=%d", other.username, option)

        client.closeInterfaceModal()
        client.interruptQueues()
        client.resetInteractions()

        client.attr[INTERACTING_PLAYER_ATTR] = WeakReference(other)
        client.attr[INTERACTING_OPT_ATTR] = option

        // Attack is the only option that is immediately handled by the combat system.
        if (client.options[optionIndex].equals("Attack")) {
            client.attack(other)
            world.plugins.executeAttackPawn(client)
        } else {
            client.executePlugin(PawnPathAction.walkPlugin)
        }
    }
}


