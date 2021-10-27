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
    testImplementation("org.junit.platform:junit-platform-commons:1.8.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}