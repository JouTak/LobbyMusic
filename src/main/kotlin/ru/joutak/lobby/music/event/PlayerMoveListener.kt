package ru.joutak.lobby.music.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import ru.joutak.lobby.music.zone.ZoneManager

object PlayerMoveListener : Listener {
    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        ZoneManager
            .getMusicZones()
            .values
            .filter { zone ->
                zone.location.world.name == event.player.world.name &&
                    zone.isInRange(event.player.location)
            }.forEach { zone ->
                zone.addListener(event.player)
            }
    }
}
