package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import mobile_platforms.AbstractPlatform


class GmailHomePage(abstractPlatform: AbstractPlatform) : AbstractPage(abstractPlatform) {

    override fun verifyPage() = composeMessageButton?.let { platform.isElementDisplayed(it) }

    fun clickOnComposeMessageButton() {
        composeMessageButton?.let { platform.clickOnElement(it) }
    }

    fun goToSentPage() {
        navigationDrawerButton?.let { platform.clickOnElement(it) }
        sentTab?.let { platform.clickOnElement(it) }
    }

    @AndroidFindBy(id = "com.google.android.gm:id/compose_button")
    private val composeMessageButton: MobileElement? = null
    @AndroidFindBy(accessibility = "Open navigation drawer")
    private val navigationDrawerButton: MobileElement? = null
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    private val sentTab: MobileElement? = null
}