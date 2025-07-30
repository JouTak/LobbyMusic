package ru.joutak.lobby.music.zone

import ru.joutak.lobby.music.config.ConfigManager

object ZoneManager {
    private var zones: MutableMap<String, Zone> = mutableMapOf()

    fun load() {
        zones = ConfigManager.loadMusicZones() ?: mutableMapOf()
    }

    fun save() {
        ConfigManager.saveMusicZones(zones)
    }

    fun getMusicZones(): Map<String, Zone> = zones

    fun startAllZones() {
        for (zone in zones.values) {
            zone.play()
        }
    }

    fun stopAllZones() {
        for (zone in zones.values) {
            zone.stop()
        }
    }

    fun addMusicZone(
        name: String,
        zone: Zone,
    ) {
        zones[name] = zone
    }

    fun removeMusicZone(name: String) {
        zones.remove(name)
    }

    fun containsMusicZone(name: String): Boolean = zones.containsKey(name)

    fun containsMusicZone(zone: Zone): Boolean = zones.values.contains(zone)
}
