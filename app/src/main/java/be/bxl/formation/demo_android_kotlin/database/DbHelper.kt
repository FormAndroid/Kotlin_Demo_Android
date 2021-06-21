package be.bxl.formation.demo_android_kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context):
    SQLiteOpenHelper(context, DbInfo.NAME, null, DbInfo.VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ProductTable.REQUEST_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(ProductTable.REQUEST_DROP)

        onCreate(db)
    }
}