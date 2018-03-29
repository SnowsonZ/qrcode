package qrcode.generate;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qrcode.generate.bean.ShopInfo;
import qrcode.generate.utils.CodeHelper;
import qrcode.generate.utils.ExcelParseTool;
import qrcode.generate.utils.QRCodeUtil;

public class QRCodeGenerationTest{
    private static final String KFC_MAIN = "http://mall.kfc.com.cn/kfcmall20/wechat/pr-con.do" +
            "?activityId=SMK20170910715_10000&utm_source=Offline&utm_medium=DM&" +
            "utm_campaign=BFMonthlyCard&utm_content";
    private static final String KFC_ZJS = "http://mall.kfc.com.cn/kfcmall20/wechat/pr-con.do" +
            "?activityId=SMK20170910731_10000&utm_source=Offline&utm_medium=DM&" +
            "utm_campaign=DeliveryMC&utm_content=";
    private static final String BASE_PATH = "E:\\result\\qrcode";
    private static final String PATH_KFC_MAIN = "\\kfc_main";
    private static final String PATH_KFC_ZJS = "\\kfc_zjs";
    private static final String SUFFIX_FILE = ".png";
    private static boolean qrCodeDir;

    public static void main(String[] args){
        ExcelParseTool excelParseTool = new ExcelParseTool();

        excelParseTool.setFilePath("E:\\result\\res\\早餐明细.xls");
//        excelParseTool.setFilePath("E:\\result\\res\\宅急送.xls");
        try{
            Workbook workbook = excelParseTool.initWorkBook();

            List<ShopInfo> outData = new ArrayList<>();

            if(workbook != null){
                excelParseTool.parseWorkbook(workbook,outData);
            }

            List<ShopInfo> unique = new ArrayList<>();

            for(ShopInfo shopInfo : outData){
                if(!unique.contains(shopInfo)){
                    unique.add(shopInfo);
                }else{
                    System.out.println("该名称重复：" + shopInfo.toString());
                }
            }

            if(unique.size()>0){
                generatorQrCode(unique,PATH_KFC_MAIN);
            }else{
                System.out.println("excel is empty....");
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }catch(Exception e){
            System.out.println("case: " + e.getCause().toString());
            System.out.println("msg: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void generatorQrCode(List<ShopInfo> unique,String childDir) throws Exception{
        System.out.println("size: " + unique.size());
        System.out.println("------------------generate QRCode started------------------");
        System.out.println();
        for(ShopInfo item : unique){
            String shopId = item.getId();
            String shopName = item.getName();
            if(CodeHelper.isEmpty(shopId) || CodeHelper.isEmpty(shopName)){
                continue;
            }
            File file = new File(BASE_PATH + childDir,shopName + "_" + shopId + SUFFIX_FILE);
            if(!file.getParentFile().exists()){
                qrCodeDir = file.getParentFile().mkdirs();
            }
            if(file.exists()){
                System.out.println("\"该名称重复：\"" + shopName);
                file.delete();
            }
            String content = KFC_MAIN + shopId;
            System.out.println("generate " + shopName + " QRCode started ...");
            System.out.println(content);
            QRCodeUtil.encode(content,new FileOutputStream(file));
            System.out.println("generate " + shopName + " QRCode completed...");
        }
        System.out.println();
        System.out.println("------------------generate QRCode completed------------------");
    }
}
