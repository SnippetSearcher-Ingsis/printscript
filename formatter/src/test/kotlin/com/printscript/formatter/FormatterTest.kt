package com.printscript.formatter

import com.google.gson.Gson
import com.printscript.lexer.Lexer
import com.printscript.models.node.ASTNode
import com.printscript.parser.PrintParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class FormatterTest {
  fun fileToString(fileName: String): String {
    val inputStream = this::class.java.classLoader.getResourceAsStream(fileName)
    return inputStream?.bufferedReader().use { it?.readText() } ?: throw IllegalArgumentException("File not found: $fileName")
  }
  private val gson = Gson()
  private val lexer = Lexer()
  private val printParser = PrintParser()
  private fun generateASTs(name: String): Iterator<ASTNode> {
    return printParser.parse(lexer.lex(File("src/test/resources/$name.ts").reader()))
  }
  private fun generateFormatter(name: String): Formatter {
    return Formatter(gson.fromJson(File(this::class.java.getResource("/$name.json")!!.file).readText(), FormatterConfig::class.java))
  }
  private val formatter1 = generateFormatter("style1")
  private val formatter2 = generateFormatter("style2")
  private val formatter3 = generateFormatter("style3")

  private fun generateResult(formatter: Formatter, asts: Iterator<ASTNode>): String {
    val result = StringBuilder()
    while (asts.hasNext()) {
      formatter.format(asts.next()).forEach { violation ->
        result.append(violation.toString())
      }
    }
    return result.toString()
  }

  @Test
  fun testPrint() {
    val actual = formatter1.format(DummyAST.print)
    assertEquals("println(\"Hello World\");\n", actual)
  }

  @Test
  fun testStyle1Declaration() {
    val actual = formatter1.format(DummyAST.declaration)
    assertEquals("let variable: String = \"Hello World\";\n", actual)
  }

  @Test
  fun testStyle2Declaration() {
    val actual = formatter2.format(DummyAST.declaration)
    assertEquals("let variable :String=\"Hello World\";\n", actual)
  }

  @Test
  fun testStyle1Assignation() {
    val actual = formatter1.format(DummyAST.assignation)
    val expected = "variable = \"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2Assignation() {
    val actual = formatter2.format(DummyAST.assignation)
    val expected = "variable=\"Hello Universe\";\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle1DoubleExpressionAndPrint() {
    val actual = formatter1.format(DummyAST.doubleExpressionAndPrint)
    val expected = "println(a);\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle3Integral() {
    val actual = formatter3.format(DummyAST.integral)
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
    val actual = formatter1.format(DummyAST.ifElse)
    val expected =
      "if (true) {\n  let variable: String = \"Hello World\";\n}\n" +
        "else {\n  let variable: String = \"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle2IfElse() {
    val actual = formatter2.format(DummyAST.ifElse)
    val expected =
      "if (true)\n{\n    let variable :String=\"Hello World\";\n}\n" +
        "else\n{\n    let variable :String=\"Hello World\";\n}\n"
    assertEquals(expected, actual)
  }

  @Test
  fun testStyle3ManyStatements() {
    val actual = generateResult(formatter3, generateASTs("bigAssCode"))
    val expected = fileToString("bigAssCodeGolden.txt")
    assertEquals(expected, actual)
  }

  @Test
  fun testInvalidStyles() {
    assertThrows<IllegalArgumentException> { generateFormatter("invalid1") }
    assertThrows<IllegalArgumentException> { generateFormatter("invalid2") }
    assertThrows<IllegalArgumentException> { generateFormatter("invalid3") }
  }
}
// holi
