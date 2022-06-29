/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:08:37
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.entities.product.cosif

import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProdutoCosifPK(
	@Column(name = "COD_PRODUTO")
	val codProduto: UUID,
	@Column(name = "COD_COSIF")
	val codCosif: UUID = UUID.randomUUID()
) : Serializable {
	companion object {
		private const val serialVersionUID = -91L
	}
}
