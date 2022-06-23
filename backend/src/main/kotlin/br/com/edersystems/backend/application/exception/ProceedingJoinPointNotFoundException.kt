/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 17:43:18
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.exception

import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import org.springframework.http.HttpStatus

class ProceedingJoinPointNotFoundException(override val errors: MutableSet<ExceptionError> = mutableSetOf()) :
	BackendException() {
	override val message: String = "Request Element Not Found:"

	override fun status(): HttpStatus {
		return HttpStatus.NOT_FOUND
	}
}
