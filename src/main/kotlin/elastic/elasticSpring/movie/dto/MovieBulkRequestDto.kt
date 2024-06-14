package elastic.elasticSpring.movie.dto

import elastic.elasticSpring.movie.document.Company
import elastic.elasticSpring.movie.document.Director

data class MovieBulkRequestDto(
    val id: String?,
    val movieCd: String?,
    val movieNm:  String?,
    val movieNmEn: String?,
    val prdtYear: String?,
    val openDt: String?,
    val typeNm: String?,
    val prdtStatNm: String?,
    val nationAlt: String?,
    val genreAlt: List<String>?,
    val repNationNm: String?,
    val repGenreNm: String?,
    val directors: List<Director>?,
    val companies: List<Company>?
)