package ru.joutak.lobby.music.music

import ru.joutak.lobby.music.config.ConfigManager

object MusicManager {
    private var playlist: Set<Music> = setOf()

    fun load() {
        playlist = ConfigManager.loadPlaylist() ?: setOf()
    }

    fun save() {
        ConfigManager.savePlaylist()
    }

    fun getPlaylist(): Set<Music> = playlist

    // fun play() {
    // Bukkit.getServer().onlinePlayers.forEach { player ->
    //     player.playSound(
    //         Location(Bukkit.getWorld("bp_lobby"), -800.0, 23.0, -130.0),
    //         ConfigManager.getMusicPlaylist().getNextTrack().key,
    //         SoundCategory.RECORDS,
    //         1.0f,
    //         1.0f,
    //     )
    // }
    // }
}
