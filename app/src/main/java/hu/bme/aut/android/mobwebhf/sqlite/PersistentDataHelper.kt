package hu.bme.aut.android.mobwebhf.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import hu.bme.aut.android.mobwebhf.Budget.BudgetFragment
import hu.bme.aut.android.mobwebhf.Data.BudgetItem

class PersistentDataHelper(context: Context) {
    private var database: SQLiteDatabase? = null
    private val dbHelper: DbHelper = DbHelper(context)

    private val BudgetItemsColumns = arrayOf(
        DbConstants.BudgetItems.Columns.ID.name,
        DbConstants.BudgetItems.Columns.NAME.name,
        DbConstants.BudgetItems.Columns.PRICE.name,
        //DbConstants.BudgetItems.Columns.PURCHASE_DATE.toString(),
        //DbConstants.BudgetItems.Columns.CATEGORY.toString(),
        //DbConstants.BudgetItems.Columns.TYPE.toString()
    )

    @Throws(SQLiteException::class)
    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun persistBudgetItems(BudgetItems: List<BudgetItem>) {
        clearBudgetItems()
        for (item in BudgetItems) {
            val values = ContentValues()
            values.put(DbConstants.BudgetItems.Columns.NAME.name, item.Name)
            values.put(DbConstants.BudgetItems.Columns.PRICE.name, item.Price)
            //values.put(DbConstants.BudgetItems.Columns.PURCHASE_DATE.name, item.timeOfPurchase.toString())
            //values.put(DbConstants.BudgetItems.Columns.CATEGORY.name, item.cat.toString())
            //values.put(DbConstants.BudgetItems.Columns.TYPE.name, item.type.toString())
            database!!.insert(DbConstants.BudgetItems.DATABASE_TABLE, null, values)
        }
    }

    fun restoreBudgetItems(): MutableList<BudgetItem> {
        val points: MutableList<BudgetItem> = ArrayList()
        val cursor: Cursor =
            database!!.query(DbConstants.BudgetItems.DATABASE_TABLE, BudgetItemsColumns, null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val item: BudgetItem = cursorToPoint(cursor)
            points.add(item)
            cursor.moveToNext()
        }
        cursor.close()
        return points
    }

    fun clearBudgetItems() {
        database!!.delete(DbConstants.BudgetItems.DATABASE_TABLE, null, null)
    }

    private fun cursorToPoint(cursor: Cursor): BudgetItem {
        val item = BudgetItem()
        item.Name = cursor.getString(DbConstants.BudgetItems.Columns.NAME.ordinal)
        item.Price = cursor.getInt(DbConstants.BudgetItems.Columns.PRICE.ordinal)
        return item
    }
}