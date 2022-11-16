package hu.bme.aut.android.mobwebhf.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        DbConstants.ExpenseItems.onCreate(sqLiteDatabase)
        DbConstants.IncomeItems.onCreate(sqLiteDatabase)
        DbConstants.Savings.onCreate(sqLiteDatabase)
    }

    override fun onUpgrade(
        sqLiteDatabase: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        DbConstants.ExpenseItems.onUpgrade(sqLiteDatabase, oldVersion, newVersion)
        DbConstants.IncomeItems.onUpgrade(sqLiteDatabase, oldVersion, newVersion)
        DbConstants.Savings.onUpgrade(sqLiteDatabase, oldVersion, newVersion)
    }
}