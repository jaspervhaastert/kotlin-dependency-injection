plugins {
    kotlin("jvm") version "1.4.21"
}

group = "nl.jvhaastert"
version = "0.0.1"

kotlin {
    explicitApi()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
