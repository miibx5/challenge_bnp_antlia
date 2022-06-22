/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 17:31:28
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.builders

import br.com.edersystems.backend.infrastructure.entities.MovimentoManualPK
import java.math.BigDecimal
import java.util.UUID
import kotlin.properties.Delegates

object MovimentoManualPKBuilder {

	var month by Delegates.notNull<Short>()
		private set
	var year by Delegates.notNull<Short>()
		private set
	lateinit var numberLaunch: BigDecimal
		private set
	lateinit var codProduto: UUID
		private set
	lateinit var codCosif: UUID
		private set

	fun build() = MovimentoManualPK(
		month = month,
		year = year,
		numberLaunch = numberLaunch,
		codProduto = codProduto,
		codCosif = codCosif
	)

	fun withMonth(month: Short) = apply { this.month = month }
	fun withYear(year: Short) = apply { this.year = year }
	fun withNumberLaunch(numberLaunch: BigDecimal) = apply { this.numberLaunch = numberLaunch }
	fun withCodProduto(codProduto: UUID) = apply { this.codProduto = codProduto }
	fun withCodCosif(codCosif: UUID) = apply { this.codCosif = codCosif }
}
