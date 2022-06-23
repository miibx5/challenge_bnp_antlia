/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:17:19
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.product.resources

import br.com.edersystems.backend.infrastructure.entities.product.Produto
import java.util.UUID

class ProductResponse private constructor(val id: UUID, val description: String, val status: Boolean) {

	companion object {
		fun toResponse(produto: Produto) = ProductResponse(
			id = produto.id!!,
			description = produto.description,
			status = produto.status
		)
	}
}
