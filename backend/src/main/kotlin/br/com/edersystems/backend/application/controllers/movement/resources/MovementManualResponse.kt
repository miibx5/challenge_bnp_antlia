/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 18:37:30
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.movement.resources

import br.com.edersystems.backend.core.extensions.moneyFormat
import br.com.edersystems.backend.core.extensions.putZero
import br.com.edersystems.backend.core.extensions.toPtBrFormat
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManual
import java.util.UUID

data class MovementManualResponse private constructor(
	val dataMovimento: String,
	val mes: String,
	val ano: String,
	val descricao: String,
	val numeroLancamento: String,
	val valor: String,
	val codProduto: UUID,
	val codCosif: UUID,
) {
	companion object {
		fun toResponse(movimento: MovimentoManual) = MovementManualResponse(
			dataMovimento = movimento.dateMovement.toPtBrFormat(),
			mes = movimento.id.month.putZero(),
			ano = movimento.id.year.putZero(),
			descricao = movimento.description,
			numeroLancamento = movimento.id.numberLaunch.putZero(),
			valor = movimento.amount.moneyFormat(),
			codCosif = movimento.id.codCosif,
			codProduto = movimento.id.codProduto
		)
	}
}
