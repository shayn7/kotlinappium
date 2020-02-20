package mobile_platforms

import driver.DriverFactory
import enums.MobilePlatform
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import pages.AbstractPage
import pages.Pages
import utils.PropertiesReader
import java.util.concurrent.TimeUnit


abstract class AbstractPlatform(protected val mobilePlatform: MobilePlatform) {

    protected val propertiesReader = PropertiesReader()
    protected val capabilities = DesiredCapabilities()
    private var driver: AppiumDriver<WebElement>? = null
    private lateinit var actualPage: AbstractPage

    fun openApp() {
        initCapabilities()
        installedAppCapabilities()
        driver = DriverFactory.initDriver(propertiesReader.url, capabilities, mobilePlatform)
        driver?.manage()?.timeouts()?.implicitlyWait(5, TimeUnit.SECONDS);
    }

    fun tearDown() {
        driver?.quit()
    }

    fun iShouldBeOnPage(pageName: String) {
        actualPage = getCurrentPageAsObject(pageName)
        val isExpectedPage = actualPage.verifyPage()
        isExpectedPage?.let { printMessageBasedOnCondition(it, pageName) }
        Assert.assertTrue(isExpectedPage!!, "actual page is not $pageName")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : AbstractPage?> getPageAs(pageClass: Class<T>?) = actualPage as T

    fun clickOnElement(element: MobileElement, waitInSeconds: Long = 10) {
        println("Trying to click on element $element")
        val isClickable = isElementClickable(element, waitInSeconds)
        if (isClickable) {
            element.click()
            println("Clicked on element $element")
        } else throw RuntimeException("Wasn't able to click on element $element")
    }

    fun isElementDisplayed(element: MobileElement, waitInSeconds: Long = 10) =
         try {
            waitFor(ExpectedConditions.visibilityOf(element), waitInSeconds)
            true
        } catch (e: Exception) {
            false
        }

    fun setText(element: MobileElement, text: String, hideKeyBoard: Boolean = false) {
        val isDisplayed = isElementDisplayed(element)
        if (isDisplayed) {
            element.sendKeys(text)
            if (hideKeyBoard) driver?.hideKeyboard()
        }
    }

    fun assertCondition(condition: Boolean, error: String) = Assert.assertTrue(condition,error)


    fun getDriver() = driver


    private fun isElementClickable(element: MobileElement, waitInSeconds: Long): Boolean {
        return try {
            waitFor(ExpectedConditions.elementToBeClickable(element), waitInSeconds)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun waitFor(expectedCondition: ExpectedCondition<WebElement>, waitInSeconds: Long) {
        val wait = WebDriverWait(driver, waitInSeconds)
        wait.until(expectedCondition)
    }


    private fun printMessageBasedOnCondition(condition: Boolean, page: String) =
         when(condition){
            true -> println("Actual page is $page as expected")
            false -> println("FAIL!!! - Actual page is not $page as expected")
        }

    private fun getCurrentPageAsObject(pageName: String): AbstractPage {
        val page = Pages.valueOf(pageName);
        return page.getPage(this);
    }


    protected abstract fun initCapabilities()
    protected abstract fun installedAppCapabilities()

}