import org.junit.jupiter.api.Test
import util.Context

class ContextTest {
  @Test
  fun contextConstructorTest() {
    Context()
  }

  @Test
  fun contextClearTest() {
    val context = Context()
    context.addOrUpdate("hello", "world")
    context.clear()
    assert(!(context has "hello"))
  }
}
