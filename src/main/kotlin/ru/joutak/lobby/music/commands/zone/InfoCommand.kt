package ru.joutak.lobby.music.commands.zone

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.zone.ZoneManager

object InfoCommand : PluginCommand("info", listOf("name"), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        if (args.size != this.args.size) {
            return false
        }

        if (ZoneManager.containsMusicZone(args[0])) {
            val zone = ZoneManager.getMusicZones()[args[0]]!!
            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Информация о зоне "),
                    Component.text(args[0], NamedTextColor.GOLD, TextDecoration.BOLD),
                    Component.text(":\n"),
                    Component.text("- Расположение: ", NamedTextColor.GOLD, TextDecoration.BOLD),
                    Component.text("${zone.location.world.name} (${zone.location.x}, ${zone.location.y}, ${zone.location.z})\n"),
                    Component.text("- Радиус: ", NamedTextColor.GOLD, TextDecoration.BOLD),
                    Component.text("${zone.range}\n"),
                    Component.text("- Текущая музыка: ", NamedTextColor.GOLD, TextDecoration.BOLD),
                    if (zone.getCurrentMusic() != null) {
                        Component.text(zone.getCurrentMusic()!!.asAuthorTitleString)
                    } else {
                        Component.text("-")
                    },
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
    }

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> = if (args.size == 1) ZoneManager.getMusicZones().keys.toList() else emptyList()
}
