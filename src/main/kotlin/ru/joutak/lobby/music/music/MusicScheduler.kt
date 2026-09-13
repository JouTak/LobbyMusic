package ru.joutak.lobby.music.music

class MusicScheduler {
    companion object {
        val playingMusic: MutableSet<Music> = hashSetOf()

        fun addPlayingMusic(music: Music) = playingMusic.add(music)

        fun removePlayingMusic(music: Music) = playingMusic.remove(music)
    }

    private val playedMusic: MutableSet<Music> = hashSetOf()

    fun getNextMusic(): Music {
        if (playedMusic.size == MusicManager.getPlaylist().size) {
            playedMusic.clear()
        }

        try {
            val nextMusic =
                MusicManager
                    .getPlaylist()
                    .subtract(playedMusic)
                    .subtract(playingMusic)
                    .random()
            playedMusic.add(nextMusic)
            return nextMusic
        } catch (e: NoSuchElementException) {
            return MusicManager.getPlaylist().random()
        }
    }
}
