package com.printscript.formatter

data class IllegalParameterException(override val message: String) : IllegalArgumentException(message)
