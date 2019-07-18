package god.util;

/**
 * @program: IMCC
 * @description:
 * @author: fanghuaizheng
 * @create: 2019-07-13 16:27
 */

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;

/**
 * Tess4J测试类
 */
public class Tess4JUtils {

    public static String validateCode(String path){
       		//我的项目存放路径

        File file = new File( path);

        File tess= LoadLibs.extractTessResources("tessdata");

        ITesseract instance = new Tesseract();

        instance.setDatapath(tess.getAbsolutePath());


        //设置训练库的位置
//        instance.setDatapath(System.getProperty("user.dir") + "/tessdata");

//        instance.setLanguage("eng");//chi_sim ：简体中文， eng	根据需求选择语言库
        String result = null;

//        CleanI

        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
//
//        System.out.println("result: ");
//        System.out.println(result);

        return result;
    }

    public static void main(String[] args){

        String code = validateCode("src/test/screenshot/code.png");

        System.out.println(code);

    }

}
