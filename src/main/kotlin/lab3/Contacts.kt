package lab3

data class Person(val firstName: String, val lastName: String)

enum class PhoneType {
    Mobile, Home, Work
}

sealed class Contact {
    class Phone(val phoneNum: String, val phoneType: PhoneType) : Contact() {
        override fun toString(): String {
            return "number: $phoneNum, type: $phoneType"
        }
    }

    class Email(val emailAddress: String) : Contact(){
        override fun toString(): String {
            return "email: $emailAddress"
        }
    }

    class Address(val city: String, val street: String, val building: Int, val apartment: Int) : Contact(){
        override fun toString(): String {
            return "address: $city, $street, $building, $apartment"
        }
    }

    class Link(val nameOfMedia: String, val url: String) : Contact(){
        override fun toString(): String {
            return "name of media: $nameOfMedia, url: $url"
        }
    }

}