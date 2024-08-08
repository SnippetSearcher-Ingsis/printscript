package printScreen.parser

import printScreen.models.ast.AST
import printScreen.parser.genTree.genTree
import printScreen.parser.verify.verifyAST
import printScreen.models.token.Token

class Parser (private val generator : genTree, private val verifier: verifyAST) {

    fun parse (tokens : List<Token>?) : List<AST>  {
        return  when {
            tokens == null -> emptyList()
            else -> checkTokens(tokens)
        }
    }

    fun checkTokens(tokens : List<Token>) : List<AST> {

    }
}