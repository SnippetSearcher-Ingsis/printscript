package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.ast.Node
import printScreen.models.token.Token

class genTree {

    fun tockenToAST ( tokens : List<Token>? )  : List<AST> {
        //TODO: Aca se pasa de Token a AST
    }

    fun rootTocken (token : Token) : AST {
        //TODO: Caso donde () o * o / para que se maneje bien la prioridad de las operaciones.
    }

    fun createTree (token: Token) : Node {

    }
}