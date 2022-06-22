package br.com.edersystems.backend

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendApplicationTests {

    @Test
    fun contextLoads() {
        assertTrue { 1 == 1 }
    }
}
