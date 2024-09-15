package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.ReadInputNode

class ReadInputStrategy : Strategy<ReadInputNode> {
  override fun visit(services: Services, node: ReadInputNode): Any {
    val value = services.visit(services, node.expression)
    services.output.write(value.toString())
    return this transform services.input.read(value.toString())
  }

  private infix fun transform(value: String): Any {
    return try {
      value.toBooleanStrict()
    } catch (_: IllegalArgumentException) {
      try {
        value.toInt()
      } catch (_: NumberFormatException) {
        try {
          value.toDouble()
        } catch (_: NumberFormatException) {
          value
        }
      }
    }
  }
}
