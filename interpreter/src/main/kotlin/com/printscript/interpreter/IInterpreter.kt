package com.printscript.interpreter

/**
 * Interface for the interpreter.
 * No se ni que escribo, es solo para commitear.
 */
sealed interface IInterpreter {
  infix fun interpret(iterator: Iterator<com.printscript.models.node.ASTNode>)
}
