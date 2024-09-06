package com.printscript.interpreter

import node.ASTNode

/**
 * Interface for the interpreter.
 * No se ni que escribo, es solo para commitear.
 */
sealed interface IInterpreter {
  infix fun interpret(iterator: Iterator<ASTNode>)
}
