package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer4Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer4Handler : MessageHandler<OpPlayer4Message> {

    override fun handle(client: Client, world: World, message: OpPlayer4Message) {
        OpPlayerHandler.handle(client, world, message.index, 4, this)
    }
}