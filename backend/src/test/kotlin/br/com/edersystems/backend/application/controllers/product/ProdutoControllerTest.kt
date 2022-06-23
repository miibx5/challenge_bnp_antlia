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
import br.com.edersystems.backend.core.extensions.getDataFromJson
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

internal class ProdutoControllerTest : ControllerConfigurationTests() {

	@Test
	fun `Given a valid product request data should return http status Created`() {
		val requestBody = readJson("product/request/create_product_with_success")

		val (status, response) = post("/produto", requestBody)

		val productId = response.getDataFromJson("id")

		status `should be equal to` HttpStatus.CREATED
		productId `should not be` null
	}
}
