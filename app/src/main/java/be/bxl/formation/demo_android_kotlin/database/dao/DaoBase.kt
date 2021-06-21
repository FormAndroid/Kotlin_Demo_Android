package be.bxl.formation.demo_android_kotlin.database.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import be.bxl.formation.demo_android_kotlin.database.DbHelper

abstract class DaoBase(val context: Context) {

    private var dbHelper: DbHelper? = null
    private var _db: SQLiteDatabase? = null

    protected val db: SQLiteDatabase
        get() =  _db ?: throw RuntimeException("Vous devez utiliser la méthodé \"openWritable\" ou \"openReadable\"")


    fun openWritable() : DaoBase {
        dbHelper = DbHelper(context)
        _db = dbHelper!!.writableDatabase
        return this
    }

    fun openReadable() : DaoBase {
        dbHelper = DbHelper(context)
        _db = dbHelper!!.readableDatabase
        return this
    }

    fun close() {
        _db?.close()
        dbHelper?.close()

        _db = null
        dbHelper = null
    }
}