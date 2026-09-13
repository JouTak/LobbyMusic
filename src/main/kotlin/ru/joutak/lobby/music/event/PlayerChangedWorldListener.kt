package ru.joutak.lobby.music.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import ru.joutak.lobby.music.zone.ZoneManager

object PlayerChangedWorldListener : Listener {
    @EventHandler
    fun onPlayerChangedWorld(event: PlayerChangedWorldEvent) {
        ZoneManager
            .getMusicZones()
            .values
            .filter { zone -> zone.location.world.name == event.from.name }
            .forEach { zone -> zone.removeListener(event.player) }
    }
}
