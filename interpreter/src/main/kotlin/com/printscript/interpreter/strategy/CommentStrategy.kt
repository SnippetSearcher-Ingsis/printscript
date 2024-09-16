package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.LineCommentNode

class CommentStrategy : Strategy<LineCommentNode> {
  override fun visit(services: Services, node: LineCommentNode): Any? {
    return null
  }
}
