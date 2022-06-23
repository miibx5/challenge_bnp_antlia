/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:18:51
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.jackson.configuration.localdate

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {

	@Throws(IOException::class)
	override fun serialize(value: LocalDate, generator: JsonGenerator, provider: SerializerProvider) {
		generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE))
	}

	companion object {
		private const val serialVersionUID = -2856410L
	}
}
