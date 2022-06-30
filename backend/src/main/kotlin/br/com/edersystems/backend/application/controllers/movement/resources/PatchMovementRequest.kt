/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 18:13:20
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
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import java.math.BigDecimal
import java.util.UUID

data class PatchMovementRequest(
	val month: BigDecimal,
	val year: BigDecimal,
	val numberLaunch: BigDecimal,
	val codProduto: UUID,
	val codCosif: UUID,
	val description: String?,
	val userCode: String?,
	val amount: BigDecimal?,
) {
	fun toMovimentoPk() = MovimentoManualPK(
		month = month.setScaleTwo(),
		year = year.setScaleFour(),
		numberLaunch = numberLaunch.setScaleThree(),
		codProduto = codProduto,
		codCosif = codCosif
	)
}
