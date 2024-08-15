package printScreen.parser.builder

import node.ASTNode
import node.DoubleExpressionNode
import node.LiteralNode
import token.Token

class ExpressionBuilder(private val line : List<Token>) : Builder {

    override fun build(): ASTNode {
        val operators = mutableListOf<Pair<Token, Int>>()
        line.forEachIndexed { index, token -> if (operatorsToCheck(token)) operators.add(Pair(token, index)) }
        val root = addNodes(line)
        return root
    }

    private fun addNodes(line : List<Token>): ASTNode {
        val operators = mutableListOf<Pair<Token, Int>>()
        line.forEachIndexed { index, token -> if (operatorsToCheck(token)) operators.add(Pair(token, index)) }

        if (line.size == 1) {
            return LiteralNode(line[0].value)
        }

        val tuple = findLowestPrecedenceOperator(operators)
        val (operator, index) = tuple

        // usar index esta mal porque en la recursion al sacar elementos de line y de operators el indice queda corrido.

        if (line.first().value == "(" && line.last().value == ")") {
            return addNodes(line.subList(1, line.size - 1))
        }

        val leftTokens = line.subList(0, index)
        val rightTokens = line.subList(index + 1, line.size)

        val leftNode = addNodes(leftTokens)
        val rightNode = addNodes(rightTokens)

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
}