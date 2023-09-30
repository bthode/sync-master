package com.tatq

import com.tatq.plugins.configureDatabases
import com.tatq.plugins.configureHTTP
import com.tatq.plugins.configureMonitoring
import com.tatq.plugins.configureRouting
import com.tatq.plugins.configureSecurity
import com.tatq.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main() {
    embeddedServer(Jetty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
