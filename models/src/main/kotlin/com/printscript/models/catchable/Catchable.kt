package com.printscript.models.catchable

interface Catchable {
  fun hasException(): Boolean

  fun getException(): Exception?
}
