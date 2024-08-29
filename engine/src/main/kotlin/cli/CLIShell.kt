package cli

import CLI

fun main() {
  while (true) {
    val input = readln()
    val cli = CLI().singleCommand(input)
  }
}
