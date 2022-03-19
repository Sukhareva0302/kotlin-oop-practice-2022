package lab1

data class Address(
    val index: Int,
    val city: String,
    val street: String,
    val building: Int
)

fun parseAddresses(addresses: String): List<Address> {
    val listOfAddresses = mutableListOf<Address>()
    var addressesForChanges = addresses
    var substringForAddingIndex: String
    var substringForAddingCity: String
    var substringForAddingStreet: String
    var substringForAddingBuilding: String
    if (!addressesForChanges.endsWith(System.lineSeparator())) {
        addressesForChanges += System.lineSeparator()
    }
    while (addressesForChanges.isNotBlank()) {
        addressesForChanges = addressesForChanges.dropWhile { it != '.' }
        addressesForChanges = addressesForChanges.drop(2)
        substringForAddingIndex = addressesForChanges.substringBefore(',')
        addressesForChanges = addressesForChanges.drop(substringForAddingIndex.length + 2)
        substringForAddingCity = addressesForChanges.substringBefore(',')
        addressesForChanges = addressesForChanges.drop(substringForAddingCity.length + 5)
        substringForAddingStreet = addressesForChanges.substringBefore(',')
        addressesForChanges = addressesForChanges.drop(substringForAddingStreet.length + 4)
        substringForAddingBuilding = addressesForChanges.substringBefore(' ')
        substringForAddingBuilding = substringForAddingBuilding.dropLast(1)
        addressesForChanges = addressesForChanges.drop(substringForAddingBuilding.length)
        listOfAddresses.add(
            Address(
                substringForAddingIndex.toInt(),
                substringForAddingCity,
                substringForAddingStreet,
                substringForAddingBuilding.toInt()
            )
        )
    }
    return listOfAddresses
}

fun main() {
    val addressesUnseparated = """
    1. 3337, Город, ул.Улица, д.13
    2. 7022, Санкт-Петербург, ул.Профессора Попова, д.5
    3. 0451, Прага, ул.Зелен, д.42
    """
    println(parseAddresses(addressesUnseparated))
}