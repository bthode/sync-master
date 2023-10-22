package com.tatq

import com.tatq.plugins.configureRouting
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

// INFO: Gradle does very aggressive caching of test results, so if you change the test and it doesn't work, try to run it with --rerun-tasks option.
class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
            application {
                configureRouting()
            }
            client.get("/").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("Hello World!", bodyAsText())
            }
        }
}
