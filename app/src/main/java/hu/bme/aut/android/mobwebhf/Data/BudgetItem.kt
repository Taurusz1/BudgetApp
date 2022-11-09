package hu.bme.aut.android.mobwebhf.Data

import java.util.Date

data class BudgetItem(var Name: String = "N/A", var Price: Int = 0, var cat: Category = Category.OTHER) {
    enum class Category{ FOOD, HOBBY, SPORTS, CLOTHES, ENTERTAINMENT, OTHER}
    //enum class Type{EXPENSE, INCOME}
}
//val type: Type = Type.EXPENSE) {
//val timeOfPurchase: Date = Date(),