package com.printscript.linter

import com.google.gson.Gson
import com.printscript.lexer.Lexer
import com.printscript.models.node.ASTNode
import com.printscript.parser.PrintParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class LinterTest {
  private val gson = Gson()
  private val lexer = Lexer()
  private val printParser = PrintParser()
  private fun generateASTs(name: String): Iterator<ASTNode> {
    return printParser.parse(lexer.lex(File("src/test/resources/$name.ts").reader()))
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

  private fun generateResult(linter: Linter, asts: Iterator<ASTNode>): List<String> {
    val result = mutableListOf<String>()
    while (asts.hasNext()) {
      linter.lint(asts.next()).forEach { violation ->
        result.add(violation.toString())
      }
    }
    return result
  }

  @Test
  fun testStyle1() {
    val astList = generateASTs("style1")
    val result = generateResult(linter1, astList)
    val expectedAt0 = "Casing violation at 2:5, camel case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0])
    assertEquals(expectedAt1, result[1])
  }

  @Test
  fun testStyle2() {
    val astList = generateASTs("style2")
    val result = generateResult(linter2, astList)
    val expectedAt0 = "Casing violation at 1:5, snake case expected"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0])
  }

  @Test
  fun testStyle3() {
    val astList = generateASTs("style3")
    val result = generateResult(linter3, astList)
    val expectedAt0 = "Casing violation at 1:5, pascal case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0])
    assertEquals(expectedAt1, result[1])
  }

  @Test
  fun testStyle4() {
    val astList = generateASTs("style4")
    val result = generateResult(linter4, astList)
    val expectedAt0 = "Casing violation at 1:5, kebab case expected"
    val expectedAt1 = "Expression inside print statement at 3:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0])
    assertEquals(expectedAt1, result[1])
  }

  @Test
  fun testStyle5() {
    val astList = generateASTs("style5")
    val result = generateResult(linter5, astList)
    val expectedAt0 = "Casing violation at 2:5, screaming snake case expected"
    val expectedAt1 = "Expression inside print statement at 4:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0])
    assertEquals(expectedAt1, result[1])
  }

  @Test
  fun testStyle6() {
    val astList = generateASTs("style6")
    val result = generateResult(linter6, astList)
    val expectedAt0 = "Casing violation at 2:5, screaming kebab case expected"
    val expectedAt1 = "Expression inside print statement at 5:1"
    assert(result.size == 2)
    assertEquals(expectedAt0, result[0])
    assertEquals(expectedAt1, result[1])
  }

  @Test
  fun testStyle72() {
    val astList = generateASTs("style7")
    val result = generateResult(linter7, astList)
    val expectedAt0 = "Expression inside read input statement at 2:5"
    assert(result.size == 1)
    assertEquals(expectedAt0, result[0])
  }
}
