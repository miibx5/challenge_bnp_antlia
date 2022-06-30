/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 17:38:30
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.builders

import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManual
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import java.math.BigDecimal

object MovimentoManualBuilder {
	lateinit var id: MovimentoManualPK
		private set
	lateinit var description: String
		private set
	lateinit var userCode: String
		private set
	lateinit var amount: BigDecimal
		private set

	fun build() = MovimentoManual(
		id = id,
		description = description,
		userCode = userCode,
		amount = amount
	)

	fun withId(id: MovimentoManualPK) = apply { this.id = id }
	fun withDescription(description: String) = apply { this.description = description }
	fun withUserCode(userCode: String) = apply { this.userCode = userCode }
	fun withAmount(amount: BigDecimal) = apply { this.amount = amount }
}
