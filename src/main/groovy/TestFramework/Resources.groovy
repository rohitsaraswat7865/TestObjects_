package TestFramework
//XML Unit
import org.custommonkey.xmlunit.Validator
import org.xml.sax.InputSource

class Resources {
    def resourceFolderPath
    Validator validator
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
    Resources(){
        try{
            String xml = this.resourceFolderPath + "Resource.xml"
            println(xml.toString())
            InputSource inputSource = new InputSource(new FileInputStream(xml))
            this.validator = new Validator(inputSource)
        }
        catch(Exception ex){
            throw new Exception(ex.getMessage())
        }

    }

    boolean GetResources(){
        if(this.validator.isValid()){

            //
        }else{
            return false
        }
        return true
    }
}
