
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.lexer.Lexer
import com.printscript.parser.PrintParser
import java.io.File
import java.io.FileReader

fun main() {
  val interpreter = Interpreter.builder {
    this.setInput(ConsoleInput())
    this.setOutput(ConsoleOutput())
    this.setProvider(VERSION_1_1)
  }
  val file = File.createTempFile("sii", ".txt")
  file.writeText(
    "println(\"Hello, world!\");\n" +
      "// This is a comment\n" +
      "println(\"Hello, universe!\");\n"
  )
  val reader = FileReader(file)
  val tokens = Lexer().lex(reader)
  interpreter.interpret(PrintParser().parse(tokens))

  // CLI().executeFile("example.txt")
}
