package printScreen.parser

import node.ASTNode
import generator.ASTGenerator
import token.Token

class Parser {

    fun parse (tokens : List<Token>?) : List<ASTNode>  {
        return  when {
            tokens == null -> emptyList()
            else -> checkTokens(tokens)
        }
    }

    private fun checkTokens(tokens : List<Token>) : List<ASTNode> {
        val astNodes : List<ASTNode>  = ASTGenerator().tokensToAST(tokens)
        return astNodes
    }
}
