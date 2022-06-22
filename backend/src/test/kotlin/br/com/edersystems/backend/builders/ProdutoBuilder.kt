/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:54:23
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.builders

import br.com.edersystems.backend.infrastructure.entities.Produto
import kotlin.properties.Delegates

object ProdutoBuilder {
	lateinit var description: String
		private set
	var status by Delegates.notNull<Boolean>()
		private set

	fun build() = Produto(description = description, status = status)
	fun withDescription(description: String) = apply { this.description = description }
	fun withStatus(status: Boolean) = apply { this.status = status }
}
