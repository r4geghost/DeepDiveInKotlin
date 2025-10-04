package delegates

fun main() {
    val zombie = Zombie("Max")
    val human = Human("John")
    zombie.move()
    zombie.fight()
    human.move()
    human.fight()

    val premiumZombie = PremiumPlayer(zombie)
    premiumZombie.move()
    premiumZombie.fight()
    premiumZombie.callForHelp()
}

interface Player {
    fun move()
    fun fight()
}

data class Zombie(val userName: String) : Player {
    override fun move() {
        println("I'm walking very slowly...")
    }

    override fun fight() {
        println("I'm eating people...")
    }
}

data class Human(val userName: String) : Player {
    override fun move() {
        println("I'm running very fast...")
    }

    override fun fight() {
        println("I'm shooting with a gun...")
    }
}

// упрощенная версия паттерна декоратор
data class PremiumPlayer(val player: Player): Player {
    override fun move() {
        player.move()
    }

    override fun fight() {
        player.fight()
    }

    fun callForHelp() {
        println("HELP ME!!!")
    }
}