package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer1Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer1Handler : MessageHandler<OpPlayer1Message> {

    override fun handle(client: Client, world: World, message: OpPlayer1Message) {
        OpPlayerHandler.handle(client, world, message.index, 1, this)
    }
}