package generator

import node.ASTNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.VariableDeclarationNode
import printScreen.parser.genTree.Builder
import token.Token
import token.TokenType

class VariableDeclarationBuilder(private val line: List<Token>) : Builder {
    private var currentTokenIndex = 0

    override fun build(): ASTNode {
        val firstToken = line[0]
        return when {
            firstToken.type == TokenType.KEYWORD && firstToken.value == "let" -> validateDeclaration()
            firstToken.type == TokenType.IDENTIFIER -> validateAssignation()
            else -> error("There is no definition in this line ")
        }
    }

    private fun validateDeclaration(): ASTNode {
        consume(TokenType.KEYWORD, "Se esperaba 'let' al principio de la declaración.")
        val name = consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.").value
        consume(TokenType.SYNTAX, "Se esperaba ':' después del nombre de la variable.")
        val type = consume(TokenType.TYPE, "Se esperaba el tipo de la variable.").value
        consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
        val expressionTokens = collectExpressionTokens()
        consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
        return VariableDeclarationNode(name, type, createTree(expressionTokens))
    }

    private fun validateAssignation() : ASTNode {
        val name = consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.").value
        consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
        val expressionTokens = collectExpressionTokens()
        consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
        return VariableDeclarationNode(name, null, createTree(expressionTokens))
    }

    private fun collectExpressionTokens(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (!isAtEnd() && peek().type != TokenType.SEMICOLON) {
            tokens.add(advance())
        }
        return tokens
    }

    private fun createTree(line : List<Token>): ASTNode {
        val operators = mutableListOf<Pair<Token, Int>>()
        line.forEachIndexed { index, token -> if (operatorsToCheck(token)) operators.add(Pair(token, index)) }
        val root = addNodes(line, operators)
        return root
    }

    private fun addNodes(line : List<Token>, operators: List<Pair<Token, Int>>): ASTNode {
        if (line.size == 1) {
            return LiteralNode(line[0].value)
        }

        val tuple = findLowestPrecedenceOperator(operators)
        val (operator, index) = tuple

        if (line.first().value == "(" && line.last().value == ")") {
            return addNodes(line.subList(1, line.size - 1), operators)
        }

        val leftTokens = line.subList(0, index)
        val rightTokens = line.subList(index + 1, line.size)

        val leftNode = addNodes(leftTokens, operators.filter { it.second < index })
        val rightNode = addNodes(rightTokens, operators.filter { it.second > index })

        return DoubleExpressionNode( operator.value, leftNode, rightNode)
    }

    private fun findLowestPrecedenceOperator(operators: List<Pair<Token, Int>>): Pair<Token, Int> {
        var result: Pair<Token, Int>? = null
        var i = 0
        var parenCount = 0

        while (i < operators.size) {
            val (token, index) = operators[i]

            if (token.value == "(") {
                parenCount++
            } else if (token.value == ")") {
                parenCount--
            } else if (parenCount == 0) {
                if (result == null || getPrecedence(token.value) < getPrecedence(result.first.value)) {
                    result = Pair(token, index)
                }
            }

            i++
        }

        return result ?: operators[0]
    }

    private fun getPrecedence(operator: String): Int {
        return when (operator) {
            "+" -> 1
            "-" -> 1
            "*" -> 2
            "/" -> 2
            else -> Int.MAX_VALUE
        }
    }

    private fun operatorsToCheck (token : Token) : Boolean {
        return listOf("/", "*", "(", ")", "+", "-").contains(token.value)
    }

    private fun check(type: TokenType): Boolean {
        return !isAtEnd() && peek().type == type
    }

    private fun advance(): Token {
        if (!isAtEnd()) currentTokenIndex++
        return previous()
    }

    private fun isAtEnd(): Boolean {
        return currentTokenIndex >= line.size
    }

    private fun peek(): Token {
        return if (currentTokenIndex < line.size) {
            line[currentTokenIndex]
        } else {
            throw IllegalStateException("No more tokens to peek.")
        }
    }

    private fun previous(): Token {
        if (currentTokenIndex > 0) {
            return line[currentTokenIndex - 1]
        } else {
            throw IllegalStateException("No previous token.")
        }
    }

    private fun consume(type: TokenType, message: String): Token {
        if (check(type)) return advance()
        throw IllegalArgumentException(message)
    }
}
