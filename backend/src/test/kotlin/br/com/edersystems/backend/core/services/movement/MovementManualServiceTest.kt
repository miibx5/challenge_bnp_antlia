/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 30/06/2022 17:20:25
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.movement

import br.com.edersystems.backend.application.controllers.movement.resources.CreateMovementRequest
import br.com.edersystems.backend.application.controllers.movement.resources.PatchMovementRequest
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import com.fasterxml.jackson.module.kotlin.readValue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.datasource.init.ScriptUtils

internal class MovementManualServiceTest : IntegrationConfigurationTests() {
	@Autowired
	private lateinit var service: MovementManualService

	@Test
	fun `Given a valid request data should create movement manual in database`() {
		val sqlFilePath = "/sql/movement/CREATE_PRODUCT_AND_PRODUCT_COSIF_FOR_CREATE_MOVEMENT.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlFilePath))
		val requestJsonBody = readJson("movement/request/create_movement_with_http_status_created")
		val expected = mapper.readValue<CreateMovementRequest>(requestJsonBody)

		val actual = service.create(expected)

		actual `should not be` null
		actual.amount `should be equal to` expected.amount
		actual.id.codCosif `should be equal to` expected.codCosif
		actual.id.codProduto `should be equal to` expected.codProduto
	}

	@Test
	fun `Given a valid request data should throw DataIntegrityViolationException`() {
		val requestJsonBody = readJson("movement/request/create_movement_with_http_status_created")
		val request = mapper.readValue<CreateMovementRequest>(requestJsonBody)

		assertThrows<DataIntegrityViolationException> { service.create(request) }
	}

	@Test
	fun `Given a valid request data should update movement manual in database`() {
		val sqlFilePath = "/sql/movement/CREATE_PRODUCT_AND_PRODUCT_COSIF_AND_MOVEMENT_MANUAL.sql"
		ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlFilePath))
		val requestBody = readJson("movement/request/create_movement_with_http_status_accepted")
		val expected = mapper.readValue<PatchMovementRequest>(requestBody)

		val actual = service.patchMovement(expected)

		actual.amount `should be equal to` expected.amount!!
		actual.id.codCosif `should be equal to` expected.codCosif
		actual.id.codProduto `should be equal to` expected.codProduto
	}
}
