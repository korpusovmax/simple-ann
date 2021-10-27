plugins {
    `java-library`
    id("maven")
}

group = AppInfo.PACKAGE
version = AppInfo.VERSION

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(Deps.Libs.JUnit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}