package ru.joutak.lobby.music

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.SoundCategory
import org.bukkit.plugin.java.JavaPlugin
import ru.joutak.lobby.music.commands.LMCommandExecutor
import java.io.File

class LobbyMusic : JavaPlugin() {
    companion object {
        @JvmStatic
        lateinit var instance: LobbyMusic
        val TITLE =
            LinearComponents.linear(
                Component.text("L", NamedTextColor.DARK_BLUE),
                Component.text("o", NamedTextColor.BLUE),
                Component.text("b", NamedTextColor.AQUA),
                Component.text("b", NamedTextColor.LIGHT_PURPLE),
                Component.text("y", NamedTextColor.DARK_PURPLE),
                Component.text("M", NamedTextColor.DARK_BLUE),
                Component.text("u", NamedTextColor.BLUE),
                Component.text("s", NamedTextColor.AQUA),
                Component.text("i", NamedTextColor.LIGHT_PURPLE),
                Component.text("c", NamedTextColor.DARK_PURPLE),
            )
    }

    private var customConfig = YamlConfiguration()

    private fun loadConfig() {
        val fx = File(dataFolder, "config.yml")
        if (!fx.exists()) {
            saveResource("config.yml", true)
        }
    }

    /**
     * Plugin startup logic
     */
    override fun onEnable() {
        instance = this

        loadConfig()

        // Register commands and events

        logger.info("Плагин ${pluginMeta.name} версии ${pluginMeta.version} включен!")
    }

    /**
     * Plugin shutdown logic
     */
    override fun onDisable() {
    }
}
