/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 12:45:15
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.configuration

import br.com.edersystems.backend.configuration.sql.Commands
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import java.sql.Connection
import java.sql.DriverManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class IntegrationConfigurationTests {

	lateinit var connection: Connection

	@Value("\${spring.datasource.url}")
	lateinit var databaseUrl: String

	@Autowired
	lateinit var mapper: ObjectMapper

	@BeforeEach
	fun setup() {
		connection = DriverManager.getConnection(databaseUrl)
		clearAllTables(connection)
	}

	@AfterEach
	fun tearDown() {
		clearAllMocks()
		unmockkAll()
	}

	@AfterEach
	fun shutDown() {
		clearAllTables(connection)
	}

	fun clearAllTables(connection: Connection) {
		val query = connection.createStatement()
		Commands.values().forEach { query.addBatch(it.commandSql) }
		query.executeBatch()
	}
}
