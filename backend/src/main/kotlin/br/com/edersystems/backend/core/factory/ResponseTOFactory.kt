/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 18:58:20
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.factory

import br.com.edersystems.backend.application.resources.ResponseDataTO
import br.com.edersystems.backend.application.resources.ResponseErrorTO
import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.core.commons.exceptions.BackendException
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import org.springframework.http.HttpStatus

object ResponseTOFactory {
	fun success(data: Any, code: String = HttpStatus.OK.name): ResponseTO {
		return ResponseDataTO.create(data = data, code = code)
	}

	fun createErrors(errors: List<ExceptionError>, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
		return ResponseErrorTO.create(errors = errors, code = code)
	}

	fun createErrors(ex: BackendException, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
		return ResponseErrorTO.create(errors = ex.errors.toList(), code = code)
	}

	fun createError(ex: BackendException): ResponseTO {
		return ResponseErrorTO.create(errors = ex.errors.toList(), code = ex.status().name)
	}

	fun createError(error: ExceptionError, code: String = HttpStatus.BAD_REQUEST.name): ResponseTO {
		return ResponseErrorTO.create(errors = listOf(error), code = code)
	}
}
