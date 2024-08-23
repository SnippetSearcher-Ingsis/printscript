import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class FormatterTest {
  @Test
  fun testPrint() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.print(), file)
    assertEquals("println(\"Hello World\");\n", result)
  }

  @Test
  fun testStyle1Declaration() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.declaration(), file)
    assertEquals("let variable: String = \"Hello World\";\n", result)
  }

  @Test
  fun testStyle2Declaration() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.declaration(), file)
    assertEquals("let variable :String=\"Hello World\";\n", result)
  }

  @Test
  fun testStyle1Assignation() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.assignation(), file)
    val expected = "let variable: String = \"Hello World\";\nvariable = \"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2Assignation() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.assignation(), file)
    val expected = "let variable :String=\"Hello World\";\nvariable=\"Hello Universe\";\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style1.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.doubleExpressionAndPrint(), file)
    val expected = "let a: number = 1 + (2 * 3);\nprintln(a);\n"
    assertEquals(expected, result)
  }

  @Test
  fun testStyle2DoubleExpressionAndPrint() {
    val formatter = Formatter()
    val resource = this::class.java.getResource("/style2.json")!!.file
    val file = File(resource)
    val result = formatter.format(DummyAST.doubleExpressionAndPrint(), file)
    val expected = "let a :number=1 + (2 * 3);\n\n\nprintln(a);\n"
    assertEquals(expected, result)
  }
}
