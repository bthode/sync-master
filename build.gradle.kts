val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

val postgresVersion: String by project
val h2Version: String by project
plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("org.jmailen.kotlinter") version "3.16.0"
    application
}

group = "com.tatq"
version = "0.0.1"

application {
    mainClass.set("com.tatq.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.9.0")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

dependencies {
    implementation(libs.h2)
    implementation(libs.kotlin.test.junit)
    implementation(libs.ktor.serialization.gson.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.auth.jvm)
    implementation(libs.ktor.server.call.id.jvm)
    implementation(libs.ktor.server.call.logging.jvm)
    implementation(libs.ktor.server.compression.jvm)
    implementation(libs.ktor.server.conditional.headers.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.cors.jvm)
    implementation(libs.ktor.server.default.headers.jvm)
    implementation(libs.ktor.server.host.common.jvm)
    implementation(libs.ktor.server.jetty.jvm)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.partial.content.jvm)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.sessions.jvm)
    implementation(libs.ktor.server.swagger.jvm)
    implementation(libs.ktor.server.tests.jvm)
    implementation(libs.logback.classic)
    implementation(libs.postgresql)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests.jvm)
}

kotlin {
    jvmToolchain(19)
}
