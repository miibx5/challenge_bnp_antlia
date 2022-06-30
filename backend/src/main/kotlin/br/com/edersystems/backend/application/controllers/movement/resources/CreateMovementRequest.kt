/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 17:52:14
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.movement.resources

import br.com.edersystems.backend.core.extensions.setScaleFour
import br.com.edersystems.backend.core.extensions.setScaleThree
import br.com.edersystems.backend.core.extensions.setScaleTwo
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManual
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import java.math.BigDecimal
import java.util.UUID

data class CreateMovementRequest private constructor(
	val month: BigDecimal,
	val year: BigDecimal,
	val numberLaunch: BigDecimal,
	val codProduto: UUID,
	val codCosif: UUID,
	val description: String,
	val userCode: String,
	val amount: BigDecimal,
) {
	fun toMovimento() = MovimentoManual(
		id = toMovimentoPk(),
		description = description,
		amount = amount,
		userCode = userCode
	)

	private fun toMovimentoPk() = MovimentoManualPK(
		month = month.setScaleTwo(),
		year = year.setScaleFour(),
		numberLaunch = numberLaunch.setScaleThree(),
		codProduto = codProduto,
		codCosif = codCosif
	)
}
