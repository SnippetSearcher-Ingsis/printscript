package com.printscript.cli
import java.io.InputStreamReader

object TXTHandler {
  fun content(filename: String): InputStreamReader? {
    val inputStream = TXTHandler::class.java.getResourceAsStream(filename)
    if (inputStream != null) {
      return inputStream.reader()
    }
    return null
  }
}
