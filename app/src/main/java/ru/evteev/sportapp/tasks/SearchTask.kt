package ru.evteev.sportapp.tasks

import android.os.AsyncTask
import android.webkit.WebView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

public class SearchTask(private val webView: WebView) : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String): String? {
        val query: String = params[0]
        val url = URL("https://nova.rambler.ru/search?query=$query")
        val connection = url.openConnection() as HttpURLConnection
        try {
            val responseCode = connection.responseCode;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Get the input stream from the connection
                val inputStream = connection.inputStream

                // Read the HTML content
                val reader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append('\n')
                }

                // Close the resources
                reader.close()
                inputStream.close()
                connection.disconnect()

                // Return the HTML content
                return stringBuilder.toString()
            } else {
                // Handle HTTP response code other than OK
                return "HTTP response code: $responseCode"
            }
            return connection.inputStream.bufferedReader().readText()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override fun onPostExecute(result: String?) {
        result?.let {
            webView.loadData(it, "text/html", "UTF-8")
        } ?: run {

        }
    }
}