plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("io.qameta.allure") version "2.8.1"
}

allure {
    version = "2.13.3"
    autoconfigure = true
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.codeborne", "selenide", "5.12.2")
    implementation("io.qameta.allure", "allure-testng", "2.13.3")
    testImplementation("org.testng", "testng", "7.0.0")
    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("io.qameta.allure", "allure-selenide", "2.13.3")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

}

tasks.test {
    useTestNG()
}
