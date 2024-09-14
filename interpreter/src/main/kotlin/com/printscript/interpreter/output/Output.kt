package com.printscript.interpreter.output

/**
 * An interface to write output to the user.
 */
interface Output {
  /**
   * Writes a message to the user.
   */
  infix fun write(message: String)
}
