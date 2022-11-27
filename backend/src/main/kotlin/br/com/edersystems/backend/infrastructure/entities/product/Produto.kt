/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 11:53:31
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.entities.product

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "PRODUTO")
data class Produto(
    @Column(name = "DES_PRODUTO")
    val description: String,
    @Column(name = "STA_STATUS")
    val status: Boolean = true,
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "COD_PRODUTO")
    val id: UUID? = null,
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    val updatedAt: LocalDateTime? = null
) {
    fun updateProduct(description: String?, status: Boolean?) = copy(
        description = description ?: this.description,
        status = status ?: this.status
    )

    fun updateStatus(status: Boolean) = copy(status = status)
}
