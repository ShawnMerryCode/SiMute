package shawnmerry.siplugins.simute

import shawnmerry.siplugins.simute.Config.config
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.lang.event.PlayerSelectLocaleEvent
import taboolib.module.lang.event.SystemSelectLocaleEvent


@SubscribeEvent
fun lang(event: PlayerSelectLocaleEvent) {
    event.locale = config.getString("Lang", "zh_CN")!!
}

@SubscribeEvent
fun lang(event: SystemSelectLocaleEvent) {
    event.locale = config.getString("Lang", "zh_CN")!!
}