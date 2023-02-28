val ktor_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version Versions.kotlin_version
    `maven-publish`
    id("org.jetbrains.dokka") version "1.7.20"
}

group = "com.njf2016"
version = "1.0.0-SNAPSHOT"

kotlin {
    jvmToolchain(8)
}

publishing {
    repositories {
        maven {
            name = "Local"
            url = uri(layout.buildDirectory.dir("repo/"))
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])

            groupId = "com.njf2016.open"
            artifactId = "openapi-sdk"
        }
    }
}

tasks.dokkaGfm {
    outputDirectory.set(rootDir.resolve("docs/api"))
}

dependencies {
    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-okhttp:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation:$ktor_version")
    api("io.ktor:ktor-client-auth:$ktor_version")
    api("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
}