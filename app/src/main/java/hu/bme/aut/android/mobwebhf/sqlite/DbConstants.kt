package hu.bme.aut.android.mobwebhf.sqlite

import android.database.sqlite.SQLiteDatabase
import android.util.Log

object DbConstants {

    const val DATABASE_NAME = "BudgetItems.db"
    const val DATABASE_VERSION = 1

    object BudgetItems {
        const val DATABASE_TABLE = "BudgetItems"

        enum class Columns { ID, NAME, PRICE, CATEGORY, TYPE }

        private val DATABASE_CREATE = """create table if not exists $DATABASE_TABLE (
            ${Columns.ID.name} integer primary key autoincrement,
            ${Columns.NAME.name} string not null,
            ${Columns.PRICE.name} integer not null
            ${Columns.CATEGORY.name} string not null
            ${Columns.TYPE.name} string not null
            );"""

        private const val DATABASE_DROP = "drop table if exists $DATABASE_TABLE;"

        fun onCreate(database: SQLiteDatabase) {
            database.execSQL(DATABASE_CREATE)
        }

        fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.w(
                BudgetItems::class.java.name,
                "Upgrading from version $oldVersion to $newVersion"
            )
            database.execSQL(DATABASE_DROP)
            onCreate(database)
        }
    }
}