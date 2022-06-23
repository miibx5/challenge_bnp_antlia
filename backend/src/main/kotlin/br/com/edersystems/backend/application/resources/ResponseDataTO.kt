/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 18:54:47
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.resources

data class ResponseDataTO private constructor(override val code: String, val data: Any) : ResponseTO() {
	companion object {
		fun create(code: String, data: Any) = ResponseDataTO(code, data)
	}
}
