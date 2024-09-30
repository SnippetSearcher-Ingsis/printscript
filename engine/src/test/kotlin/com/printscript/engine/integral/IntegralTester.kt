package com.printscript.engine.integral

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.OperationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IntegralTester {
  @Test
  fun testMixedScript() {
    val tester = Tester("mixed_script_valid")
    assertThrows<Exception> { tester.test() }
  }

  @Test
  fun testConstReAssignation() {
    val tester = Tester("const_re_assignation_fail")
    assertThrows<AssignationException> { tester.test() }
  }

  @Test
  fun testInvalidOperations() {
    val tester = Tester("invalid_operations_fail")
    assertThrows<OperationException> { tester.test() }
  }

  @Test
  fun testInvalidIfCondition() {
    val tester = Tester("invalid_if_condition_fail")
    assertThrows<OperationException> { tester.test() }
  }
}
