package StepDef

import TestFramework.*
import TestInterfaces.*
import org.openqa.selenium.WebDriver

for(int i=0;i<30;i++){
    try(TestObject testObject = TestObject.GetTestObject(AlignmentOptions.MobileSite, Target.Edge)){

        URL url = new URL("https://www.selenium.dev/")
        WebDriver driver = testObject.GetDriver()
        driver.navigate().to(url)
        BHelper helper = new Helper()
        helper.CheckIfPageIsLoaded(testObject,"selenium")

    }catch(Exception ex){
        println(ex.getMessage())
    }
}
