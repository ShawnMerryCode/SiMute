package shawnmerry.siplugins.simute

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.io.newFile
import taboolib.common.platform.function.disablePlugin
import taboolib.common.platform.function.getDataFolder
import taboolib.expansion.setupPlayerDatabase
import shawnmerry.siplugins.simute.Config.config
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit
import taboolib.expansion.getDataContainer
import taboolib.expansion.releasePlayerDataContainer
import taboolib.expansion.setupDataContainer


object PlayerDB{

    @Awake(LifeCycle.ENABLE)
    private fun setup(){
        try {
            if (config.getBoolean("Database.enable")) {
                setupPlayerDatabase(config.getConfigurationSection("Database")!!)
            } else {
                setupPlayerDatabase(newFile(getDataFolder(), "data.db"))
            }
        } catch (ex: Throwable) {
            ex.printStackTrace()
            disablePlugin()
            return
        }
    }

    @SubscribeEvent
    private fun onJoin(e: PlayerJoinEvent) {
        submit(async = true) {
            e.player.setupDataContainer()
        }
    }

    @SubscribeEvent
    private fun onQuit(e: PlayerQuitEvent) {
        e.player.uniqueId.releasePlayerDataContainer()
    }

    fun get(player: Player,key: String): String? {
        return player.getDataContainer()[key]
    }

    fun set(player: Player,key: String,value: String): Boolean {
        try {
            player.getDataContainer()[key] = value
            return true
        }catch (e: Exception){
            return false
        }
    }

}