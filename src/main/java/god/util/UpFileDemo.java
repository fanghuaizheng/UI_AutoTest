package god.util;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-07-11 20:58
 */
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UpFileDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebdriverUtil.chromeWebdriver();
//        File file = new File(System.getProperty("user.dir")+"/src/main/resources/upfile.html");
//        String filePath = file.getAbsolutePath();
        driver.get("http://permit.mee.gov.cn/permitExt/outside/registration.jsp");

        //定位上传按钮， 添加本地文件
        driver.findElement(By.name("imgfile")).sendKeys(System.getProperty("user.dir")+"/src/main/resources/enterpriseLicensing/菏泽市宝利泽航空服务有限公司.jpg");
        Thread.sleep(5000);

        driver.quit();
    }
}
