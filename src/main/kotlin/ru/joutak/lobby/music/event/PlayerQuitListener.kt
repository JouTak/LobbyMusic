package ru.joutak.lobby.music.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import ru.joutak.lobby.music.zone.ZoneManager

object PlayerQuitListener : Listener {
    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        ZoneManager
            .getMusicZones()
            .values
            .forEach { zone -> zone.removeListener(event.player) }
    }
}
