
import generator.ASTGenerator
import org.junit.jupiter.api.Test
import token.*




class GenTreeTest {

    @Test
    fun simpleStringGenTreeTest() {
        val example1 = listOf(
            ValuedToken(TokenType.KEYWORD, "let", 1,1 ),
            ValuedToken(TokenType.IDENTIFIER, "a", 1,5 ),
            ValuedToken(TokenType.SYNTAX, ":", 1,7 ),
            ValuedToken(TokenType.TYPE, "string", 1,9 ),
            ValuedToken(TokenType.EQUAL, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1,18 ),
            ValuedToken(TokenType.SEMICOLON, ";", 1,31 ),
        )
        val result = ASTGenerator().tokensToAST(example1)
        println(result)
    }

    @Test
    fun simpleNumberGenTreeTest() {
        val example2 = listOf(
            ValuedToken(TokenType.KEYWORD, "let", 1,1 ),
            ValuedToken(TokenType.IDENTIFIER, "a", 1,5 ),
            ValuedToken(TokenType.SYNTAX, ":", 1,7 ),
            ValuedToken(TokenType.TYPE, "number", 1,9 ),
            ValuedToken(TokenType.EQUAL, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "9", 1,18 ),
            ValuedToken(TokenType.SEMICOLON, ";", 1,31 ),
        )
        val result =  ASTGenerator().tokensToAST(example2)
        println(result)
    }

    // NO FUNCIONA PORQUE ME FALTA EL ASSIGNATION
    @Test
    fun alreadyExistingVariableTest () {
        val example = listOf(
            ValuedToken(TokenType.IDENTIFIER, "a", 1,1 ),
            ValuedToken(TokenType.SYNTAX, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "9", 1,18 ),
            ValuedToken(TokenType.SYNTAX, ";", 1,31 ),
        )
        val result =  ASTGenerator().tokensToAST(example)
        println(result)
    }
}