/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:52:27
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.configuration

import java.nio.charset.StandardCharsets
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
internal class ControllerConfigurationTests : IntegrationConfigurationTests() {

	val configuration: RecursiveComparisonConfiguration = RecursiveComparisonConfiguration
		.builder()
		.withIgnoreAllExpectedNullFields(true)
		.build()

	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var webApplicationContext: WebApplicationContext

	@BeforeEach
	fun init() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
	}

	fun delete(path: String): Pair<HttpStatus, String> {

		val response =
			mockMvc.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()

		return getResponse(response)
	}

	fun get(path: String, queryString: String? = ""): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			MockMvcRequestBuilders.get("$path?$queryString")
				.characterEncoding(StandardCharsets.UTF_8)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andReturn()

		return getResponse(response)
	}

	fun patch(path: String, requestBody: String): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			MockMvcRequestBuilders.patch(path)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.UTF_8)
				.content(requestBody)
		).andReturn()

		return getResponse(response)
	}

	fun post(path: String, requestBody: String): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			MockMvcRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.UTF_8)
				.content(requestBody)
		).andReturn()

		return getResponse(response)
	}

	fun postWithMultipartFile(
		path: String,
		params: Map<String, String>,
		file: MockMultipartFile
	): Pair<HttpStatus, String> {
		val multiPartRequestBuilder = RestDocumentationRequestBuilders.multipart(path).apply {
			file(file)
			params.forEach { (paramName, value) ->
				param(paramName, value)
			}
		}
		val response = mockMvc.perform(
			multiPartRequestBuilder.accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
		).andReturn()

		return getResponse(response)
	}

	private fun getResponse(response: MvcResult): Pair<HttpStatus, String> {
		val contentToReturn = response.response.getContentAsString(StandardCharsets.UTF_8)
		val statusToReturn = HttpStatus.valueOf(response.response.status)
		return Pair(statusToReturn, contentToReturn)
	}
}
