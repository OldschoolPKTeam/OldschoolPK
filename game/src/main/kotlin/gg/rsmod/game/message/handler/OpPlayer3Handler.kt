package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpPlayer3Message
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client

/**
 * @author Triston Plummer ("Dread")
 */
class OpPlayer3Handler : MessageHandler<OpPlayer3Message> {

    override fun handle(client: Client, world: World, message: OpPlayer3Message) {
        OpPlayerHandler.handle(client, world, message.index, 3, this)
    }
}