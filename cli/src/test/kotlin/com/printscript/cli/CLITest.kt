package com.printscript.cli

import org.junit.jupiter.api.Test

class CLITest {
  @Test
  fun mainTest() {
    CLI().executeFile("example.txt")
    CLI().singleCommand("execute integral.txt")
  }
}
