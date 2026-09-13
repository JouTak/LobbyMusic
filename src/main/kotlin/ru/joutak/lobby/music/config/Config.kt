package ru.joutak.lobby.music.config

import com.sksamuel.hoplite.ConfigAlias

data class Config(
    @ConfigAlias("START_DELAY") val startDelay: Int,
) {
    init {
        require(startDelay >= 0) {
            "START_DELAY должен быть больше или равен 0, но был $startDelay"
        }
    }

    companion object {
        val default = Config(0)
    }
}
