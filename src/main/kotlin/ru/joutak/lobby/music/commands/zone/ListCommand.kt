package ru.joutak.lobby.music.commands.zone

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.zone.ZoneManager
import kotlin.collections.List

object ListCommand : PluginCommand("list", emptyList(), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        if (ZoneManager.getMusicZones().isEmpty()) {
            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Отсутствуют", NamedTextColor.RED, TextDecoration.BOLD),
                    Component.text(" зоны для проигрывания музыки."),
                ),
            )
        } else {
            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Зоны для проигрывания музыки:\n", NamedTextColor.GREEN, TextDecoration.BOLD),
                    Component.text(
                        ZoneManager
                            .getMusicZones()
                            .keys
                            .sortedDescending()
                            .joinToString("\n"),
                    ),
                ),
            )
        }

        return true
    }

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = emptyList()
}
