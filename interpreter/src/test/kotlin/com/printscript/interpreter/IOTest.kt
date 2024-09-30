package com.printscript.interpreter

import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.output.ReadableOutput
import org.junit.jupiter.api.Test

class IOTest {
  @Test
  fun testConsoleInput() {
    val input = ConsoleInput()
    System.setIn("test\n".byteInputStream())
    assert(input read "test" == "test")
    System.setIn("".byteInputStream())
    assert(input read "test" == "")
    assert(input read "" == "")
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
