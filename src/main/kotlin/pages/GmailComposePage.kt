package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import mobile_platforms.AbstractPlatform

class GmailComposePage(platform: AbstractPlatform) : AbstractPage(platform) {
    override fun verifyPage() = sendButton?.let { platform.isElementDisplayed(it) }

    fun sendMail(to: String, subject: String = "", body: String = "") {
        bodyField?.let {
            platform.clickOnElement(it)
            platform.getDriver()?.keyboard?.sendKeys(body)
        }
        subjectField?.let { platform.setText(it,subject) }
        toField?.let { platform.setText(it,to) }
        platform.clickOnElement(sendButton!!)
    }


    @AndroidFindBy(id = "com.google.android.gm:id/send")
    private val sendButton: MobileElement? = null
    @AndroidFindBy(id = "com.google.android.gm:id/to")
    private val toField: MobileElement? = null
    @AndroidFindBy(id = "com.google.android.gm:id/subject")
    private val subjectField: MobileElement? = null
    @AndroidFindBy(xpath = "//android.view.View[@text='Compose email']")
    private val bodyField: MobileElement? = null
}