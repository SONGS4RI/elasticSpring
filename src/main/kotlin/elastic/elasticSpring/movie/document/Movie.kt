package elastic.elasticSpring.movie.document

import com.fasterxml.jackson.annotation.JsonProperty
import elastic.elasticSpring.movie.dto.MovieBulkRequestDto
import elastic.elasticSpring.movie.dto.MovieRequestDto

data class Movie(
    val id: String?,
    @JsonProperty("movieCd")
    val movieCd: String?,
    @JsonProperty("movieNm")
    val movieNm:  String?,
    @JsonProperty("movieNmEn")
    val movieNmEn: String?,
    @JsonProperty("prdtYear")
    val prdtYear: String?,
    @JsonProperty("openDt")
    val openDt: String?,
    @JsonProperty("typeNm")
    val typeNm: String?,
    @JsonProperty("prdtStatNm")
    val prdtStatNm: String?,
    @JsonProperty("nationAlt")
    val nationAlt: String?,
    @JsonProperty("genreAlt")
    val genreAlt: List<String>,
    @JsonProperty("repNationNm")
    val repNationNm: String?,
    @JsonProperty("repGenreNm")
    val repGenreNm: String?,
    @JsonProperty("directors")
    val directors: List<Director>?,
    @JsonProperty("companies")
    val companies: List<Company>?
) {
    companion object {
        fun from(movieRequestDto: MovieRequestDto, id: String?): Movie {
            return Movie(
                id = id,
                movieCd = movieRequestDto.movieCd ?: "",
                movieNm = movieRequestDto.movieNm ?: "",
                movieNmEn = movieRequestDto.movieNmEn ?: "",
                prdtYear = movieRequestDto.prdtYear ?: "",
                openDt = movieRequestDto.openDt ?: "",
                typeNm = movieRequestDto.typeNm ?: "",
                prdtStatNm = movieRequestDto.prdtStatNm ?: "",
                nationAlt = movieRequestDto.nationAlt ?: "",
                genreAlt = movieRequestDto.genreAlt  ?: mutableListOf(),
                repNationNm = movieRequestDto.repNationNm  ?: "",
                repGenreNm = movieRequestDto.repGenreNm  ?: "",
                directors = movieRequestDto.directors?.map { Director(it.peopleNm) } ?: mutableListOf(),
                companies = movieRequestDto.companies?.map { Company(it.companyCd, it.companyNm) } ?: mutableListOf(),
            )
        }
        fun from(movieBulkRequestDto: MovieBulkRequestDto): Movie {
            return Movie(
                id = movieBulkRequestDto.id,
                movieCd = movieBulkRequestDto.movieCd ?: "",
                movieNm = movieBulkRequestDto.movieNm ?: "",
                movieNmEn = movieBulkRequestDto.movieNmEn ?: "",
                prdtYear = movieBulkRequestDto.prdtYear ?: "",
                openDt = movieBulkRequestDto.openDt ?: "",
                typeNm = movieBulkRequestDto.typeNm ?: "",
                prdtStatNm = movieBulkRequestDto.prdtStatNm ?: "",
                nationAlt = movieBulkRequestDto.nationAlt ?: "",
                genreAlt = movieBulkRequestDto.genreAlt  ?: mutableListOf(),
                repNationNm = movieBulkRequestDto.repNationNm  ?: "",
                repGenreNm = movieBulkRequestDto.repGenreNm  ?: "",
                directors = movieBulkRequestDto.directors?.map { Director(it.peopleNm) } ?: mutableListOf(),
                companies = movieBulkRequestDto.companies?.map { Company(it.companyCd, it.companyNm) } ?: mutableListOf(),
            )
        }
    }
}