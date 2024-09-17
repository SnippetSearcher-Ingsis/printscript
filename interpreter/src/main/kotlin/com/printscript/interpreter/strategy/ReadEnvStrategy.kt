package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Environment
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ReadEnvNode

class ReadEnvStrategy : Strategy<ReadEnvNode> {
  override fun visit(services: Services, node: ReadEnvNode): Any {
    val value = services.visit(services, node.expression)
    return when {
      value == null -> throw ReferenceException("Value is null")
      value !is String -> throw ReferenceException("Value is not a string")
      !(Environment.hasGlobalVariable(value)) -> throw ReferenceException("Environment variable $value not found.")
      else -> Environment.getGlobalVariable(value)!!
    }
  }
}
