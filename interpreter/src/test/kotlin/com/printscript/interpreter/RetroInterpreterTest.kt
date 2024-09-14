package com.printscript.interpreter

import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_0
import org.junit.jupiter.api.Test

class RetroInterpreterTest {
  private val interpreter = CatchableInterpreter(
    Interpreter builder {
      add input ConsoleInput()
      add output ConsoleOutput()
      add provider VERSION_1_0
    }
  )

  @Test
  fun testReadEnv() {
    interpreter interpret DummyAST.readEnv.iterator()
    assert(interpreter.getException()?.message == "Unsupported node: ReadEnvNode")
  }

  @Test
  fun testReadInput() {
    interpreter interpret DummyAST.readInput.iterator()
    assert(interpreter.getException()?.message == "Unsupported node: ReadInputNode")
  }

  @Test
  fun testConstantDeclaration() {
    interpreter interpret DummyAST.invalidDeclaration.iterator()
    assert(interpreter.getException()?.message == "Unsupported node: ConstantDeclarationNode")
  }
}
