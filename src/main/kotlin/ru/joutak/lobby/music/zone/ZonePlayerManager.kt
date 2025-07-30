package ru.joutak.lobby.music.zone

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import ru.joutak.lobby.music.event.zone.PlayerJoinZoneEvent
import ru.joutak.lobby.music.event.zone.PlayerLeaveZoneEvent

class ZonePlayerManager(
    private val zone: Zone,
) {
    private val listeners: MutableSet<Player> = hashSetOf()

    fun getListeners(): Set<Player> = listeners

    fun add(player: Player) {
        if (listeners.contains(player) || !zone.isMusicPlaying()) return

        listeners.add(player)
        Bukkit.getPluginManager().callEvent(PlayerJoinZoneEvent(player, zone))
    }

    fun remove(player: Player) {
        if (!listeners.contains(player)) return

        listeners.remove(player)
        Bukkit.getPluginManager().callEvent(PlayerLeaveZoneEvent(player, zone))
    }
}
