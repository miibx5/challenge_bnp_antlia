/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:08:00
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.commons.jackson

import br.com.edersystems.backend.core.commons.jackson.configuration.localdate.LocalDateDeserializer
import br.com.edersystems.backend.core.commons.jackson.configuration.localdate.LocalDateSerializer
import br.com.edersystems.backend.core.commons.jackson.configuration.localdatetime.LocalDateTimeDeserializer
import br.com.edersystems.backend.core.commons.jackson.configuration.localdatetime.LocalDateTimeSerializer
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

object ObjectMapperProvider {

	fun provider(): ObjectMapper {
		val objectMapper = jacksonObjectMapper().apply {
			propertyNamingStrategy = LOWER_CAMEL_CASE
			setDefaultPrettyPrinter(
				DefaultPrettyPrinter().apply {
					indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
					indentObjectsWith(DefaultIndenter("  ", "\n"))
				}
			)

			setSerializationInclusion(JsonInclude.Include.NON_NULL)

			registerModule(JavaTimeModule())

			val module = SimpleModule()
			module.addSerializer(java.sql.Date::class.java, DateSerializer())
			module.addSerializer(Date::class.java, DateSerializer())
			module.addSerializer(BigInteger::class.java, ToStringSerializer())
			module.addSerializer(LocalDate::class.java, LocalDateSerializer())
			module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
			module.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
			module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
			module.addSerializer(BigInteger::class.java, ToStringSerializer())

			registerModule(module)
			configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
			enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
			enable(SerializationFeature.INDENT_OUTPUT)
			enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
			enable(SerializationFeature.INDENT_OUTPUT)
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			canSerialize(Boolean::class.java)

			deserializationConfig.withAppendedAnnotationIntrospector(JacksonAnnotationIntrospector())

			serializationConfig.withAppendedAnnotationIntrospector(JacksonAnnotationIntrospector())
		}
		return objectMapper
	}
}
