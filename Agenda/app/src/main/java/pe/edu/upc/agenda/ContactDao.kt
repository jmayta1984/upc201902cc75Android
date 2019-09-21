package pe.edu.upc.agenda

import androidx.room.*

@Dao
interface ContactDao {
    @Query("select * from contact")
    fun getAll(): List<Contact>

    @Insert
    fun insert(vararg contacts: Contact)

    @Delete
    fun delete(vararg contacts: Contact)

    @Update
    fun update(vararg contact: Contact)
}