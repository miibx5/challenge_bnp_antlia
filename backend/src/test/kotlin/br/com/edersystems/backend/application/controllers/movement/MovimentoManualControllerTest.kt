/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 30/06/2022 10:06:40
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.movement

import br.com.edersystems.backend.application.controllers.movement.resources.MovementManualResponse
import br.com.edersystems.backend.application.controllers.movement.resources.PatchMovementRequest
import br.com.edersystems.backend.configuration.ControllerConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import br.com.edersystems.backend.core.extensions.getField
import br.com.edersystems.backend.core.extensions.moneyFormat
import com.fasterxml.jackson.module.kotlin.readValue
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.jdbc.datasource.init.ScriptUtils

internal class MovimentoManualControllerTest : ControllerConfigurationTests() {
	@Test
	fun `Given a valid request data should return http status Created`() {
		val sqlFilePath = "/sql/movement/CREATE_PRODUCT_AND_PRODUCT_COSIF_FOR_CREATE_MOVEMENT.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlFilePath))
		val requestBody = readJson("movement/request/create_movement_with_http_status_created")
		val expectedCodCosif = "57156001-B886-4DE8-BEBF-1D3369BD96DC"
		val expectedCodProduto = "0589F0B6-067F-49E2-A6DC-FD9B7F10F58B"

		val (status, response) = post("/movimento", requestBody)
		val codCosif = getFieldFromJsonNode(response, codCosif).uppercase()
		val codProduto = getFieldFromJsonNode(response, codProduto).uppercase()

		status `should be equal to` HttpStatus.CREATED
		codCosif `should be equal to` expectedCodCosif
		codProduto `should be equal to` expectedCodProduto
	}

	@Test
	fun `Given a valid request data should return http status Conflict`() {
		val requestBody = readJson("movement/request/create_movement_with_http_status_conflict")
		val expectedMessage = "could not execute statement"

		val (status, response) = post("/movimento", requestBody)
		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		status `should be equal to` HttpStatus.CONFLICT
		actual `should be equal to` expectedMessage
	}

	@Test
	fun `Given an invalid request data should throw UnProcessableEntityException`() {
		val sqlFilePath = "/sql/movement/CREATE_PRODUCT_AND_PRODUCT_COSIF_FOR_CREATE_MOVEMENT.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlFilePath))
		val jsonFilePath = "movement/request/post_create_movement_throw_unprocessable_entity_exception"
		val requestBody = readJson(jsonFilePath)
		val expectedMessage = "O campo ano deve estar preenchido."

		val (status, response) = post("/movimento", requestBody)
		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		status `should be equal to` HttpStatus.UNPROCESSABLE_ENTITY
		actual `should be equal to` expectedMessage
	}

	@Test
	fun `Given a valid movement request data should return http status Accepted`() {
		val sqlFilePath = "/sql/movement/CREATE_PRODUCT_AND_PRODUCT_COSIF_AND_MOVEMENT_MANUAL.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlFilePath))
		val requestBody = readJson("movement/request/create_movement_with_http_status_accepted")
		val expected = mapper.readValue<PatchMovementRequest>(requestBody)

		val (status, response) = patch("/movimento", requestBody)
		val parsedResponse = getStringJsonFromJsonNode(response)
		val actual = mapper.readValue<MovementManualResponse>(parsedResponse.toString())

		status `should be equal to` HttpStatus.ACCEPTED
		actual.valor `should be equal to` expected.amount!!.moneyFormat()
	}

	@Test
	fun `Given an invalid movement request data should throw UnProcessableEntityException`() {
		val jsonFilePath = "movement/request/patch_create_movement_throw_unprocessable_entity_exception"
		val requestBody = readJson(jsonFilePath)
		val expected = "Payload inválido."

		val (status, response) = patch("/movimento", requestBody)
		val parsedResponse = getJsonObject(response)
		val errors = getValueFromJsonObject(parsedResponse)
		val actual = errors.getField(message)

		status `should be equal to` HttpStatus.UNPROCESSABLE_ENTITY
		actual `should be equal to` expected
	}

	@Test
	fun `Given a valid movement request data should return http status Not Found`() {
		val jsonFilePath = "movement/request/create_movement_with_http_status_created"
		val requestBody = readJson(jsonFilePath)

		val (status) = patch("/movimento", requestBody)

		status `should be equal to` HttpStatus.NOT_FOUND
	}
}
