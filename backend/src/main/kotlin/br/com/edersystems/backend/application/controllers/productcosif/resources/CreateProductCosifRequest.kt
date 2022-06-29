/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 18:14:55
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.productcosif.resources

import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosif
import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosifPK
import java.util.UUID

data class CreateProductCosifRequest private constructor(val codProduto: UUID, val classificacao: String) {

	fun toProdutoCosif() = ProdutoCosif(id = toProdutoCosifPK(), classification = classificacao, status = true)

	private fun toProdutoCosifPK() = ProdutoCosifPK(codProduto)
}
