package src.main.kotlin.ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is User) return false
        return other.city == city && other.age == age && other.name == name
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "Kate")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
    user1.equals(user3)
}