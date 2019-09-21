package pe.edu.upc.catchup.network

import java.lang.Exception
import java.net.URI
import java.net.URL

class LogoApi {
    companion object {
        const val BASE_URL = "https://logo.clearbit.com/"

        fun urlToLogo(urlString: String?): String {
            urlString?.apply {
                try {
                    val domain =  URI(this).host
                    return "$BASE_URL$domain"
                } catch (e: Exception) {
                    return this
                }
            }
            return ""
        }
    }
}