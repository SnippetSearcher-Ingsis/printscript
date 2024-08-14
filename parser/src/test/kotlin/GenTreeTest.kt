
import org.junit.jupiter.api.Test
import printScreen.parser.genTree.GenTree
import token.*




class GenTreeTest {

    @Test
    fun simpleStringGenTreeTest() {
        val example1 = listOf(
            ValuedToken(TokenType.KEYWORD, "let", 1,1 ),
            ValuedToken(TokenType.IDENTIFIER, "a", 1,5 ),
            ValuedToken(TokenType.SYNTAX, ":", 1,7 ),
            ValuedToken(TokenType.KEYWORD, "string", 1,9 ),
            ValuedToken(TokenType.SYNTAX, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1,18 ),
            ValuedToken(TokenType.SYNTAX, ";", 1,31 ),
        )
        val result = GenTree().tokensToAST(example1)
        result.forEach {ast -> println(ast.toString())}
        // tendria que hacer un toString() para que se vea mas facil si funciona o no.
    }

    @Test
    fun simpleNumberGenTreeTest() {
        val example2 = listOf(
            ValuedToken(TokenType.KEYWORD, "let", 1,1 ),
            ValuedToken(TokenType.IDENTIFIER, "a", 1,5 ),
            ValuedToken(TokenType.SYNTAX, ":", 1,7 ),
            ValuedToken(TokenType.KEYWORD, "number", 1,9 ),
            ValuedToken(TokenType.SYNTAX, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "9", 1,18 ),
            ValuedToken(TokenType.SYNTAX, ";", 1,31 ),
        )
        val result =  GenTree().tokensToAST(example2)
        result.forEach {ast -> println(ast.toString())}
    }

    @Test
    fun alreadyExistingVariableTest () {
        val example = listOf(
            ValuedToken(TokenType.IDENTIFIER, "a", 1,1 ),
            ValuedToken(TokenType.SYNTAX, "=", 1,16 ),
            ValuedToken(TokenType.LITERAL, "9", 1,18 ),
            ValuedToken(TokenType.SYNTAX, ";", 1,31 ),
        )
        val result =  GenTree().tokensToAST(example)
        result.forEach {ast -> println(ast.toString())}

    }
}