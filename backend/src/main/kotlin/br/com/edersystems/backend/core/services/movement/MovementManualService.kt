/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 19:49:14
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
import br.com.edersystems.backend.core.commons.exceptions.error.Errors
import br.com.edersystems.backend.core.commons.exceptions.error.ExceptionError
import br.com.edersystems.backend.core.exceptions.UnProcessableEntityException
import br.com.edersystems.backend.core.extensions.isInvalid
import br.com.edersystems.backend.core.services.AbstractService
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManual
import br.com.edersystems.backend.infrastructure.entities.movement.MovimentoManualPK
import br.com.edersystems.backend.infrastructure.exceptions.movement.MovementManualNotFoundException
import br.com.edersystems.backend.infrastructure.repositories.IMovimentoManualRepository
import br.com.edersystems.backend.infrastructure.util.MessageCodeUtil
import java.math.BigDecimal
import java.util.Objects
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class MovementManualService(private val repository: IMovimentoManualRepository) : AbstractService() {
    override val errors: Errors = createErrors()

    fun create(request: CreateMovementRequest) = saveMovement(request.toMovimento())

    fun patchMovement(request: PatchMovementRequest): MovimentoManual {
        val (_, _, _, _, _, description, userCode, amount) = request
        val retrievedMovement = getById(request.toMovimentoPk())
        val movementToUpdate = retrievedMovement.update(description, userCode, amount)
        return saveMovement(movementToUpdate)
    }

    fun getById(movementId: MovimentoManualPK): MovimentoManual = repository.findById(movementId).orElseThrow {
        throw MovementManualNotFoundException(createErrors(MessageCodeUtil.PROD_0011, movementId))
    }

    fun validateCreateMovementRequest(request: CreateMovementRequest) {
        errors.clearErrors()

        validateMonth(request.month)?.let { errors.exceptionErrors.add(it) }
        validateYear(request.year)?.let { errors.exceptionErrors.add(it) }
        validateNumberLaunch(request.numberLaunch)?.let { errors.exceptionErrors.add(it) }
        validateCodProduto(request.codProduto)?.let { errors.exceptionErrors.add(it) }
        validateCodCosif(request.codCosif)?.let { errors.exceptionErrors.add(it) }
        validateDescription(request.description)?.let { errors.exceptionErrors.add(it) }
        validateUserCode(request.userCode)?.let { errors.exceptionErrors.add(it) }
        validateAmount(request.amount)?.let { errors.exceptionErrors.add(it) }

        throwErrors(UnProcessableEntityException(errors.exceptionErrors))
    }

    fun validatePatchMovementRequest(request: PatchMovementRequest) {
        errors.clearErrors()

        if ((request.description?.isBlank() == true) &&
            (request.userCode?.isBlank() == true) &&
            (request.amount?.isInvalid() == true)
        ) {
            errors.addErrors(createError(MessageCodeUtil.DEFAULT))
        }

        throwErrors(UnProcessableEntityException(errors.exceptionErrors))
    }

    fun validateMonth(month: BigDecimal): ExceptionError? {
        return if (month.isInvalid()) createError(MessageCodeUtil.PROD_0005) else null
    }

    fun validateYear(year: BigDecimal): ExceptionError? {
        return if (year.isInvalid()) createError(MessageCodeUtil.PROD_0006) else null
    }

    fun validateNumberLaunch(numberLaunch: BigDecimal): ExceptionError? {
        return if (numberLaunch.isInvalid()) createError(MessageCodeUtil.PROD_0008) else null
    }

    fun validateCodProduto(codProduto: UUID): ExceptionError? {
        return if (Objects.isNull(codProduto)) createError(MessageCodeUtil.PROD_0003) else null
    }

    fun validateCodCosif(codCosif: UUID): ExceptionError? {
        return if (Objects.isNull(codCosif)) createError(MessageCodeUtil.PROD_0007) else null
    }

    fun validateDescription(description: String): ExceptionError? {
        return if (description.isBlank()) createError(MessageCodeUtil.PROD_0001) else null
    }

    fun validateUserCode(userCode: String): ExceptionError? {
        return if (userCode.isBlank()) createError(MessageCodeUtil.PROD_0009) else null
    }

    fun validateAmount(amount: BigDecimal): ExceptionError? {
        return if (amount.isInvalid()) createError(MessageCodeUtil.PROD_0010) else null
    }

    private fun saveMovement(movimentoManual: MovimentoManual) = repository.save(movimentoManual)
}
