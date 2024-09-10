package com.printscript.interpreter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode

/**
 * Interpreter that only supports nodes from PrintScript v1.0
 */
class RetroInterpreter(private val interpreter: Interpreter) : Interpreter {
  /**
   * Interprets a list of nodes. Throws an exception if a node is not supported in PrintScript v1.0
   */
  override fun interpret(iterator: Iterator<ASTNode>) {
    while (iterator.hasNext()) {
      val node = iterator.next()
      checkVersion(node)
      interpreter.interpret(listOf(node).iterator())
    }
  }

  private val retroNodes = listOf(
    AssignationNode::class,
    DoubleExpressionNode::class,
    ErrorNode::class,
    LiteralNode::class,
    Position::class,
    PrintStatementNode::class,
    VariableDeclarationNode::class,
    VariableNode::class
  )

  private fun checkVersion(node: ASTNode) {
    if (node::class !in retroNodes) {
      throw OperationException("Unsupported node type: ${node::class.simpleName} in PrintScript v1.0")
    }
    when (node) {
      is AssignationNode -> checkVersion(node.expression)
      is DoubleExpressionNode -> {
        checkVersion(node.left)
        checkVersion(node.right)
      }
      is PrintStatementNode -> checkVersion(node.expression)
      is VariableDeclarationNode -> checkVersion(node.expression)
      is VariableNode -> checkVersion(node.expression)
      else -> {}
    }
  }
}
