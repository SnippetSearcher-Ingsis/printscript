package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.VariableDeclarationNode

object DummyAST {
  fun print(): ASTNode = PrintStatementNode(
    expression = LiteralNode("\"Hello World\""),
    position = Position(0, 0)
  )

  fun declaration(): ASTNode = VariableDeclarationNode(
    identifier = "variable",
    valueType = "String",
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

  fun ifElse(): ASTNode = IfElseNode(
    ifBranch = listOf(
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    ),
    elseBranch = listOf(
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    ),
    condition = LiteralNode("true")
  )
}
