import modifier.Variable
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
    context.put("hello", Variable("string", "world"))
    context.clear()
    assert(!(context has "hello"))
  }
}
