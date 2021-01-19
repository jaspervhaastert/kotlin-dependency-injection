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

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.mockito:mockito-core:3.7.7")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

sourceSets {
    create("integrationTest") {
        kotlin {
            compileClasspath += main.get().output + configurations.testCompileClasspath
            runtimeClasspath += main.get().output + configurations.testRuntimeClasspath
        }
    }
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    shouldRunAfter("test")
}

tasks.check {
    dependsOn(integrationTest)
}
