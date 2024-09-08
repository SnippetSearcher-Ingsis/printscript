package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Context
import com.printscript.models.node.ASTNode

internal interface Strategy {
  fun visit(context: Context, node: ASTNode)
}
