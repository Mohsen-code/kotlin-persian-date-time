plugins {
    kotlin("multiplatform") version "1.5.10"
    id("maven-publish")
}

group = "mohsen.coder"
version = "0.1"

repositories {
    mavenCentral()
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting
        val jvmTest by getting
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "mohsen.coder"
                artifactId = "PersianDateTimeLibrary"
                version = "0.1"

                from(components["java"])
            }
        }
    }
}


/*
publishing {
    publications {
    }
    repositories {
        maven {
            name = "kotlin-persian-date-time" //  optional target repository name
            url = uri("https://github.com/Mohsen-code/kotlin-persian-date-time.git")
            credentials {
                username = "mohsen-code"
                password = "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"
            }
        }
    }
}
*/
