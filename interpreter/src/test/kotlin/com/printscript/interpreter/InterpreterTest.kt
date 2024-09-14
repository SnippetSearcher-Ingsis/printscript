package com.printscript.interpreter

import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import org.junit.jupiter.api.Test

class InterpreterTest {
  private val interpreter = CatchableInterpreter(
    Interpreter builder {
      add input DummyInput()
      add output ConsoleOutput()
      add provider VERSION_1_1
    }
  )

  @Test
  fun testPrintInterpreter() {
    interpreter interpret DummyAST.print.iterator()
  }

  @Test
  fun testAssignationException() {
    interpreter interpret DummyAST.invalidAssignation.iterator()
    assert(interpreter.getException() is AssignationException)
  }

  @Test
  fun testDeclarationException() {
    interpreter interpret DummyAST.invalidDeclaration.iterator()
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testOperationException() {
    interpreter interpret DummyAST.invalidOperation.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testReferenceException() {
    interpreter interpret DummyAST.invalidReference.iterator()
    assert(interpreter.getException() is ReferenceException)
  }

  @Test
  fun testInvalidOperator() {
    interpreter interpret DummyAST.invalidOperator.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testErrorNodeBackdoor() {
    interpreter interpret DummyAST.errorNodeBackdoor.iterator()
  }

  @Test
  fun testReadEnv() {
    interpreter interpret DummyAST.readEnv.iterator()
  }

  @Test
  fun testReadInput() {
    val output = ReadableOutput()
    val interpreter = Interpreter builder {
      add input DummyInput()
      add output output
      add provider VERSION_1_1
    }
    interpreter interpret DummyAST.readInput.iterator()
    assert(output.getOutput() == listOf("best football club = ", "Newell's Old Boys"))
  }
}
