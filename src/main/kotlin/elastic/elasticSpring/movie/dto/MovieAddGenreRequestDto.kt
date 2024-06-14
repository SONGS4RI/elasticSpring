package elastic.elasticSpring.movie.dto

import jakarta.validation.constraints.NotBlank

data class MovieAddGenreRequestDto (
    @field: NotBlank
    val movieNm : String,
    @field: NotBlank
    val genre : String
)