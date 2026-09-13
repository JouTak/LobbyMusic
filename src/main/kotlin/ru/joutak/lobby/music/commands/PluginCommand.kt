package ru.joutak.lobby.music.commands

import org.bukkit.command.CommandSender
import kotlin.collections.List

abstract class PluginCommand(
    val name: String,
    val args: List<String>,
    val description: String,
    val permission: String? = null,
) {
    abstract fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean

    abstract fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String>
}
