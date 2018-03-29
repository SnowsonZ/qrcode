package qrcode.generate;

import qrcode.generate.utils.BatchQRCodeGenerateUtils;

public class QRCodeGenerationTest {
    public static void main(String[] args) {
        BatchQRCodeGenerateUtils.generateQRCode("E:\\workspace\\qrcode\\src\\qrcode" +
                "\\generate\\config\\config.properties");
    }
}
