package ru.joutak.lobby.music.utils

import ru.joutak.lobby.music.LobbyMusic

object PluginManager {
    val lobbyMusic = LobbyMusic.instance
    val dataFolder = lobbyMusic.dataFolder
    val logger = lobbyMusic.logger
}
