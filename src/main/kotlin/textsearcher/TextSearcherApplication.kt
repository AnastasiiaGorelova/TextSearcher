package textsearcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextSearcherApplication

fun main(args: Array<String>) {
    runApplication<TextSearcherApplication>(*args)
}