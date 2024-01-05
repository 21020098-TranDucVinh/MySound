package uet.app.mysound.data.parser.MyDB

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MyAlbumBrowse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("is_compilation")
    val isCompilation: Boolean,
    @SerializedName("songs")
    val songs: List<Song>,  // Thay đổi tên từ "songs" thành "tracks"
    @SerializedName("artist")
    val artist: Artist
)


data class Artist(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?
)

data class Song(
    @SerializedName("id")
    val id: String,
    @SerializedName("album_id")
    val albumId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("length")
    val length: Double,
    @SerializedName("track")
    val track: Int,
    @SerializedName("disc")
    val disc: Int,
    @SerializedName("lyrics")
    val lyrics: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("artist_id")
    val artistId: Int,
    @SerializedName("year")
    val year: String?,
    @SerializedName("genre")
    val genre: String?
)

fun parseMyAlbumBrowseJson(jsonString: String): MyAlbumBrowse {
    return Gson().fromJson(jsonString, MyAlbumBrowse::class.java)
}

fun main() {
    val jsonString = """
        {
            "id": 1,
            "name": "MySound Albums",
            "cover": null,
            "created_at": "2023-12-19T05:00:29.000000Z",
            "is_compilation": false,
            "songs": [
                {
                    "id": "055e4c5c-f17e-4cfa-9208-194177a86b05",
                    "album_id": 1,
                    "title": "bai3",
                    "length": 208.01,
                    "track": 0,
                    "disc": 1,
                    "lyrics": "",
                    "created_at": "2023-12-19T05:01:27.000000Z",
                    "artist_id": 1,
                    "year": null,
                    "genre": ""
                },
                {
                    "id": "c42b0003-f8c3-4301-8bb6-253910f4a35b",
                    "album_id": 1,
                    "title": "bai2",
                    "length": 192.29,
                    "track": 0,
                    "disc": 1,
                    "lyrics": "",
                    "created_at": "2023-12-19T05:01:27.000000Z",
                    "artist_id": 1,
                    "year": null,
                    "genre": ""
                },
                {
                    "id": "dfd226f2-a76d-426c-99c6-0b7a05b3026e",
                    "album_id": 1,
                    "title": "bai1",
                    "length": 302.34,
                    "track": 0,
                    "disc": 1,
                    "lyrics": "",
                    "created_at": "2023-12-19T05:01:27.000000Z",
                    "artist_id": 1,
                    "year": null,
                    "genre": ""
                }
            ],
            "artist": {
                "id": 1,
                "name": "MYSOUND Artist",
                "image": null
            }
        }
    """.trimIndent()

    val albumBrowse = parseMyAlbumBrowseJson(jsonString)
    println(albumBrowse)
}
