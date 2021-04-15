package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer6Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer6Handler : MessageHandler<OpPlayer6Message> {

    override fun handle(client: Client, world: World, message: OpPlayer6Message) {
        OpPlayerHandler.handle(client, world, message.index, 6, this)
    }
}