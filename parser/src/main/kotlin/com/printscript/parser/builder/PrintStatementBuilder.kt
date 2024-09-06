package com.printscript.parser.builder

class PrintStatementBuilder(private val line: List<com.printscript.models.token.Token>) : Builder {
  override fun build(): com.printscript.models.node.ASTNode {
    val handler = com.printscript.models.token.TokenHandler(line)
    val print = handler.consume(com.printscript.models.token.TokenType.PRINTLN, "Se esperaba 'println' al principio de la declaración.")
    val position = com.printscript.models.node.Position(print.line, print.column)
    handler.consume(com.printscript.models.token.TokenType.SYNTAX, "Se esperaba '(' después de 'println'.")
    val expressionTokens = handler.collectExpressionTokensInParenthesis()
    handler.consume(com.printscript.models.token.TokenType.SYNTAX, "Se esperaba ')' después de la expresión.")
    handler.consume(com.printscript.models.token.TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
    return com.printscript.models.node.PrintStatementNode(ExpressionBuilder(expressionTokens).build(), position)
  }
}
