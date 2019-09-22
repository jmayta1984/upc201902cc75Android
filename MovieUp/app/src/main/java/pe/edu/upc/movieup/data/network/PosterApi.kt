package pe.edu.upc.movieup.data.network

class PosterApi {

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"

        fun urlToLogo(urlString: String?): String {

            return when (urlString) {
                null -> ""

                else -> "$BASE_URL$urlString"
            }
        }
    }
}