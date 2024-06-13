package elastic.elasticSpring.movie.document

import com.fasterxml.jackson.annotation.JsonProperty

data class Company(
    @JsonProperty("companyCd")
    val companyCd: String = "",
    @JsonProperty("companyNm")
    val companyNm: String = ""
)