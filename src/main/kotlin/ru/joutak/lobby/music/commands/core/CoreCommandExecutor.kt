package ru.joutak.lobby.music.commands.core

import ru.joutak.lobby.music.commands.PluginCommandExecutor
import ru.joutak.lobby.music.commands.music.MusicCommandExecutor
import ru.joutak.lobby.music.commands.zone.ZoneCommandExecutor

object CoreCommandExecutor : PluginCommandExecutor("lm") {
    init {
        registerExecutor(MusicCommandExecutor)
        registerExecutor(ZoneCommandExecutor)

        registerCommand(HelpCommand)
        registerCommand(PlayCommand)
        registerCommand(StopCommand)
    }
}
