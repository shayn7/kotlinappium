package utils

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.*

class PropertiesReader {
    private var properties: Properties = Properties()
    val deviceName: String? by lazy { properties.getProperty("deviceName") }
    val platformVersion: String? by lazy { properties.getProperty("platformVersion") }
    val appPackage: String? by lazy { properties.getProperty("appPackage") }
    val appActivity: String? by lazy { properties.getProperty("appActivity") }
    val url: String? by lazy { properties.getProperty("url") }
    val automationName: String? by lazy { properties.getProperty("automationName") }
    val udid: String? by lazy {  properties.getProperty("udid") }
    val noReset: String? by lazy {  properties.getProperty("noReset") }

    init {
        val workingDirectory = File(System.getProperty("user.dir"))
        val path = "$workingDirectory/src/main/resources"
        try {
            properties.load(FileInputStream("$path/env-configuration.properties"))
        } catch (e: FileNotFoundException) {
            println("Device Configuration properties file cannot be found: $e")
        }
    }

}