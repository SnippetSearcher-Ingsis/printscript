package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.ast.DataType
import printScreen.models.ast.Node
import printScreen.models.token.Token
import printScreen.models.token.TokenType
import token.Token
import token.TokenType

class VariableDeclaration : BuilderAST {

    private val matchPattern = mapOf<TokenType, Boolean>(
        TokenType.IDENTIFIER to false ,
        TokenType.SYNTAX to false,
        TokenType.KEYWORD to false,
        TokenType.OPERATOR to false,
    )

    override fun createAST(line: List<Token>): AST {
        val firstToken = line[0]
        return when  {
            firstToken.type == TokenType.KEYWORD && firstToken.value == "let"-> newVariable (line)
            firstToken.type == TokenType.IDENTIFIER -> createdVariable (line)
            else -> error("There is no definition in this line ")
        }
    }

    fun newVariable (line: List<Token>): AST {
        val hasOperator : Boolean = line.any { it.type == TokenType.OPERATOR && it.value == "="}
        return AST (getPairNameType(line), DataType.VariableDeclaration, createTree(line))
    }

    fun createdVariable (line: List<Token>): AST {
        val hasOperator : Boolean = line.any { it.type == TokenType.OPERATOR && it.value == "="}
    }

    fun getPairNameType (line : List <Token>) : Pair<String, String?>? {

    }

    fun createTree (line : List<Token>): Node {

    }

}