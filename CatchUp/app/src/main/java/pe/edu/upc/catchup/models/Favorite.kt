package pe.edu.upc.catchup.models

import com.orm.SugarRecord
import pe.edu.upc.catchup.R

data class Favorite (
    val sourceId: String,
    val sourceName: String
) : SugarRecord() {
    constructor() : this("", "")
    companion object {
        fun findFor(source: Source): Favorite? {
            val favorites = SugarRecord.find(Favorite::class.java, "source_id = ?", source.id)
            return if(favorites.isEmpty()) null else favorites.first()
        }

        fun resourceForState(state: Boolean): Int {
            return if (state) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
        }

        fun allSourceIds(): String {
            val favorites = SugarRecord.findAll(Favorite::class.java)
            return favorites.asSequence().filterNot { it.sourceId.isEmpty() }.joinToString { it.sourceId }
        }
    }
}