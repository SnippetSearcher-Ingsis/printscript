package com.printscript.interpreter.readenv

import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.ReadEnvNode

object ReadEnvAST {
  val readEnvNull = listOf(
    ConstantNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
    ReadEnvNode(
      LiteralNode("hello"),
      Position(0, 0)
    )
  )

  val readEnvBoolean = listOf(
    ConstantDeclarationNode(
      "hello",
      "boolean",
      LiteralNode(true),
      Position(0, 0)
    ),
    ReadEnvNode(
      LiteralNode("hello"),
      Position(0, 0)
    )
  )

  val readEnvNotFound = listOf(
    ReadEnvNode(
      LiteralNode("\"NON_EXISTENT_VALUE\""),
      Position(0, 0)
    )
  )
}
