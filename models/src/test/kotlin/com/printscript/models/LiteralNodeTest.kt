package com.printscript.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class LiteralNodeTest {
  @Test
  fun literalNodeTest() {
    val literalNode = com.printscript.models.node.LiteralNode("Hello, World!")
    assertEquals("Hello, World!", literalNode.value)
    assertEquals("LiteralNode(value=Hello, World!)", literalNode.toString())
    assertNotEquals(com.printscript.models.node.LiteralNode("Hello, Universe!"), literalNode)
    assertNotEquals(
      com.printscript.models.node.PrintStatementNode(
        com.printscript.models.node.LiteralNode("Hello, World!"),
        com.printscript.models.node.Position(0, 0)
      ),
      literalNode
    )
    println(literalNode.hashCode())
    assertEquals(1498789909, literalNode.hashCode())
  }
}
