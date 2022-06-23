/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 11:58:10
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.exceptions.error

data class ExceptionError private constructor(
	val code: String,
	val message: String,
) {
	companion object {
		fun create(code: String, message: String) = ExceptionError(code = code, message = message)
	}
}
