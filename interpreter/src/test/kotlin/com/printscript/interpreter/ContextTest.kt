package com.printscript.interpreter

import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.util.Context
import org.junit.jupiter.api.Test

class ContextTest {
  @Test
  fun contextConstructorTest() {
    Context()
  }

  @Test
  fun contextClearTest() {
    val context = Context()
    context.put("hello", Variable("string", "world"))
    context.clear()
    assert("hello" !in context)
  }
}
