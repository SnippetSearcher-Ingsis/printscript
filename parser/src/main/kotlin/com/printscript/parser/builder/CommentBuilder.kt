package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.LineCommentNode

class CommentBuilder(private val comment: String) : Builder {
  override fun build(): ASTNode {
    return LineCommentNode(comment)
  }
}
