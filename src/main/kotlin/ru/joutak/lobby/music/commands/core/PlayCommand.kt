package ru.joutak.lobby.music.commands.core

import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.zone.ZoneManager
import kotlin.collections.List

object PlayCommand : PluginCommand("play", emptyList(), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        ZoneManager.getMusicZones().values.forEach { zone -> zone.play() }
        return true
    }

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = emptyList()
}
