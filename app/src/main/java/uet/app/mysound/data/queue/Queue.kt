package uet.app.mysound.data.queue

import uet.app.mysound.data.model.metadata.MetadataSong
import java.util.LinkedList
import java.util.Queue

object Queue {
    private var queue: Queue<MetadataSong> = LinkedList()
    fun add(song: MetadataSong) {
        queue.add(song)
    }

    fun addAll(songs: List<MetadataSong>) {
        queue.addAll(songs)
    }

    fun remove(song: MetadataSong) {
        queue.remove(song)
    }

    fun peek(): MetadataSong? {
        return queue.remove()
    }

    fun getQueue(): Queue<MetadataSong> {
        return queue
    }
}