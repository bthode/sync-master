val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

val postgresVersion: String by project
val h2Version: String by project
plugins {
    alias(libs.plugins.kotlinjvm)
    id("io.ktor.plugin") version "2.3.4" // TODO libs alias?
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotser) // org.jetbrains.kotlin.plugin.serialization
    application
    id("com.autonomousapps.dependency-analysis") version "1.25.0"
}

group = "com.tatq"
version = "0.0.1"




//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "16"
//}
//
//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
//    kotlinOptions.jvmTarget = "16"
//}
//
//tasks.withType(JavaCompile::class.java) {
//    targetCompatibility = "16"
//    sourceCompatibility = "16"
//}
//

application {
    mainClass.set("com.tatq.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}


//tasks.test {
//    useJUnitPlatform()
//    testLogging {
//        events("passed", "skipped", "failed")
//    }
//}

//testing.suites {
//    withType<JvmTestSuite>().configureEach {
//        useJUnitJupiter()
//    }
//}


// Only useJUnitPlatform working at time of change
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.9.0")
        }
    }
}

kotlin {
    jvmToolchain(19)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

dependencies {
    runtimeOnly(libs.h2)
//    implementation(libs.kotlin.test.junit)
    implementation(libs.ktor.serialization.gson.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
//    implementation(libs.ktor.server.auth.jvm)
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
//    implementation(libs.ktor.server.tests.jvm)
    runtimeOnly(libs.logback.classic)
    runtimeOnly(libs.postgresql)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests.jvm)

//    testRuntimeOnly("org.junit.jupiter:junit-jupiter:5.9.2")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.2")

//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")


    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.ktor:ktor-http:2.3.4")
    implementation("io.ktor:ktor-resources:2.3.4")
    implementation("io.ktor:ktor-serialization:2.3.4")
    implementation("io.ktor:ktor-utils:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.slf4j:slf4j-api:2.0.5")
    testImplementation("io.ktor:ktor-client-core:2.3.4")
    testImplementation("io.ktor:ktor-server-test-host:2.3.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
}


