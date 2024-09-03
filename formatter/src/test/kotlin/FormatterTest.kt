import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class FormatterTest {
  private val style1: File = File(this::class.java.getResource("/style1.json")!!.file)
  private val style2: File = File(this::class.java.getResource("/style2.json")!!.file)

  @Test
  fun testPrint() {
    val result = Formatter.format(DummyAST.print(), style1)
    assertEquals("println(\"Hello World\");\n", result)
  }

  @Test
  fun testStyle1Declaration() {
    val result = Formatter.format(DummyAST.declaration(), style1)
    assertEquals("let variable: String = \"Hello World\";\n", result)
  }

  @Test
  fun testStyle2Declaration() {
    val result = Formatter.format(DummyAST.declaration(), style2)
    assertEquals("let variable :String=\"Hello World\";\n", result)
  }

  @Test
  fun testStyle1Assignation() {
    val result = Formatter.format(DummyAST.assignation(), style1)
    val expected = "variable = \"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2Assignation() {
    val result = Formatter.format(DummyAST.assignation(), style2)
    val expected = "variable=\"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val result = Formatter.format(DummyAST.doubleExpressionAndPrint(), style1)
    val expected = "println(a);\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2DoubleExpressionAndPrint() {
    val result = Formatter.format(DummyAST.doubleExpressionAndPrint(), style2)
    val expected = "\n\nprintln(a);\n"
    assertEquals(expected, result)
  }
}
// holi