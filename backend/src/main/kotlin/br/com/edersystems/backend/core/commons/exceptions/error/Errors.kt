/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 11:57:40
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.exceptions.error

data class Errors(
	private var _exceptionErrors: MutableSet<ExceptionError> = mutableSetOf()
) {
	val exceptionErrors: MutableSet<ExceptionError>
		get() = this._exceptionErrors

	fun addErrors(exceptionError: ExceptionError) {
		this._exceptionErrors.add(exceptionError)
	}

	fun clearErrors() {
		this._exceptionErrors = mutableSetOf()
	}
}
