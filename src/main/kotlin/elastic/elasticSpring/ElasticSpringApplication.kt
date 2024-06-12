package elastic.elasticSpring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElasticSpringApplication

fun main(args: Array<String>) {
	runApplication<ElasticSpringApplication>(*args)
}
