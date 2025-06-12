package ru.joutak.lobby.music.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import ru.joutak.lobby.music.LobbyMusic
import java.util.SortedMap
import java.util.TreeMap

object LMCommandExecutor : CommandExecutor, TabExecutor {
    private val subcommands: SortedMap<String, LMCommand> = TreeMap()

    private fun registerCommand(command: LMCommand) {
        subcommands[command.name] = command
    }

    init {
        registerCommand(LMHelp)
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        if (args.isEmpty()) {
            sendUsageMessage(sender)
            return true
        }

        val subcommandName = args[0].lowercase()
        val subcommand = subcommands[subcommandName]

        if (subcommand == null) {
            sendUsageMessage(sender)
            return true
        }

        if (subcommand.permission != null && !sender.hasPermission(subcommand.permission)) {
            sender.sendMessage(
                LinearComponents.linear(Component.text("Недостаточно прав для использования данной команды!", NamedTextColor.RED)),
            )
            return true
        }

        val subArgs = if (args.size > 1) args.sliceArray(1 until args.size) else emptyArray()

        if (subcommand.execute(sender, subArgs)) {
            return true
        }

        sendUsageMessage(sender)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>,
    ): List<String> {
        if (args.size == 1) {
            return subcommands.keys
                .filter { sender.hasPermission(subcommands[it]?.permission ?: return@filter true) }
                .filter { it.startsWith(args[0].lowercase()) }
                .sorted()
        }

        if (args.size > 1) {
            val subCommandName = args[0].lowercase()
            val subCommand = subcommands[subCommandName] ?: return emptyList()

            if (subCommand.permission != null && !sender.hasPermission(subCommand.permission)) {
                return emptyList()
            }

            val subArgs = args.sliceArray(1 until args.size)
            return subCommand.getHint(sender, subArgs)
        }

        return emptyList()
    }

    private fun sendUsageMessage(sender: CommandSender) {
        val usageMessage = LobbyMusic.TITLE
        subcommands.forEach { (name, subcommand) ->
            if (subcommand.permission == null || sender.hasPermission(subcommand.permission)) {
                usageMessage.append(
                    LinearComponents.linear(
                        Component.text("• /$name ", NamedTextColor.WHITE, TextDecoration.BOLD),
                        Component.text("${subcommand.args.joinToString(" ")} ", NamedTextColor.WHITE),
                    ),
                )
            }
        }

        sender.sendMessage(usageMessage)
    }
}
