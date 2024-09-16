package com.printscript.linter

import com.google.gson.Gson
import com.printscript.lexer.Lexer
import com.printscript.parser.CatchableParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class LinterTest {
  private val gson = Gson()
  private val lexer = Lexer()
  private val parser = CatchableParser()
  private fun generateASTs(name: String): CatchableParser.CatchableParserIterator {
    return parser.parse(lexer.lex(File("src/test/resources/$name.ts").reader()))
  }
  private fun generateLinter(name: String): Linter {
    return Linter(gson.fromJson(File(this::class.java.getResource("/$name.json")!!.file).readText(), LinterConfig::class.java))
  }
  private val linter1 = generateLinter("style1")
  private val linter2 = generateLinter("style2")
  private val linter3 = generateLinter("style3")
  private val linter4 = generateLinter("style4")
  private val linter5 = generateLinter("style5")
  private val linter6 = generateLinter("style6")
  private val linter7 = generateLinter("style7")

  @Test
  fun testStyle1() {
    val result = linter1.lint(generateASTs("style1"))
    val expectedAt0 = "Casing violation at 2:5, camel case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle2() {
    val result = linter2.lint(generateASTs("style2"))
    val expectedAt0 = "Casing violation at 1:5, snake case expected"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0].toString())
  }

  @Test
  fun testStyle3() {
    val astList = generateASTs("style3")
    val result = linter3.lint(astList)
    val expectedAt0 = "Casing violation at 1:5, pascal case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle4() {
    val result = linter4.lint(generateASTs("style4"))
    val expectedAt0 = "Casing violation at 1:5, kebab case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle5() {
    val result = linter5.lint(generateASTs("style5"))
    val expectedAt0 = "Casing violation at 2:5, screaming snake case expected"
    val expectedAt1 = "Expression inside print statement at 4:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle6() {
    val result = linter6.lint(generateASTs("style6"))
    val expectedAt0 = "Casing violation at 2:5, screaming kebab case expected"
    val expectedAt1 = "Expression inside print statement at 5:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0].toString())
    assertEquals(expectedAt1, result[1].toString())
  }

  @Test
  fun testStyle7() {
    val result = linter7.lint(generateASTs("style7"))
    val expectedAt0 = "Expression inside read input statement at 2:5"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0].toString())
  }
}
