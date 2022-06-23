/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 18:56:03
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.resources

import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError

data class ResponseErrorTO private constructor(override val code: String, val errors: List<ExceptionError>) :
	ResponseTO() {
	companion object {
		fun create(code: String, errors: List<ExceptionError>) = ResponseErrorTO(code, errors)
	}
}
