/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 14:39:01
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.productcosif

import br.com.edersystems.backend.application.controllers.productcosif.resources.CreateProductCosifRequest
import br.com.edersystems.backend.configuration.UnitTestsConfiguration
import br.com.edersystems.backend.infrastructure.entities.product.Produto
import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosif
import br.com.edersystems.backend.infrastructure.entities.product.cosif.ProdutoCosifPK
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import java.util.UUID
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test

internal class ProdutoCosifServiceUnitTest : UnitTestsConfiguration() {
	@MockK
	private lateinit var service: ProdutoCosifService

	@Test
	fun `Given a valid codProduto and classification, should save produto cosif in database`() {
		val product = mockk<Produto> {
			every { description } returns "Teste Product"
			every { id } returns UUID.fromString("2D25BEDC-D813-4C11-B26E-3CE8F0F2C5FD")
			every { status } returns true
		}
		val produtoCosifPK = mockk<ProdutoCosifPK> {
			every { codProduto } returns product.id!!
			every { codCosif } returns UUID.fromString("6AAD368B-6795-40A8-9D81-A188435EC34B")
		}
		val request = mockk<CreateProductCosifRequest> {
			every { classificacao } returns "CL4ZVZ"
			every { codProduto } returns product.id!!
		}
		val productCosif = mockk<ProdutoCosif> {
			every { id } returns produtoCosifPK
			every { classification } returns request.classificacao
			every { status } returns true
		}
		every { service.create(request) } returns productCosif

		val createdProductCosif = service.create(request)

		createdProductCosif.id `should not be` null
		createdProductCosif.id.codCosif `should be equal to` productCosif.id.codCosif
		createdProductCosif.id.codProduto `should be equal to` productCosif.id.codProduto
	}
}
