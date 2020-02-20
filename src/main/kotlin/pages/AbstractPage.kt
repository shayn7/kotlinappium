package pages

import io.appium.java_client.pagefactory.AppiumFieldDecorator
import mobile_platforms.AbstractPlatform
import org.openqa.selenium.support.PageFactory

@Suppress("LeakingThis")
abstract class AbstractPage(protected val platform: AbstractPlatform){

    init {
        PageFactory.initElements(AppiumFieldDecorator(platform.getDriver()),this)
    }

    abstract fun verifyPage(): Boolean?
}