plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.bizzaroerik"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Kafka and cloud event dependencies
    //implementation("org.springframework.kafka:spring-kafka:2.7.6")
    //implementation("io.cloudevents:cloudevents-kafka:2.5.0")
    //implementation("io.cloudevents:cloudevents-json-jackson:2.5.0")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("net.logstash.logback:logstash-logback-encoder:7.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-runner:1.7.0")
    testImplementation("org.assertj:assertj-core:3.21.0")
    //testImplementation("org.springframework.kafka:spring-kafka-test:2.7.6")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
