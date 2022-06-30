/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 19:46:31
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DEFAULT_PATTERN_DATE_TIME_PT_BR = "dd/MM/yyyy HH:mm:ss"

fun LocalDateTime.toPtBrFormat(): String {
	val dateFormat = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_DATE_TIME_PT_BR)
	return dateFormat.format(this)
}
