/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:20:15
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.jackson.configuration.localdatetime

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime?>() {

	@Throws(IOException::class, JsonProcessingException::class)
	override fun deserialize(jsonParser: JsonParser, context: DeserializationContext?): LocalDateTime {
		val oc: ObjectCodec = jsonParser.codec
		val node: TextNode = oc.readTree(jsonParser) as TextNode
		val dateString: String = node.textValue()
		return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
	}
}
