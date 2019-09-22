package pe.edu.upc.movieup.data.db

import androidx.room.*
import pe.edu.upc.movieup.data.db.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getAll(): MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg movies: Movie)

    @Delete
    fun delete(vararg movies: Movie)

}