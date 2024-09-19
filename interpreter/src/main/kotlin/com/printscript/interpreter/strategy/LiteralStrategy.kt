package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.LiteralNode

val literalStrategy = Strategy<LiteralNode<*>> { services, node ->
  when {
    node.value is String -> handleString(services, node.value as String)
    else -> node.value
  }
}

private val handleString = { services: Services, identifier: String ->
  when {
    identifier.startsWith("\"") -> identifier.replace("\"", "")
    identifier.startsWith("'") -> identifier.replace("'", "")
    identifier !in services.context -> throw ReferenceException("Identifier $identifier not found.")
    else -> (services.context get identifier)?.value
  }
}
