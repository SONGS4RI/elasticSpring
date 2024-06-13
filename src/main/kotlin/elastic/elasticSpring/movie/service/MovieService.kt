package elastic.elasticSpring.movie.service

import com.fasterxml.jackson.databind.ObjectMapper
import elastic.elasticSpring.movie.document.Movie
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val restHighLevelClient: RestHighLevelClient,
    private val  objectMapper: ObjectMapper
) {
    private val index = "movie_search"

    fun addMovie(movie: Movie, id: String?): IndexResponse {
        val indexRequest = IndexRequest(index)
            .source(objectMapper.writeValueAsString(movie), XContentType.JSON)
        if (!id.isNullOrEmpty()) {
            indexRequest.id(id)
        }
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT)
    }
}