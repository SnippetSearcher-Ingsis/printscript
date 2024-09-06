package com.printscript.interpreter

import com.printscript.interpreter.tracer.ReadableTracer
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {
  @Test
  fun testDeclaration() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("'world'"),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("world"))
  }

  @Test
  fun testAssignation() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode("\"universe\""),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("universe"))
  }

  @Test
  fun testAssignationWithDifferentType() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testAddition() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "+"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("3"))
  }

  @Test
  fun testSubtraction() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "-"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("-1"))
  }

  @Test
  fun testMultiplication() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "*"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("2"))
  }

  @Test
  fun testDivision() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "/"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)

      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("0.5"))
  }

  @Test
  fun testStringAddition() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "+"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("hello world"))
  }

  @Test
  fun testAdditionWithDifferentType() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode(1),
          operator = "+"
        ),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("hello1"))
  }

  @Test
  fun testStringPrint() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      PrintStatementNode(
        LiteralNode("\"HOLA XOACO\""),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter.interpret(ast.iterator())
    assert(tracer.getOutput() == listOf("HOLA XOACO"))
  }

  @Test
  fun testVariablePrint() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("world"))
  }

  @Test
  fun testNumber() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      LiteralNode(1),
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == emptyList<String>())
  }

  @Test
  fun testString() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      LiteralNode("xoaco"),
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == emptyList<String>())
  }

  @Test
  fun testDoubleExpression() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      com.printscript.models.node.DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(1),
        operator = "-",
      )
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == emptyList<String>())
  }

  @Test
  fun testStringSubtractionFailure() {
    kotlin.runCatching {
      val ast = listOf<com.printscript.models.node.ASTNode>(
        com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "-",

        ),
      )
      val interpreter = PrintInterpreter()
      interpreter.interpret(ast.iterator())
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testStringMultiplicationFailure() {
    kotlin.runCatching {
      val ast = listOf<com.printscript.models.node.ASTNode>(
        com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "*",

        ),
      )
      val interpreter = PrintInterpreter()
      interpreter.interpret(ast.iterator())
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testStringDivisionFailure() {
    kotlin.runCatching {
      val ast = listOf<com.printscript.models.node.ASTNode>(
        com.printscript.models.node.DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "/",

        ),
      )
      val interpreter = PrintInterpreter()
      interpreter.interpret(ast.iterator())
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testInvalidOperatorFailure() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      com.printscript.models.node.DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(1),
        operator = "yes",
      ),
    )
    val interpreter = PrintInterpreter()
    assertThrows<OperationException> { interpreter.interpret(ast.iterator()) }
  }

  @Test
  fun testAssignationFailure() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)

      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testDeclarationFailure() {
    val ast = listOf<com.printscript.models.node.ASTNode>(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<DeclarationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testReferenceError() {
    val ast = listOf(
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<ReferenceException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testInvalidAddition() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode(listOf(1, 2)),
        Position(0, 0)
      ),
      com.printscript.models.node.DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode("hello"),
        operator = "+"
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<OperationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testRedeclaration() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        position = Position(0, 0)
      ),
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"universe\""),
        position = Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<DeclarationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun tracingInterpreterAssignationTest() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode("\"universe\""),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    interpreter interpret ast.iterator()
  }

  @Test
  fun catchableTracingInterpreterTest() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val tracer = ReadableTracer()
    val interpreter = CatchableInterpreter(TracingInterpreter(tracer))
    interpreter interpret ast.iterator()
    assert(interpreter.hasException())
    assert(interpreter.getException() is AssignationException)
    assert(tracer.getOutput() == emptyList<String>())
  }

  @Test
  fun testInterpreter() {
    val interpreter = PrintInterpreter()
    val ast = listOf(
      com.printscript.models.node.DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(2),
        operator = "+"
      )
    )
    interpreter interpret ast.iterator()
  }

  @Test
  fun testErrorNode() {
    val ast = listOf(com.printscript.models.node.ErrorNode("Error"))
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    assertThrows<OperationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testErrorNodeBackdoor() {
    val ast = listOf(com.printscript.models.node.ErrorNode("NODE_ERROR_BACKDOOR"))
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
  }

  @Test
  fun testUnsupportedOperation() {
    val ast = listOf(
      com.printscript.models.node.DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(2),
        operator = "?",
      )
    )
    val tracer = ReadableTracer()
    val interpreter = CatchableInterpreter(TracingInterpreter((tracer)))
    interpreter interpret ast.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testOperationError() {
    val ast = listOf(
      com.printscript.models.node.DoubleExpressionNode(
        left = com.printscript.models.node.ErrorNode("Error"),
        right = PrintStatementNode(
          expression = LiteralNode("Hello"),
          position = Position(0, 0),
        ),
        operator = "+"
      )
    )
    val tracer = ReadableTracer()
    val interpreter = CatchableInterpreter(TracingInterpreter(tracer))
    interpreter interpret ast.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testInvalidDeclarationType() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "invalid",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<DeclarationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testInvalidAssignation() {
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "hello",
        expression = LiteralNode(Position(0, 0)),
        Position(0, 0)
      )
    )
    val interpreter = PrintInterpreter()
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun printTrueFalse() {
    val ast = listOf(
      PrintStatementNode(
        expression = LiteralNode("true"),
        position = Position(0, 0)
      ),
      PrintStatementNode(
        expression = LiteralNode("false"),
        position = Position(0, 0)
      ),
    )
    val tracer = ReadableTracer()
    val interpreter = TracingInterpreter(tracer)
    interpreter interpret ast.iterator()
    assert(tracer.getOutput() == listOf("true", "false"))
  }
}
