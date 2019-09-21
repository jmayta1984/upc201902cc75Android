package pe.edu.upc.catchup.network

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import pe.edu.upc.catchup.CatchUpApp
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.Favorite


class NewsApi {
    companion object {
        private const val BASE_URL = "https://newsapi.org"
        val topHeadlinesUrl = "$BASE_URL/v2/top-headlines"
        val everythingUrl = "$BASE_URL/v2/everything"
        val sourcesUrl = "$BASE_URL/v2/sources"
        const val TAG = "NewsApi"

        fun requestSources(responseHandler: (SourcesResponse?) -> Unit,
                            errorHandler: (ANError) -> Unit) {
             request(sourcesUrl, mapOf("" to ""), responseHandler, errorHandler)

        }

        fun requestTopHeadlines(responseHandler: (ArticlesResponse?) -> Unit,
                                errorHandler: (ANError) -> Unit) {
            request(topHeadlinesUrl, mapOf("language" to "en"), responseHandler, errorHandler)
        }

        fun requestArticlesFromFavoriteSources(responseHandler: (ArticlesResponse?) -> Unit,
                                           errorHandler: (ANError) -> Unit) {
            request(everythingUrl, mapOf("sources" to Favorite.allSourceIds()), responseHandler, errorHandler)
        }

        var apiKey: String = ""


        private inline fun <reified T> request(url: String, parameter: Map<String, String>?, crossinline responseHandler: (T?) -> Unit,
                                       crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.get(url)
                .addQueryParameter("apiKey", apiKey)
                .addQueryParameter(parameter)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                val response = this as NewsApiResponse
                                if (response.status.equals("ok", ignoreCase = true)) {
                                    responseHandler(this)
                                    Log.d(TAG, response.status)
                                }

                                else {
                                    Log.d(TAG, "Error $response.code: $response.message")
                                }
                            }
                        }

                        override fun onError(anError: ANError?) {
                            anError?.apply {
                                Log.d(TAG, "Error $errorCode: $errorBody $localizedMessage")
                                errorHandler(this)
                            }
                        }

                    })

        }
    }
}