package TestFramework

import org.openqa.selenium.*
import org.openqa.selenium.support.*
import org.openqa.selenium.support.ui.*

import java.time.Duration
import java.util.concurrent.TimeUnit
import TestInterfaces.*

class Helper implements BHelper{

    WebElement webElement
    def webElementList

    Helper(){
        this.webElement = null
        this.webElementList = null
    }

    boolean CheckIfPageIsLoaded(TestObject testObject){
        try{
            WebDriverWait explicitWait = new WebDriverWait(testObject.GetDriver(),100)
            return explicitWait.until(x -> (testObject.GetDriver() as JavascriptExecutor).executeScript("return document.readyState") == ("complete"))
        }catch(Exception ex){
            throw new Exception("HELPER-"+ex.getMessage())
        }
    }

    boolean CheckIfPageIsLoaded(TestObject testObject, String urlSubString){
        try{
            FluentRoutine(testObject, urlSubString)
            if(testObject.GetDriver().currentUrl.contains(urlSubString)){
                if(CheckIfPageIsLoaded(testObject)){
                    return true
                }
            }
        }catch(Exception ex){
            throw new Exception("HELPER-"+ex.getMessage())
        }
        return false
    }

    boolean TryGetElement(TestObject testObject, By by){
        try{
            WebDriver driver = testObject.GetDriver()
            WebElement testElement = driver.findElement(by)
            FluentRoutine(testElement)
            if(testElement.isDisplayed() && testElement.isEnabled()){
                testObject.elementCache << [(by):(testElement)]
            }
            else{
                return false
            }
            this.webElement = testElement
        }catch(Exception ex){
            throw new Exception("HELPER-"+ex.getMessage())
        }
        return true
    }

    boolean TryGetElementList(TestObject testObject, By by){

        try{
            def testElementList = testObject.GetDriver().findElements(by)
            for(WebElement element in testElementList){
                FluentRoutine(element)
                if(!(element.isDisplayed() && element.isEnabled())){
                    return false
                }
            }
            this.webElementList = testElementList
        }catch(Exception ex){
            throw new Exception("HELPER-"+ex.getMessage())
        }
        return true
    }

    boolean CheckIfElementsAreVisible(TestObject testObject, By by){
        try{


        }catch(Exception ex){
            throw new Exception("HELPER-"+ex.getMessage())
        }
        return true
    }

    boolean CheckIfElementsAreNotVisible(TestObject testObject, By by){

    }

    private void FluentRoutine(WebElement testElement){
        Duration duration = Duration.ofSeconds(4)
        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(testElement)
        fluentWait.pollingEvery(duration)
        fluentWait.withMessage("HELPER-fluent timeout")
        fluentWait.withTimeout(Duration.ofSeconds(90))
        fluentWait.until(x -> testElement.isDisplayed() && testElement.isEnabled())
    }

    private void  FluentRoutine(TestObject testObject, String urlSubString){
        Duration duration = Duration.ofSeconds(4)
        WebDriverWait fluentWait = new WebDriverWait(testObject.GetDriver(), 90)
        fluentWait.pollingEvery(duration)
        fluentWait.withMessage("HELPER-fluent url mismatch")
        fluentWait.until(x -> testObject.GetDriver().currentUrl.contains(urlSubString))
    }
}
