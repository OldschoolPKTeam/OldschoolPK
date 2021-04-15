package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer7Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer7Handler : MessageHandler<OpPlayer7Message> {

    override fun handle(client: Client, world: World, message: OpPlayer7Message) {
        OpPlayerHandler.handle(client, world, message.index, 7, this)
    }
}