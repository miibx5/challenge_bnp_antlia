/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 24/06/2022 15:58:08
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.configuration

import br.com.edersystems.backend.core.commons.jackson.ObjectMapperProvider
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
internal open class UnitTestsConfiguration {

	protected val mapper = ObjectMapperProvider.provider()
}
