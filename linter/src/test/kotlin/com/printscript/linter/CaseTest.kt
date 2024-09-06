package com.printscript.linter

import com.printscript.linter.rule.caseTypes.CamelCase
import com.printscript.linter.rule.caseTypes.KebabCase
import com.printscript.linter.rule.caseTypes.PascalCase
import com.printscript.linter.rule.caseTypes.ScreamingKebabCase
import com.printscript.linter.rule.caseTypes.ScreamingSnakeCase
import com.printscript.linter.rule.caseTypes.SnakeCase
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CaseTest {
  @Test
  fun testCamelCase() {
    val input = "camelCase"
    val result = CamelCase.check(input)
    assertTrue(result)
    val input2 = "camel_case"
    val result2 = CamelCase.check(input2)
    assertFalse(result2)
  }

  @Test
  fun testPascalCase() {
    val input = "PascalCase"
    val result = PascalCase.check(input)
    assertTrue(result)
    val input2 = "pascal_case"
    val result2 = PascalCase.check(input2)
    assertFalse(result2)
  }

  @Test
  fun testKebabCase() {
    val input = "kebab-case"
    val result = KebabCase.check(input)
    assertTrue(result)
    val input2 = "kebab_case"
    val result2 = KebabCase.check(input2)
    assertFalse(result2)
  }

  @Test
  fun testScreamingKebabCase() {
    val input = "SCREAMING-KEBAB-CASE"
    val result = ScreamingKebabCase.check(input)
    assertTrue(result)
    val input2 = "screaming_kebab_case"
    val result2 = ScreamingKebabCase.check(input2)
    assertFalse(result2)
  }

  @Test
  fun testSnakeCase() {
    val input = "snake_case"
    val result = SnakeCase.check(input)
    assertTrue(result)
    val input2 = "snake-case"
    val result2 = SnakeCase.check(input2)
    assertFalse(result2)
  }

  @Test
  fun testScreamingSnakeCase() {
    val input = "SCREAMING_SNAKE_CASE"
    val result = ScreamingSnakeCase.check(input)
    assertTrue(result)
    val input2 = "screaming-snake-case"
    val result2 = ScreamingSnakeCase.check(input2)
    assertFalse(result2)
  }
}
