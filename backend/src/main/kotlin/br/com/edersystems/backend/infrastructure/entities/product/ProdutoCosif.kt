/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:07:58
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.entities.product

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "PRODUTO_COSIF")
data class ProdutoCosif(
	@EmbeddedId
	val id: ProdutoCosifPK,
	@Column(name = "COD_CLASSIFICACAO")
	val classification: String,
	@Column(name = "STA_STATUS")
	val status: Boolean,
	@CreationTimestamp
	@Column(name = "CREATED_AT")
	val createdAt: LocalDateTime? = null,
	@UpdateTimestamp
	@Column(name = "UPDATED_AT")
	val updatedAt: LocalDateTime? = null
)
