package com.printscript.interpreter.strategy

import com.printscript.models.node.ErrorNode

val errorStrategy = Strategy<ErrorNode> { _, node -> throw Exception(node.error) }
