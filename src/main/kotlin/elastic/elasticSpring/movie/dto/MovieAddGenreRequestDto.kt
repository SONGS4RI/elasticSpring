package elastic.elasticSpring.movie.dto

import org.jetbrains.annotations.NotNull

data class MovieAddGenreRequestDto (
    @NotNull
    val movieNm : String,
    @NotNull
    val genre : String
)