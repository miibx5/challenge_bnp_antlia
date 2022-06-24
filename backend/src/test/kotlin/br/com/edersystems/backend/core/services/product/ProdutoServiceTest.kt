/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 24/06/2022 15:48:15
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.product

import br.com.edersystems.backend.application.controllers.product.resources.CreateProductRequest
import br.com.edersystems.backend.configuration.IntegrationConfigurationTests
import br.com.edersystems.backend.configuration.readjson.readJson
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class ProdutoServiceTest : IntegrationConfigurationTests() {

	@Autowired
	private lateinit var service: ProdutoService

	@Test
	fun `Given a valid product request data should save product in database`() {
		val requestBodyJson = readJson("product/request/create_product_with_success")
		val request = mapper.readValue(requestBodyJson, CreateProductRequest::class.java)

		val savedProduct = service.create(request)

		savedProduct.id `should not be` null
		savedProduct.description `should be equal to` request.description
	}
}
