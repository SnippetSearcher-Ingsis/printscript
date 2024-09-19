package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.Branch
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenHandler
import com.printscript.models.token.TokenType
import com.printscript.parser.generator.ASTGenerator

class ConditionalBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    handler.advance()

    val condition = parseCondition(handler)
    val ifBranch = parseBranch(handler)

    val elseBranch = if (handler.isAtEnd() || handler.peek().type != TokenType.ELSE) {
      null
    } else {
      parseElseBranch(handler)
    }

    return IfElseNode(ifBranch, elseBranch, LiteralNode(condition.toBooleanStrictOrNull() ?: condition))
  }

  private fun parseCondition(handler: TokenHandler): String {
    handler.consume(TokenType.SYNTAX, "Se esperaba '('")
    val condition = when (handler.peek().type) {
      TokenType.IDENTIFIER -> handler.consume(TokenType.IDENTIFIER, "Se esperaba un identificador").value
      TokenType.LITERAL -> handler.consume(TokenType.LITERAL, "Se esperaba un boolean").value
      else -> throw Exception("Se esperaba un identificador o un booleano.")
    }
    handler.consume(TokenType.SYNTAX, "Se esperaba ')'")
    return condition
  }

  private fun parseBranch(handler: TokenHandler): Branch {
    val list = mutableListOf<ASTNode>()
    handler.consume(TokenType.OPEN_BRACKET, "Se esperaba '{'")
    while (!handler.isAtEnd()) {
      if (handler.peek().type == TokenType.CLOSE_BRACKET) break
      val line = handler.collectExpressionTokens(true)
      list.add(ASTGenerator().createAST(line))
    }
    if (!handler.isAtEnd() && handler.peek().type == TokenType.CLOSE_BRACKET)
      handler.consume(TokenType.CLOSE_BRACKET, "Se esperaba '}'")
    if (!handler.isAtEnd() && handler.peek().type != TokenType.ELSE) list.add(ASTGenerator().createAST(handler.collectExpressionTokens(true)))
    return Branch(*list.toTypedArray())
  }

  private fun parseElseBranch(handler: TokenHandler): Branch {
    handler.advance()
    return parseBranch(handler)
  }
}
