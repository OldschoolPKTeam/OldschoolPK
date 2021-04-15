package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer5Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer5Handler : MessageHandler<OpPlayer5Message> {

    override fun handle(client: Client, world: World, message: OpPlayer5Message) {
        OpPlayerHandler.handle(client, world, message.index, 5, this)
    }
}