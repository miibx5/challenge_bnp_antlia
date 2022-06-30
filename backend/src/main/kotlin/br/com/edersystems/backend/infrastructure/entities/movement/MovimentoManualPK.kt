/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:24:16
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.entities.movement

import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MovimentoManualPK(
	@Column(name = "DAT_MES")
	val month: BigDecimal,
	@Column(name = "DAT_ANO")
	val year: BigDecimal,
	@Column(name = "NUM_LANCAMENTO")
	val numberLaunch: BigDecimal,
	@Column(name = "COD_PRODUTO")
	val codProduto: UUID,
	@Column(name = "COD_COSIF")
	val codCosif: UUID
) : Serializable {
	companion object {
		private const val serialVersionUID = -126L
	}
}
