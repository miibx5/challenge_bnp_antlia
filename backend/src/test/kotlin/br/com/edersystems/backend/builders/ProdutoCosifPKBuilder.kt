/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 15:02:04
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.builders

import br.com.edersystems.backend.infrastructure.entities.ProdutoCosifPK
import java.util.UUID

object ProdutoCosifPKBuilder {
	lateinit var codProduto: UUID
		private set

	fun build() = ProdutoCosifPK(codProduto = codProduto)

	fun withCodProduct(codProduto: UUID) = apply { this.codProduto = codProduto }
}
