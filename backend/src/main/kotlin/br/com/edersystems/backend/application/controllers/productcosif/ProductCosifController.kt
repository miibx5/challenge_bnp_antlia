/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 18:13:19
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.productcosif

import br.com.edersystems.backend.application.controllers.productcosif.resources.CreateProductCosifRequest
import br.com.edersystems.backend.application.controllers.productcosif.resources.ProductCosifResponse
import br.com.edersystems.backend.application.extensions.APPLICATION_JSON_UTF8_VALUE
import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.application.validation.annotation.RequestValidator
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import br.com.edersystems.backend.core.services.productcosif.ProdutoCosifService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto-cosif", produces = [APPLICATION_JSON_UTF8_VALUE])
class ProductCosifController(private val service: ProdutoCosifService) {

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	@RequestValidator
	fun create(@RequestBody request: CreateProductCosifRequest): ResponseEntity<ResponseTO> {
		val createdProductCosif = service.create(request)
		val response = ProductCosifResponse.toResponse(createdProductCosif)
		return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.CREATED.name), HttpStatus.CREATED)
	}
}
