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

sourceSets.main {
    proto {
        srcDir("$buildDir/extract-included-protos/**.*.proto")
    }
}

tasks["generateProto"].dependsOn("unzip")

val file = "protos-v1.19.0-rc2.zip"
val location = "https://github.com/provenance-io/provenance/releases/download/v1.19.0-rc2/$file"

val buildDir = layout.buildDirectory.asFile.get()
val downloadDir = (buildDir / "download")
val unzipDir = (buildDir / "extracted-include-protos" / "main")

tasks.getByName("clean").doFirst {
    println("deleting downloads")
    downloadDir.deleteRecursively()
    unzipDir.deleteRecursively()
}

tasks.register("unzip") {
    // Download the zip.
    downloadDir.mkdirs()
    val zip = download(uri(location), downloadDir / file)
    // Unzip the zip.
    unzipDir.mkdirs()

    unzipTo(unzipDir, zip)
}

fun download(uri: URI, dest: File): File {
    ant.invokeMethod("get", mapOf("src" to uri.toString(), "dest" to dest))
    return dest.absoluteFile
}

operator fun File.div(more: String): File = File(this, more)