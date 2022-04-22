package lab3

class Service : ContactsService {
    private val allPersons = mutableMapOf<Person, MutableList<Contact>>()
    override fun addContact(person: Person, contact: Contact) {
        if (allPersons.contains(person))
            allPersons[person]?.add(contact)
        else {
            allPersons[person] = mutableListOf()
            allPersons[person]!!.add(contact)
        }

    }

    override fun removeContact(person: Person, contact: Contact) {
        allPersons[person]!!.remove(contact)
    }

    override fun removePerson(person: Person) {
        allPersons.remove(person)
    }

    override fun addPhone(person: Person, phone: String, phoneType: PhoneType) {
        if (allPersons.contains(person))
            allPersons[person]?.add(Contact.Phone(phone, phoneType))
        else
            allPersons[person] = mutableListOf(Contact.Phone(phone, phoneType))
    }

    override fun addEmail(person: Person, email: String) {
        if (allPersons.contains(person))
            allPersons[person]?.add(Contact.Email(email))
        else
            allPersons[person] = mutableListOf(Contact.Email(email))
    }

    override fun addLink(person: Person, nameOfMedia: String, url: String) {
        if (allPersons.contains(person))
            allPersons[person]?.add(Contact.Link(nameOfMedia, url))
        else
            allPersons[person] = mutableListOf(Contact.Link(nameOfMedia, url))
    }

    override fun addAddress(person: Person, city: String, street: String, building: Int, apartment: Int) {
        if (allPersons.contains(person))
            allPersons[person]?.add(Contact.Address(city, street, building, apartment))
        else
            allPersons[person] = mutableListOf(Contact.Address(city, street, building, apartment))
    }

    override fun getPersonContacts(person: Person): List<Contact> {
        return if (allPersons.contains(person))
            allPersons[person]!!.toList()
        else
            emptyList()
    }

    override fun getPersonPhones(person: Person): List<Contact.Phone> {
        return if (allPersons.contains(person))
            allPersons[person]!!.filterIsInstance<Contact.Phone>().ifEmpty { emptyList() }
        else
            emptyList()
    }

    override fun getPersonEmails(person: Person): List<Contact.Email> {
        return if (allPersons.contains(person))
            allPersons[person]!!.filterIsInstance<Contact.Email>().ifEmpty { emptyList() }
        else
            emptyList()
    }

    override fun getPersonLinks(person: Person): List<Contact.Link> {
        return if (allPersons.contains(person))
            allPersons[person]!!.filterIsInstance<Contact.Link>().ifEmpty { emptyList() }
        else
            emptyList()
    }

    override fun getPersonAddresses(person: Person): List<Contact.Address> {
        return if (allPersons.contains(person))
            allPersons[person]!!.filterIsInstance<Contact.Address>().ifEmpty { emptyList() }
        else
            emptyList()
    }

    override fun getAllPersons(): List<Person> {
        return allPersons.keys.toList()
    }

    override fun getAllContacts(): Map<Person, List<Contact>> {
        for ((person) in allPersons) {
            allPersons[person]!!.toList()
        }
        return allPersons.toMap()
    }

    override fun findPerson(substring: String): List<Person> {
        val result = mutableListOf<Person>()
        for ((person) in allPersons) {
            if (person.firstName.contains(substring) || person.lastName.contains(substring))
                result.add(person)
        }
        return result
    }
}

interface ContactsService {
    fun addContact(person: Person, contact: Contact)
    fun removeContact(person: Person, contact: Contact)
    fun removePerson(person: Person)

    fun addPhone(person: Person, phone: String, phoneType: PhoneType)
    fun addEmail(person: Person, email: String)
    fun addLink(person: Person, nameOfMedia: String, url: String)
    fun addAddress(person: Person, city: String, street: String, building: Int, apartment: Int)

    fun getPersonContacts(person: Person): List<Contact>
    fun getPersonPhones(person: Person): List<Contact.Phone>
    fun getPersonEmails(person: Person): List<Contact.Email>
    fun getPersonLinks(person: Person): List<Contact.Link>
    fun getPersonAddresses(person: Person): List<Contact.Address>

    fun getAllPersons(): List<Person>
    fun getAllContacts(): Map<Person, List<Contact>>

    fun findPerson(substring: String): List<Person>
}
