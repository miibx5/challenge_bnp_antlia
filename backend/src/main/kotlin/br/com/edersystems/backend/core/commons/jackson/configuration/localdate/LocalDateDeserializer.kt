/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:13:23
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.jackson.configuration.localdate

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.time.LocalDate

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {

	@Throws(IOException::class)
	override fun deserialize(jsonParser: JsonParser, context: DeserializationContext): LocalDate {
		return LocalDate.parse(jsonParser.readValueAs(String::class.java))
	}

	companion object {
		private const val serialVersionUID = -3712447L
	}
}
