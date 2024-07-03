import java.net.URI
import org.gradle.kotlin.dsl.support.unzipTo

plugins {
    `java-library`
    id("com.google.protobuf") version("0.9.4")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:4.27.2")
}

// Directories
val buildDir = layout.buildDirectory.asFile.get()
val downloadDir = (buildDir / "download")
val unzipDir = (buildDir / "unzip")

sourceSets.main {
    proto {
        srcDir(unzipDir / "proto")
    }
}

tasks["generateProto"].dependsOn("unzip")

val version = "v1.19.0-rc2"
val file = "protos-$version.zip"
val location = "https://github.com/provenance-io/provenance/releases/download/$version/$file"

tasks.getByName("clean").doFirst {
    println("deleting downloads")
    downloadDir.deleteRecursively()
    unzipDir.deleteRecursively()
}

tasks.register("unzip") {
    downloadDir.mkdirs()
    unzipDir.mkdirs()
    unzipTo(unzipDir, download(uri(location), downloadDir / file))
}

fun download(uri: URI, dest: File): File {
    ant.invokeMethod("get", mapOf("src" to uri.toString(), "dest" to dest))
    return dest.absoluteFile
}

operator fun File.div(more: String): File = File(this, more)
