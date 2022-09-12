import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.7.20-Beta"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20-Beta"
    id("com.google.devtools.ksp") version "1.7.20-Beta-1.0.6"
}

group = "ink.bluecloud"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven ("https://repo.huaweicloud.com/repository/maven/")
    maven ("https://maven.aliyun.com/repository/central/")
    maven ("https://maven.aliyun.com/repository/public/")
    maven ("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
}

javafx {
    version = "18.0.2"
    modules = listOf("javafx.controls")
}

dependencies {
    //GUI
    implementation(group = "no.tornado", name = "tornadofx", version = "2.0.0-SNAPSHOT")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-javafx", version = "1.6.4")
    implementation(group = "eu.iamgio", name = "animated", version = "0.6.0")
    implementation(files("libs\\CloudTools-1.0-SNAPSHOT.jar"))
    implementation(files("libs\\ElementFX-1.0-SNAPSHOT.jar"))

    //Utils
    implementation(group = "org.zeroturnaround", name = "zt-exec", version = "1.12")
    implementation(group = "eu.iamgio", name = "animated", version = "0.6.0")

    //DataBase
    implementation(group = "org.ktorm", name = "ktorm-core", version = "3.5.0")
    implementation(group = "org.ktorm", name = "ktorm-ksp-api", version = "1.0.0-RC3")
    implementation(group = "com.h2database", name = "h2", version = "2.1.214")
    ksp(group = "org.ktorm", name = "ktorm-ksp-compiler", version = "1.0.0-RC3")

    //OKHttp
    implementation(group = "com.squareup.okhttp3", name = "okhttp", version = "5.0.0-alpha.10")

    //Serialization
    implementation(group = "com.alibaba.fastjson2", name = "fastjson2-kotlin", version = "2.0.13")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-protobuf-jvm", version = "1.4.0")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json-jvm", version = "1.4.0")

    //QRCode
    implementation(group = "com.google.zxing", name = "core", version = "3.5.0")
    implementation(group = "com.google.zxing", name = "javase", version = "3.5.0")

    //Logger
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.36")
    implementation(group = "org.slf4j", name = "slf4j-simple", version = "1.7.36")
    implementation(group = "io.github.microutils", name = "kotlin-logging", version = "2.1.23")

    //Video
    implementation(group = "uk.co.caprica", name = "vlcj-javafx", version = "1.1.0")
    implementation(group = "uk.co.caprica", name = "vlcj", version = "4.7.3")

    testImplementation(group = "junit", name = "junit", version = "4.13.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("ink.bluecloud.MainKt")
}