package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.ast.Node
import printScreen.models.token.Token
import printScreen.models.token.TokenType

class VariableDeclaration : BuilderAST{
    override fun createAST(line: List<Token>): AST {
        val firstToken = line[0]
        return when  {
            firstToken.type == TokenType.KEYWORD -> newVariable (line)
            firstToken.type == TokenType.IDENTIFIER -> createdVariable (line)
            else -> {

            }
        }
    }

    fun newVariable (line: List<Token>): AST {

    }

    fun createdVariable (line: List<Token>): AST {

    }

}