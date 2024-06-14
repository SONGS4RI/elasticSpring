package elastic.elasticSpring.movie.dto

import org.jetbrains.annotations.NotNull

data class MovieDeleteByNameRequestDto (
    @NotNull
    val movieNm : String
)
