package elastic.elasticSpring.movie.controller

import elastic.elasticSpring.movie.document.Movie
import elastic.elasticSpring.movie.dto.MovieAddGenreRequestDto
import elastic.elasticSpring.movie.dto.MovieRequestDto
import elastic.elasticSpring.movie.dto.MovieBulkRequestDto
import elastic.elasticSpring.movie.dto.MovieDeleteByNameRequestDto
import elastic.elasticSpring.movie.service.MovieService
import jakarta.validation.Valid
import org.elasticsearch.action.bulk.BulkResponse
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.index.reindex.BulkByScrollResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController(val movieService: MovieService) {
    @PutMapping("")
    fun index(@RequestBody movieDto: MovieRequestDto,
                 @RequestParam(required = false) id: String?): ResponseEntity<IndexResponse> {
        val movie = Movie.from(movieDto, id)
        val result = movieService.add(movie)
        return ResponseEntity(result, HttpStatus.valueOf(result.status().status))
    }

    // bulk
    @PutMapping("/bulk/index")
    fun bulkIndex(@RequestBody @Validated movieDtoList: List<MovieBulkRequestDto>): ResponseEntity<BulkResponse> {
        val movieList : MutableList<Movie> = mutableListOf()
        for (movieDto in movieDtoList) {
            movieList.add(Movie.from(movieDto))
        }
        val result = movieService.bulkIndex(movieList)
        return ResponseEntity(result, HttpStatus.valueOf(result.status().status))
    }

    // update by query
    @PostMapping("/genre")
    fun addGenre(@RequestBody @Valid movieDto: MovieAddGenreRequestDto): ResponseEntity<BulkByScrollResponse> {
        val result = movieService.addGenre(movieDto)
        return ResponseEntity(result, HttpStatus.OK)
    }

    // delete by query
    @DeleteMapping("")
    fun deleteByMovieName(@RequestBody @Valid movieDto: MovieDeleteByNameRequestDto): ResponseEntity<BulkByScrollResponse> {
        val result = movieService.deleteByMovieName(movieDto)
        return ResponseEntity(result, HttpStatus.OK)
    }
}