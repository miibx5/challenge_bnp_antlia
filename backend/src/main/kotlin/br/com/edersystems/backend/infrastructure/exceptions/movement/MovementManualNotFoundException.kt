/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 18:34:58
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.exceptions.movement

import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import org.springframework.http.HttpStatus

class MovementManualNotFoundException(override val errors: MutableSet<ExceptionError> = mutableSetOf()) :
	BackendException() {
	override fun status() = HttpStatus.NOT_FOUND
}
