package printScreen.parser.genTree


import ast.*
import token.Token
import token.TokenType

class VariableDeclaration : BuilderAST {

    override fun createAST(line: List<Token>): AST {
        val firstToken = line[0]
        return when  {
            firstToken.type == TokenType.KEYWORD && firstToken.value == "let"-> newVariable (line)
            firstToken.type == TokenType.IDENTIFIER -> newVariable (line)
            else -> error("There is no definition in this line ")
        }
    }

    private fun newVariable (line: List<Token>): AST {
        val name = line.find { it.type == TokenType.IDENTIFIER }!!.value
        val type = line.find { it.type == TokenType.KEYWORD && it.value == "string" || it.value == "number" }?.value
        val expression = line.subList(line.indexOfFirst { it.value == "="} + 1, line.size - 1)
        return ExecutionLine (name, type, DataType.VariableDeclaration, createTree(expression))
    }

    private fun createTree (line : List<Token>, ): AST {
        val operators = mutableListOf<Pair<Token, Int>>()
        line.forEachIndexed { index, token -> if (operatorsToCheck(token)) operators.add(Pair(token, index)) }
        val root = addNodes(line, operators)
        return root

    }

    private fun addNodes(line : List<Token>, operators: List<Pair<Token, Int>>): AST {
        if (line.size == 1) {
            return Literal(line[0].value)
        }

        val tupla = findLowestPrecedenceOperator(operators)
        val (operator, index) = tupla

        if (line.first().value == "(" && line.last().value == ")") {
            return addNodes(line.subList(1, line.size - 1), operators)
        }

        val leftTokens = line.subList(0, index)
        val rightTokens = line.subList(index + 1, line.size)

        val leftNode = addNodes(leftTokens, operators.filter { it.second < index })
        val rightNode = addNodes(rightTokens, operators.filter { it.second > index })

        return DoubleExpression( operator.value, leftNode, rightNode)
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

}