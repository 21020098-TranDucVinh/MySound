//package uet.app.mysound
//
//import com.google.gson.Gson
//
//import com.google.gson.annotations.SerializedName
//
//data class Song(
//    val id: String,
//    @SerializedName("album_id")
//    val albumId: Int,
//    val title: String,
//    val length: Double,
//    val track: Int,
//    val disc: Int,
//    val lyrics: String,
//    @SerializedName("created_at")
//    val createdAt: String,
//    @SerializedName("artist_id")
//    val artistId: Int,
//    val year: String?,
//    val genre: String
//)
//
//data class Settings(
//    @SerializedName("media_path")
//    val mediaPath: String
//)
//
//data class Interaction(
//    @SerializedName("song_id")
//    val songId: String,
//    val liked: Boolean,
//    @SerializedName("play_count")
//    val playCount: Int
//)
//
//data class User(
//    val id: Int,
//    val name: String,
//    val email: String,
//    @SerializedName("is_admin")
//    val isAdmin: Boolean,
//    val preferences: Preferences,
//    val avatar: String
//)
//
//data class Preferences(
//    @SerializedName("lastfm_session_key")
//    val lastfmSessionKey: String?
//)
//
//data class MySoundData(
//    val albums: List<Album>,
//    val artists: List<Artist>,
//    val songs: List<Song>,
//    val settings: Settings,
//    val playlists: List<String>,
//    val interactions: List<Interaction>,
//    @SerializedName("recentlyPlayed")
//    val recentlyPlayed: List<String>,
//    val users: List<User>,
//    @SerializedName("currentUser")
//    val currentUser: User
//)
//
//data class Album(
//    val id: Int,
//    @SerializedName("artist_id")
//    val artistId: Int,
//    val name: String,
//    val cover: String?,
//    @SerializedName("created_at")
//    val createdAt: String,
//    @SerializedName("is_compilation")
//    val isCompilation: Boolean
//)
//
//data class Artist(
//    val id: Int,
//    val name: String,
//    val image: String?
//)
//
//
//fun parseMixedContentFromMySound(jsonData: String?): MySoundData? {
//    if (jsonData != null) {
//        // Sử dụng Gson để phân tích chuỗi JSON thành đối tượng MySoundData
//        val gson = Gson()
//        return gson.fromJson(jsonData, MySoundData::class.java)
//    }
//    return null
//}
//
//fun main() {
//    val jsonString = """
//{
//  "albums": [
//    {
//      "id": 1,
//      "artist_id": 1,
//      "name": "Unknown Album",
//      "cover": null,
//      "created_at": "2023-12-12T15:42:14.000000Z",
//      "is_compilation": false
//    }
//  ],
//  "artists": [
//    { "id": 1, "name": "Unknown Artist", "image": null },
//    { "id": 2, "name": "Various Artists", "image": null }
//  ],
//  "songs": [
//    {
//      "id": "09d09959-bbc9-4474-84aa-7891f0153232",
//      "album_id": 1,
//      "title": "bai1",
//      "length": 302.34,
//      "track": 0,
//      "disc": 1,
//      "lyrics": "",
//      "created_at": "2023-12-18T08:48:40.000000Z",
//      "artist_id": 1,
//      "year": null,
//      "genre": ""
//    },
//    {
//      "id": "5f8bf371-b584-473d-bf36-6f7cdd53b0bc",
//      "album_id": 1,
//      "title": "bai2",
//      "length": 192.29,
//      "track": 0,
//      "disc": 1,
//      "lyrics": "",
//      "created_at": "2023-12-18T08:48:40.000000Z",
//      "artist_id": 1,
//      "year": null,
//      "genre": ""
//    },
//    {
//      "id": "facdedbe-9cac-49f8-9fb5-bf59483ee1ac",
//      "album_id": 1,
//      "title": "bai3",
//      "length": 208.01,
//      "track": 0,
//      "disc": 1,
//      "lyrics": "",
//      "created_at": "2023-12-18T08:48:40.000000Z",
//      "artist_id": 1,
//      "year": null,
//      "genre": ""
//    }
//  ],
//  "settings": { "media_path": "/home/thanhyk14/DBMUSIC" },
//  "playlists": [],
//  "interactions": [
//    {
//      "song_id": "5f8bf371-b584-473d-bf36-6f7cdd53b0bc",
//      "liked": false,
//      "play_count": 2
//    },
//    {
//      "song_id": "facdedbe-9cac-49f8-9fb5-bf59483ee1ac",
//      "liked": false,
//      "play_count": 1
//    }
//  ],
//  "recentlyPlayed": [
//    "5f8bf371-b584-473d-bf36-6f7cdd53b0bc",
//    "facdedbe-9cac-49f8-9fb5-bf59483ee1ac"
//  ],
//  "users": [
//    {
//      "id": 1,
//      "name": "Sound",
//      "email": "admin@sound.dev",
//      "is_admin": true,
//      "preferences": { "lastfm_session_key": null },
//      "avatar": "https://www.gravatar.com/avatar/f39f3b5d6e192bbc8e289deec7e004a5?s=192&d=robohash"
//    }
//  ],
//  "currentUser": {
//    "id": 1,
//    "name": "Sound",
//    "email": "admin@sound.dev",
//    "is_admin": true,
//    "preferences": { "lastfm_session_key": null },
//    "avatar": "https://www.gravatar.com/avatar/f39f3b5d6e192bbc8e289deec7e004a5?s=192&d=robohash"
//  }
//}
//"""
//
//    val mySoundData = parseMixedContentFromMySound(jsonString)
//    println(mySoundData?.albums)
//    println(mySoundData?.artists)
//    println(mySoundData?.songs)
//    println(mySoundData?.settings)
//
//    println(mySoundData?.interactions)
//    println(mySoundData?.users)
//    println(mySoundData?.currentUser)
//
//}
