package com.printscript.models.catchable

interface ICatchable {
  fun hasException(): Boolean

  fun getException(): Exception?
}
