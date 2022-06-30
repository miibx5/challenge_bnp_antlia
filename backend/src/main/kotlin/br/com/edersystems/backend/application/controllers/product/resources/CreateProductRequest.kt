/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:16:20
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.product.resources

import br.com.edersystems.backend.infrastructure.entities.product.Produto

data class CreateProductRequest private constructor(val description: String) {
	fun toProduto() = Produto(description = this.description)
}
