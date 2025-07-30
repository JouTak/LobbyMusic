package ru.joutak.lobby.music.commands.zone

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.zone.Zone
import ru.joutak.lobby.music.zone.ZoneManager

object AddCommand : PluginCommand("add", listOf("name", "x", "y", "z", "range"), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        if (args.size != this.args.size) {
            return false
        }

        if (sender !is Player) {
            sender.sendMessage(Component.text("Данную команду можно использовать только в игре!", NamedTextColor.RED))
            return true
        }

        try {
            if (ZoneManager.containsMusicZone(args[0])) {
                sender.sendMessage(Component.text("Зона с таким именем уже существует!", NamedTextColor.RED))
                return false
            }

            val zone = Zone(Location(sender.world, args[1].toDouble(), args[2].toDouble(), args[3].toDouble()), args[4].toInt())

            if (ZoneManager.containsMusicZone(zone)) {
                sender.sendMessage(Component.text("Зона с такими параметрами уже существует!", NamedTextColor.RED))
                return false
            }

            ZoneManager.addMusicZone(args[0], zone)

            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Добавлена зона для проигрывания музыки "),
                    Component.text(args[0], NamedTextColor.GREEN),
                    Component.text("."),
                ),
            )
        } catch (e: NumberFormatException) {
            sender.sendMessage(Component.text("Координаты и радиус зоны должны быть числами.", NamedTextColor.RED))
        } catch (e: IllegalArgumentException) {
            sender.sendMessage(Component.text("${e.message}", NamedTextColor.RED))
        }

        return true
    }

    override fun tabComplete(
        sender: CommandSender,
        args: Array<out String>,
    ): List<String> {
        if (sender !is Player) {
            return emptyList()
        }

        return when (args.size) {
            2 -> {
                listOf(
                    sender.location.x
                        .toInt()
                        .toString(),
                )
            }

            3 -> {
                listOf(
                    sender.location.y
                        .toInt()
                        .toString(),
                )
            }

            4 -> {
                listOf(
                    sender.location.z
                        .toInt()
                        .toString(),
                )
            }

            5 -> {
                listOf("16", "32", "64")
            }

            else -> emptyList()
        }
    }
}
