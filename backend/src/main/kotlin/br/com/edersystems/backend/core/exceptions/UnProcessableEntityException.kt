/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 18:21:39
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.exceptions

import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import org.springframework.http.HttpStatus

class UnProcessableEntityException(override val errors: MutableSet<ExceptionError> = mutableSetOf()) :
	BackendException() {
	override fun status() = HttpStatus.UNPROCESSABLE_ENTITY
}
