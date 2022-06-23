/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 19:30:33
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.configuration

import br.com.edersystems.backend.core.commons.jackson.ObjectMapperProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BackendApplicationConfiguration {
	
	@Bean
	fun getObjectMapper(): ObjectMapper {
		return ObjectMapperProvider.provider()
	}
}
