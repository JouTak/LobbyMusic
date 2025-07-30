package ru.joutak.lobby.music.commands.music

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.CommandSender
import ru.joutak.lobby.music.commands.PluginCommand
import ru.joutak.lobby.music.music.Music
import ru.joutak.lobby.music.music.MusicManager

object ListCommand : PluginCommand("list", emptyList(), "", null) {
    override fun execute(
        sender: CommandSender,
        args: Array<out String>,
    ): Boolean {
        if (MusicManager.getPlaylist().isEmpty()) {
            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Отсутствует", NamedTextColor.RED, TextDecoration.BOLD),
                    Component.text(" музыка для проигрывания."),
                ),
            )
        } else {
            sender.sendMessage(
                LinearComponents.linear(
                    Component.text("Доступная музыка:\n", NamedTextColor.GREEN, TextDecoration.BOLD),
                    Component.text(
                        MusicManager
                            .getPlaylist()
                            .sortedWith(Music.defaultComparator)
                            .joinToString("\n") { music -> music.asAuthorTitleString },
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
