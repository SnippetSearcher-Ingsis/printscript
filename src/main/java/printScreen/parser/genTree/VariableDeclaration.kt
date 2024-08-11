package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.ast.DataType
import printScreen.models.ast.Node
import printScreen.models.token.Token
import printScreen.models.token.TokenType

class VariableDeclaration : BuilderAST {
    override fun createAST(line: List<Token>): AST {
        val firstToken = line[0]
        return when  {
            firstToken.type == TokenType.KEYWORD && firstToken.value == "let"-> newVariable (line)
            firstToken.type == TokenType.IDENTIFIER -> createdVariable (line)
            else -> error("There is no definition in this line ")
        }
    }

    fun newVariable (line: List<Token>): AST {
        return AST (getPairNameType(line), DataType.VariableDeclaration, createTree(line))
    }

    fun createdVariable (line: List<Token>): AST {

    }

    fun getPairNameType (line : List <Token>) : Pair<String, String?>? {

    }

    fun createTree (line : List<Token>): Node {

    }

}