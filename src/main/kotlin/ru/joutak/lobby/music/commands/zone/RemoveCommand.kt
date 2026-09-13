package ru.joutak.lobby.music.commands.zone

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.zone.ZoneManager

object RemoveCommand : PluginCommand("remove", listOf("name"), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        if (args.size != this.args.size) {
            return false
        }

        try {
            if (ZoneManager.containsMusicZone(args[0])) {
                ZoneManager.getMusicZones()[args[0]]!!.stop()
                ZoneManager.removeMusicZone(args[0])
                sender.sendMessage(
                    LinearComponents.linear(
                        Component.text("Зона "),
                        Component.text(args[0], NamedTextColor.GOLD, TextDecoration.BOLD),
                        Component.text(" была успешно "),
                        Component.text("удалена!", NamedTextColor.RED),
                    ),
                )
            } else {
                sender.sendMessage(
                    LinearComponents.linear(
                        Component.text("Отсутствует", NamedTextColor.RED, TextDecoration.BOLD),
                        Component.text(" зона для проигрывания музыки с таким именем."),
                    ),
                )
            }
            return true
        } catch (e: IllegalArgumentException) {
            sender.sendMessage(Component.text("${e.message}", NamedTextColor.RED))
        }

        return false
    }

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = if (args.size == 1) ZoneManager.getMusicZones().keys.toList() else emptyList()
}
