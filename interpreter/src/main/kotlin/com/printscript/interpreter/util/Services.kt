package com.printscript.interpreter.util

import com.printscript.interpreter.input.Input
import com.printscript.interpreter.output.Output
import com.printscript.models.node.ASTNode

internal data class Services(val context: Context, val input: Input, val output: Output, val interpret: (services: Services, node: ASTNode) -> Unit) {
  infix fun withContext(context: Context) = Services(context, input, output, interpret)
}
