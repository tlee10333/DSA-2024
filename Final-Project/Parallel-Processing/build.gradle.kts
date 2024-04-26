plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.8.0")
    implementation( "org.jetbrains.kotlinx:multik-core:0.2.3")
    implementation( "org.jetbrains.kotlinx:multik-default:0.2.3")
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}