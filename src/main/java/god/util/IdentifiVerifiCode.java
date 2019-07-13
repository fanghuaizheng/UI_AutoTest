package god.util;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IdentifiVerifiCode {

    public static String getCode(WebDriver driver,Integer[] list,String srcimage,String subimage)throws Exception{
        String imagepath = Constant.SCR_SHOT_PATH+srcimage+".png";
        String codepath = Constant.SCR_SHOT_PATH+subimage+".png";
        new GetScreenshot(srcimage,driver);

        try {
            MyImageOperate.cut(imagepath,codepath,444,480,80,30);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imagecode = Tess4JUtils.validateCode(codepath);
        return imagecode;
    }

    public static void main(String[] args) throws Exception {
        String code = Tess4JUtils.validateCode("src/test/screenshot/code.png");

        System.out.println(code);


    }


}
