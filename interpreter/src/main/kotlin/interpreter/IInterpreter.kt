package interpreter

import node.ASTNode

/**
 * Interface for the interpreter.
 */
interface IInterpreter {
  infix fun interpret(nodes: List<ASTNode>)
}
