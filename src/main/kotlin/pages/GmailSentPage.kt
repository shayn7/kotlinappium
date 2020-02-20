package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import mobile_platforms.AbstractPlatform
import org.openqa.selenium.By

class GmailSentPage(platform: AbstractPlatform) : AbstractPage(platform) {

    override fun verifyPage() = title?.let { platform.isElementDisplayed(it) }

    fun verifySentMail(predicate: String) {
        var locator = "//android.view.View[contains(@content-desc,'X')]".replace("X",predicate)
        val element = platform.getDriver()?.findElement(By.xpath(locator))
        val isDisplayed = element?.let { platform.isElementDisplayed(it as MobileElement)}
        platform.assertCondition(isDisplayed?:false,"Mail was not displayed")
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    private val title: MobileElement? = null
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'X')]")
    private val mail: MobileElement? = null

}