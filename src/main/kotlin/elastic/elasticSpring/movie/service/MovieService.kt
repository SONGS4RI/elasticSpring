package elastic.elasticSpring.movie.service

import com.fasterxml.jackson.databind.ObjectMapper
import elastic.elasticSpring.movie.document.Movie
import elastic.elasticSpring.movie.dto.MovieAddGenreRequestDto
import elastic.elasticSpring.movie.dto.MovieDeleteByNameRequestDto
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.bulk.BulkResponse
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.BulkByScrollResponse
import org.elasticsearch.index.reindex.DeleteByQueryRequest
import org.elasticsearch.index.reindex.UpdateByQueryRequest
import org.elasticsearch.script.Script
import org.elasticsearch.script.ScriptType
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val restHighLevelClient: RestHighLevelClient,
    private val  objectMapper: ObjectMapper
) {
    private val index = "movie_search"

    fun add(movie: Movie): IndexResponse {
        val indexRequest = IndexRequest(index)
            .source(objectMapper.writeValueAsString(movie), XContentType.JSON)
        if (!movie.id.isNullOrEmpty()) {
            indexRequest.id(movie.id)
        }
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT)
    }

    fun bulkIndex(movieList: List<Movie>): BulkResponse {
        val bulkRequest = BulkRequest()

        for (movie in movieList) {
            val indexRequest = IndexRequest(index)
                .source(objectMapper.writeValueAsString(movie), XContentType.JSON)
            if (!movie.id.isNullOrEmpty()) {
                indexRequest.id(movie.id)
            }
            bulkRequest.add(indexRequest)
        }

        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT)
    }

    fun addGenre(movieDto: MovieAddGenreRequestDto): BulkByScrollResponse {
        val updateByQueryRequest = UpdateByQueryRequest(index)
        val script = Script(
            ScriptType.INLINE,
            "painless",
            "ctx._source.genreAlt.add(params.genre)",
            mapOf("genre" to movieDto.genre)
        )
        val query = QueryBuilders.matchQuery("movieNm", movieDto.movieNm)

        updateByQueryRequest.setScript(script)
        updateByQueryRequest.setQuery(query)

        return restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT)
    }

    fun deleteByMovieName(movieDto: MovieDeleteByNameRequestDto) : BulkByScrollResponse {
        val deleteByQueryRequest = DeleteByQueryRequest(index)
        val query = QueryBuilders.matchQuery("movieNm", movieDto.movieNm)
        deleteByQueryRequest.setQuery(query)

        return restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT)
    }
}