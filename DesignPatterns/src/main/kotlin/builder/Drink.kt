package builder

data class Drink(
    val type: String,
    val additives: List<String>,
    val diningOption: String,
    val temperature: String
) {
    class Builder {
        private var type: String = "Coffee"
        private var additives: List<String> = listOf()
        private var diningOption: String = "In cafe"
        private var temperature: String = "Hot"

        fun type(type: String): Builder = this.apply { this.type = type }
        fun additives(additives: List<String>): Builder = this.apply { this.additives = additives }
        fun diningOption(option: String): Builder = this.apply { this.diningOption = option }
        fun temperature(temperature: String): Builder = this.apply { this.temperature = temperature }

        fun build(): Drink {
            return Drink(type, additives, diningOption, temperature)
        }
    }
}
