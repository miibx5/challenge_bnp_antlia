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

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.fasterxml.jackson.databind.JsonNode
import java.nio.charset.StandardCharsets
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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
	val code = "code"
	val codCosif = "codCosif"
	val codProduto = "codProduto"
	val descricao = "descricao"
	val errors = "errors"
	val id = "id"
	val message = "message"
	private val data = "data"
	private val uuidRegexValidator = """
			^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}${'$'}
		""".trimIndent()

	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var webApplicationContext: WebApplicationContext

	@BeforeEach
	fun init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
	}

	fun delete(path: String): Pair<HttpStatus, String> {
		val response = mockMvc.perform(MockMvcRequestBuilders.delete(path).accept(APPLICATION_JSON_VALUE)).andReturn()

		return getResponse(response)
	}

	fun get(path: String, queryString: String? = ""): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			MockMvcRequestBuilders.get("$path?$queryString")
				.characterEncoding(StandardCharsets.UTF_8)
				.contentType(APPLICATION_JSON_VALUE)
		).andReturn()

		return getResponse(response)
	}

	fun patch(path: String, requestBody: String): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			patch(path)
				.contentType(APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.UTF_8)
				.content(requestBody)
		).andReturn()

		return getResponse(response)
	}

	fun post(path: String, requestBody: String): Pair<HttpStatus, String> {
		val response = mockMvc.perform(
			post(path)
				.contentType(APPLICATION_JSON_VALUE)
				.characterEncoding(StandardCharsets.UTF_8)
				.content(requestBody)
		).andReturn()

		return getResponse(response)
	}

	fun getJsonNode(stringJson: String): JsonNode = mapper.readTree(stringJson)
	fun getStringJsonFromJsonNode(stringJson: String): JsonNode = getJsonNode(stringJson).get(data)
	fun getFieldFromJsonNode(stringJson: String, field: String): String = getStringJsonFromJsonNode(stringJson)
		.get(field).textValue()

	fun isUUID(stringJson: String): Boolean = stringJson.matches(Regex(uuidRegexValidator))
	fun getJsonObject(stringJson: String) = Parser.default().parse(StringBuilder(stringJson)) as JsonObject
	fun getValueFromJsonObject(jsonObject: JsonObject) = jsonObject.getValue(errors) as JsonArray<JsonObject>
	private fun getResponse(response: MvcResult): Pair<HttpStatus, String> {
		val contentToReturn = response.response.getContentAsString(StandardCharsets.UTF_8)
		val statusToReturn = HttpStatus.valueOf(response.response.status)
		return Pair(statusToReturn, contentToReturn)
	}
}
