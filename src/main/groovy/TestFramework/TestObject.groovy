package TestFramework

import org.openqa.selenium.*
import org.openqa.selenium.chrome.*
import org.openqa.selenium.edge.*
import org.openqa.selenium.remote.*
import org.openqa.selenium.support.*
import org.openqa.selenium.support.ui.*

import java.util.concurrent.TimeUnit

class TestObject implements AutoCloseable {

    private WebDriver webDriver
    def elementCache = [:]
    def dataCache = [:]
    def resourceFolderPath

    {
        try {
            StringBuilder strB = new StringBuilder()
            strB.append(System.getProperty("user.home").toString())
            strB.append(File.separator)
            strB.append("Resources")
            strB.append(File.separator)
            this.resourceFolderPath = strB.toString()
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage())
        }
    }

    private TestObject(AlignmentOptions aOptions, Target tOptions) {

        switch(aOptions){
            case [AlignmentOptions.MobileSite, AlignmentOptions.DesktopSite] :
                if(tOptions === Target.Chrome){
                    ChromeOptions chromeOptions = new ChromeOptions()
                    chromeOptions.pageLoadStrategy = PageLoadStrategy.NORMAL
                    chromeOptions.addArguments("--disable-notifications")
                    chromeOptions.addArguments("--start-maximized")
                    chromeOptions.addArguments("--disable-sync")
                    chromeOptions.addArguments("--disable-gpu")
                    chromeOptions.addArguments("--mute-audio")
                    chromeOptions.addArguments("--no-sandbox")
                    if(aOptions === AlignmentOptions.MobileSite){
                        def mobileEmulationOptions = [:]
                        mobileEmulationOptions << ["deviceName":"iPhone X"]
                        chromeOptions.setExperimentalOption("mobileEmulation",mobileEmulationOptions)
                    }
                    chromeOptions.setAcceptInsecureCerts(true)
                    //chromeOptions.addArguments("--headless")
                    this.resourceFolderPath = this.resourceFolderPath + "chromedriver.exe"
                    def driverKey = "webdriver.chrome.driver"
                    System.setProperty(driverKey.toString(),this.resourceFolderPath.toString())
                    this.webDriver = new ChromeDriver(chromeOptions)
                }
                if(tOptions === Target.Edge){
                    EdgeOptions edgeOptions = new EdgeOptions()
                    edgeOptions.pageLoadStrategy = PageLoadStrategy.NORMAL
                    edgeOptions.setCapability("disable-infobars",true)
                    //inherit edge capabilities from local installtion
                    this.resourceFolderPath = this.resourceFolderPath + "msedgedriver.exe"
                    def driverKey = "webdriver.edge.driver"
                    System.setProperty(driverKey.toString(),this.resourceFolderPath.toString())
                    this.webDriver = new EdgeDriver(edgeOptions)
                    this.webDriver.manage().window().maximize()

                }
                ////------------------------------------------------------------------////
                this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                this.webDriver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS)
                break
            case [AlignmentOptions.MobileApp]:
                break
            default:
                throw new Exception("Device code mismatch")
        }
    }

    static TestObject GetTestObject(AlignmentOptions aOptions, Target tOptions){
        try{
            if(aOptions != null && tOptions != null){
                return new TestObject(aOptions, tOptions)
            }
            else{
                throw new Exception("Alignment options and Target app cannot be null")
            }

        }catch(Exception ex){
            throw new Exception(ex.getMessage())
        }

    }

    WebDriver GetDriver(){
        return this.webDriver
    }

    boolean Reset(){
        try{
            this.webDriver.manage().deleteAllCookies()
            return true
        }catch(ignored){
            return false
        }

    }

    void Push(def element){
        def elementCode = (element as Object).hashCode().toString()
        this.dataCache << [(elementCode):element]
    }

    void close() {
        this.webDriver.close()
        Thread.sleep(2000)
        this.webDriver.quit()
    }
}



