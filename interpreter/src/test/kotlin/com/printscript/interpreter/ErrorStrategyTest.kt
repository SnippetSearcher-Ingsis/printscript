package com.printscript.interpreter

import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.ErrorStrategy
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.interpreter.strategy.StrategyProvider
import com.printscript.models.node.ErrorNode
import org.junit.jupiter.api.Test

class ErrorStrategyTest {
  val interpreter = CatchableInterpreter(
    Interpreter builder {
      this setInput DummyInput()
      this setOutput ConsoleOutput()
      this setProvider VERSION_1_1 + StrategyProvider.builder {
        this addStrategy ErrorStrategy()
      }
    }
  )

  @Test
  fun testErrorStrategy() {
    interpreter.interpret(listOf(ErrorNode("")).iterator())
    assert(interpreter.hasException())
    assert(interpreter.getException() is Exception)
  }
}
