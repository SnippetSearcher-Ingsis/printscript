package node

data class Position(val line: Int, val column: Int) {
    override fun toString(): String {
        return "Position(line=$line, column=$column)"
    }
}