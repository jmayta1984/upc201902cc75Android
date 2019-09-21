package pe.edu.upc.catchup.models

import android.os.Parcel
import android.os.Parcelable
import com.orm.SugarRecord
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import pe.edu.upc.catchup.network.LogoApi
import java.io.Serializable


data class Source(
    val id: String?,
    val name: String,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
) : Serializable {


    constructor() : this("", "", "", "", "", "", "")

    val urlToLogo: String
        get() = LogoApi.urlToLogo(url)

    var isFavorite: Boolean
        get() = SugarRecord.find(Favorite::class.java, "source_id = ?", this.id).size > 0
        set(value) {
            if ((value and (Favorite.findFor(this) is Favorite)) or
                (!value and (Favorite.findFor(this) == null))
            ) return
            if (value) this.id?.let { Favorite(it, name).save() } else {
                Favorite.findFor(this)?.apply { delete() }
            }
        }
}
