package pe.edu.upc.catchup.network

import pe.edu.upc.catchup.models.Article

class ArticlesResponse(val totalResults: Int,
                       val articles: List<Article>) : NewsApiResponse() {
    constructor() : this(0, ArrayList<Article>())
}