package elastic.elasticSpring.movie.document

import com.fasterxml.jackson.annotation.JsonProperty

data class Director(
    @JsonProperty("peopleNm")
    val peopleNm: String = ""
)
