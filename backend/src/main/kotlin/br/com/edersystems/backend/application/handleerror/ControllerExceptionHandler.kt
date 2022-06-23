/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:06:08
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.handleerror

import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import br.com.edersystems.backend.core.services.handleerror.HandleErrorService
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler(
	private val service: HandleErrorService
) : ResponseEntityExceptionHandler() {

	@ExceptionHandler(ConstraintViolationException::class)
	fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ResponseTO> {
		return ResponseEntity(
			ResponseTOFactory.createError(
				service.createError(HttpStatus.CONFLICT.name, ex.localizedMessage)
			), HttpStatus.CONFLICT
		)
	}

	@ExceptionHandler(BackendException::class)
	fun handlePersonalFinanceException(ex: BackendException): ResponseEntity<ResponseTO> {
		return ResponseEntity(ResponseTOFactory.createError(ex), ex.status())
	}
}
