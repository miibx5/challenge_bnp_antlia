/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:43:18
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.repositories

import br.com.edersystems.backend.builders.ProdutoBuilder
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

internal class ProdutoRepositoryTest : IntegrationConfigurationTests() {
	@Autowired
	private lateinit var repository: ProdutoRepository

	@Test
	fun `Should save product`() {
		val productToBeSave = ProdutoBuilder.withDescription("Primeiro Produto")
			.withStatus(true)
			.build()

		val productSaved = repository.save(productToBeSave)

		assertNotNull(productSaved.id)
		assertNotNull(productSaved.createdAt)
		assertNotNull(productSaved.updatedAt)
	}

	@Test
	fun `Should not save the product`() {
		val productToBeSave = ProdutoBuilder.withDescription("")
			.withStatus(true)
			.build()

		assertThrows<DataIntegrityViolationException> { repository.save(productToBeSave) }
	}
}
