package com.printscript.interpreter.util

import com.printscript.interpreter.input.Input
import com.printscript.interpreter.output.Output
import com.printscript.models.node.ASTNode

/**
 * @param context the map to store values and declarations.
 * @param input the input provider to use.
 * @param output the output handler to use.
 * @param visit a recursive callback to re-send requests to the interpreter from inside a strategy.
 */
internal data class Services(val context: Context, val input: Input, val output: Output, val visit: (services: Services, node: ASTNode) -> Unit) {
  infix fun withContext(context: Context) = Services(context, input, output, visit)
}
