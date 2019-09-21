package pe.edu.upc.catchup.network

abstract class NewsApiResponse(
    val status: String,
    val code: String?,
    val message: String?
) {
    constructor() : this("", "", "")

}