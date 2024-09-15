package com.printscript.interpreter.input

/**
 * An interface to read input from the user.
 */
interface Input {
  /**
   * Reads a message from the user.
   */
  infix fun read(message: String): String
}
