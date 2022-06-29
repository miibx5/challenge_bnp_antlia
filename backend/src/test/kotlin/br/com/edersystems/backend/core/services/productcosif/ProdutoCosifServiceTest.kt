/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 13:09:47
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.productcosif

import br.com.edersystems.backend.application.controllers.productcosif.resources.CreateProductCosifRequest
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.datasource.init.ScriptUtils

internal class ProdutoCosifServiceTest : IntegrationConfigurationTests() {
	@Autowired
	private lateinit var service: ProdutoCosifService

	@Test
	fun `Given a valid codProduto and classification, should save produto cosif in database`() {
		val filePath = "/sql/product-cosif/CREATE_PRODUCT_TO_CREATE_COSIF_PRODUCT.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(filePath))
		val requestBody = readJson("product-cosif/request/create_product_cosif_with_http_status_created")

		val request = mapper.readValue(requestBody, CreateProductCosifRequest::class.java)

		val savedProductCosif = service.create(request)

		savedProductCosif.id.codCosif `should not be` null
		savedProductCosif.id.codProduto `should be equal to` request.codProduto
		savedProductCosif.classification `should be equal to` request.classificacao
	}

	@Test
	fun `Given an invalid codProduto and a valid classification, should not save produto cosif in database`() {
		val filePath = "product-cosif/request/create_product_cosif_with_http_status_unprocessable_entity"
		val requestBody = readJson(filePath)
		val request = mapper.readValue(requestBody, CreateProductCosifRequest::class.java)

		assertThrows<DataIntegrityViolationException> { service.create(request) }
	}
}
