/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:23:37
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.jackson.configuration.localdatetime

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer @JvmOverloads constructor(localDateTimeClass: Class<LocalDateTime?>? = null) :
	StdSerializer<LocalDateTime?>(localDateTimeClass) {

	@Throws(IOException::class, JsonProcessingException::class)
	override fun serialize(value: LocalDateTime?, gen: JsonGenerator, arg2: SerializerProvider?) {
		gen.writeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value))
	}

	companion object {
		private const val serialVersionUID = -25L
	}
}
