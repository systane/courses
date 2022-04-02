plugins {
    kotlin("jvm")
}

dependencies {
    implementation("javax.inject:javax.inject:1")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.12.3")
}