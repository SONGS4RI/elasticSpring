package elastic.elasticSpring.movie.controller

import elastic.elasticSpring.movie.document.Movie
import elastic.elasticSpring.movie.dto.MovieRequestDto
import elastic.elasticSpring.movie.dto.ElasticsearchResponseDto
import elastic.elasticSpring.movie.service.MovieService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController(val movieService: MovieService) {
    @PostMapping("")
    fun addMovie(@RequestBody movieDto: MovieRequestDto,
                 @RequestParam(required = false) id: String?): ResponseEntity<ElasticsearchResponseDto> {
        val movie = Movie.from(movieDto)
        val result = movieService.addMovie(movie, id)
        val response = ElasticsearchResponseDto.from(result)
        return ResponseEntity(response, HttpStatus.valueOf(result.status().status))
    }

    @PostMapping("/bulk")
    fun bulkMovie(@RequestBody @Valid movieDtoList: List<MovieRequestDto>): ResponseEntity<List<ElasticsearchResponseDto>> {
        return ResponseEntity(HttpStatus.OK)
    }

    //update_by_query
}