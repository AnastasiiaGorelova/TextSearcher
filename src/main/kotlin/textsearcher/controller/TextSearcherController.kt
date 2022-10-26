package textsearcher.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import textsearcher.service.TextSearcherService

@RestController
class TextSearcherController(
        private val service: TextSearcherService
) {
    @GetMapping("/files")
    fun getFilesWithWords(@RequestParam(value = "query") query: String): List<String> {
        return service.getFilesWithWords(query)
    }
}
