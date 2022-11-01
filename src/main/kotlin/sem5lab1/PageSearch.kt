package sem5lab1

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.awt.Desktop
import java.net.URI
import java.net.URL
import java.net.URLEncoder

class PageSearch(private val request: String) {
    private var results: MutableList<OnePage> = mutableListOf()

    init {
        try {
            results = getResults(
                URL(
                    "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch="
                            + URLEncoder.encode(request, "UTF-8")
                ).readText()
            ).toMutableList()
        } catch (e: Exception) {
            println("ERROR (check your Internet connection)")
        }

    }

    private fun getResults(jsonString: String): List<OnePage> {
        val newResults: MutableList<OnePage> = mutableListOf()

        val jsonArray = Gson()
            .fromJson(jsonString, JsonObject::class.java)
            .getAsJsonObject("query")
            .getAsJsonArray("search")

        jsonArray.forEach {
            newResults.add(
                OnePage(
                    it.asJsonObject.getAsJsonPrimitive("title").toString(),
                    it.asJsonObject.getAsJsonPrimitive("pageid").toString()
                )
            )
        }

        return newResults
    }

    fun print() {
        try {
            if (results.isNotEmpty()) {
                for (i in results.indices) {
                    println("$i) ${results[i]}")
                }

                println("Choose page number to open")
                val id = readln().toInt()
                open(id)
            } else
                println("No results")
        } catch (e: java.lang.Exception) {
            println(e.message)
        } catch (e: java.lang.NumberFormatException) {
            println("Enter a number!")
        } catch (e: java.lang.IndexOutOfBoundsException) {
            println("Enter a valid page ID!")
        }
    }

    private fun open(id: Int) {
        val u = URI("https://ru.wikipedia.org/w/index.php?curid=" + results[id].pageId)
        val desktop = Desktop.getDesktop()
        desktop.browse(u)
    }
}