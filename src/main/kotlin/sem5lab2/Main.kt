package sem5lab2

import java.io.File


fun main() {
    println("Enter path to file or 'end':")

    //"C:\Users\asukh\IdeaProjects\kotlin-oop-practice-2022\src\main\resources\address.xml"
    //"C:\Users\asukh\IdeaProjects\kotlin-oop-practice-2022\src\main\resources\address.csv"
    var file = File(readln())
    while (file.toString() != "end") {
        try {
            val begin = System.nanoTime()
            App(file)
            val end = System.nanoTime()
            println("time spend: ${(end - begin) / 1000000000} seconds")
        } catch (e: Exception) {
            println("wrong file name or path")
        }
        file = File(readln())
    }
}
