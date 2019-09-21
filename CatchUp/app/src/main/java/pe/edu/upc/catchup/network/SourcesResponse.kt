package pe.edu.upc.catchup.network

import pe.edu.upc.catchup.models.Source

class SourcesResponse(val sources: List<Source>?) : NewsApiResponse() {
    constructor() : this(ArrayList<Source>())
}