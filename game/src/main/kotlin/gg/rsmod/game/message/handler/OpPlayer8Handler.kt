package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer8Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer8Handler : MessageHandler<OpPlayer8Message> {

    override fun handle(client: Client, world: World, message: OpPlayer8Message) {
        OpPlayerHandler.handle(client, world, message.index, 8, this)
    }
}