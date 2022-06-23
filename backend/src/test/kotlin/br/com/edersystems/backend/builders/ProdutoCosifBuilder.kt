/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 14:58:36
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.builders

import br.com.edersystems.backend.infrastructure.entities.product.ProdutoCosif
import br.com.edersystems.backend.infrastructure.entities.product.ProdutoCosifPK
import kotlin.properties.Delegates

object ProdutoCosifBuilder {

	lateinit var id: ProdutoCosifPK
		private set
	lateinit var classification: String
		private set
	var status by Delegates.notNull<Boolean>()
		private set

	fun build() = ProdutoCosif(id = id, classification = classification, status = status)
	fun withId(id: ProdutoCosifPK) = apply { this.id = id }
	fun withClassification(classification: String) = apply { this.classification = classification }
	fun withStatus(status: Boolean) = apply { this.status = status }
}
