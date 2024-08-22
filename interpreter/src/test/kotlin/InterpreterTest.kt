import catchable.CatchableTracingInterpreter
import errors.AssignationError
import errors.DeclarationError
import errors.OperationError
import errors.ReferenceError
import interpreter.Context
import interpreter.Interpreter
import logger.TracingInterpreter
import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {
  @Test
  fun testDeclaration() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == "world")
  }

  @Test
  fun testAssignation() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode("\"universe\""),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == "universe")
  }

  @Test
  fun testAssignationWithDifferentType() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<AssignationError> { interpreter interpret ast }
  }

  @Test
  fun testAddition() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "+"
        ),
        Position(0, 0)
      ),
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == 3)
  }

  @Test
  fun testSubtraction() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "-"
        ),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == -1)
  }

  @Test
  fun testMultiplication() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "*"
        ),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == 2)
  }

  @Test
  fun testDivision() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "/"
        ),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == 0.5)
  }

  @Test
  fun testStringAddition() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "+"
        ),
        Position(0, 0)
      ),
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == "hello world")
  }

  @Test
  fun testAdditionWithDifferentType() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode(1),
          operator = "+"
        ),
        Position(0, 0)
      ),
    )
    val interpreter = Interpreter()
    interpreter interpret ast
    assert(Context get "hello" == "hello1")
  }

  @Test
  fun testStringPrint() {
    val ast = listOf<ASTNode>(
      PrintStatementNode(
        LiteralNode("\"HOLA XOACO\""),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast)
    assert(interpreter.getLog() == listOf("HOLA XOACO"))
  }

  @Test
  fun testVariablePrint() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
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
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast)
    assert(interpreter.getLog() == listOf("world"))
  }

  @Test
  fun testNumber() {
    val ast = listOf<ASTNode>(
      LiteralNode(1),
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast)
    assert(interpreter.getLog() == emptyList<String>())
  }

  @Test
  fun testString() {
    val ast = listOf<ASTNode>(
      LiteralNode("xoaco"),
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast)
    assert(interpreter.getLog() == emptyList<String>())
  }

  @Test
  fun testDoubleExpression() {
    val ast = listOf<ASTNode>(
      DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(1),
        operator = "-",
      )
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast)
    assert(interpreter.getLog() == emptyList<String>())
  }

  @Test
  fun testStringSubtractionFailure() {
    kotlin.runCatching {
      val ast = listOf<ASTNode>(
        DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "-",

        ),
      )
      val interpreter = Interpreter()
      interpreter.interpret(ast)
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testStringMultiplicationFailure() {
    kotlin.runCatching {
      val ast = listOf<ASTNode>(
        DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "*",

        ),
      )
      val interpreter = Interpreter()
      interpreter.interpret(ast)
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testStringDivisionFailure() {
    kotlin.runCatching {
      val ast = listOf<ASTNode>(
        DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "/",

        ),
      )
      val interpreter = Interpreter()
      interpreter.interpret(ast)
    }.onSuccess {
      assert(false)
    }.onFailure {
      assert(true)
    }
  }

  @Test
  fun testInvalidOperatorFailure() {
    val ast = listOf<ASTNode>(
      DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(1),
        operator = "pdmwapdmap",
      ),
    )
    val interpreter = Interpreter()
    assertThrows<OperationError> { interpreter.interpret(ast) }
  }

  @Test
  fun testAssignationFailure() {
    val ast = listOf<ASTNode>(
      AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)

      )
    )
    val interpreter = Interpreter()
    assertThrows<AssignationError> { interpreter interpret ast }
  }

  @Test
  fun testDeclarationFailure() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<DeclarationError> { interpreter interpret ast }
  }

  @Test
  fun testReferenceError() {
    val ast = listOf(
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<ReferenceError> { interpreter interpret ast }
  }

  @Test
  fun testInvalidNodeType() {
    val ast = listOf(
      PrintStatementNode(
        DummyNode(),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<OperationError> { interpreter interpret ast }
  }

  @Test
  fun testInvalidAddition() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode(listOf(1, 2)),
        Position(0, 0)
      ),
      DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode("hello"),
        operator = "+"
      )
    )
    val interpreter = Interpreter()
    assertThrows<OperationError> { interpreter interpret ast }
  }

  @Test
  fun testRedeclaration() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        position = Position(0, 0)
      ),
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"universe\""),
        position = Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<DeclarationError> { interpreter interpret ast }
  }

  @Test
  fun tracingInterpreterAssignationTest() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode("\"universe\""),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast
  }

  @Test
  fun catchableTracingInterpreterTest() {
    val ast = listOf<ASTNode>(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode(1),
        Position(0, 0)
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = CatchableTracingInterpreter()
    interpreter interpret ast
    assert(interpreter.hasError())
    assert(interpreter.getError() is AssignationError)
    assert(interpreter.getLog() == emptyList<String>())
  }
}
