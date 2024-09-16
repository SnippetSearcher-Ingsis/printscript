package com.printscript.interpreter

import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.output.ReadableOutput
import org.junit.jupiter.api.Test

class IOTest {
  @Test
  fun testConsoleInput() {
    val input = ConsoleInput()
    input.read("test")
  }

  @Test
  fun testConsoleOutput() {
    val output = ConsoleOutput()
    output.write("test")
  }

  @Test
  fun testReadableOutput() {
    val output = ReadableOutput()
    output.write("Hello, world!")
    assert(output.getOutput() == listOf("Hello, world!"))
  }
}
