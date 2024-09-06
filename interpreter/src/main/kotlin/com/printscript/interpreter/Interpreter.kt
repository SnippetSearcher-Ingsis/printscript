package com.printscript.interpreter

import com.printscript.interpreter.util.Context
import com.printscript.interpreter.visitor.EvaluationStrategy
import com.printscript.interpreter.visitor.Visitor
import node.ASTNode
import node.ASTVisitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  private val context = Context()

  private val strategy = EvaluationStrategy

  private val visitor: ASTVisitor = Visitor(context, strategy)

  override fun interpret(iterator: Iterator<ASTNode>) {
    while (iterator.hasNext()) {
      val node = iterator.next()
      node.accept(visitor)
    }
  }
}
