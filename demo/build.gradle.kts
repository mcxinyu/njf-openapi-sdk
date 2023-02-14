val ktor_version: String by project

val appId: String? by project
val secret: String? by project

plugins {
    id("java")
    id("com.github.gmazzo.buildconfig") version "3.1.0"
}

group = "org.example"
version = "unspecified"

dependencies {
    implementation(project(":openapi-sdk"))
//    implementation("com.njf2016:njf-openapi-sdk:main-SNAPSHOT")
//    implementation("com.njf2016:njf-openapi-sdk:1.0.0-2")
}

buildConfig {
    buildConfigField("String", "appId", appId.isNullOr { "${localProperties["appId"]}" })
    buildConfigField("String", "secret", secret.isNullOr { "${localProperties["secret"]}" })
}