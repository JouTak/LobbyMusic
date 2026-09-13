package ru.joutak.lobby.music.commands.core

import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import kotlin.collections.List

object HelpCommand : PluginCommand("help", emptyList(), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean = false // TODO

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = emptyList() // TODO
}
