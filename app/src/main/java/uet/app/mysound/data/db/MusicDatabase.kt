package uet.app.mysound.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uet.app.mysound.data.db.entities.AlbumEntity
import uet.app.mysound.data.db.entities.ArtistEntity
import uet.app.mysound.data.db.entities.FormatEntity
import uet.app.mysound.data.db.entities.LocalPlaylistEntity
import uet.app.mysound.data.db.entities.LyricsEntity
import uet.app.mysound.data.db.entities.PairSongLocalPlaylist
import uet.app.mysound.data.db.entities.PlaylistEntity
import uet.app.mysound.data.db.entities.QueueEntity
import uet.app.mysound.data.db.entities.SearchHistory
import uet.app.mysound.data.db.entities.SetVideoIdEntity
import uet.app.mysound.data.db.entities.SongEntity

@Database(entities = [SearchHistory::class, SongEntity::class, ArtistEntity::class, AlbumEntity::class, PlaylistEntity::class,
    LocalPlaylistEntity::class, LyricsEntity::class,
    FormatEntity::class, QueueEntity::class, SetVideoIdEntity::class, PairSongLocalPlaylist::class], version = 6,
    exportSchema = true,)
@TypeConverters(Converters::class)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun getDatabaseDao(): DatabaseDao
}