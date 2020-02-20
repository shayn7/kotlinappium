package mobile_platforms

import enums.MobilePlatform

class AndroidPlatform(mobilePlatform: MobilePlatform) : AbstractPlatform(mobilePlatform) {


    override fun initCapabilities() {
        capabilities.setCapability("deviceName", propertiesReader.deviceName)
        capabilities.setCapability("platformName",mobilePlatform.toString())
        capabilities.setCapability("platformVersion",propertiesReader.platformVersion)
        capabilities.setCapability("automationName",propertiesReader.automationName)
        capabilities.setCapability("noReset",propertiesReader.noReset?.toBoolean())
        capabilities.setCapability("newCommandTimeout",60)
    }

    override fun installedAppCapabilities() {
        capabilities.setCapability("appPackage", propertiesReader.appPackage);
        capabilities.setCapability("appActivity", propertiesReader.appActivity);
    }
}