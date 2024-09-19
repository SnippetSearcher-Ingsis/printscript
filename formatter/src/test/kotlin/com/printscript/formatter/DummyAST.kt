package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.Branch
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.node.VariableDeclarationNode

data object DummyAST {
  internal val print: ASTNode = PrintStatementNode(
    expression = LiteralNode("\"Hello World\""),
    position = Position(0, 0)
  )

  internal val declaration: ASTNode = VariableDeclarationNode(
    identifier = "variable",
    valueType = "String",
    expression = LiteralNode("\"Hello World\""),
    position = Position(0, 0)
  )

  internal val assignation: ASTNode =
    AssignationNode(
      variable = "variable",
      expression = LiteralNode("\"Hello Universe\""),
      position = Position(0, 0)
    )

  internal val doubleExpressionAndPrint: ASTNode = PrintStatementNode(
    expression = LiteralNode("a"),
    position = Position(0, 0)
  )

  internal val ifElse: ASTNode = IfElseNode(
    ifBranch = Branch(
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    ),
    elseBranch = Branch(
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    ),
    condition = LiteralNode("true")
  )

  internal val integral: ASTNode = IfElseNode(
    ifBranch = Branch(
      ReadEnvNode(
        expression = LiteralNode(2),
        position = Position(0, 0)
      ),
      PrintStatementNode(
        expression = DoubleExpressionNode(
          operator = "-",
          left = LiteralNode(3),
          right = DoubleExpressionNode(
            operator = "*",
            left = LiteralNode(1),
            right = LiteralNode(4)
          )
        ),
        position = Position(0, 0)
      ),
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    ),
    elseBranch = Branch(
      VariableDeclarationNode(
        identifier = "variable",
        valueType = "String",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      ),
      ReadInputNode(
        expression = LiteralNode("hola"),
        position = Position(0, 0)
      ),
      ConstantDeclarationNode(
        identifier = "constant",
        valueType = "number",
        expression = LiteralNode(2),
        position = Position(0, 0)
      )
    ),
    condition = LiteralNode("true")
  )
}
