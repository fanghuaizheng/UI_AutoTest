package god.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverUtil {
    /**
     * fireFox:53.0.3
     * selenium:3.14
     * geckodriver:0.17
     */
    public static void fireFoxWebdriver(){
        System.setProperty("webdriver.firefox.bin",System.getProperty("user.dir")+"/"+"src\\main\\resources\\Firefox Setup 53.0b10.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/"+"src\\main\\resources\\geckodriver.exe");
    }
    /**
     * chrome:68.0.3440.84
     * selenium:3.14
     * chromedriver:忘了
     * chrome会被检测到window.navigator.webdriver
     */
    public static WebDriver chromeWebdriver(){
//        System.setProperty("webdriver.chrome.driver","/Users/woni/Downloads/UI_AutoTest_programOne/src/main/resources/chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        return driver;

    }


    public static void main(String[] args) {

//        chromeWebdriver();

        WebDriver driver = chromeWebdriver();

        driver.get("http://permit.mee.gov.cn/permitExt/outside/registration.jsp");


    }


}
