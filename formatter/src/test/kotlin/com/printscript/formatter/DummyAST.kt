package com.printscript.formatter

import com.printscript.models.node.PrintStatementNode

object DummyAST {
  fun print(): com.printscript.models.node.ASTNode = PrintStatementNode(
    expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
    position = com.printscript.models.node.Position(0, 0)
  )

  fun declaration(): com.printscript.models.node.ASTNode = com.printscript.models.node.VariableDeclarationNode(
    variable = "variable",
    variableType = "String",
    expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
    position = com.printscript.models.node.Position(0, 0)
  )

  fun assignation(): com.printscript.models.node.ASTNode =
    com.printscript.models.node.AssignationNode(
      variable = "variable",
      expression = com.printscript.models.node.LiteralNode("\"Hello Universe\""),
      position = com.printscript.models.node.Position(0, 0)
    )

  fun doubleExpressionAndPrint(): com.printscript.models.node.ASTNode = PrintStatementNode(
    expression = com.printscript.models.node.LiteralNode("a"),
    position = com.printscript.models.node.Position(0, 0)
  )
}
