package god.testscripts.permitregistercase;

import god.util.Constant;

public class GetCompanyImgPath {
    public static String getCompanyImgPath(String companyName){
        String companyImgpath = null;
        companyImgpath = System.getProperty("user.dir")+Constant.COMPANY_IMAGE+companyName+".jpg";
        return companyImgpath;
    }
}
