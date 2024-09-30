package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Environment
import com.printscript.models.node.ReadEnvNode

val readEnvStrategy = Strategy<ReadEnvNode> { services, node ->
  val value = services.visit(services, node.expression)
  when {
    value == null -> throw ReferenceException("Value is null")
    value !is String -> throw ReferenceException("Value is not a string")
    !(Environment.hasGlobalVariable(value)) -> throw ReferenceException("Environment variable $value not found.")
    else -> Environment.getGlobalVariable(value)!!
  }
}
