package com.printscript.interpreter

import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_0
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.interpreter.util.Tester
import org.junit.jupiter.api.Test

class InterpreterTest {
  private val output = ReadableOutput()

  private val interpreter = CatchableInterpreter(
    Interpreter builder {
      this setInput DummyInput()
      this setOutput output
      this setProvider VERSION_1_1
    }
  )

  private val retroInterpreter = CatchableInterpreter(
    Interpreter builder {
      this setInput DummyInput()
      this setOutput output
      this setProvider VERSION_1_0
    }
  )

  @Test
  fun testInvalidType() {
    interpreter.interpret(ErrorAST.invalidType.iterator())
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testInvalidReadEnv() {
    retroInterpreter interpret ErrorAST.readEnv.iterator()
    assert(retroInterpreter.getException()?.message == "Unsupported node: ReadEnvNode")
  }

  @Test
  fun testInvalidReadInput() {
    retroInterpreter interpret ErrorAST.readInput.iterator()
    assert(retroInterpreter.getException()?.message == "Unsupported node: ReadInputNode")
  }

  @Test
  fun testInvalidConstantDeclaration() {
    retroInterpreter interpret ErrorAST.invalidDeclaration.iterator()
    assert(retroInterpreter.getException()?.message == "Unsupported node: ConstantDeclarationNode")
  }

  @Test
  fun testInvalidVariableDeclaration() {
    interpreter interpret ErrorAST.variableInvalidType.iterator()
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testAssignationException() {
    interpreter interpret ErrorAST.invalidAssignation.iterator()
    assert(interpreter.getException() is AssignationException)
  }

  @Test
  fun testDeclarationException() {
    interpreter interpret ErrorAST.invalidDeclaration.iterator()
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testInvalidDivision() {
    interpreter interpret ErrorAST.invalidDivision.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testInvalidSubtraction() {
    interpreter interpret ErrorAST.invalidSubtraction.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testInvalidMultiplication() {
    interpreter interpret ErrorAST.invalidMultiplication.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testInvalidAddition() {
    interpreter interpret ErrorAST.invalidAddition.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testReferenceException() {
    interpreter interpret ErrorAST.invalidReference.iterator()
    assert(interpreter.getException() is ReferenceException)
  }

  @Test
  fun testInvalidOperator() {
    interpreter interpret ErrorAST.invalidOperator.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testIntegralValid() {
    val tester = Tester("integral_valid", IntegralAST.valid.iterator())
    tester.test()
    assert(!interpreter.hasException())
  }

  @Test
  fun testInvalidAssignationType() {
    interpreter interpret ErrorAST.invalidAssignationType.iterator()
    println(interpreter.getException())
    assert(interpreter.getException() is AssignationException)
  }

  @Test
  fun testRepeatedVariable() {
    interpreter interpret ErrorAST.repeatedVariable.iterator()
    assert(interpreter.getException() is DeclarationException)
  }

  @Test
  fun testRepeatedConstant() {
    interpreter interpret ErrorAST.repeatedConstant.iterator()
    assert(interpreter.getException() is DeclarationException)
  }
}
