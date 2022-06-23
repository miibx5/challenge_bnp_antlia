/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:04:16
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.extensions

import br.com.edersystems.backend.core.commons.jackson.ObjectMapperProvider
import com.fasterxml.jackson.databind.JsonNode

private const val DATA = "data"

fun String.getDataFromJson(field: String): JsonNode {
	val json = ObjectMapperProvider.provider().readTree(this)
	return json.get(DATA)[field]
}
