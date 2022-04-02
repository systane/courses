import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.strider"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
	kotlin("jvm") version "1.6.10"
	id("org.springframework.boot") version "2.6.5" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
	kotlin("plugin.spring") version "1.6.10" apply false
}

allprojects {
	apply {
		plugin("java")
	}

	repositories {
		mavenCentral()
	}

	configurations {
		all {
			exclude("org.junit.vintage", module = "junit-vintage-engine")
			exclude("junit", module = "junit")
		}
	}

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	}
}

subprojects {
	apply {
		plugin("io.spring.dependency-management")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

}