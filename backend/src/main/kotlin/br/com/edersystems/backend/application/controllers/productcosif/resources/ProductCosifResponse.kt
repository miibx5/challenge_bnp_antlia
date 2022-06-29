/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 18:57:05
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.productcosif.resources

import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosif
import java.util.UUID

data class ProductCosifResponse private constructor(
	val codCosif: UUID,
	val codProduto: UUID,
	val classification: String
) {
	companion object {
		fun toResponse(cosif: ProdutoCosif) = ProductCosifResponse(
			codCosif = cosif.id.codCosif,
			codProduto = cosif.id.codProduto,
			classification = cosif.classification
		)
	}
}
