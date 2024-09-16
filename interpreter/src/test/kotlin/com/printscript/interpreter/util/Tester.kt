package com.printscript.interpreter.util

import com.printscript.interpreter.DummyInput
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.models.node.ASTNode

class Tester(private val name: String, private val ast: Iterator<ASTNode>) {
  fun test() {
    val loader = Loader(name)
    val output = ReadableOutput()
    val interpreter = Interpreter.builder {
      this setInput DummyInput()
      this setOutput output
      this setProvider VERSION_1_1
    }
    interpreter interpret ast
    println(loader.loadOutput())
    assert(output.getOutput() == loader.loadOutput())
  }
}
