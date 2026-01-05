plugins {
	kotlin("jvm") version "2.2.20"
	kotlin("plugin.spring") version "2.2.20"
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.flywaydb.flyway") version "11.20.0"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"
description = "Back-end da barbearia"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
	gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    val dotenvVersion: String = "0.0.2"
	val dateTimeVersion: String = "0.4.0"
	val h2Version: String = "2.2.220"

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.flywaydb.flyway:org.flywaydb.flyway.gradle.plugin:11.20.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.github.dotenv-org:dotenv-vault-kotlin:${dotenvVersion}")
    implementation("org.postgresql:postgresql")
	implementation("org.jetbrains.kotlinx:kotlinx-datetime:${dateTimeVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("com.h2database:h2:${h2Version}")
	runtimeOnly("com.h2database:h2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

apply(plugin = "org.flywaydb.flyway")

flyway {
	val address: String? = System.getenv("PSQL_URL")
	val usr: String? = System.getenv("PSQL_USER")
	val pswd: String? = System.getenv("PSQL_PASS")
	url = address ?: "jdbc:postgresql://localhost:5432/projeto_barbearia"
	user = usr ?: "alexssander"
	password = pswd ?: "@Boomer7296"
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
