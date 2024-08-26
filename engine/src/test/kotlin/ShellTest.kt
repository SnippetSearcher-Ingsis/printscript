import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ShellTest {
  @Test
  fun mainTest() {
    assertThrows<Exception> { shell.main() }
  }
}
