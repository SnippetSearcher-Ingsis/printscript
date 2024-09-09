package com.printscript.interpreter

import com.printscript.interpreter.tracer.ReadableTracer
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

  @Test
  fun testReadInput() {
    val tracer = ReadableTracer()
    val input = DummyInput(tracer)
    val interpreter = TracingInterpreter(tracer, input)
    interpreter interpret DummyAST.readInput.iterator()
    assert(tracer.getOutput() == listOf("best football club = ", "Newell's Old Boys"))
  }
}
