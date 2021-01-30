plugins {
    kotlin("jvm") version "1.4.21"
    id("org.gradle.maven-publish")
}

group = "nl.jvhaastert"
version = "1.1.0"

kotlin {
    explicitApi()
}

java {
    withSourcesJar()
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/jaspervhaastert/kotlin-dependency-injection")
            credentials {
                username = project.findProperty("gpr.user") as? String ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as? String ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("java") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
