import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

const val FN_LOCAL_PROPERTIES = "local.properties"

fun gradleLocalProperties(projectRootDir: File): Properties {
    val properties = Properties()
    val localProperties = File(projectRootDir, FN_LOCAL_PROPERTIES)

    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else {
        println("Gradle local properties file not found at $localProperties")
    }
    return properties
}

val Project.localProperties: Properties
    get() = gradleLocalProperties(rootDir)

fun String?.isNullOr(action: () -> String): String = if (isNullOrEmpty()) action() else this