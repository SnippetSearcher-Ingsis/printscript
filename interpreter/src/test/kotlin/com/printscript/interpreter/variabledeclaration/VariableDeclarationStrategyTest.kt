package com.printscript.interpreter.variabledeclaration

import com.printscript.interpreter.CatchableInterpreter
import com.printscript.interpreter.DummyInput
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import org.junit.jupiter.api.Test

class VariableDeclarationStrategyTest {
  private val interpreter = CatchableInterpreter(
    Interpreter builder {
      this setInput DummyInput()
      this setOutput ConsoleOutput()
      this setProvider VERSION_1_1
    }
  )

  @Test
  fun testNullReference() {
    interpreter interpret VariableDeclarationAST.nullReference.iterator()
    assert(interpreter.getException() is ReferenceException)
  }
}
