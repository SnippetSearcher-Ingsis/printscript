package catchable

interface ICatchable {
  fun hasError(): Boolean

  fun getError(): Error?
}
