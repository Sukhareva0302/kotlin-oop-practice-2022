package sem5lab1

fun main() {
    println("Enter request phrase:")
    val userSearch = readln()
    val searchResult = PageSearch(userSearch)
    searchResult.print()

}