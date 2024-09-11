package com.printscript.interpreter

import org.junit.jupiter.api.Test

class RetroInterpreterTest {
  @Test
  fun testReadEnv() {
    val interpreter = CatchableInterpreter(RetroInterpreter(PrintInterpreter()))
    interpreter interpret DummyAST.readEnv.iterator()
    assert(interpreter.getException()?.message == "Unsupported node type: ReadEnvNode in PrintScript v1.0")
  }

  @Test
  fun testReadInput() {
    val interpreter = CatchableInterpreter(RetroInterpreter(PrintInterpreter()))
    interpreter interpret DummyAST.readInput.iterator()
    assert(interpreter.getException()?.message == "Unsupported node type: ReadInputNode in PrintScript v1.0")
  }

  @Test
  fun testConstantDeclaration() {
    val interpreter = CatchableInterpreter(RetroInterpreter(PrintInterpreter()))
    interpreter interpret DummyAST.invalidDeclaration.iterator()
    assert(interpreter.getException()?.message == "Unsupported node type: ConstantDeclarationNode in PrintScript v1.0")
  }
}
