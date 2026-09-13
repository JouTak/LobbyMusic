package ru.joutak.lobby.music.event.zone

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import ru.joutak.lobby.music.zone.Zone

class PlayerLeaveZoneEvent(
    val player: Player,
    val zone: Zone,
) : Event() {
    companion object {
        @JvmStatic
        private val handlersList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = handlersList
    }

    override fun getHandlers(): HandlerList = handlersList
}
