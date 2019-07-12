package god.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by thomas on 2018/9/25.
 */
public class SetBrowserProperty {
    private static WebDriver driver;
    static {
        //默认浏览器页面为最大化
//        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\SysWOW64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver");
//        driver= new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
   public static WebDriver openChromeBrowser(){
       return driver;
   }
}
