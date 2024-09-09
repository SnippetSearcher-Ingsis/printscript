package com.printscript.interpreter

import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.node.VariableDeclarationNode

object DummyAST {
  val print = listOf(
    PrintStatementNode(
      LiteralNode("\"Hello, world!\""),
      Position(0, 0)
    )
  )

  val invalidDeclaration = listOf(
    VariableDeclarationNode(
      "hello",
      "number",
      LiteralNode("\"world\""),
      Position(0, 0)
    ),
  )

  val invalidAssignation = listOf(
    VariableDeclarationNode(
      "hello",
      "number",
      LiteralNode(1),
      Position(0, 0)
    ),
    AssignationNode(
      "hello",
      LiteralNode("\"world\""),
      Position(0, 0)
    )
  )

  val invalidOperation = listOf(
    DoubleExpressionNode(
      "/",
      LiteralNode(1),
      LiteralNode("\"world\""),
    )
  )

  val invalidReference = listOf(
    PrintStatementNode(
      LiteralNode("hello"),
      Position(0, 0)
    )
  )

  val invalidOperator = listOf(
    DoubleExpressionNode(
      "$",
      LiteralNode(1),
      LiteralNode(2),
    )
  )

  val errorNodeBackdoor = listOf(
    ErrorNode("NODE_ERROR_BACKDOOR")
  )

  val readEnv = listOf(
    VariableDeclarationNode(
      "pi",
      "number",
      ReadEnvNode(LiteralNode("\"pi\""), Position(0, 0)),
      Position(0, 0)
    )
  )

  val readInput = listOf(
    VariableDeclarationNode(
      "hello",
      "number",
      ReadInputNode(LiteralNode("\"best football club = \""), Position(0, 0)),
      Position(0, 0)
    )
  )
}
