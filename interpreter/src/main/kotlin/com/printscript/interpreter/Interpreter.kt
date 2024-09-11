package com.printscript.interpreter

import com.printscript.models.node.ASTNode

/**
 * Interface for the interpreter.
 * No se ni que escribo, es solo para commitear.
 */
sealed interface Interpreter {
  infix fun interpret(iterator: Iterator<ASTNode>)
}
