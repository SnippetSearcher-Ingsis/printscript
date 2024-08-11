package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.token.Token
import printScreen.models.token.TokenType

class genTree {

    private val dataToToken = mapOf (
        TokenType.IDENTIFIER to { tokens: List<Token> -> VariableDeclaration().createAST(tokens) },
        TokenType.KEYWORD to { tokens: List<Token> -> VariableDeclaration().createAST(tokens) },
    )

    // por cada linea de codigo se genera un ast.AST.
    fun tokensToAST ( tokens : List<Token>)  : List<AST> {
        val split : List<List<Token>> = splitEachLine(tokens)
        //
        return if (split.isEmpty()) emptyList() else split.map { token -> createAST( token ) }

    }

    private fun splitEachLine(tokens : List<Token>): List<List<Token>> {
        var i = 0
        val result = ArrayList<List<Token>>()
        var line = mutableListOf<Token>()
        while (i != tokens.size) {
            val token = tokens[i]
            when {
                isEndOfLine(token) -> {
                    line.add(token)
                    result.add(line.map {token -> token})
                    line = mutableListOf()
                }

                else -> {
                    line.add(tokens[i])
                }
            }
            i++
        }
        return result
    }



// caracteres que representan el final de una linea de ejecucion.
    private fun isEndOfLine (token : Token) : Boolean {
        return token.type == TokenType.SYNTAX && token.value == ";"
    }

    private fun createAST (line : List<Token>) : AST {
        val firstToken :Token = line[0]
        return dataToToken[firstToken.type]?.invoke(line) ?: error("Unknown token ${firstToken.type}")
    }

}