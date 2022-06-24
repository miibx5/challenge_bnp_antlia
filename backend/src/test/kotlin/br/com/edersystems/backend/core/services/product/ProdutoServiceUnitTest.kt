/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 24/06/2022 15:57:33
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.product

import br.com.edersystems.backend.application.controllers.product.resources.CreateProductRequest
import br.com.edersystems.backend.configuration.UnitTestsConfiguration
import br.com.edersystems.backend.core.services.handleerror.HandleErrorService
import br.com.edersystems.backend.infrastructure.entities.product.Produto
import br.com.edersystems.backend.infrastructure.repositories.IProdutoRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class ProdutoServiceUnitTest : UnitTestsConfiguration() {

	@InjectMockKs
	private lateinit var service: ProdutoService

	@MockK
	private lateinit var repository: IProdutoRepository

	@MockK
	private lateinit var handleErrorService: HandleErrorService

	@Test
	fun `When a valid request, should save product in database`() {
		val request = mockk<CreateProductRequest> {
			every { description } returns "Teste Product"
		}
		val createdProduct = mockk<Produto> {
			every { description } returns "Teste Product"
			every { id } returns UUID.fromString("2D25BEDC-D813-4C11-B26E-3CE8F0F2C5FD")
			every { status } returns true
		}

		every { repository.save(request.toProduto()) } returns createdProduct

		assertDoesNotThrow { service.create(request) }
		verify(exactly = 1) { repository.save(request.toProduto()) }
	}
}
