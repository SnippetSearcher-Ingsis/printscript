package catchable

interface ICatchable {
  fun hasException(): Boolean

  fun getException(): Exception?
}
