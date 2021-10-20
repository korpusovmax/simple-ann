plugins {
    `kotlin-dsl`
    id("dependencies")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.Plugins.Dependencies.Classpath)
}