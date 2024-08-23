package builder

import Result

interface CommandBuilder {
  fun build(command: List<String>): Result
}
