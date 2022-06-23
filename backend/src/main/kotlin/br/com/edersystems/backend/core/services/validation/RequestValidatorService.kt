/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 17:48:08
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.validation

import br.com.edersystems.backend.application.controllers.product.resources.CreateProductRequest
import br.com.edersystems.backend.application.controllers.product.resources.PatchProductRequest
import br.com.edersystems.backend.core.services.product.ProdutoService
import org.springframework.stereotype.Service

@Service
class RequestValidatorService(private val produtoService: ProdutoService) {
	fun validateRequest(request: Any) {
		when (request) {
			is CreateProductRequest -> produtoService.validateCreateProduct(request)
			is PatchProductRequest -> produtoService.validatePatchProduct(request)
		}
	}
}
