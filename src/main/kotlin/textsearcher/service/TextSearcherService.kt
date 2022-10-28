package textsearcher.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import textsearcher.exceptions.SourceFolderException
import java.io.File

@Service
class TextSearcherService(
        @Value("\${environment.variable.for.folder.path}")
        private val environmentVariable: String
) {
    private val folderPath: String? = System.getenv(environmentVariable)

    /**
     * Check if everything is alright with given directory
     */
    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        checkSourceFolderPath()
        println("Application successfully started up!")
    }

    private fun checkSourceFolderPath() {
        if (folderPath == null || !File(folderPath).canRead() || !File(folderPath).isDirectory) {
            throw SourceFolderException(
                    "Environment variable $environmentVariable does not exist or invalid folder."
            )
        }
        File(folderPath).listFiles()!!.forEach { file ->
            if (!file.canRead() || !file.isFile) {
                throw SourceFolderException("Something is wrong with $file")
            }
        }
    }

    fun getFilesWithWords(input: String): List<String> {
        val words = parseInput(input)
        return checkFiles(words, folderPath!!)
    }

    companion object {
        fun parseInput(input: String): List<String> {
            return input.trim().split("[\\s,]+".toRegex()).filter { w -> w.isNotEmpty() }
        }

        fun checkFiles(words: List<String>, folderPath: String): List<String> {
            if (words.isEmpty()) {
                return emptyList()
            }
            return try {
                File(folderPath).listFiles()!!
                        .filter { words.stream().allMatch { word -> it.readText().contains(word, true) } }
                        .map { file -> file.name }
                        .toList()
            } catch (_: Exception) {
                throw SourceFolderException("Error while reading directory $folderPath")
            }
        }
    }

}
