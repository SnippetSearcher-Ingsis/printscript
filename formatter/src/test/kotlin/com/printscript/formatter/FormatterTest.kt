package com.printscript.formatter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class FormatterTest {
  private val style1: File = File(this::class.java.getResource("/style1.json")!!.file)
  private val style2: File = File(this::class.java.getResource("/style2.json")!!.file)
  private val style3: File = File(this::class.java.getResource("/style3.json")!!.file)
  private val invalid1: File = File(this::class.java.getResource("/invalid1.json")!!.file)
  private val invalid2: File = File(this::class.java.getResource("/invalid2.json")!!.file)
  private val invalid3: File = File(this::class.java.getResource("/invalid3.json")!!.file)

  @Test
  fun testPrint() {
    val actual = Formatter.format(DummyAST.print, style1)
    assertEquals("println(\"Hello World\");\n", actual)
  }

  @Test
  fun testStyle1Declaration() {
    val actual = Formatter.format(DummyAST.declaration, style1)
    assertEquals("let variable: String = \"Hello World\";\n", actual)
  }

  @Test
  fun testStyle2Declaration() {
    val actual = Formatter.format(DummyAST.declaration, style2)
    assertEquals("let variable :String=\"Hello World\";\n", actual)
  }

  @Test
  fun testStyle1Assignation() {
    val actual = Formatter.format(DummyAST.assignation, style1)
    val expected = "variable = \"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2Assignation() {
    val actual = Formatter.format(DummyAST.assignation, style2)
    val expected = "variable=\"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val actual = Formatter.format(DummyAST.doubleExpressionAndPrint, style1)
    val expected = "println(a);\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2Integral() {
    val actual = Formatter.format(DummyAST.integral, style3)
    val expected =
      "if (true) {\n" +
        "   readEnv(2);\n" +
        "   println(3 - (1 * 4));\n" +
        "\n" +
        "\n" +
        "   let variable: String = \"Hello World\";\n" +
        "}\n" +
        "else {\n" +
        "   let variable: String = \"Hello World\";\n" +
        "   readInput(hola);\n" +
        "   const constant: number = 2;\n" +
        "}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle1IfElse() {
    val actual = Formatter.format(DummyAST.ifElse, style1)
    val expected =
      "if (true) {\n  let variable: String = \"Hello World\";\n}\n" +
        "else {\n  let variable: String = \"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2IfElse() {
    val actual = Formatter.format(DummyAST.ifElse, style2)
    val expected =
      "if (true)\n{\n    let variable :String=\"Hello World\";\n}\n" +
        "else\n{\n    let variable :String=\"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testInvalidStyles() {
    assertThrows<IllegalArgumentException> { Formatter.format(DummyAST.assignation, invalid1) }
    assertThrows<IllegalArgumentException> { Formatter.format(DummyAST.assignation, invalid2) }
    assertThrows<IllegalArgumentException> { Formatter.format(DummyAST.assignation, invalid3) }
  }
}
// holi
