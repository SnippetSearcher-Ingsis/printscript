package com.printscript.interpreter.strategy

import com.printscript.models.node.ReadInputNode

val readInputStrategy = Strategy<ReadInputNode> { services, node ->
  val value = services.visit(services, node.expression)
  services.output.write(value.toString())
  transform(services.input.read(value.toString()))
}

private val transform = { value: String ->
  try {
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
