package shawnmerry.siplugins.simute

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object Config{
    @Config("config.yml")
    lateinit var config : ConfigFile
}