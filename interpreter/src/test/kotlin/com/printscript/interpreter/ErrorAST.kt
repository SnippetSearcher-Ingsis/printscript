package com.printscript.interpreter

import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode

object ErrorAST {
  val invalidType = listOf(
    ConstantDeclarationNode(
      "hello",
      "invalid type",
      LiteralNode("\"world\""),
      Position(0, 0)
    ),
  )

  val invalidDeclaration = listOf(
    ConstantDeclarationNode(
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

  val invalidAssignationType = listOf(
    VariableNode(
      "hello",
      "invalid type",
      LiteralNode(1),
      Position(0, 0)
    ),
    AssignationNode(
      "hello",
      LiteralNode("'world'"),
      Position(0, 0)
    )
  )

  val invalidDivision = listOf(
    DoubleExpressionNode(
      "/",
      LiteralNode(1),
      LiteralNode("\"world\""),
    )
  )

  val invalidSubtraction = listOf(
    DoubleExpressionNode(
      "-",
      LiteralNode(1),
      LiteralNode("\"world\""),
    )
  )

  val invalidMultiplication = listOf(
    DoubleExpressionNode(
      "*",
      LiteralNode(1),
      LiteralNode("\"world\""),
    )
  )

  val invalidAddition = listOf(
    DoubleExpressionNode(
      "+",
      LiteralNode(true),
      LiteralNode(false),
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
      "string",
      ReadInputNode(LiteralNode("\"best football club = \""), Position(0, 0)),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("hello"),
      Position(0, 0)
    )
  )

  val variableInvalidType = listOf(
    VariableDeclarationNode(
      "hello",
      "invalid type",
      LiteralNode(1),
      Position(0, 0)
    )
  )

  val repeatedVariable = listOf(
    VariableNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
    VariableNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    )
  )

  val repeatedConstant = listOf(
    ConstantNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
    ConstantNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    )
  )
}
