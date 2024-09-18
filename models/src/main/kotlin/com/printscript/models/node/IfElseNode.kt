package com.printscript.models.node

data class IfElseNode(val ifBranch: Branch, val elseBranch: Branch?, val condition: LiteralNode<*>) : ASTNode {
  override fun toString(): String {
    return "if ($condition) { $ifBranch }" + if (elseBranch != null) " else { $elseBranch }" else ""
  }
}
