import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

class LinterTest {
  private val style1: File = File(this::class.java.getResource("/style1.json")!!.file)
  private val style2: File = File(this::class.java.getResource("/style2.json")!!.file)

  @Test
  fun testStyle1() {
    val code = "let myNumber: number = \"1\";\nlet my_int: number = \"2\";\n println(myNumber + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style1)
    val expectedAt0 = "Casing violation at 2:5, camel case expected"
    val expectedAt1 = "Expression inside print statement at 3:2"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle2() {
    val code = "let myNumber: number = \"1\";\nlet my_int: number = \"2\";\n println(myNumber + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style2)
    val expectedAt0 = "Casing violation at 1:5, snake case expected"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0].toString())
  }
}
