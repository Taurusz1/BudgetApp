package hu.bme.aut.android.mobwebhf.Data

data class BudgetItem(
    var Name: String = "N/A",
    var Price: Int = 0,
    var cat: Category = Category.OTHER,
) {
    enum class Category{ FOOD, HOBBY, CLOTHES, ENTERTAINMENT, OTHER, INCOME, SAVINGS}
}