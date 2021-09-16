package src.main.kotlin.ru.sber.oop

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = (0..20).random()

    fun attack(opponent: Fightable): Int
}

class Player(
    override val powerType: String, override var healthPoints: Int,
    var name: String, var isBlessed: Boolean
) : Fightable {

    override fun attack(opponent: Fightable): Int {
        var sumDamage = damageRoll
        if (!isBlessed) {
            opponent.healthPoints -= sumDamage
        } else {
            sumDamage = damageRoll * 2
            opponent.healthPoints -= sumDamage
        }
        return sumDamage
    }
}

    abstract class Monster(var name: String, var description: String) : Fightable {

        override fun attack(opponent: Fightable): Int {
            val sumDamage = damageRoll
            opponent.healthPoints -= damageRoll
            return sumDamage
        }
    }

    class Goblin(override val powerType: String, override var healthPoints: Int) :
        Monster("Goblin", "This is a goblin") {
        override val damageRoll: Int
            get() = super.damageRoll / 2
    }

    fun Monster.getSalutation(): String {
        return "Hello,  $this.name"
    }