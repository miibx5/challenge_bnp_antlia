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

import br.com.edersystems.backend.core.extensions.setScaleFour
import br.com.edersystems.backend.core.extensions.setScaleThree
import br.com.edersystems.backend.core.extensions.setScaleTwo
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import java.math.BigDecimal
import java.util.UUID

object MovimentoManualPKBuilder {

	lateinit var month: BigDecimal
		private set
	lateinit var year: BigDecimal
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

	fun withMonth(month: BigDecimal) = apply { this.month = month.setScaleTwo() }
	fun withYear(year: BigDecimal) = apply { this.year = year.setScaleFour() }
	fun withNumberLaunch(numberLaunch: BigDecimal) = apply { this.numberLaunch = numberLaunch.setScaleThree() }
	fun withCodProduto(codProduto: UUID) = apply { this.codProduto = codProduto }
	fun withCodCosif(codCosif: UUID) = apply { this.codCosif = codCosif }
}
