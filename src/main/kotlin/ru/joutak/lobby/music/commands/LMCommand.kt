package ru.joutak.lobby.music.commands

import org.bukkit.command.CommandSender

abstract class LMCommand(
    val name: String,
    val args: List<String>,
    val description: String,
    val permission: String? = null,
) {
    abstract fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean

    abstract fun getHint(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String>
}
