package uet.app.mysound

import com.google.gson.Gson

import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.parser.parseMixeSongContend

data class Song(
    val id: String,
    @SerializedName("album_id")
    val albumId: Int,
    val title: String,
    val length: Double,
    val track: Int,
    val disc: Int,
    val lyrics: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("artist_id")
    val artistId: Int,
    val year: String?,
    val genre: String
)

data class Settings(
    @SerializedName("media_path")
    val mediaPath: String
)

data class Interaction(
    @SerializedName("song_id")
    val songId: String,
    val liked: Boolean,
    @SerializedName("play_count")
    val playCount: Int
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("is_admin")
    val isAdmin: Boolean,
    val preferences: Preferences,
    val avatar: String
)

data class Preferences(
    @SerializedName("lastfm_session_key")
    val lastfmSessionKey: String?
)

data class MySoundData(
    val albums: List<Album>,
    val artists: List<Artist>,
    val songs: List<Song>,
    val settings: Settings,
    val playlists: List<String>,
    val interactions: List<Interaction>,
    @SerializedName("recentlyPlayed")
    val recentlyPlayed: List<String>,
    val users: List<User>,
    @SerializedName("currentUser")
    val currentUser: User
)

data class Album(
    val id: Int,
    @SerializedName("artist_id")
    val artistId: Int,
    val name: String,
    val cover: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("is_compilation")
    val isCompilation: Boolean
)

data class Artist(
    val id: Int,
    val name: String,
    val image: String?
)


fun parseMixedContentFromMySound(jsonData: String?): MySoundData? {
    if (jsonData != null) {
        val gson = Gson()
        return gson.fromJson(jsonData, MySoundData::class.java)
    }
    return null
}

fun main() {
    val jsonString = """
{
    "songs": [
        {
            "id": "6e54d9f2-4b58-4a1a-8c56-a38f97323ae1",
            "title": "Rhymastic  Yêu 5 Remix",
            "length": 208.01,
            "track": 0,
            "disc": 1,
            "lyrics": "",
            "created_at": "2023-12-24T07:41:47.000000Z",
            "year": null,
            "genre": "",
            "artist": {
                "id": 1,
                "name": "Unknown Artist",
                "image": null
            },
            "album": {
                "id": 1,
                "artist_id": 1,
                "name": "Unknown Album",
                "cover": null,
                "created_at": "2023-12-24T07:32:14.000000Z",
                "is_compilation": false
            }
        },
        {
            "id": "a677bbad-9d93-4147-a84d-daee203863b1",
            "title": "Yêu Đừng Sợ Đau  Ngô Lan HươngCukak Remix Audio Lyrics Video",
            "length": 233.09,
            "track": 0,
            "disc": 1,
            "lyrics": "",
            "created_at": "2023-12-24T07:41:47.000000Z",
            "year": null,
            "genre": "",
            "artist": {
                "id": 1,
                "name": "Unknown Artist",
                "image": null
            },
            "album": {
                "id": 1,
                "artist_id": 1,
                "name": "Unknown Album",
                "cover": null,
                "created_at": "2023-12-24T07:32:14.000000Z",
                "is_compilation": false
            }
        },
        {
            "id": "b7a6fbb3-8493-40d0-a54a-2bed9a1184a3",
            "title": "Official MV Gấp Đôi Yêu Thương  Tuấn Hưng",
            "length": 302.34,
            "track": 0,
            "disc": 1,
            "lyrics": "",
            "created_at": "2023-12-24T07:41:48.000000Z",
            "year": null,
            "genre": "",
            "artist": {
                "id": 1,
                "name": "Unknown Artist",
                "image": null
            },
            "album": {
                "id": 1,
                "artist_id": 1,
                "name": "Unknown Album",
                "cover": null,
                "created_at": "2023-12-24T07:32:14.000000Z",
                "is_compilation": false
            }
        },
        {
            "id": "f139cc54-b81d-4c94-b647-005eb6f536dc",
            "title": "Bật Tình Yêu Lên  Hòa Minzy x Tăng Duy Tân  MV Lyrics",
            "length": 219.56,
            "track": 0,
            "disc": 1,
            "lyrics": "",
            "created_at": "2023-12-24T07:41:47.000000Z",
            "year": null,
            "genre": "",
            "artist": {
                "id": 1,
                "name": "Unknown Artist",
                "image": null
            },
            "album": {
                "id": 1,
                "artist_id": 1,
                "name": "Unknown Album",
                "cover": null,
                "created_at": "2023-12-24T07:32:14.000000Z",
                "is_compilation": false
            }
        }
    ]
}
"""

    val mySoundData = parseMixeSongContend(jsonString)
    println(mySoundData);
}
