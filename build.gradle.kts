import org.gradle.util.GUtil.loadProperties

plugins {
    `java-library`
    id("deploy")
}

repositories {
    mavenCentral()
}

val deployFile: File = rootProject.file("deploy.properties")

/**
 * Deploy applies only if you have `deploy.properties` file in root with properties that used under the comment.
 */
deploy {
    if (deployFile.exists()) {
        ignore = false
        val properties = loadProperties(deployFile)

        componentName = "java"
        name = "Simple Artificial Neural Network"
        artifactId = "saan"
        group = AppInfo.PACKAGE
        description = "Simple Artificial Neural Network java library"
        version = AppInfo.VERSION

        host = properties.getProperty("host")
        user = properties.getProperty("user")
        password = properties.getProperty("password")
        deployPath = properties.getProperty("deployPath")
    }
}