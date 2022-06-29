/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 19:30:27
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.productcosif

import br.com.edersystems.backend.configuration.ControllerConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import br.com.edersystems.backend.core.extensions.getField
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.jdbc.datasource.init.ScriptUtils

internal class ProductCosifControllerTest : ControllerConfigurationTests() {
	@Test
	fun `Given a valid codProduto and classification, should return http status Created`() {
		val filePath = "/sql/product-cosif/CREATE_PRODUCT_TO_CREATE_COSIF_PRODUCT.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(filePath))
		val requestBody = readJson("product-cosif/request/create_product_cosif_with_http_status_created")
		val expectedCodProduct = "c6ef176a-0abc-4f00-90ad-2c1f5a5c2207"

		val (status, response) = post("/produto-cosif", requestBody)

		val codCosif = getFieldFromJsonNode(response, codCosif)
		val codProduto = getFieldFromJsonNode(response, codProduto)

		status `should be equal to` HttpStatus.CREATED
		codProduto `should be equal to` expectedCodProduct
		codCosif `should not be` null
		isUUID(codCosif) `should be` true
	}

	@Test
	fun `Given an invalid codProduto and a valid classification, should return http status unprocessable entity`() {
		val filePath = "product-cosif/request/create_product_cosif_with_http_status_unprocessable_entity"
		val requestBody = readJson(filePath)
		val expected = "Produto 86be329d-ed7b-4d2f-9a73-7e73927c7385 não encontrado."

		val (status, response) = post("/produto-cosif", requestBody)

		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		status `should be equal to` HttpStatus.UNPROCESSABLE_ENTITY
		actual `should be equal to` (expected)
	}
}
