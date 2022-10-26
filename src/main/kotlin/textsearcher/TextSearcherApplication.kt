package textsearcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import textsearcher.exceptions.SourceFolderException

@SpringBootApplication
class TextSearcherApplication

fun main(args: Array<String>) {
    try {
        runApplication<TextSearcherApplication>(*args)
    } catch (e: SourceFolderException) {
        System.err.println(e.message)
    }
}