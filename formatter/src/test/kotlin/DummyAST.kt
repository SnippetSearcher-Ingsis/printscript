import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode

object DummyAST {
  fun print(): List<ASTNode> = listOf(
    PrintStatementNode(
      expression = LiteralNode("\"Hello World\""),
      position = Position(0, 0)
    )
  )

  fun declaration(): List<ASTNode> = listOf(
    VariableDeclarationNode(
      variable = "variable",
      variableType = "String",
      expression = LiteralNode("\"Hello World\""),
      position = Position(0, 0)
    )
  )

  fun assignation(): List<ASTNode> = listOf(
    VariableDeclarationNode(
      variable = "variable",
      variableType = "String",
      expression = LiteralNode("\"Hello World\""),
      position = Position(0, 0)
    ),
    AssignationNode(
      variable = "variable",
      expression = LiteralNode("\"Hello Universe\""),
      position = Position(0, 0)
    )
  )

  fun doubleExpressionAndPrint(): List<ASTNode> = listOf(
    VariableDeclarationNode(
      variable = "a",
      variableType = "number",
      expression = DoubleExpressionNode(
        left = LiteralNode(1),
        operator = "+",
        right = DoubleExpressionNode(
          left = LiteralNode(2),
          operator = "*",
          right = LiteralNode(3)
        )
      ),
      position = Position(0, 0)
    ),
    PrintStatementNode(
      expression = LiteralNode("a"),
      position = Position(0, 0)
    )
  )
}
