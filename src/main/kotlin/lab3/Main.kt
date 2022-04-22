package lab3

fun main() {
    val service = Service()
    val phone = Contact.Phone("+7123456789", PhoneType.Mobile)
    service.addContact(Person("Abc", "Def"), phone)
    service.addPhone(Person("Aaadds", "GdJkf"), "4782637", PhoneType.Home)
    service.addEmail(Person("Abc", "Def"), "abc@der.ee")
    service.addPhone(Person("Abc", "Def"), "4278904", PhoneType.Work)
    service.addAddress(Person("Aaadds", "GdJkf"), "Aaa", "Bbbbb", 4, 2)
    service.addLink(Person("Ddd", "Ff"), "PeopleEe", "ppl.com/123p")
    service.addContact(Person("Gdd", "Ff"), Contact.Link("PeopleEe", "ppl.com/123b"))
    println(service.getAllContacts())
    println(service.getPersonContacts(Person("Abc", "Def")))
    service.removeContact(Person("Abc", "Def"), phone)
    println(service.getPersonContacts(Person("Abc", "Def")))
    println(service.findPerson("Gd"))
    service.removePerson(Person("Aaadds", "GdJkf"))
    println(service.getAllContacts())

}