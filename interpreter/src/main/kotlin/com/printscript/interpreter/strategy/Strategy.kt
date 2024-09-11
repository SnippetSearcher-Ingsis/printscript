package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode

internal interface Strategy {
  fun visit(services: Services, node: ASTNode)
}
