package dictionary

fun main() {
    val phoneDictionary = mutableMapOf(
        "Alex" to "+79101234567",
        "Mihail" to "+79109876543"
    )

    print("Enter your contact name: ")
    val contactName = readln()
    print("Enter your contact phone number: ")
    val contactPhoneNumber = readln()
    phoneDictionary[contactName] = contactPhoneNumber

    findContact(phoneDictionary)
}

private fun findContact(contacts: MutableMap<String, String>) {
    while (true) {
        print("Enter your contact name or 0 to exit: ")
        val contactName = readln()
        if (contactName == "0") break
        println(contacts[contactName] ?: "Contact not found")
    }
}