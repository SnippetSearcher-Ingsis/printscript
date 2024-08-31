import node.ASTNode
import node.AssignationNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode

object DummyAST {
  fun print(): ASTNode = PrintStatementNode(
    expression = LiteralNode("\"Hello World\""),
    position = Position(0, 0)
  )

  fun declaration(): ASTNode = VariableDeclarationNode(
    variable = "variable",
    variableType = "String",
    expression = LiteralNode("\"Hello World\""),
    position = Position(0, 0)
  )

  fun assignation(): ASTNode =
    AssignationNode(
      variable = "variable",
      expression = LiteralNode("\"Hello Universe\""),
      position = Position(0, 0)
    )

  fun doubleExpressionAndPrint(): ASTNode = PrintStatementNode(
    expression = LiteralNode("a"),
    position = Position(0, 0)
  )
}
