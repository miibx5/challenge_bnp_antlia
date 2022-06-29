/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 11:38:39
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services

import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.Errors
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import br.com.edersystems.backend.infrastructure.util.MessageUtil
import org.springframework.http.HttpStatus

abstract class AbstractService {

	abstract val errors: Errors

	protected fun throwErrors(ex: BackendException) {
		if (ex.errors.isNotEmpty()) {
			throw ex
		}
	}

	fun createErrors(): Errors {
		return Errors()
	}

	fun createErrors(error: ExceptionError): MutableSet<ExceptionError> {
		return mutableSetOf(error)
	}

	fun createError(ex: BackendException): ResponseTO {
		return buildError(ex)
	}

	fun createError(code: String, data: Any): ExceptionError {
		return buildError(code, getMessageWithParam(code, data))
	}

	fun createError(message: String, code: String = HttpStatus.BAD_REQUEST.name): ExceptionError {
		return buildError(code, message)
	}

	fun createError(code: String): ExceptionError {
		return buildError(code)
	}

	fun createErrors(errors: List<ExceptionError>): ResponseTO {
		return ResponseTOFactory.createErrors(errors, HttpStatus.UNPROCESSABLE_ENTITY.name)
	}

	fun createErrors(code: String, data: Any): MutableSet<ExceptionError> {
		val errors: MutableSet<ExceptionError> = mutableSetOf()
		errors.add(buildError(code, getMessageWithParam(code, data)))

		return errors
	}

	private fun buildError(code: String, message: String): ExceptionError {
		return ExceptionError.create(code, message)
	}

	private fun buildError(ex: BackendException): ResponseTO {
		return ResponseTOFactory.createErrors(ex)
	}

	private fun buildError(code: String): ExceptionError {
		return ExceptionError.create(code, MessageUtil.getMessage(code))
	}

	private fun getMessageWithParam(code: String, data: Any): String {
		return MessageUtil.createMessageWithResourceBundle(code, data)
	}
}
