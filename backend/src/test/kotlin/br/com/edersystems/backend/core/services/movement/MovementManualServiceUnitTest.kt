/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 30/06/2022 18:50:13
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.movement

import br.com.edersystems.backend.application.controllers.movement.resources.CreateMovementRequest
import br.com.edersystems.backend.configuration.UnitTestsConfiguration
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManual
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import br.com.edersystems.backend.infrastructure.entities.product.Produto
import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosif
import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosifPK
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import java.math.BigDecimal
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MovementManualServiceUnitTest : UnitTestsConfiguration() {

	@MockK
	private lateinit var service: MovementManualService

	@Test
	fun `Given a valid request data should create movement manual in database`() {
		val product = mockProduto()
		val produtoCosifPK = mockProdutoCosifPK(product)
		val productCosif = mockProdutoCosif(produtoCosifPK)
		val movimentoManualPK = mockMovimentoManualPK(productCosif)
		val movimentoManual = mockMovimentoManual(movimentoManualPK)
		val request = mockRequest()
		every { service.create(request) } returns movimentoManual

		val savedMovement = service.create(request)

		verify(exactly = 1) { service.create(request) }
		assertEquals(savedMovement.id.codCosif, request.codCosif)
		assertEquals(savedMovement.id.codProduto, request.codProduto)
	}

	private fun mockRequest() = mockk<CreateMovementRequest> {
		every { codCosif } returns UUID.fromString("41EDE5D2-A9F0-4FA9-A98D-5DE7002C3371")
		every { codProduto } returns UUID.fromString("33705334-1B84-4B49-B4A9-1DFB4845F6B5")
		every { month } returns BigDecimal.valueOf(1)
		every { year } returns BigDecimal.valueOf(2022)
		every { numberLaunch } returns BigDecimal.valueOf(1)
		every { description } returns "Test Launch"
		every { userCode } returns "CL512DO0H0"
		every { amount } returns BigDecimal.valueOf(1200)
	}

	private fun mockProduto() = mockk<Produto> {
		every { description } returns "Teste Product"
		every { id } returns UUID.fromString("33705334-1B84-4B49-B4A9-1DFB4845F6B5")
		every { status } returns true
	}

	private fun mockProdutoCosifPK(product: Produto) = mockk<ProdutoCosifPK> {
		every { codProduto } returns product.id!!
		every { codCosif } returns UUID.fromString("41EDE5D2-A9F0-4FA9-A98D-5DE7002C3371")
	}

	private fun mockProdutoCosif(produtoCosifPK: ProdutoCosifPK) = mockk<ProdutoCosif> {
		every { id } returns produtoCosifPK
		every { classification } returns "CL4ZVZ"
		every { status } returns true
	}

	private fun mockMovimentoManualPK(productCosif: ProdutoCosif) = mockk<MovimentoManualPK> {
		every { codCosif } returns productCosif.id.codCosif
		every { codProduto } returns productCosif.id.codProduto
		every { numberLaunch } returns BigDecimal.valueOf(1)
		every { month } returns BigDecimal.valueOf(1)
		every { year } returns BigDecimal.valueOf(2022)
	}

	private fun mockMovimentoManual(movimentoManualPK: MovimentoManualPK) = mockk<MovimentoManual> {
		every { id } returns movimentoManualPK
		every { description } returns "Test Launch"
		every { userCode } returns "CL512DO0H0"
		every { amount } returns BigDecimal.valueOf(1200)
	}
}
