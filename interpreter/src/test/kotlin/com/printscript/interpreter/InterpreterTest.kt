package com.printscript.interpreter

import org.junit.jupiter.api.Test

class InterpreterTest {
  @Test
  fun testPrintInterpreter() {
    val interpreter = PrintInterpreter()
    interpreter interpret DummyAST.print.iterator()
  }

  @Test
  fun testAssignationException() {
    val interpreter = CatchableInterpreter(PrintInterpreter())
    interpreter interpret DummyAST.invalidAssignation.iterator()
    assert(interpreter.getException() is AssignationException)
  }

  @Test
  fun testDeclarationException() {
    val interpreter = CatchableInterpreter(PrintInterpreter())
    interpreter interpret DummyAST.invalidDeclaration.iterator()
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testOperationException() {
    val interpreter = CatchableInterpreter(PrintInterpreter())
    interpreter interpret DummyAST.invalidOperation.iterator()
    println(interpreter.getException())
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testReferenceException() {
    val interpreter = CatchableInterpreter(PrintInterpreter())
    interpreter interpret DummyAST.invalidReference.iterator()
    assert(interpreter.getException() is ReferenceException)
  }

  @Test
  fun testInvalidOperator() {
    val interpreter = CatchableInterpreter(PrintInterpreter())
    interpreter interpret DummyAST.invalidOperator.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testErrorNodeBackdoor() {
    val interpreter = PrintInterpreter()
    interpreter interpret DummyAST.errorNodeBackdoor.iterator()
  }

  @Test
  fun testReadEnv() {
    val interpreter = PrintInterpreter()
    interpreter interpret DummyAST.readEnv.iterator()
  }
}
