/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 17:46:23
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.validation.aspect

import br.com.edersystems.backend.application.exception.ProceedingJoinPointNotFoundException
import br.com.edersystems.backend.core.services.validation.RequestValidatorService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class RequestValidatorAspect(private val service: RequestValidatorService) {
	companion object {
		const val ANNOTATION_CLASS = "br.com.edersystems.backend.application.validation.annotation.RequestValidator"
	}

	@Around("@annotation($ANNOTATION_CLASS)")
	fun validateRequest(joinPoint: ProceedingJoinPoint): Any? {
		val request = extractObjectRequest(joinPoint)

		service.validateRequest(request)

		return joinPoint.proceed()
	}

	private fun extractObjectRequest(joinPoint: ProceedingJoinPoint): Any {
		joinPoint.args.ifEmpty { throw ProceedingJoinPointNotFoundException() }

		val possibleObject = joinPoint.args[0]
		if (possibleObject !is Any) {
			throw ProceedingJoinPointNotFoundException()
		}

		return possibleObject
	}
}
