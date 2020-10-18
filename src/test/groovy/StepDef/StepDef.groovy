package StepDef

import TestInterfaces.*
import TestFramework.*
import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks
import org.openqa.selenium.*


this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)


BHelper helper = new Helper()

Given(~/^Open URL <"([^"]*)">$/) { String arg1 ->

}


