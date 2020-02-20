import enums.MobilePlatform
import mobile_platforms.AbstractPlatform
import mobile_platforms.MobilePlatformFactory
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import java.io.File


open class Hooks {

    lateinit var platform: AbstractPlatform

    @BeforeMethod
    fun setup(){
        platform = MobilePlatformFactory.getMobilePlatform(MobilePlatform.ANDROID)
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown(testResult: ITestResult){
        if(testResult.status == ITestResult.FAILURE){
            val file = (platform.getDriver() as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            FileUtils.copyFile(file, File("Screenshot.jpg"))
        }
        platform.tearDown()
    }
}