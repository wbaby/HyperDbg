package hwdbg.script

import chisel3._
import chisel3.util._

/**
 * @brief
 *   The structure of SYMBOL used in script engine of HyperDbg
 */
class Symbol extends Bundle {
  val Type = UInt(64.W) // long long unsigned is 64 bits
  val Len = UInt(64.W)
  val VariableType = UInt(64.W)
  val Value = UInt(64.W)
}

/**
 * @brief
 *   Constant values for the script engine
 */
object ScriptConstants {
  val SYMBOL_GLOBAL_ID_TYPE = 0
  val SYMBOL_LOCAL_ID_TYPE = 1
  val SYMBOL_NUM_TYPE = 2
  val SYMBOL_REGISTER_TYPE = 3
  val SYMBOL_PSEUDO_REG_TYPE = 4
  val SYMBOL_SEMANTIC_RULE_TYPE = 5
  val SYMBOL_TEMP_TYPE = 6
  val SYMBOL_STRING_TYPE = 7
  val SYMBOL_VARIABLE_COUNT_TYPE = 8
  val SYMBOL_MEM_VALID_CHECK_MASK = 1 << 31
  val SYMBOL_INVALID = 9
  val SYMBOL_WSTRING_TYPE = 10
  val SYMBOL_USER_DEFINED_FUNCTION_TYPE = 11
  val SYMBOL_FUNCTION_PARAMETER_ID_TYPE = 12
  val SYMBOL_RETURN_ADDRESS_TYPE = 13
  val SYMBOL_STACK_TEMP_TYPE = 14
  val SYMBOL_FUNCTION_PARAMETER_TYPE = 15
  val INVALID = 0x80000000
  val LALR_ACCEPT = 0x7fffffff
}

object ScriptEvalFunc {
  object ScriptOperators extends ChiselEnum {
    val sFuncInc, sFuncDec, sFuncReference, sFuncDereference, sFuncOr, sFuncXor, sFuncAnd, sFuncAsr, sFuncAsl, sFuncAdd, sFuncSub, sFuncMul, sFuncDiv, sFuncMod, sFuncGt, sFuncLt, sFuncEgt, sFuncElt, sFuncEqual, sFuncNeq, sFuncStart_of_if, sFuncJmp, sFuncJz, sFuncJnz, sFuncJmp_to_end_and_jzcompleted, sFuncEnd_of_if, sFuncStart_of_while, sFuncEnd_of_while, sFuncVargstart, sFuncMov, sFuncStart_of_do_while, sFunc, sFuncStart_of_do_while_commands, sFuncEnd_of_do_while, sFuncStart_of_for, sFuncFor_inc_dec, sFuncStart_of_for_ommands, sFuncIgnore_lvalue, sFuncEnd_of_user_defined_function, sFuncReturn_of_user_defined_function_with_value, sFuncReturn_of_user_defined_function_without_value, sFuncCall_user_defined_function_parameter, sFuncEnd_of_calling_user_defined_function_without_returning_value, sFuncEnd_of_calling_user_defined_function_with_returning_value, sFuncCall_user_defined_function, sFuncStart_of_user_defined_function, sFuncMov_return_value, sFuncVoid, sFuncBool, sFuncChar, sFuncShort, sFuncInt, sFuncLong, sFuncUnsigned, sFuncSigned, sFuncFloat, sFuncDouble, sFuncPrint, sFuncFormats, sFuncEvent_enable, sFuncEvent_disable, sFuncEvent_clear, sFuncTest_statement, sFuncSpinlock_lock, sFuncSpinlock_unlock, sFuncEvent_sc, sFuncPrintf, sFuncPause, sFuncFlush, sFuncEvent_trace_step, sFuncEvent_trace_step_in, sFuncEvent_trace_step_out, sFuncEvent_trace_instrumentation_step, sFuncEvent_trace_instrumentation_step_in, sFuncSpinlock_lock_custom_wait, sFuncEvent_inject, sFuncPoi, sFuncDb, sFuncDd, sFuncDw, sFuncDq, sFuncNeg, sFuncHi, sFuncLow, sFuncNot, sFuncCheck_address, sFuncDisassemble_len, sFuncDisassemble_len32, sFuncDisassemble_len64, sFuncInterlocked_increment, sFuncInterlocked_decrement, sFuncPhysical_to_virtual, sFuncVirtual_to_physical, sFuncEd, sFuncEb, sFuncEq, sFuncInterlocked_exchange, sFuncInterlocked_exchange_add, sFuncInterlocked_compare_exchange, sFuncStrlen, sFuncStrcmp, sFuncMemcmp, sFuncWcslen, sFuncWcscmp, sFuncEvent_inject_error_code, sFuncMemcpy = Value
  }
} 