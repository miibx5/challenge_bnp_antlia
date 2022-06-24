/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 24/06/2022 11:47:15
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.extensions

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

fun JsonArray<JsonObject>.getField(field: String) = this.map { it.getValue(field) }.firstOrNull() as String
