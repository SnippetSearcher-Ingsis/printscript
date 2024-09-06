package com.printscript.parser.builder

class VariableDeclarationBuilder(private val line: List<com.printscript.models.token.Token>) : Builder {
  override fun build(): com.printscript.models.node.ASTNode {
    val handler = com.printscript.models.token.TokenHandler(line)
    handler.advance()
    val identifier = handler.consume(com.printscript.models.token.TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
    val name = identifier.value
    val position = com.printscript.models.node.Position(identifier.line, identifier.column)
    handler.consume(com.printscript.models.token.TokenType.SYNTAX, "Se esperaba ':' después del nombre de la variable.")
    val type = handler.consume(com.printscript.models.token.TokenType.TYPE, "Se esperaba el tipo de la variable.").value
    try {
      handler.consume(com.printscript.models.token.TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
      val expressionTokens = handler.collectExpressionTokens(false)
      handler.consume(com.printscript.models.token.TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
      return com.printscript.models.node.VariableDeclarationNode(
        name,
        type,
        ExpressionBuilder(expressionTokens).build(),
        position
      )
    } catch (e: Exception) {
      handler.consume(com.printscript.models.token.TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
      return com.printscript.models.node.VariableNode(name, type)
    }
  }
}
