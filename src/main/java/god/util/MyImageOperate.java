package god.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @program: IMCC
 * @description:
 * @author: fanghuaizheng
 * @create: 2019-07-13 15:47
 */
public class MyImageOperate {


    /**
     *
     * @param srcPath
     * @param targetPath
     * 裁剪图片, 参数说明:
     *     x: 裁剪起点横坐标
     *     y: 裁剪起点纵坐标
     *     w: 需要裁剪的宽度
     *     h: 需要裁剪的高度
     * @throws IOException
     */
    public static void cut(String srcPath,String targetPath,int x,int y,int w,int h) throws IOException {
        BufferedImage bufImage = ImageIO.read(new File(srcPath));

        int width = bufImage.getWidth();
        int height = bufImage.getHeight();

        BufferedImage subimage = bufImage.getSubimage(x, y, w, h);

        ImageIO.write(subimage,"PNG",new File(targetPath));

    }


    public static void main(String[] args) throws IOException {

        String src = "src/test/screenshot/image.png";
        String tar = "src/test/screenshot/code.png";

        cut(src,tar,444,480,80,30);





    }

}
