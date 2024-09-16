package com.printscript.interpreter

import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode

object IntegralAST {
  val valid = listOf(
    ConstantNode(
      "constant",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
    AssignationNode(
      "constant",
      LiteralNode(1),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("constant"),
      Position(0, 0)
    ),
    VariableNode(
      "variable",
      "number",
      LiteralNode(null),
      Position(0, 0)
    ),
    AssignationNode(
      "variable",
      LiteralNode(2),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("variable"),
      Position(0, 0)
    ),
    AssignationNode(
      "variable",
      LiteralNode(3),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("variable"),
      Position(0, 0)
    ),
    ConstantDeclarationNode(
      "constant2",
      "number",
      LiteralNode(4),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("constant2"),
      Position(0, 0)
    ),
    VariableDeclarationNode(
      "variable2",
      "number",
      LiteralNode(5),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("variable2"),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode(10.5),
        LiteralNode(10.5),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "-",
        LiteralNode(10.5),
        LiteralNode(10.5),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "*",
        LiteralNode(1.5),
        LiteralNode(1.5),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "/",
        LiteralNode(1.5),
        LiteralNode(1.5),
      ),
      Position(0, 0)
    ),
    IfElseNode(
      listOf(
        PrintStatementNode(
          LiteralNode("\"true\""),
          Position(0, 0)
        )
      ),
      listOf(
        PrintStatementNode(
          LiteralNode("\"false\""),
          Position(0, 0)
        )
      ),
      LiteralNode(true),
    ),
    IfElseNode(
      listOf(
        PrintStatementNode(
          LiteralNode("\"true\""),
          Position(0, 0)
        )
      ),
      listOf(
        PrintStatementNode(
          LiteralNode("\"false\""),
          Position(0, 0)
        )
      ),
      LiteralNode(false),
    ),
    VariableDeclarationNode(
      "variable3",
      "string",
      ReadInputNode(
        LiteralNode("\"best football club =\""),
        Position(0, 0)
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("variable3"),
      Position(0, 0)
    ),
    AssignationNode(
      "variable3",
      ReadEnvNode(
        LiteralNode("\"BEST_FOOTBALL_CLUB\""),
        Position(0, 0)
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      LiteralNode("variable3"),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode("\"Leo Messi \""),
        LiteralNode(10),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode(10),
        LiteralNode("\" Leo Messi\""),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode("\"Is the goat =\""),
        LiteralNode(true),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode(true),
        LiteralNode("\"= Is the goat\""),
      ),
      Position(0, 0)
    ),
    PrintStatementNode(
      DoubleExpressionNode(
        "+",
        LiteralNode("\"Leo Messi\""),
        LiteralNode("\" is the goat\""),
      ),
      Position(0, 0)
    ),
    VariableDeclarationNode(
      "var1",
      "string",
      LiteralNode("'hello'"),
      Position(0, 0)
    ),
    AssignationNode(
      "var1",
      LiteralNode("'world'"),
      Position(0, 0)
    ),

    VariableDeclarationNode(
      "var2",
      "number",
      LiteralNode(10),
      Position(0, 0)
    ),
    AssignationNode(
      "var2",
      LiteralNode(20),
      Position(0, 0)
    ),
    VariableDeclarationNode(
      "var3",
      "boolean",
      LiteralNode(true),
      Position(0, 0)
    ),
    AssignationNode(
      "var3",
      LiteralNode(false),
      Position(0, 0)
    ),
    ConstantDeclarationNode(
      "const1",
      "string",
      LiteralNode("'hello'"),
      Position(0, 0)
    ),
    ConstantDeclarationNode(
      "const2",
      "number",
      LiteralNode(10),
      Position(0, 0)
    ),
    ConstantDeclarationNode(
      "const3",
      "boolean",
      LiteralNode(true),
      Position(0, 0)
    ),
  )
}
