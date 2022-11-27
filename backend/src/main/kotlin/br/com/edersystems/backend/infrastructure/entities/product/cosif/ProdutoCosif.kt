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
package br.com.edersystems.backend.infrastructure.entities.product.cosif

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime
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
