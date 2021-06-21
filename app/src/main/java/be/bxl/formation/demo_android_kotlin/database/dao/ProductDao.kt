package be.bxl.formation.demo_android_kotlin.database.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import be.bxl.formation.demo_android_kotlin.database.ProductTable
import be.bxl.formation.demo_android_kotlin.models.Product

class ProductDao(context: Context) : DaoBase(context) {

    private fun cursorToProduct(c: Cursor) : Product {
        val id = c.getLong(c.getColumnIndex(ProductTable.COLUMN_ID))
        val name = c.getString(c.getColumnIndex(ProductTable.COLUMN_NAME))
        val qte = c.getInt(c.getColumnIndex(ProductTable.COLUMN_QTE))
        val urgent = c.getInt(c.getColumnIndex(ProductTable.COLUMN_URGENT))

        return Product(id, name, qte, urgent == 1)
    }

    private fun createContentValues(product: Product): ContentValues {
        return ContentValues().apply {
            put(ProductTable.COLUMN_NAME, product.name)
            put(ProductTable.COLUMN_QTE, product.quantity)
            put(ProductTable.COLUMN_URGENT, if (product.urgent) 1 else 0)
        }
    }

    // CRUD
    fun insert(product: Product): Long {
        val cv: ContentValues = createContentValues(product)
        return db.insert(ProductTable.TABLE_NAME, null, cv)
    }

    fun read(id: Long): Product? {
        val cursor: Cursor = db.query(ProductTable.TABLE_NAME, null,
            "${ProductTable.COLUMN_ID} = ?", arrayOf(id.toString()),
            null, null, null)

        if(cursor.count == 0) {
            return null
        }

        cursor.moveToFirst()
        return cursorToProduct(cursor)
    }

    fun readAll(): List<Product> {
        val cursor: Cursor = db.query(ProductTable.TABLE_NAME, null, null, null,null, null, null)

        if(cursor.count == 0) return emptyList()

        val result: MutableList<Product> = mutableListOf()

        cursor.moveToFirst()
        while(!cursor.isAfterLast) {
            val p: Product = cursorToProduct(cursor)
            result.add(p)

            cursor.moveToNext()
        }

        return result.toList()
    }

    fun update(id: Long, product: Product): Boolean {
        val cv: ContentValues = createContentValues(product)

        val nbRow: Int = db.update(ProductTable.TABLE_NAME, cv, "${ProductTable.COLUMN_ID} = ?", arrayOf(id.toString()))
        return nbRow == 1
    }

    fun delete(id: Long): Boolean {
        val nbRow: Int = db.delete(ProductTable.TABLE_NAME, "${ProductTable.COLUMN_ID} = ?", arrayOf(id.toString()))
        return nbRow == 1
    }

    fun deleteAll() {
        val nbRow: Int = db.delete(ProductTable.TABLE_NAME, null, null)
    }
}