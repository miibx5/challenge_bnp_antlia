/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:11:29
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.product

import br.com.edersystems.backend.application.controllers.product.resources.CreateProductRequest
import br.com.edersystems.backend.application.controllers.product.resources.PatchProductRequest
import br.com.edersystems.backend.application.controllers.product.resources.ProductResponse
import br.com.edersystems.backend.application.extensions.APPLICATION_JSON_UTF8_VALUE
import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.application.validation.annotation.RequestValidator
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import br.com.edersystems.backend.core.services.product.ProdutoService
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto", produces = [APPLICATION_JSON_UTF8_VALUE])
class ProdutoController(private val service: ProdutoService) {

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	@RequestValidator
	fun create(@RequestBody request: CreateProductRequest): ResponseEntity<ResponseTO> {
		val createdProduct = service.create(request)
		val response = ProductResponse.toResponse(createdProduct)
		return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.CREATED.name), HttpStatus.CREATED)
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: UUID) {
		service.delete(id)
	}

	@GetMapping("/{id}")
	fun getById(@PathVariable id: UUID): ResponseEntity<ResponseTO> {
		val retrievedProduct = service.getById(id)
		val response = ProductResponse.toResponse(retrievedProduct)
		return ResponseEntity.ok(ResponseTOFactory.success(response))
	}

	@PatchMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
	fun patch(@PathVariable id: UUID, @RequestBody request: PatchProductRequest): ResponseEntity<ResponseTO> {
		val updatedProduto = service.patchProduct(id, request)
		val response = ProductResponse.toResponse(updatedProduto)
		return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.ACCEPTED.name), HttpStatus.ACCEPTED)
	}
}
