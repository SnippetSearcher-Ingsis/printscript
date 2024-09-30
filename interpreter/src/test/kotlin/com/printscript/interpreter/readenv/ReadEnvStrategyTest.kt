package com.printscript.interpreter.readenv

import com.printscript.interpreter.CatchableInterpreter
import com.printscript.interpreter.DummyInput
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import org.junit.jupiter.api.Test

class ReadEnvStrategyTest {
  private val interpreter = CatchableInterpreter(
    Interpreter builder {
      this setInput DummyInput()
      this setOutput ConsoleOutput()
      this setProvider VERSION_1_1
    }
  )

  @Test
  fun testReadEnvNull() {
    interpreter.interpret(ReadEnvAST.readEnvNull.iterator())
    assert(interpreter.getException() is ReferenceException)
  }

  @Test
  fun testReadEnvBoolean() {
    interpreter.interpret(ReadEnvAST.readEnvBoolean.iterator())
    assert(interpreter.getException() is ReferenceException)
  }

  @Test
  fun testReadEnvNotFound() {
    interpreter.interpret(ReadEnvAST.readEnvNotFound.iterator())
    assert(interpreter.getException() is ReferenceException)
  }
}
