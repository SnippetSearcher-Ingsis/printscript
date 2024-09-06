package com.printscript.parser.builder

import com.printscript.models.node.ASTNode

interface Builder {

  fun build(): ASTNode
}
