/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:46:35
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.configuration.sql

enum class Commands(val commandSql: String) {
	CLEAR_PRODUCT_TABLE("DELETE FROM PRODUTO")
}
