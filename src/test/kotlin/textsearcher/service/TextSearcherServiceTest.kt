package textsearcher.service

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.nio.file.Paths

@SpringBootTest
class TextSearcherServiceTest {
    private val testSourceFolderPath = Paths.get("").toAbsolutePath().toString() + "/src/test/resources/test_data"

    @Test
    fun parseEmptyInputTest() {
        val expectedResult = emptyList<String>()
        TextSearcherService.parseInput("") shouldBe expectedResult
        TextSearcherService.parseInput("         ") shouldBe expectedResult
        TextSearcherService.parseInput("  \n ") shouldBe expectedResult
        TextSearcherService.parseInput(",") shouldBe expectedResult
        TextSearcherService.parseInput(" ,\n") shouldBe expectedResult
        TextSearcherService.parseInput("  \n  \n") shouldBe expectedResult
    }

    @Test
    fun parseInputWithSpacesAndCommasTest() {
        val expectedResult = listOf("one", "two", "three", "four")
        TextSearcherService.parseInput("one two three four") shouldBe expectedResult
        TextSearcherService.parseInput("one, two, three, four") shouldBe expectedResult
        TextSearcherService.parseInput("    one,two         three , four  ") shouldBe expectedResult
        TextSearcherService.parseInput("\n  one two   \n three ,four \n") shouldBe expectedResult
    }

    @Test
    fun findFilesWithWordsTest() {
        TextSearcherService.checkFiles(listOf("Alice"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt")

        TextSearcherService.checkFiles(listOf("cat", "alice"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt")

        TextSearcherService.checkFiles(listOf("said", "go"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt")

        TextSearcherService.checkFiles(listOf("hare", "then"), testSourceFolderPath) shouldContainAll
                listOf("file3.txt", "file5.txt")
    }

    @Test
    fun isCaseInsensitiveTest() {
        TextSearcherService.checkFiles(listOf("alice"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt")

        TextSearcherService.checkFiles(listOf("ALICE"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt")

        TextSearcherService.checkFiles(listOf("AlIcE"), testSourceFolderPath) shouldContainAll
                listOf("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt")
    }

}
