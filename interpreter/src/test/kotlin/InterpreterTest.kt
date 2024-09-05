import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode
import node.VariableNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {
  @Test
  fun testDeclaration() {
    val ast = listOf(
      VariableDeclarationNode(
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
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("world"))
  }

  @Test
  fun testAssignation() {
    val ast = listOf(
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
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("universe"))
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
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testAddition() {
    val ast = listOf(
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
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("3"))
  }

  @Test
  fun testSubtraction() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
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
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("-1"))
  }

  @Test
  fun testMultiplication() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
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
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("2"))
  }

  @Test
  fun testDivision() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = DoubleExpressionNode(
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
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("0.5"))
  }

  @Test
  fun testStringAddition() {
    val ast = listOf(
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
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("hello world"))
  }

  @Test
  fun testAdditionWithDifferentType() {
    val ast = listOf(
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
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("hello1"))
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
    interpreter.interpret(ast.iterator())
    assert(interpreter.getLog() == listOf("HOLA XOACO"))
  }

  @Test
  fun testVariablePrint() {
    val ast = listOf(
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
    interpreter.interpret(ast.iterator())
    assert(interpreter.getLog() == listOf("world"))
  }

  @Test
  fun testNumber() {
    val ast = listOf<ASTNode>(
      LiteralNode(1),
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast.iterator())
    assert(interpreter.getLog() == emptyList<String>())
  }

  @Test
  fun testString() {
    val ast = listOf<ASTNode>(
      LiteralNode("xoaco"),
    )
    val interpreter = TracingInterpreter()
    interpreter.interpret(ast.iterator())
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
    interpreter.interpret(ast.iterator())
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
      val ast = listOf<ASTNode>(
        DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "*",

        ),
      )
      val interpreter = Interpreter()
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
      val ast = listOf<ASTNode>(
        DoubleExpressionNode(
          left = LiteralNode("\"hello\""),
          right = LiteralNode("\" world\""),
          operator = "/",

        ),
      )
      val interpreter = Interpreter()
      interpreter.interpret(ast.iterator())
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
        operator = "yes",
      ),
    )
    val interpreter = Interpreter()
    assertThrows<OperationException> { interpreter.interpret(ast.iterator()) }
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
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
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
    val interpreter = Interpreter()
    assertThrows<ReferenceException> { interpreter interpret ast.iterator() }
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
    assertThrows<OperationException> { interpreter interpret ast.iterator() }
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
    assertThrows<DeclarationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun tracingInterpreterAssignationTest() {
    val ast = listOf(
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
    interpreter interpret ast.iterator()
  }

  @Test
  fun catchableTracingInterpreterTest() {
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
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = CatchableTracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.hasException())
    assert(interpreter.getException() is AssignationException)
    assert(interpreter.getLog() == emptyList<String>())
  }

  @Test
  fun testInterpreter() {
    val interpreter = Interpreter()
    val ast = listOf(
      DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(2),
        operator = "+"
      )
    )
    interpreter interpret ast.iterator()
  }

  @Test
  fun testErrorNode() {
    val ast = listOf(ErrorNode("Error"))
    val interpreter = CatchableTracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.hasException())
  }

  @Test
  fun testUnsupportedOperation() {
    val ast = listOf(
      DoubleExpressionNode(
        left = LiteralNode(1),
        right = LiteralNode(2),
        operator = "?",
      )
    )
    val interpreter = CatchableTracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testOperationError() {
    val ast = listOf(
      DoubleExpressionNode(
        left = ErrorNode("Error"),
        right = PrintStatementNode(
          expression = LiteralNode("Hello"),
          position = Position(0, 0),
        ),
        operator = "+"
      )
    )
    val interpreter = CatchableTracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getException() is OperationException)
  }

  @Test
  fun testDeclarationWithoutAssignation() {
    val ast = listOf(
      VariableNode(
        name = "hello",
        type = "string",
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),

      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast.iterator()
    assert(interpreter.getLog() == listOf("world"))
  }

  @Test
  fun testInvalidDeclarationType() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "invalid",
        expression = LiteralNode(1),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<DeclarationException> { interpreter interpret ast.iterator() }
  }

  @Test
  fun testInvalidAssignation() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "string",
        expression = LiteralNode("\"world\""),
        Position(0, 0)
      ),
      AssignationNode(
        variable = "hello",
        expression = LiteralNode(Position(0, 0)),
        Position(0, 0)
      )
    )
    val interpreter = Interpreter()
    assertThrows<AssignationException> { interpreter interpret ast.iterator() }
  }
}
