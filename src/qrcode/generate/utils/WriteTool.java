package qrcode.generate.utils;

import java.io.BufferedWriter;
import java.io.IOException;

public class WriteTool{
    private static final String SEP_CHAR = "\"";
    private static final String DOUBLE_SPACE = "  ";
    private static final String FOUR_SPACE = "    ";

//    void write(String filePath, List<ShopInfo> list) throws IOException{
//        File file = new File(filePath);
//
//        boolean newFileCreated = false;
//        if (!file.exists()) {
//            newFileCreated = file.createNewFile();
//        }
//
//        if (newFileCreated) {
//            BufferedWriter out = new BufferedWriter(new FileWriter(file));
//
//            writeLine(out, "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//            writeLine(out, "");
//
//            writeLine(out, "<apns version=\"8\">");
//            writeLine(out, "");
//
//            for (ShopInfo shopInfo : list) {
//                writeLine(out, DOUBLE_SPACE + "<apn " + "carrier=" + SEP_CHAR + shopInfo.getCarrier() + SEP_CHAR);
//                writeLine(out, FOUR_SPACE + "mcc=" + SEP_CHAR + shopInfo.getMcc() + SEP_CHAR);
//                writeLine(out, FOUR_SPACE + "mnc=" + SEP_CHAR + shopInfo.getMnc() + SEP_CHAR);
//                writeLine(out, FOUR_SPACE + "apn=" + SEP_CHAR + shopInfo.getApn() + SEP_CHAR);
//
//                if (validString(shopInfo.getProxy()))
//                    writeLine(out, FOUR_SPACE + "proxy=" + SEP_CHAR + shopInfo.getProxy() + SEP_CHAR);
//
//                if (validString(shopInfo.getPort()))
//                    writeLine(out, FOUR_SPACE + "port=" + SEP_CHAR + shopInfo.getPort() + SEP_CHAR);
//
//                if (validString(shopInfo.getMmsc()))
//                    writeLine(out, FOUR_SPACE + "mmsc=" + SEP_CHAR + shopInfo.getMmsc() + SEP_CHAR);
//
//                if (validString(shopInfo.getMmsproxy()))
//                    writeLine(out, FOUR_SPACE + "mmsproxy=" + SEP_CHAR + shopInfo.getMmsproxy() + SEP_CHAR);
//
//                if (validString(shopInfo.getMmsport()))
//                    writeLine(out, FOUR_SPACE + "mmsport=" + SEP_CHAR + shopInfo.getMmsport() + SEP_CHAR);
//
//                if (validString(shopInfo.getUser()))
//                    writeLine(out, FOUR_SPACE + "user=" + SEP_CHAR + shopInfo.getUser() + SEP_CHAR);
//
//                if (validString(shopInfo.getPassword()))
//                    writeLine(out, FOUR_SPACE + "password=" + SEP_CHAR + shopInfo.getPassword() + SEP_CHAR);
//
//                if (validString(shopInfo.getProtocol())) {
//                    String tmp = shopInfo.getProtocol();
//
//                    if (tmp.contains("4") && tmp.contains("6")) {
//                        tmp = "IPV4V6";
//                    } else if (tmp.contains("4")) {
//                        tmp = "IPV4";
//                    } else if (tmp.contains("6")) {
//                        tmp = "IPV6";
//                    } else {
//                        System.out.println("WTF, bad protocol: " + tmp);
//                        tmp = null;
//                    }
//
//                    if (tmp != null)
//                        writeLine(out, FOUR_SPACE + "protocol=" + SEP_CHAR + tmp + SEP_CHAR);
//                }
//
//                if (validString(shopInfo.getAuthtype()))
//                    writeLine(out, FOUR_SPACE + "authtype=" + SEP_CHAR + shopInfo.getAuthtype() + SEP_CHAR);
//
//                if (validString(shopInfo.getType()))
//                    writeLine(out, FOUR_SPACE + "type=" + SEP_CHAR + shopInfo.getType() + SEP_CHAR);
//
//                //In fact, it may need to change here, my apn-excel only have spn type
//                if (validString(shopInfo.getMvnoMatchData())) {
//                    writeLine(out, FOUR_SPACE + "mvno_type=" + SEP_CHAR + "spn" + SEP_CHAR);
//                    writeLine(out, FOUR_SPACE + "mvno_match_data=" + SEP_CHAR + shopInfo.getMvnoMatchData() + SEP_CHAR);
//                }
//
//                writeLine(out, DOUBLE_SPACE + "/>");
//                writeLine(out, "");
//            }
//
//            writeLine(out, "</apns>");
//
//            out.close();
//        }
//    }

    private boolean validString(String str) {
        return str != null && !str.isEmpty();
    }

    private void writeLine(BufferedWriter out, String str) throws IOException{
        out.write(str + "\n");
    }
}
