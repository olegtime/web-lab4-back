plugins {
    java
    war
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.4")

    compileOnly("org.springframework.boot:spring-boot-starter-tomcat:3.1.0")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    implementation("org.slf4j:slf4j-api:2.0.5")

    implementation("org.mindrot:jbcrypt:0.4")
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")

    runtimeOnly("org.postgresql:postgresql:42.5.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
