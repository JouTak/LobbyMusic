package ru.joutak.lobby.music.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.LinearComponents
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import ru.joutak.lobby.music.event.zone.PlayerJoinZoneEvent
import ru.joutak.lobby.music.utils.PluginManager

object PlayerJoinZoneListener : org.bukkit.event.Listener {
    @EventHandler
    fun onPlayerJoinZone(event: PlayerJoinZoneEvent) {
        event.zone.playFor(event.player)
    }
}
