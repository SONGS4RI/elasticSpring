package elastic.elasticSpring.movie.dto

import org.elasticsearch.action.index.IndexResponse

data class ElasticsearchResponseDto(
    val _index: String,
    val _id: String,
    val _version: Long,
    val result: String,
    val _shards: ShardInfo,
    val _seq_no: Long,
    val _primary_term: Long,
) {
    companion object {
        fun from(indexResponse: IndexResponse): ElasticsearchResponseDto {
            return ElasticsearchResponseDto(
                _index = indexResponse.index,
                _id = indexResponse.id,
                _version = indexResponse.version,
                result = indexResponse.result.name,
                _shards = ShardInfo(
                    total = indexResponse.shardInfo.total,
                    successful = indexResponse.shardInfo.successful,
                    failed = indexResponse.shardInfo.failed
                ),
                _seq_no = indexResponse.seqNo,
                _primary_term = indexResponse.primaryTerm
            )
        }
    }
}

data class ShardInfo(
    val total: Int,
    val successful: Int,
    val failed: Int
)

