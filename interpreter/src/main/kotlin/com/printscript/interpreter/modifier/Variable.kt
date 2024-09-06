package com.printscript.interpreter.modifier

internal data class Variable(override val type: String, override val value: Any?) : Modifier
