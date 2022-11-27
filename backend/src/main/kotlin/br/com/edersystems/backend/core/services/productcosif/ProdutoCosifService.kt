/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 18:23:57
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.services.productcosif

import br.com.edersystems.backend.application.controllers.productcosif.resources.CreateProductCosifRequest
import br.com.edersystems.backend.core.commons.exceptions.error.Errors
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.exceptions.UnProcessableEntityException
import br.com.edersystems.backend.core.services.AbstractService
import br.com.edersystems.backend.core.services.product.ProdutoService
import br.com.edersystems.backend.infrastructure.repositories.IProdutoCosifRepository
import br.com.edersystems.backend.infrastructure.util.MessageCodeUtil
import java.util.Objects
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ProdutoCosifService(
	private val repository: IProdutoCosifRepository,
	private val produtoService: ProdutoService
) : AbstractService() {

	override val errors: Errors = createErrors()

	fun create(request: CreateProductCosifRequest) = repository.save(request.toProdutoCosif())

	fun validateCreateProductCosifRequest(request: CreateProductCosifRequest) {
		errors.clearErrors()

		validateCodProduct(request.codProduto)?.let { errors.exceptionErrors.add(it) }
		validateExistsProduct(request.codProduto)?.let { errors.exceptionErrors.add(it) }
		validateClassification(request.classificacao)?.let { errors.exceptionErrors.add(it) }

		throwErrors(UnProcessableEntityException(errors.exceptionErrors))
	}

	private fun validateCodProduct(codProduct: UUID): ExceptionError? {
		return if (Objects.isNull(codProduct)) {
			createError(MessageCodeUtil.PROD_0003)
		} else {
		    null
		}
	}

	private fun validateExistsProduct(codProduct: UUID): ExceptionError? {
		return if (produtoService.isExistsProduct(codProduct).not()) {
			createError(MessageCodeUtil.PROD_0002, codProduct)
		} else {
		    null
		}
	}

	private fun validateClassification(classification: String): ExceptionError? {
		return if (classification.isBlank()) {
			createError(MessageCodeUtil.PROD_0004)
		} else {
		    null
		}
	}
}
