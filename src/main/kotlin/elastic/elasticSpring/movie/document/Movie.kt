package elastic.elasticSpring.movie.document

import com.fasterxml.jackson.annotation.JsonProperty
import elastic.elasticSpring.movie.dto.MovieRequestDto

data class Movie(
    @JsonProperty("movieCd")
    val movieCd: String,
    @JsonProperty("movieNm")
    val movieNm:  String = "",
    @JsonProperty("movieNmEn")
    val movieNmEn: String = "",
    @JsonProperty("prdtYear")
    val prdtYear: String = "",
    @JsonProperty("openDt")
    val openDt: String = "",
    @JsonProperty("typeNm")
    val typeNm: String = "",
    @JsonProperty("prdtStatNm")
    val prdtStatNm: String = "",
    @JsonProperty("nationAlt")
    val nationAlt: String = "",
    @JsonProperty("genreAlt")
    val genreAlt: List<String> = mutableListOf(),
    @JsonProperty("repNationNm")
    val repNationNm: String = "",
    @JsonProperty("repGenreNm")
    val repGenreNm: String = "",
    @JsonProperty("directors")
    val directors: List<Director> = mutableListOf(),
    @JsonProperty("companies")
    val companies: List<Company> = mutableListOf()
) {
    companion object {
        fun from(movieRequestDto: MovieRequestDto): Movie {
            return Movie(
                movieCd = movieRequestDto.movieCd,
                movieNm = movieRequestDto.movieNm,
                movieNmEn = movieRequestDto.movieNmEn,
                prdtYear = movieRequestDto.prdtYear,
                openDt = movieRequestDto.openDt,
                typeNm = movieRequestDto.typeNm,
                prdtStatNm = movieRequestDto.prdtStatNm,
                nationAlt = movieRequestDto.nationAlt,
                genreAlt = movieRequestDto.genreAlt,
                repNationNm = movieRequestDto.repNationNm,
                repGenreNm = movieRequestDto.repGenreNm,
                directors = movieRequestDto.directors.map { Director(it.peopleNm) },
                companies = movieRequestDto.companies.map { Company(it.companyCd, it.companyNm) }
            )
        }
    }
}