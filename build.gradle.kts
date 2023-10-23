val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

val postgresVersion: String by project
val h2Version: String by project
plugins {
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.kotlinjvm)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ktorPlugin)
    id("application")
}

group = "com.tatq"
version = "0.0.1"

kotlin {
    jvmToolchain(19)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

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

dependencies {
    implementation(libs.gson)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.seraliazation.core)
    implementation(libs.kotlinx.seraliazation.json)
    implementation(libs.ktor.http)
    implementation(libs.ktor.resources)
    implementation(libs.ktor.seraliazation)
    implementation(libs.ktor.serialization.gson.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
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
    implementation(libs.ktor.utils)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.h2)
//    runtimeOnly(libs.kotlin.test.junit)
    runtimeOnly(libs.logback.classic)
    runtimeOnly(libs.postgresql)
    testImplementation(libs.junit.jupiter.api)
//    testImplementation(libs.kotlin.test)
    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.server.test.host)
//    testImplementation(libs.ktor.server.tests.jvm)
}
