/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:39:51
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.product

import br.com.edersystems.backend.application.controllers.product.resources.CreateProductRequest
import br.com.edersystems.backend.application.controllers.product.resources.PatchProductRequest
import br.com.edersystems.backend.core.commons.exceptions.error.Errors
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.exceptions.UnProcessableEntityException
import br.com.edersystems.backend.core.services.AbstractService
import br.com.edersystems.backend.infrastructure.entities.product.Produto
import br.com.edersystems.backend.infrastructure.exceptions.product.ProdutoNotFoundException
import br.com.edersystems.backend.infrastructure.repositories.IProdutoRepository
import br.com.edersystems.backend.infrastructure.util.MessageCodeUtil
import java.util.Objects
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ProdutoService(private val repository: IProdutoRepository) : AbstractService() {

	override val errors: Errors = createErrors()

	fun create(request: CreateProductRequest) = saveProduct(request.toProduto())

	fun delete(productId: UUID) {
		val retrievedProduct = getById(productId)
		val productToUpdate = retrievedProduct.updateStatus(false)
		saveProduct(productToUpdate)
	}

	fun getById(productId: UUID): Produto = repository.findById(productId).orElseThrow {
		throw ProdutoNotFoundException(createErrors(MessageCodeUtil.PROD_0002, productId))
	}

	fun patchProduct(productId: UUID, request: PatchProductRequest): Produto {
		val retrievedProduct = getById(productId)
		saveProduct(retrievedProduct.updateProduct(request.description, request.status))
		return getById(productId)
	}

	fun validateCreateProduct(request: CreateProductRequest) {
		errors.clearErrors()

		validateProductDescription(request.description)?.let { errors.exceptionErrors.add(it) }

		throwErrors(UnProcessableEntityException(errors.exceptionErrors))
	}

	fun validatePatchProduct(request: PatchProductRequest) {
		errors.clearErrors()

		validatePatchProductRequest(request)?.let { errors.exceptionErrors.add(it) }

		throwErrors(UnProcessableEntityException(errors.exceptionErrors))
	}

	fun isExistsProduct(codProduto: UUID) = repository.findById(codProduto).isPresent

	private fun saveProduct(produto: Produto) = repository.save(produto)

	private fun validateProductDescription(description: String): ExceptionError? {
		return if (description.isBlank()) {
			createError(MessageCodeUtil.PROD_0001)
		} else null
	}

	private fun validatePatchProductRequest(request: PatchProductRequest): ExceptionError? {
		return if ((request.description?.isBlank() == true) && (Objects.isNull(request.status))) {
			createError(MessageCodeUtil.DEFAULT)
		} else null
	}
}
