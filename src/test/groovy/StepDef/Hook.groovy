package StepDef

import TestFramework.*
import TestInterfaces.*
import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

TestObject testObject
//not, ~, @ignore, @wip

Before("@chromeDesktop"){

    testObject = TestObject.GetTestObject(AlignmentOptions.DesktopSite,Target.Chrome)

}

After("@chromeDesktop"){

    testObject.close()
}


Before("@chromeMobile"){

    testObject = TestObject.GetTestObject(AlignmentOptions.MobileSite, Target.Chrome)
}

After("@chromeMobile"){

    testObject.close()
}

Before("@edgeDesktop"){

    testObject =  TestObject.GetTestObject(AlignmentOptions.DesktopSite, Target.Edge)
}

After("@edgeDesktop"){

    testObject.close()
}