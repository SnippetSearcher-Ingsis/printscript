package node

class LiteralNode<T>(val value: T) : ASTNode {

    override fun accept(visitor: ASTVisitor) {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "LiteralNode(value=$value)"
    }
}
