package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer2Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer2Handler : MessageHandler<OpPlayer2Message> {

    override fun handle(client: Client, world: World, message: OpPlayer2Message) {
        OpPlayerHandler.handle(client, world, message.index, 2, this)
    }
}