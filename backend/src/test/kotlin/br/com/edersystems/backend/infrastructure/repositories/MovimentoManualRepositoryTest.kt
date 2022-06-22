/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 16:36:18
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.repositories

import br.com.edersystems.backend.builders.MovimentoManualBuilder
import br.com.edersystems.backend.builders.MovimentoManualPKBuilder
import br.com.edersystems.backend.builders.ProdutoBuilder
import br.com.edersystems.backend.builders.ProdutoCosifBuilder
import br.com.edersystems.backend.builders.ProdutoCosifPKBuilder
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

internal class MovimentoManualRepositoryTest : IntegrationConfigurationTests() {
	@Autowired
	private lateinit var repository: MovimentoManualRepository

	@Autowired
	private lateinit var produtoCosifRepository: ProdutoCosifRepository

	@Autowired
	private lateinit var produtoRepository: ProdutoRepository

	@Test
	fun `Should save manual movement`() {
		val productToBeSave = ProdutoBuilder.withDescription("Teste").withStatus(true).build()
		val codProduto = produtoRepository.save(productToBeSave).id!!
		val produtoCosifToBeSave = ProdutoCosifBuilder.withId(ProdutoCosifPKBuilder.withCodProduct(codProduto).build())
			.withClassification("CL4Q14").withStatus(true).build()
		val produtoCosif = produtoCosifRepository.save(produtoCosifToBeSave).id.codCosif
		val movementManalPk = MovimentoManualPKBuilder.withCodCosif(produtoCosif)
			.withCodProduto(codProduto)
			.withMonth(4)
			.withYear(2022)
			.withNumberLaunch(BigDecimal.valueOf(10000))
			.build()
		val movementManualToBeSave = MovimentoManualBuilder
			.withId(movementManalPk)
			.withUserCode("CL4Q3ELBR")
			.withDescription("Movimentação de Teste")
			.withDateMovement(LocalDateTime.of(2022, 4, 20, 0, 0, 0))
			.withAmount(BigDecimal.valueOf(10000))
			.build()

		val movementManualSaved = repository.save(movementManualToBeSave)

		movementManualSaved.id.codProduto `should not be` null
		movementManualSaved.id.codCosif `should not be` null
		movementManualSaved.id.codProduto `should be equal to` codProduto
		movementManualSaved.id.codCosif `should be equal to` produtoCosif
	}

	@Test
	fun `Should not save manual movement`() {
		val codProduto = UUID.randomUUID()
		val produtoCosif = UUID.randomUUID()
		val movementManalPk = MovimentoManualPKBuilder.withCodCosif(produtoCosif)
			.withCodProduto(codProduto)
			.withMonth(4)
			.withYear(2022)
			.withNumberLaunch(BigDecimal.valueOf(10000))
			.build()
		val movementManualToBeSave = MovimentoManualBuilder
			.withId(movementManalPk)
			.withUserCode("CL4Q3ELBR")
			.withDescription("Movimentação de Teste")
			.withDateMovement(LocalDateTime.of(2022, 4, 20, 0, 0, 0))
			.withAmount(BigDecimal.valueOf(10000))
			.build()

		assertThrows<DataIntegrityViolationException> { repository.save(movementManualToBeSave) }
	}
}
