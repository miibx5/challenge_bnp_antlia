/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:03:21
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.handleerror

import br.com.edersystems.backend.core.commons.exceptions.error.Errors
import br.com.edersystems.backend.core.services.AbstractService
import org.springframework.stereotype.Service

@Service
class HandleErrorService : AbstractService() {
	override val errors: Errors = createErrors()
}
