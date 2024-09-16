package com.printscript.interpreter

import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.util.Context
import org.junit.jupiter.api.Test

class ContextTest {
  @Test
  fun testConstructor() {
    Context()
  }

  @Test
  fun testPut() {
    val context = Context()
    context.put("hello", Variable("string", "world"))
    assert(context get "hello" == Variable("string", "world"))
  }

  @Test
  fun testClear() {
    val context = Context()
    context.put("hello", Variable("string", "world"))
    context.clear()
    assert("hello" !in context)
  }

  @Test
  fun testClone() {
    val context = Context()
    context.put("hello", Variable("string", "world"))
    val clone = context.clone()
    assert(clone get "hello" == Variable("string", "world"))
  }

  @Test fun testReplace() {
    val context = Context()
    context.put("hello", Variable("string", "world"))
    val newContext = Context()
    newContext.put("hello", Variable("string", "universe"))
    newContext.put("world", Variable("string", "hello"))
    context replace newContext
    assert(context get "hello" == Variable("string", "universe"))
    assert("world" !in context)
  }
}
