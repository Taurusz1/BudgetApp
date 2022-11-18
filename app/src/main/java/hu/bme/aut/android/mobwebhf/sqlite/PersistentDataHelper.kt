package hu.bme.aut.android.mobwebhf.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import hu.bme.aut.android.mobwebhf.Data.BudgetItem

class PersistentDataHelper(context: Context) {
    private var database: SQLiteDatabase? = null
    private val dbHelper: DbHelper = DbHelper(context)

    private val BudgetItemsColumns = arrayOf(
        DbConstants.Columns.ID.name,
        DbConstants.Columns.NAME.name,
        DbConstants.Columns.PRICE.name,
        DbConstants.Columns.CATEGORY.name,
    )

    @Throws(SQLiteException::class)
    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }
    fun calcSums(dbName: String):Int{
        val items = restoreItems(dbName)
        var sum = 0
        for(i in items){
            sum+=i.Price
        }
        return sum
    }
    fun calcExpense(dbName: String):ArrayList<Int>{
        val items = restoreItems(dbName)
        var foodSum = 0
        var hobbySum = 0
        var clothesSum = 0
        var entertainmentSum = 0
        var otherSum = 0
        var sumList = ArrayList<Int>()
        for(i in items){
            when(i.cat.name){
                "FOOD" -> foodSum+=i.Price
                "HOBBY" -> hobbySum+=i.Price
                "CLOTHES" -> clothesSum+=i.Price
                "ENTERTAINMENT" -> entertainmentSum+=i.Price
                "OTHER"-> otherSum+=i.Price
                else -> {}
            }
        }
        sumList.add(foodSum)
        sumList.add(hobbySum)
        sumList.add(clothesSum)
        sumList.add(entertainmentSum)
        sumList.add(otherSum)
        return sumList
    }
    fun persistItems(BudgetItems: List<BudgetItem>, dbName: String) {
        clearBudgetItems(dbName)
        for (item in BudgetItems) {
            val values = ContentValues()
            values.put(DbConstants.Columns.NAME.name, item.Name)
            values.put(DbConstants.Columns.PRICE.name, item.Price)
            values.put(DbConstants.Columns.CATEGORY.name, item.cat.name)
            database!!.insert(dbName, null, values)
        }
    }

    fun restoreItems(dbName: String): MutableList<BudgetItem> {
        val items: MutableList<BudgetItem> = ArrayList()
        val cursor: Cursor = database!!.query(dbName, BudgetItemsColumns,null , null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val item: BudgetItem = cursorToPoint(cursor)
            items.add(item)
            cursor.moveToNext()
        }
        cursor.close()
        return items
    }


    fun clearBudgetItems(dbName: String) {
        database!!.delete(dbName, null, null)
    }

    private fun cursorToPoint(cursor: Cursor): BudgetItem {
        val item = BudgetItem()
        item.Name = cursor.getString(DbConstants.Columns.NAME.ordinal)
        item.Price = cursor.getInt(DbConstants.Columns.PRICE.ordinal)
        item.cat = enumValueOf(cursor.getString(DbConstants.Columns.CATEGORY.ordinal))
        return item
    }
}