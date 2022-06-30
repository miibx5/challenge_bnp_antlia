/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 18:34:41
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.controllers.movement

import br.com.edersystems.backend.application.controllers.movement.resources.CreateMovementRequest
import br.com.edersystems.backend.application.controllers.movement.resources.MovementManualResponse
import br.com.edersystems.backend.application.controllers.movement.resources.PatchMovementRequest
import br.com.edersystems.backend.application.extensions.APPLICATION_JSON_UTF8_VALUE
import br.com.edersystems.backend.application.resources.ResponseTO
import br.com.edersystems.backend.application.validation.annotation.RequestValidator
import br.com.edersystems.backend.core.factory.ResponseTOFactory
import br.com.edersystems.backend.core.services.movement.MovementManualService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movimento", produces = [APPLICATION_JSON_UTF8_VALUE])
class MovimentoManualController(private val service: MovementManualService) {

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	@RequestValidator
	fun create(@RequestBody request: CreateMovementRequest): ResponseEntity<ResponseTO> {
		val createdMovement = service.create(request)
		val response = MovementManualResponse.toResponse(createdMovement)
		return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.CREATED.name), HttpStatus.CREATED)
	}

	@PatchMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	@RequestValidator
	fun patch(@RequestBody request: PatchMovementRequest): ResponseEntity<ResponseTO> {
		val updatedMovement = service.patchMovement(request)
		val response = MovementManualResponse.toResponse(updatedMovement)
		return ResponseEntity(ResponseTOFactory.success(response, HttpStatus.ACCEPTED.name), HttpStatus.ACCEPTED)
	}
}
