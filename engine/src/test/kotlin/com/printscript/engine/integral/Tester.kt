package com.printscript.engine.integral

import com.printscript.interpreter.TracingInterpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.tracer.ReadableTracer
import com.printscript.lexer.Lexer
import com.printscript.parser.PrintParser
import kotlin.jvm.Throws

class Tester(private val name: String) {
  @Throws(Exception::class)
  fun test() {
    val loader = Loader(name)
    val lexer = Lexer()
    val tokens = lexer.lex(loader.loadInput())
    val parser = PrintParser()
    val ast = parser.parse(tokens)
    val tracer = ReadableTracer()
    val input = ConsoleInput(tracer)
    val interpreter = TracingInterpreter(tracer, input)
    interpreter.interpret(ast.iterator())
    assert(tracer.getOutput() == loader.loadOutput())
  }
}
