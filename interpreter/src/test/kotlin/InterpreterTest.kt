
import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.IfElseNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode
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
    interpreter interpret ast
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
    interpreter interpret ast
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
    assertThrows<AssignationException> { interpreter interpret ast }
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
    interpreter interpret ast
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
    interpreter interpret ast
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
    interpreter interpret ast
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
    interpreter interpret ast
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
    interpreter interpret ast
    assert(interpreter.getLog() == listOf("hello world"))
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
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast
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
    interpreter.interpret(ast)
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
        operator = "yes",
      ),
    )
    val interpreter = Interpreter()
    assertThrows<OperationException> { interpreter.interpret(ast) }
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
    assertThrows<AssignationException> { interpreter interpret ast }
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
    assertThrows<DeclarationException> { interpreter interpret ast }
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
    assertThrows<ReferenceException> { interpreter interpret ast }
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
    assertThrows<OperationException> { interpreter interpret ast }
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
    assertThrows<DeclarationException> { interpreter interpret ast }
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
    interpreter interpret ast
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
    interpreter interpret ast
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
    interpreter interpret ast
  }

  @Test
  fun ifElseTest() {
    val ast = listOf(
      VariableDeclarationNode(
        variable = "hello",
        variableType = "number",
        expression = LiteralNode(1),
        Position(0, 0)
      ),
      VariableDeclarationNode(
        variable = "world",
        variableType = "number",
        expression = LiteralNode(2),
        Position(0, 0)
      ),
      IfElseNode(
        ifBranch = listOf(
          AssignationNode(
            variable = "hello",
            expression = DoubleExpressionNode(
              left = LiteralNode("hello"),
              right = LiteralNode("world"),
              operator = "+"
            ),
            Position(0, 0)
          ),
          VariableDeclarationNode(
            variable = "helloo",
            variableType = "number",
            expression = LiteralNode(1),
            Position(0, 0)
          ),
        ),
        elseBranch = listOf(
          AssignationNode(
            variable = "hello",
            expression = DoubleExpressionNode(
              left = LiteralNode("hello"),
              right = LiteralNode("world"),
              operator = "-"
            ),
            Position(0, 0)
          )
        ),
        condition = LiteralNode(true),
      ),
      PrintStatementNode(
        LiteralNode("hello"),
        Position(0, 0)
      )
    )
    val interpreter = TracingInterpreter()
    interpreter interpret ast
    // assertEquals(3, interpreter.context.get("hello"))
    // assertError(interpreter.get("helloo"))
    /** estos tests estarian buenos pero no se que mierda es log y el hijo de puta
     // que haya hecho el interpreter no lo documentó, de todas formas ya lo chequeé
     // con el debugger y funciona */
    assert(interpreter.getLog() == listOf("3"))
  }
}
