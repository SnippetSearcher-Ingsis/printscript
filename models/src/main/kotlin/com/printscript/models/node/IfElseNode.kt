package com.printscript.models.node

class IfElseNode(val ifBranch: List<ASTNode>, val elseBranch: List<ASTNode>, val condition: LiteralNode<*>) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    return visitor.visit(this)
  }

  override fun toString(): String {
    return "ConditionalNode(ifBranch=$ifBranch, elseBranch=$elseBranch, condition=$condition)"
  }

  override fun equals(other: Any?): Boolean {
    return other is IfElseNode
  }

  override fun hashCode(): Int {
    return 0
  }
}
