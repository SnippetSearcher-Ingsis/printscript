package com.printscript.models.token

data class ValuedToken(
  override val type: TokenType,
  override val value: String,
  override val line: Int,
  override val column: Int
) : Token
