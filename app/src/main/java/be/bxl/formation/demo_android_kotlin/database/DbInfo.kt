package be.bxl.formation.demo_android_kotlin.database

class DbInfo {
    companion object {
        val NAME: String = "MyDB.sql"
        val VERSION: Int = 1
    }
}

class ProductTable {
    companion object {
        val TABLE_NAME = "produit"

        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
        val COLUMN_QTE = "quantity"
        val COLUMN_URGENT = "urgent"

        val REQUEST_CREATE: String = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_QTE INT NOT NULL,
                $COLUMN_URGENT INT NOT NULL
            );
        """.trimIndent()

        val REQUEST_DROP: String = """
            DROP TABLE $TABLE_NAME;
        """.trimIndent()
    }
}