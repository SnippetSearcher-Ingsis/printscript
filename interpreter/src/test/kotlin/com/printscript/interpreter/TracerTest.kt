package com.printscript.interpreter

import com.printscript.interpreter.tracer.ReadableTracer
import org.junit.jupiter.api.Test

class TracerTest {
  @Test
  fun testReadableTracer() {
    val tracer = ReadableTracer()
    tracer.print("Hello, world!")
    assert(tracer.getOutput() == listOf("Hello, world!"))
  }
}
