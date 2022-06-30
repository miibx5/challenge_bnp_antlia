/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:29:59
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.entities.movement

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "MOVIMENTO_MANUAL")
data class MovimentoManual(
	@EmbeddedId
	val id: MovimentoManualPK,
	@Column(name = "DES_DESCRICAO")
	val description: String,
	@Column(name = "COD_USUARIO")
	val userCode: String,
	@Column(name = "VAL_VALOR")
	val amount: BigDecimal,
	@Column(name = "DAT_MOVIMENTO")
	val dateMovement: LocalDateTime = LocalDateTime.now(),
	@CreationTimestamp
	@Column(name = "CREATED_AT")
	val createdAt: LocalDateTime? = null,
	@UpdateTimestamp
	@Column(name = "UPDATED_AT")
	val updatedAt: LocalDateTime? = null
) {
	fun update(description: String?, userCode: String?, amount: BigDecimal?) = copy(
		amount = amount ?: this.amount,
		description = description ?: this.description,
		userCode = userCode ?: this.userCode
	)
}
