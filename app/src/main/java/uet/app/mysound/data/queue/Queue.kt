package uet.app.mysound.data.queue

import uet.app.mysound.data.model.browse.album.Track

object Queue {
    private var queue: ArrayList<Track> = ArrayList()
    private var recently: ArrayList<Track> = ArrayList()
    private var nowPlaying: Track? = null
    fun add(song: Track) {
        queue.add(song)
    }
    fun addAll(songs: Collection<Track>) {
        queue.addAll(songs)
    }
    fun getQueue(): ArrayList<Track> {
        return queue
    }
    fun clear() {
        queue.clear()
    }
    fun setNowPlaying(song: Track) {
        nowPlaying = song
    }
    fun getNowPlaying(): Track? {
        return nowPlaying
    }
    fun getTrack(index: Int): Track {
        recently.add(queue.elementAt(index))
        nowPlaying = queue.elementAt(index)
        for (i in index downTo 0) {
            queue.removeAt(i)
        }
        return nowPlaying!!
    }
    fun removeFirstTrackForPlaylistAndAlbum() {
        queue.removeAt(0)
    }
    fun removeTrackWithIndex(index: Int) {
        queue.removeAt(index)
    }
}