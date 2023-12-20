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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.tatq.plugins.configureIndex

fun main() {
    embeddedServer(Jetty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    configureSecurity()
    configureHTTP() // Need to understand what of this to keep
    configureMonitoring()
    configureSerialization()
    configureDatabases() // Do db migration here if it isn't in the state we need.

    // TODO configOurDB()
    // TODO configOurRestEndpoints()

    configureRouting()
    configureIndex()
}
