import org.testng.annotations.Test
import pages.GmailComposePage
import pages.GmailHomePage
import pages.GmailSentPage

class TestRunner : Hooks(){

    @Test
    fun sendMailTest(){
        platform.openApp()
        platform.iShouldBeOnPage("GmailHomePage")
        platform.getPageAs(GmailHomePage::class.java).clickOnComposeMessageButton()
        platform.iShouldBeOnPage("GmailComposePage")
        platform.getPageAs(GmailComposePage::class.java).sendMail("amir.ho@moonactive.com","Automation test","This is test1 and it should pass")
        platform.iShouldBeOnPage("GmailHomePage")
        platform.getPageAs(GmailHomePage::class.java).goToSentPage()
        platform.iShouldBeOnPage("GmailSentPage")
        platform.getPageAs(GmailSentPage::class.java).verifySentMail("Automation test")
    }

    @Test
    fun sendMailFailure(){
        platform.openApp()
        platform.iShouldBeOnPage("GmailHomePage")
        platform.getPageAs(GmailHomePage::class.java).clickOnComposeMessageButton()
        platform.iShouldBeOnPage("GmailComposePage")
        platform.getPageAs(GmailComposePage::class.java).sendMail("not_an_email_address")
        platform.iShouldBeOnPage("GmailHomePage")
    }
}