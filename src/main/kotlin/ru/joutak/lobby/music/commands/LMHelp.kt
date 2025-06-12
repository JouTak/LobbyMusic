package ru.joutak.lobby.music.commands

import org.bukkit.command.CommandSender

object LMHelp : LMCommand("help", emptyList(), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean = false

    override fun getHint(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = emptyList()
}
