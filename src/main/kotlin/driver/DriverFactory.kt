package driver

import enums.MobilePlatform
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class DriverFactory {

    companion object{
        @Synchronized fun initDriver(
            url: String?,
            desiredCapabilities: DesiredCapabilities,
            mobilePlatform: MobilePlatform)
            :AppiumDriver<WebElement> =
            when(mobilePlatform){
                MobilePlatform.ANDROID -> AndroidDriver<WebElement>(URL(url), desiredCapabilities)
                MobilePlatform.IOS -> IOSDriver<WebElement>(URL(url), desiredCapabilities)
            }
    }
}