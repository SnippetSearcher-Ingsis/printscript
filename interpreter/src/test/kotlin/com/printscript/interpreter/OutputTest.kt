package com.printscript.interpreter

import com.printscript.interpreter.output.ReadableOutput
import org.junit.jupiter.api.Test

class OutputTest {
  @Test
  fun testReadableOutput() {
    val output = ReadableOutput()
    output.write("Hello, world!")
    assert(output.getOutput() == listOf("Hello, world!"))
  }
}
