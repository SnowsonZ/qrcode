package qrcode.generate.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import qrcode.generate.bean.ShopInfo;
import utils.CodeHelper;

/**
 * @Author: Snowson
 * @Date: 2018/3/30 3:03
 * @Description: 批量生成二维码
 */

public class BatchQRCodeGenerateUtils {
    public static void generateQRCode(String filePath) {
        Properties props = new Properties();
        try {
            //配置资源路径
            props.load(new InputStreamReader(new FileInputStream(new File(filePath)),
                    "utf-8"));
            String baseUrl = props.getProperty("BASE_URL");
            String resPath = props.getProperty("RES_PATH");
            String outputPath = props.getProperty("OUTPUT_PATH");
            String suffixFile = props.getProperty("SUFFIX_FILE");

            //读取数据
            List<ShopInfo> unique = new ArrayList<>();
            List<ShopInfo> srcData = new ArrayList<>();
            List<ShopInfo> invalidData = new ArrayList<ShopInfo>();
            ExcelParseTool excelParseTool = new ExcelParseTool();
            excelParseTool.setFilePath(resPath);

            Workbook workbook = excelParseTool.initWorkBook();
            if (workbook != null) {
                excelParseTool.parseWorkbook(workbook, srcData);
            }
            for (ShopInfo shopInfo : srcData) {
                if (!(CodeHelper.isEmpty(shopInfo.getId())
                        || CodeHelper.isEmpty(shopInfo.getName()))
                        && !unique.contains(shopInfo)) {
                    unique.add(shopInfo);
                } else {
                    invalidData.add(shopInfo);
                    System.out.println("该名称重复：" + shopInfo.toString());
                }
            }

            if (unique.size() <= 0) {
                System.out.println("excel is empty....");
                return;
            }

            //批量生成二维码
            System.out.println("size: " + unique.size());
            System.out.println("------------------generate QRCode started------------------");
            System.out.println();
            //输出合法数据
            for (ShopInfo item : unique) {
                String shopId = item.getId();
                String shopName = item.getName();
                File file = new File(outputPath, shopName + "_" + shopId + suffixFile);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if(file.exists()) {
                    boolean delete = file.delete();
                    System.out.println(delete);
                }
                String content = baseUrl + shopId;
                System.out.println();
                System.out.println("generate " + shopName + " QRCode started ...");
                System.out.println(content);
                QRCodeUtil.encode(content, new FileOutputStream(file));
                System.out.println("generate " + shopName + " QRCode completed...");
            }
            System.out.println();
            System.out.println("------------------generate QRCode completed------------------");
            if (invalidData.size() <= 0) {
                return;
            }
            //输出不合法的数据
            System.out.println();
            System.out.println();
            System.out.println("size: " + invalidData.size());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!invalid data start!!!!!!!!!!!!!!!!!!!!!!!!!!");
            for (ShopInfo anInvalidData : invalidData) {
                System.out.println();
                String id = anInvalidData.getId();
                String name = anInvalidData.getName();
                System.out.println("ID: " + (CodeHelper.isEmpty(id) ? "" : id));
                System.out.println("Name: " + (CodeHelper.isEmpty(name) ? "" : name));
            }
            System.out.println();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!invalid data start!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("load config.properties error...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateQRCodeByType() {

    }

}
