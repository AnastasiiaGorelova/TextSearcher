package textsearcher.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class TextSearcherService(
        @Value("\${environment.variable.for.folder.path}")
        final var environmentVariable: String
) {

    final var folderPath = System.getenv(environmentVariable)
        private set

    fun getFilesWithWords(input: String): List<String> {
        val words = parseInput(input)
        val files = checkFiles(words)
        return files
    }

    private fun parseInput(input: String): List<String> {
        return input.trim().split("[\\s,]+".toRegex())
    }

    private fun checkFiles(words: List<String>): List<String> {
        return File(folderPath).listFiles()!!   //TODO make check for !!
                .filter { words.stream().allMatch { word -> it.readText().contains(word, true) } }
                .map { file -> file.name }
                .toList()
    }

}
