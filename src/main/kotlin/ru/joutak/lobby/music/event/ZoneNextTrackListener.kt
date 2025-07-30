package ru.joutak.lobby.music.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import ru.joutak.lobby.music.event.zone.ZoneNextTrackEvent

object ZoneNextTrackListener : Listener {
    @EventHandler
    fun onZoneNextTrack(event: ZoneNextTrackEvent) {
        val zone = event.zone

        zone.stop()
        zone.play()
    }
}
