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

abstract class PluginCommandExecutor(
    val name: String,
    val permission: String? = null,
) : CommandExecutor,
    TabExecutor {
    protected val subcommands: SortedMap<String, PluginCommand> = TreeMap()
    protected val subexecutors: SortedMap<String, PluginCommandExecutor> = TreeMap()

    protected fun registerCommand(command: PluginCommand) {
        subcommands[command.name] = command
    }

    protected fun registerExecutor(executor: PluginCommandExecutor) {
        subexecutors[executor.name] = executor
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

        val subexecutor = subexecutors[subcommandName]

        val subArgs = if (args.size > 1) args.sliceArray(1 until args.size) else emptyArray()

        if (subexecutor != null) {
            if (!subexecutor.onCommand(sender, command, label, subArgs)) {
                sendUsageMessage(sender)
            }
            return true
        }

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
                .plus(
                    subexecutors.keys
                        .filter { sender.hasPermission(subexecutors[it]?.permission ?: return@filter true) }
                        .filter { it.startsWith(args[0].lowercase()) },
                ).sorted()
        }

        if (args.size > 1) {
            val subCommandName = args[0].lowercase()

            if (subcommands[subCommandName] == null && subexecutors[subCommandName] == null) {
                return emptyList()
            }

            if (subcommands[subCommandName] != null) {
                val subCommand = subcommands[subCommandName]!!

                if (subCommand.permission != null && !sender.hasPermission(subCommand.permission)) {
                    return emptyList()
                }

                val subArgs = args.sliceArray(1 until args.size)
                return subCommand.tabComplete(sender, subArgs)
            } else {
                val subExecutor = subexecutors[subCommandName]!!

                if (subExecutor.permission != null && !sender.hasPermission(subExecutor.permission)) {
                    return emptyList()
                }

                val subArgs = args.sliceArray(1 until args.size)
                return subExecutor.onTabComplete(sender, command, alias, subArgs)
            }
        }

        return emptyList()
    }

    protected fun sendUsageMessage(sender: CommandSender) {
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
