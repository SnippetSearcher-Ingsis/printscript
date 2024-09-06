package com.printscript.parser.builder

interface Builder {

  fun build(): com.printscript.models.node.ASTNode
}
