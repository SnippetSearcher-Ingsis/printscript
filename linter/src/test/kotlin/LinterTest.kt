import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

class LinterTest {
  private val style1: File = File(this::class.java.getResource("/style1.json")!!.file)
  private val style2: File = File(this::class.java.getResource("/style2.json")!!.file)
  private val style3: File = File(this::class.java.getResource("/style3.json")!!.file)
  private val style4: File = File(this::class.java.getResource("/style4.json")!!.file)
  private val style5: File = File(this::class.java.getResource("/style5.json")!!.file)
  private val style6: File = File(this::class.java.getResource("/style6.json")!!.file)

  @Test
  fun testStyle1() {
    val code = "let myNumber: number = 5;\nlet my_int: number = 1;\nprintln(myNumber + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style1)
    val expectedAt0 = "Casing violation at 2:5, camel case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle2() {
    val code = "let myNumber: number = 2;\nlet my_int: number = 2;\nprintln(myNumber + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style2)
    val expectedAt0 = "Casing violation at 1:5, snake case expected"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0].toString())
  }

  @Test
  fun testStyle3() {
    val code = "let myNumber: number = 1;\nlet MyInt: number = 2;\nprintln(myNumber + MyInt);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style3)
    val expectedAt0 = "Casing violation at 1:5, pascal case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle4() {
    val code = "let myNumber: number = 1;\nlet my-int: number = 5;\nprintln(my-number + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style4)
    val expectedAt0 = "Casing violation at 1:5, kebab case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle5() {
    val code = "let MY_NUMBER: number = 7;\nlet my_int: number = 1;\n\nprintln(MY_NUMBER + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style5)
    val expectedAt0 = "Casing violation at 2:5, screaming_snake case expected"
    val expectedAt1 = "Expression inside print statement at 4:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle6() {
    val code = "let MY-NUMBER: number = 1;\nlet my_int: number = 2;\n\n\nprintln(MY-NUMBER + my_int);"
    val tokens = Lexer(code).tokenize()
    val astList = Parser().parse(tokens)
    val result = Linter.lint(astList, style6)
    val expectedAt0 = "Casing violation at 2:5, screaming_kebab case expected"
    val expectedAt1 = "Expression inside print statement at 5:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }
}
