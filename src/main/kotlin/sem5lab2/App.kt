package sem5lab2

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.w3c.dom.Node
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.xml.parsers.DocumentBuilderFactory


class App(file: File) {

    private data class Address(val city: String, val street: String, val house: Int, val floor: Int)
    private data class Floors(
        var first: Int = 0,
        var second: Int = 0,
        var third: Int = 0,
        var fourth: Int = 0,
        var fifth: Int = 0,
    )

    private var mapAddress = HashMap<Address, Int>()
    private var mapCity = HashMap<String, Floors>()

    init {
        when (file.toString().substring(file.toString().length - 3)) {
            "xml" -> xmlParser(file)
            "csv" -> csvParser(file)
        }
    }

    private fun csvParser(file: File) {
        val reader = Files.newBufferedReader(Paths.get(file.toString()))
        val csvParser = CSVParser(
            reader, CSVFormat.DEFAULT
                .withDelimiter(';')
                .withHeader("city", "street", "house", "floor")
                .withFirstRecordAsHeader()
                .withTrim()
        )
        for (csvRecord in csvParser) {
            val address =
                Address(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2).toInt(), csvRecord.get(3).toInt())
            if (mapAddress.containsKey(address)) {
                mapAddress[address] = mapAddress.getValue(address) + 1
            } else {
                mapAddress[address] = 1
            }
        }
        resultCount()
    }

    private fun resultCount() {
        mapAddress.forEach { entry ->
            if (entry.value > 1)
                println("${entry.key} repeats ${entry.value} times")
        }
        mapAddress.forEach { entry ->
            if (!mapCity.containsKey(entry.key.city))
                mapCity[entry.key.city] = Floors()
            when (entry.key.floor) {
                1 -> {
                    mapCity[entry.key.city]!!.first++
                }
                2 -> {
                    mapCity[entry.key.city]!!.second++
                }
                3 -> {
                    mapCity[entry.key.city]!!.third++
                }
                4 -> {
                    mapCity[entry.key.city]!!.fourth++
                }
                5 -> {
                    mapCity[entry.key.city]!!.fifth++
                }
            }
        }
        mapCity.forEach { entry ->
            println("in ${entry.key} are: first floors:${entry.value.first}, second floors:${entry.value.second}, third floors:${entry.value.third}, fourth floors:${entry.value.fourth}, fifth floors:${entry.value.fifth}")
        }
    }

    private fun xmlParser(file: File) {
        val nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).firstChild.childNodes
        for (i in 0 until nodeList.length) {
            if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) {
                continue
            }
            val address = Address(
                nodeList.item(i).attributes.getNamedItem("city").textContent,
                nodeList.item(i).attributes.getNamedItem("street").textContent,
                nodeList.item(i).attributes.getNamedItem("house").textContent.toInt(),
                nodeList.item(i).attributes.getNamedItem("floor").textContent.toInt()
            )
            if (mapAddress.containsKey(address)) {
                mapAddress[address] = mapAddress.getValue(address) + 1
            } else {
                mapAddress[address] = 1
            }
        }
        resultCount()
    }
}
