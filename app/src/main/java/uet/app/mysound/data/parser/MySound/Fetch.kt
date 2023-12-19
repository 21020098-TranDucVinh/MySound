package uet.app.mysound.data.parser.MySound
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun fetchDataFromUrl(urlString: String): String? {
    var connection: HttpURLConnection? = null
    var reader: BufferedReader? = null
    var result: String? = null

    try {
        val url = URL(urlString)
        connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        // Đọc dữ liệu từ InputStream
        val inputStream = connection.inputStream
        reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }

        result = stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Đóng các resource sau khi sử dụng
        reader?.close()
        connection?.disconnect()
    }

    return result
}

fun main() {
}
