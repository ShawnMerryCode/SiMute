package shawnmerry.siplugins.simute

import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptPlayer
import taboolib.common.platform.function.info
import taboolib.module.chat.component
import taboolib.module.lang.asLangText

@SubscribeEvent
fun mainEvent(event: AsyncPlayerChatEvent){
    if (PlayerDB.get(event.player,"mute").toBoolean()){
        event.isCancelled = true
        info(event.player.name + " talked but muting")
        val player = adaptPlayer(event.player)
        player.asLangText("talk_when_mute").component().buildColored().sendTo(player)
    }
}