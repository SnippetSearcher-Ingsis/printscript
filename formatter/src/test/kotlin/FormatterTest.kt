import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class FormatterTest {
  @Test
  fun testPrint() {
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.print(), file)
    assertEquals("println(\"Hello World\");\n", result)
  }

  @Test
  fun testStyle1Declaration() {
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.declaration(), file)
    assertEquals("let variable: String = \"Hello World\";\n", result)
  }

  @Test
  fun testStyle2Declaration() {
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.declaration(), file)
    assertEquals("let variable :String=\"Hello World\";\n", result)
  }

  @Test
  fun testStyle1Assignation() {
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.assignation(), file)
    val expected = "let variable: String = \"Hello World\";\nvariable = \"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2Assignation() {
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.assignation(), file)
    val expected = "let variable :String=\"Hello World\";\nvariable=\"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.doubleExpressionAndPrint(), file)
    val expected = "let a: number = 1 + (2 * 3);\nprintln(a);\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2DoubleExpressionAndPrint() {
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = Formatter.format(DummyAST.doubleExpressionAndPrint(), file)
    val expected = "let a :number=1 + (2 * 3);\n\n\nprintln(a);\n"
    assertEquals(expected, result)
  }
}
