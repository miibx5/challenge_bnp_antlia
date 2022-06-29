/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 28/06/2022 18:45:39
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.application.exception

class RequestUnknownException(override val message: String) : UnsupportedOperationException(message)
