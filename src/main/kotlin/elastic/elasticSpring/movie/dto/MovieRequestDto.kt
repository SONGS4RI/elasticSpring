package elastic.elasticSpring.movie.dto

import elastic.elasticSpring.movie.document.Company
import elastic.elasticSpring.movie.document.Director
import org.jetbrains.annotations.NotNull

data class MovieRequestDto(
    @NotNull
    val movieCd: String,
    @NotNull
    val movieNm:  String = "",
    val movieNmEn: String = "",
    val prdtYear: String = "",
    val openDt: String = "",
    val typeNm: String = "",
    val prdtStatNm: String = "",
    val nationAlt: String = "",
    val genreAlt: List<String> = mutableListOf(),
    val repNationNm: String = "",
    val repGenreNm: String = "",
    val directors: List<Director> = mutableListOf(),
    val companies: List<Company> = mutableListOf()
)