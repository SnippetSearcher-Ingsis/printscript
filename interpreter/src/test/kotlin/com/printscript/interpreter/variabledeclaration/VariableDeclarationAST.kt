package com.printscript.interpreter.variabledeclaration

import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.VariableDeclarationNode

object VariableDeclarationAST {
  val nullReference = listOf(
    VariableDeclarationNode(
      "hello",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
  )
}
