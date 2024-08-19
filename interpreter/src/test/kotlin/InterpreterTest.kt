import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.Position
import node.VariableDeclarationNode
import org.junit.jupiter.api.Test

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
            )
        )
        val interpreter = Interpreter()
        interpreter interpret ast
        assert(Context get "hello" == "world")
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
}
