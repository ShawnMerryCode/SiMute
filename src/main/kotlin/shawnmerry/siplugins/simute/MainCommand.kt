package shawnmerry.siplugins.simute

import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.info
import taboolib.module.chat.component
import taboolib.module.lang.asLangText

@CommandHeader("mute")
object MainCommand {
    @CommandBody
    val on = subCommand {
        player("target") {
            execute<ProxyCommandSender> { sender, context, _ ->
                val player = context.player("target").castSafely<Player>()
                if (player != null) {
                    PlayerDB.set(player,"mute","true")
                    info(player.name + " | UUID:" + player.uniqueId.toString() + " be muted.")
                    sender.asLangText("mute_successfully",player.name).component().buildColored().sendTo(sender)
                }else {
                    sender.asLangText("mute_failed",context).component().buildColored().sendTo(sender)
                }
            }
        }
    }

    @CommandBody
    val off = subCommand {
        player("target") {
            execute<ProxyCommandSender> { sender, context, _ ->
                val player = context.player("target").castSafely<Player>()
                if (player != null) {
                    info(player.name + " | UUID:" + player.uniqueId.toString() + " be unMuted.")
                    PlayerDB.set(player,"mute","false")
                    sender.asLangText("unmute_successfully", player.name).component().buildColored().sendTo(sender)
                }else {
                    sender.asLangText("unmute_failed", context).component().buildColored().sendTo(sender)
                }
            }
        }
    }

    @CommandBody
    val get = subCommand {
        player("target") {
            execute<ProxyCommandSender> { sender, context, _ ->
                val player = context.player("target").castSafely<Player>()
                if (player != null) {
                    val status = if (PlayerDB.get(player,"mute").toBoolean()){
                        sender.asLangText("muted")
                    }else {
                        sender.asLangText("un_muted")
                    }
                    sender.asLangText("get_successfully",player.name,status).component().buildColored().sendTo(sender)
                }else {
                    sender.asLangText("get_failed",context).component().buildColored().sendTo(sender)
                }
            }
        }
    }
}