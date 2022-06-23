/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 11:30:13
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.exceptions

import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.services.handleerror.HandleErrorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

abstract class BackendException : RuntimeException() {

	@Autowired
	protected lateinit var service: HandleErrorService

	abstract val errors: MutableSet<ExceptionError>
	abstract fun status(): HttpStatus

	fun response(): ResponseTO {
		return service.createErrors(errors.toList())
	}
}
