package god.testscripts.permitregistercase;

import god.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterCase {
    public WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        ExcelUtil.setExcelFile(System.getProperty("user.dir")+"/"+Constant.TESTCASE_DATA,"Sheet1");
        DOMConfigurator.configure("target\\classes\\log4j.xml");//加载lo4gj.xml文件，以便生成日志文件
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver = WebdriverUtil.chromeWebdriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void register()throws Exception{
        for (int i = 1; ExcelUtil.getCellData(i,0).contains("公司") ; i++) {
            registerAction(i);
        }
    }

    private void registerAction(int i) throws Exception {
        driver.get("http://permit.mee.gov.cn/permitExt/outside/registration.jsp");
        driver.switchTo().alert().accept();

        DriverWait.findElement(driver, By.id("regBtn"));
        String RegisterEnterName =  ExcelUtil.getCellData(i,0);//注册公司名
        String EnterName = RegisterEnterName;//注册公司名
        String LoginName = ExcelUtil.getCellData(i,1);//注册账号
        String LoginPwd = ExcelUtil.getCellData(i,2);//注册密码
        String MailAddr = ExcelUtil.getCellData(i,3);//注册邮箱
        DragScrollBar.dragToBottom(driver);
//        Integer[] list = {890, 822, 70, 25};
        Integer[] list = {444, 1334, 514, 1359};
        String verCode = IdentifiVerifiCode.getCode(driver,list,"image","code");//验证编码
        List<String> list1 = GetCompanyInfo.getCompanyInfo(EnterName);
        String EnterAddress = list1.get(1);//注册地址
        String ProductAddress = EnterAddress;//生产经营场所地址
        String ZipCode = list1.get(3);//邮政编码
        String SocietyCode = list1.get(0);//统一信用编码
        String province = "山东省";
        String city = "菏泽市";
        String counties = list1.get(2);//区县
        String reiver = "淮河流域";//流域
        String trade = "D443";//行业
        String CSocietyCode = "/";//总公司统一信用编码
        String imgfile = GetCompanyImgPath.getCompanyImgPath(EnterName);//复印件



        //输入数据
        DriverWait.findElement(driver,By.id("regBtn"));
        DragScrollBar.dragToEleBot(driver,driver.findElement(By.id("trade")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='industryandname']/following-sibling::input")).click();
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        DriverWait.findElement(driver,By.id("tradename")).sendKeys(trade);
        driver.findElement(By.id("cxgjlb")).click();
        Thread.sleep(1000);
        DriverWait.findElement(driver,By.id("tree_1_check")).click();
        driver.findElement(By.id("savebtn")).click();
        Thread.sleep(1000);
        driver.switchTo().parentFrame();
        Thread.sleep(1000);

        driver.findElement(By.id("RegisterEnterName")).sendKeys(RegisterEnterName);
        driver.findElement(By.id("EnterName")).sendKeys(EnterName);
        driver.findElement(By.id("EnterAddress")).sendKeys(EnterAddress);
        driver.findElement(By.id("ProductAddress")).sendKeys(ProductAddress);
        driver.findElement(By.id("ZipCode")).sendKeys(ZipCode);
        SelectSigleBox.chooseOption(driver,By.id("province"),province);
        SelectSigleBox.chooseOption(driver,By.id("city"),city);
        SelectSigleBox.chooseOption(driver,By.id("counties"),counties);
        SelectSigleBox.chooseOption(driver,By.id("reiver"),reiver);
        driver.findElement(By.id("SocietyCode")).sendKeys(SocietyCode);
        driver.findElement(By.id("CSocietyCode")).sendKeys(CSocietyCode);
        driver.findElement(By.id("LoginName")).sendKeys(LoginName);
        driver.findElement(By.id("LoginPwd")).sendKeys(LoginPwd);
        driver.findElement(By.id("ReLoginPwd")).sendKeys(LoginPwd);
        driver.findElement(By.id("MailAddr")).sendKeys(MailAddr);
        driver.findElement(By.id("verCode")).sendKeys(verCode);

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"td_1\"]/input[2]")).click();
        Thread.sleep(1000);

        selectImg(EnterName);

        Thread.sleep(10000);
        try {
            int timeIndex = 10;
            while (StringUtils.isBlank(driver.findElement(By.id("imgfile")).getText())){
                System.out.println("选择图片");
                Thread.sleep(timeIndex*1000);

                driver.findElement(By.xpath("//*[@id=\"td_1\"]/input[2]")).click();
                selectImg(EnterName);
                timeIndex+=10;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        driver.findElement(By.id("regBtn")).click();

        try{

            if (driver.findElement(By.cssSelector(".test1"))!=null){

                FileUtils.write(new File("saveResult.txt"),EnterName+"\t保存失败\r\n","Utf-8",true);

                System.out.println(EnterName+"保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread.sleep(10000L);

//        driver.quit();
       

    }
//
//    @Test
//    public void startExec(){
//        startUploadImg(System.getProperty("user.dir")+UploadImgUtils.HK);
//    }


    private void selectImg(String EnterName){
        //开始自动上传命令
        switch (EnterName){
            case "菏泽市宝利泽航空服务有限公司":
                startUploadImg(System.getProperty("user.dir")+UploadImgUtils.HK);break;
            case "菏泽市新良市政服务有限公司":
                startUploadImg(System.getProperty("user.dir")+UploadImgUtils.Zwfw);break;
            case "菏泽华鑫民间融资登记服务有限公司":
                startUploadImg(System.getProperty("user.dir")+UploadImgUtils.RZDJ);break;

        }
    }

    private void startUploadImg(String imgfile){
        Runtime rn = Runtime.getRuntime();
        try {
            rn.exec(imgfile);
            System.out.println("执行完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
