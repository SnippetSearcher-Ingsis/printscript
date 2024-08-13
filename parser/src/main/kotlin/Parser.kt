package printScreen.parser

import ast.AST
import token.Token
import printScreen.parser.genTree.GenTree
import printScreen.parser.verify.VerifyAST

class Parser (private val generator : GenTree, private val verifier: VerifyAST) {

    fun parse (tokens : List<Token>?) : List<AST>  {
        return  when {
            tokens == null -> emptyList()
            else -> checkTokens(tokens)
        }
    }

    fun checkTokens(tokens : List<Token>) : List<AST> {
        val asts : List<AST>  = generator.tokensToAST(tokens)
        return emptyList()
    }
}
