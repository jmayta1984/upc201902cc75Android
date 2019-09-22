package pe.edu.upc.movieup.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import pe.edu.upc.movieup.data.network.PosterApi

@Entity
data class Movie(

    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster")
    val posterPath: String
) {

    val urlToPoster: String
        get() = PosterApi.urlToLogo(posterPath)

}



