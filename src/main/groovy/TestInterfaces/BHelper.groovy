package TestInterfaces

import TestFramework.TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

interface BHelper {

    boolean CheckIfPageIsLoaded(TestObject testObject)
    boolean CheckIfPageIsLoaded(TestObject testObject, String urlSubString)
    boolean TryGetElement(TestObject testObject, By by)
    boolean TryGetElementList(TestObject testObject, By by)
    boolean CheckIfElementsAreVisible(TestObject testObject, By by)
    boolean CheckIfElementsAreNotVisible(TestObject testObject, By by)
}