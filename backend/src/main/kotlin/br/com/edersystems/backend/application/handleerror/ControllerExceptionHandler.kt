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

import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {

	@ExceptionHandler(ConstraintViolationException::class)
	fun handleConstraintViolation(ex: ConstraintViolationException) = ResponseEntity(createError(ex), CONFLICT)

	@ExceptionHandler(BackendException::class)
	fun handleBackendException(ex: BackendException) = ResponseEntity(ResponseTOFactory.createError(ex), ex.status())
	private fun createError(ex: ConstraintViolationException) = ResponseTOFactory.createError(
		code = CONFLICT.name,
		error = ExceptionError.create(code = CONFLICT.name, message = ex.message!!)
	)
}
