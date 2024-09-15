package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode
import kotlin.jvm.Throws

/**
 * A type safe strategy pattern used to visit a [ASTNode] in the AST.
 */
interface Strategy<T>
        where T : ASTNode {
  /**
   * Visits the given [ASTNode] and returns the result of the visit.
   */
  @Throws(Exception::class)
  fun visit(services: Services, node: T): Any?
}
