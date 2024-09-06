package com.printscript.engine

import com.printscript.cli.CLI

fun main() {
  while (true) {
    val input = readln()
    CLI().singleCommand(input)
  }
}
