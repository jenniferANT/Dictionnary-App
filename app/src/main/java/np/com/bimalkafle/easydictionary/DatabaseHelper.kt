package np.com.bimalkafle.easydictionary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqldemo3.DatabaseUser

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dictionary"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USER = "user"
        private const val COL_ID="id"
        private const val COL_USERNAME = "username"
        private const val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_USERNAME TEXT," +
                "$COL_PASSWORD TEXT" +
                ")"
        db.execSQL(CREATE_TABLE_USER)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    fun addUser(user: DatabaseUser): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_USERNAME, user.name)
            put(COL_PASSWORD, user.pass)
        }
        val userId = db.insert(TABLE_USER, null, values)
        println("Save user completed with id " + userId )
        db.close()
        return userId
    }
    fun getUserByUsername(username: String): DatabaseUser? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USER,
            arrayOf(COL_USERNAME, COL_PASSWORD),
            "$COL_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        var user: DatabaseUser? = null
        cursor?.use {
            if (it.moveToFirst()) {
                user = DatabaseUser(it.getString(0), it.getString(1)) // Read both columns
            }
        }
        cursor?.close()
        db.close()
        return user
    }




}
