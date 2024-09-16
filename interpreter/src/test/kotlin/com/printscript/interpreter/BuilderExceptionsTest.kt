package com.printscript.interpreter

import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BuilderExceptionsTest {
  @Test
  fun testNullInput() {
    assertThrows<BuilderException> {
      Interpreter builder {
        this.setOutput(ConsoleOutput())
        this.setProvider(VERSION_1_1)
      }
    }
  }

  @Test
  fun testNullOutput() {
    assertThrows<BuilderException> {
      Interpreter builder {
        this.setInput(DummyInput())
        this.setProvider(VERSION_1_1)
      }
    }
  }

  @Test
  fun testNullProvider() {
    assertThrows<BuilderException> {
      Interpreter builder {
        this.setInput(DummyInput())
        this.setOutput(ConsoleOutput())
      }
    }
  }
}
