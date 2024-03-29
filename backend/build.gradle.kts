import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val failed: String by project
val passed: String by project
val skipped: String by project
val detektVersion: String by project
val jacksonFasterXmlVersion: String by project
val javassistVersion: String by project
val klaxonVersion: String by project
val kluentVersion: String by project
val mockkVersion: String by project
val snippetsDir = file("build/generated-snippets")
val springmockkVersion: String by project

plugins {
	val kotlinVersion = "1.7.10"
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("org.asciidoctor.convert") version "1.5.8"
	id("io.gitlab.arturbosch.detekt").version("1.21.0")
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
}

group = "br.com.edersystems"
java.sourceCompatibility = JavaVersion.VERSION_18

repositories {
	mavenCentral()
}

dependencies {
	// DATABASE DEPENDENCIES
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

	// DETEKT DEPENDENCIES
	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")

	// JACKSON DEPENDENCIES
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// KLAXON DEPENDENCIES
	implementation("com.beust:klaxon:$klaxonVersion")

	// KOTLIN DEPENDENCIES
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// WEB DEPENDENCIES
	implementation("org.springframework.boot:spring-boot-starter-web")

	// BYTE CODE MANIPULATION
	implementation("org.javassist:javassist:$javassistVersion")

	//TEST IMPLEMENTATION DEPENDENCIES
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("org.amshove.kluent:kluent:$kluentVersion")
	testImplementation("io.mockk:mockk:$mockkVersion")
	implementation("com.ninja-squad:springmockk:$springmockkVersion")
}

tasks {

	asciidoctor {
		inputs.dir(snippetsDir)
		dependsOn(test)
	}

	getByName<Jar>("jar") {
		enabled = false
	}

	detekt {
		autoCorrect = true
		config = files("./detekt-config.yml")
		source = files("./src")
		toolVersion = detektVersion
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "18"
		}
	}

	withType<Test> {
		useJUnitPlatform()

		testLogging {
			events(failed, passed, skipped)
		}
	}

	test {
		outputs.dir(snippetsDir)
	}
}