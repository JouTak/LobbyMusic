package ru.joutak.lobby.music.config

import org.bukkit.configuration.file.YamlConfiguration
import ru.joutak.lobby.music.music.Music
import ru.joutak.lobby.music.utils.PluginManager
import ru.joutak.lobby.music.zone.Zone
import java.io.File
import java.io.IOException

object ConfigManager {
    private val playlistFile = File(PluginManager.dataFolder, "playlist.yml")
    private val musicZonesFile = File(PluginManager.dataFolder, "zones.yml")

    // private var config: Config = Config()
    // @OptIn(ExperimentalHoplite::class)

    // val musicZones =
    //     ConfigLoaderBuilder
    //         .newBuilder()
    //         .withClassLoader(PluginManager.lobbyMusic.javaClass.classLoader)
    //         .addDefaultDecoders()
    //         .addDefaultPreprocessors()
    //         .addDefaultParamMappers()
    //         .addDefaultPropertySources()
    //         .addDefaultParsers()
    //         .withExplicitSealedTypes()
    //         .addFileSource(musicZonesFile)
    //         .build()
    //         .loadConfigOrThrow<MutableMap<String, MusicZone>>()

    fun load() {
    }

    fun loadPlaylist(): Set<Music>? {
        if (!playlistFile.exists()) {
            PluginManager.logger.severe(
                "Отсутствует файл со списком доступной музыки (${playlistFile.path}), пожалуйста, проверьте и перезагрузите плагин!",
            )
            return null
        }

        try {
            val playlistYaml = YamlConfiguration.loadConfiguration(playlistFile)
            val playlist =
                playlistYaml.getList("playlist")
                    ?: throw NullPointerException("Не найден ключ tracks в файле с доступной музыкой")

            // PluginManager.logger.info(playlist.toString())
            PluginManager.logger.info("Список доступной музыки успешно загружен!")

            return playlist.toSet() as Set<Music>
        } catch (e: Exception) {
            PluginManager.logger.severe("Не удалось загрузить список доступной музыки: ${e.message}")
            return null
        }
    }

    fun savePlaylist() {
        // val playlistYaml = YamlConfiguration()
        // playlistYaml.set(
        //     "tracks",
        //     listOf(Track("minecraft.test", 20, "Author", "Title")),
        // )
        //
        // try {
        //     playlistYaml.save(playlistFile)
        // } catch (e: IOException) {
        //     PluginManager.logger.severe("Не удалось сохранить плейлист: ${e.message}")
        // }
    }

    fun loadMusicZones(): MutableMap<String, Zone>? {
        val musicZonesFile = File(PluginManager.dataFolder, "zones.yml")

        if (!musicZonesFile.exists()) {
            PluginManager.lobbyMusic.saveResource("zones.yml", true)
            PluginManager.logger.warning(
                "Отсутствует файл со списком зон (${musicZonesFile.path}), поэтому был создан файл с пустым списком.",
            )
            return null
        }

        try {
            val musicZoneYaml = YamlConfiguration.loadConfiguration(musicZonesFile)
            val musicZones = musicZoneYaml.getConfigurationSection("zones")?.getValues(false)

            // PluginManager.logger.info(musicZones.toString())
            PluginManager.logger.info("Список зон для проигрывания музыки успешно загружен!")

            return musicZones as HashMap<String, Zone>
        } catch (e: Exception) {
            PluginManager.logger.severe("Не удалось загрузить список зон для проигрывания музыки: ${e.message}")
            return null
        }
    }

    fun saveMusicZones(zones: Map<String, Zone>) {
        val musicZonesYaml = YamlConfiguration()
        musicZonesYaml.set("zones", zones)

        try {
            musicZonesYaml.save(musicZonesFile)
        } catch (e: IOException) {
            PluginManager.logger.severe("Ошибка при сохранении списка зон: ${e.message}")
        }
    }
}
