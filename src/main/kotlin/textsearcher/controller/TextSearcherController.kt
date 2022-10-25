package textsearcher.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import textsearcher.service.TextSearcherService

@RestController
class TextSearcherController(
        private val service: TextSearcherService
) {

    @GetMapping("/folder")
    fun getFolderPath(): String {
        return service.folderPath
    }

    @GetMapping("/files")
    fun getFilesWithWords(@RequestBody request: String): List<String> {
        return service.getFilesWithWords(request)
    }
}
