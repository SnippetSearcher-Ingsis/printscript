package com.printscript.formatter

import com.google.gson.Gson
import com.printscript.lexer.Lexer
import com.printscript.parser.CatchableParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class FormatterTest {
  fun fileToString(fileName: String): String {
    val inputStream = this::class.java.classLoader.getResourceAsStream(fileName)
    return (inputStream?.bufferedReader().use { it?.readText() })?.replace("\r", "")
      ?: throw IllegalArgumentException("File not found: $fileName")
  }
  private val gson = Gson()
  private val lexer = Lexer()
  private val parser = CatchableParser()
  private fun generateASTs(name: String): CatchableParser.CatchableParserIterator {
    return parser.parse(lexer.lex(File("src/test/resources/$name.ts").reader()))
  }
  private fun generateFormatter(name: String): Formatter {
    return Formatter(gson.fromJson(File(this::class.java.getResource("/$name.json")!!.file).readText(), FormatterConfig::class.java))
  }
  private val formatter1 = generateFormatter("style1")
  private val formatter2 = generateFormatter("style2")
  private val formatter3 = generateFormatter("style3")

  @Test
  fun testPrint() {
    val actual = formatter1.format(listOf(DummyAST.print).iterator())
    assertEquals("println(\"Hello World\");\n", actual)
  }

  @Test
  fun testStyle1Declaration() {
    val actual = formatter1.format(listOf(DummyAST.declaration).iterator())
    assertEquals("let variable: String = \"Hello World\";\n", actual)
  }

  @Test
  fun testStyle2Declaration() {
    val actual = formatter2.format(listOf(DummyAST.declaration).iterator())
    assertEquals("let variable :String=\"Hello World\";\n", actual)
  }

  @Test
  fun testStyle1Assignation() {
    val actual = formatter1.format(listOf(DummyAST.assignation).iterator())
    val expected = "variable = \"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2Assignation() {
    val actual = formatter2.format(listOf(DummyAST.assignation).iterator())
    val expected = "variable=\"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val actual = formatter1.format(listOf(DummyAST.doubleExpressionAndPrint).iterator())
    val expected = "println(a);\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle3Integral() {
    val actual = formatter3.format(listOf(DummyAST.integral).iterator())
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
    val actual = formatter1.format(listOf(DummyAST.ifElse).iterator())
    val expected =
      "if (true) {\n  let variable: String = \"Hello World\";\n}\n" +
        "else {\n  let variable: String = \"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2IfElse() {
    val actual = formatter2.format(listOf(DummyAST.ifElse).iterator())
    val expected =
      "if (true)\n{\n    let variable :String=\"Hello World\";\n}\n" +
        "else\n{\n    let variable :String=\"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle3ManyStatements() {
    val actual = formatter3.format(generateASTs("bigAssCode"))
    val expected = fileToString("bigAssCodeGolden.txt")
    assertEquals(expected, actual)
  }

  @Test
  fun testInvalidStyles() {
    assertThrows<IllegalParameterException> { generateFormatter("invalid1") }
    assertThrows<IllegalParameterException> { generateFormatter("invalid2") }
    assertThrows<IllegalParameterException> { generateFormatter("invalid3") }
  }
}
// holi
