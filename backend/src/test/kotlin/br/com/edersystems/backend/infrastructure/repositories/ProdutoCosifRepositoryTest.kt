/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 14:55:50
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.repositories

import br.com.edersystems.backend.builders.ProdutoBuilder
import br.com.edersystems.backend.builders.ProdutoCosifBuilder
import br.com.edersystems.backend.builders.ProdutoCosifPKBuilder
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

internal class ProdutoCosifRepositoryTest : IntegrationConfigurationTests() {
	@Autowired
	private lateinit var repository: ProdutoCosifRepository

	@Autowired
	private lateinit var produtoRepository: ProdutoRepository

	@Test
	fun `Should save product cosif`() {
		val productToBeSave = ProdutoBuilder.withDescription("Teste").withStatus(true).build()
		val savedProduct = produtoRepository.save(productToBeSave)
		val productId = savedProduct.id!!
		val produtoCosifToBeSave = ProdutoCosifBuilder.withId(ProdutoCosifPKBuilder.withCodProduct(productId).build())
			.withClassification("CL4Q13").withStatus(true).build()

		val produtoCosifSaved = repository.save(produtoCosifToBeSave)

		assertNotNull(produtoCosifSaved.id)
		assertNotNull(produtoCosifSaved.createdAt)
		assertNotNull(produtoCosifSaved.updatedAt)
	}

	@Test
	fun `Should not save product cosif`() {
		val productId = UUID.randomUUID()

		val produtoCosifToBeSave = ProdutoCosifBuilder.withId(ProdutoCosifPKBuilder.withCodProduct(productId).build())
			.withClassification("Classificação").withStatus(true).build()

		assertThrows<DataIntegrityViolationException> { repository.save(produtoCosifToBeSave) }
	}
}
