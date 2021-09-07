package src.main.kotlin.ru.sber.oop

open class Room(val name: String, val size: Int) {
    var monster: Monster = Goblin("hit", 120)

    constructor(_name: String) : this(name = _name, size = 100)

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = "Nothing much to see here..."
}

class TownSquare() : Room("Town Square", 1000) {
    override val dangerLevel = super.dangerLevel - 3

    final override fun load(): String {
        return monster.getSalutation()
    }
}
