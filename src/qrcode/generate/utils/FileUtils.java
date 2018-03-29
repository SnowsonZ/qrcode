package qrcode.generate.utils;

import java.io.File;

public class FileUtils{
    /**
     * user: Rex
     * date: 2016年12月29日  上午12:25:04
     *
     * @param dir void
     *            TODO 判断路径是否存在，如果不存在则创建
     */
    public static void mkdirs(String dir){
        if(dir == null || dir.equalsIgnoreCase("")){
            return;
        }

        File file = new File(dir);
        if(file.isDirectory()){
            return;
        }else{
            file.mkdirs();
        }
    }
}
