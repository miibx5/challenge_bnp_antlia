/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 18:57:48
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.product

import br.com.edersystems.backend.configuration.ControllerConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import br.com.edersystems.backend.core.extensions.getField
import org.amshove.kluent.assertEqualsIgnoringCase
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBeEmpty
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.jdbc.datasource.init.ScriptUtils

internal class ProdutoControllerTest : ControllerConfigurationTests() {

	@Test
	fun `Given a valid product request data should return http status Created`() {
		val requestBody = readJson("product/request/create_product_with_success")

		val (status, response) = post("/produto", requestBody)
		val productId = getFieldFromJsonNode(response, id)

		status `should be equal to` HttpStatus.CREATED
		productId `should not be` null
		isUUID(productId) `should be` true
	}

	@Test
	fun `Given an invalid product request data should return throw exception`() {
		val requestBody = "{\"description\":\"\"}"
		val expected = "O Campo descrição deve estar preenchido."

		val (status, response) = post("/produto", requestBody)
		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		assertThat(parsedResponse).hasSize(2).containsOnlyKeys(code, this.errors)
		status `should be equal to` HttpStatus.UNPROCESSABLE_ENTITY
		actual `should be equal to` (expected)
	}

	@Test
	fun `Given a valid product id should return http status no content`() {
		ScriptUtils.executeSqlScript(connection, ClassPathResource("/sql/product/DELETE_SUCCESSFUL_PRODUCT.sql"))
		val productId = "9F1292EF-AD02-4559-9B20-0811E1DD85BA"

		val (status, response) = delete("/produto/$productId")

		status `should be equal to` HttpStatus.NO_CONTENT
		response.shouldBeEmpty()
	}

	@Test
	fun `Given an invalid product id should return throw `() {
		val productId = "136AF845-FF51-4A70-9B36-0DBA4A30EC36"
		val expected = "Produto 136af845-ff51-4a70-9b36-0dba4a30ec36 não encontrado."

		val (status, response) = delete("/produto/$productId")
		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		assertThat(parsedResponse).hasSize(2).containsOnlyKeys(code, this.errors)
		status `should be equal to` HttpStatus.NOT_FOUND
		actual `should be equal to` (expected)
	}

	@Test
	fun `Given a valid product id should return http status ok`() {
		ScriptUtils.executeSqlScript(connection, ClassPathResource("/sql/product/GET_PRODUCT_BY_ID_SUCCESSFUL.sql"))
		val productId = "339F3DAE-F407-4860-BEA9-A595437D1922"

		val (status, response) = get("/produto/$productId")
		val actual = getFieldFromJsonNode(response, id)

		status `should be equal to` HttpStatus.OK
		assertEqualsIgnoringCase(productId, actual)
	}

	@Test
	fun `Given a valid product request data should return http status Accepted`() {
		ScriptUtils.executeSqlScript(connection, ClassPathResource("/sql/product/UPDATE_PRODUCT_SUCCESSFUL.sql"))
		val productId = "04217D2C-DBAF-46FF-8963-991B0CCCE2DB"
		val expected = "Changed Product Test"
		val requestBody = readJson("product/request/update_product_with_success")

		val (status, response) = patch("/produto/$productId", requestBody)
		val actual = getFieldFromJsonNode(response, description)

		status `should be equal to` HttpStatus.ACCEPTED
		actual `should be equal to` expected
	}
}
