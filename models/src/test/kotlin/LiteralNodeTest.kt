import node.LiteralNode
import node.Position
import node.PrintStatementNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class LiteralNodeTest {
  @Test
  fun literalNodeTest() {
    val literalNode = LiteralNode("Hello, World!")
    assertEquals("Hello, World!", literalNode.value)
    assertEquals("LiteralNode(value=Hello, World!)", literalNode.toString())
    assertNotEquals(LiteralNode("Hello, Universe!"), literalNode)
    assertNotEquals(
      PrintStatementNode(
        LiteralNode("Hello, World!"),
        Position(0, 0)
      ),
      literalNode
    )
    println(literalNode.hashCode())
    assertEquals(1498789909, literalNode.hashCode())
  }
}
