//package bean;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import sun.rmi.runtime.Log;
//import utils.CodeHelper;
//
///**
// * @Author: Snowson
// * @Date: 2018/5/15 14:48
// * @Description:
// */
//public class ArtQRCode {
//    public ArtQRCode(String url) throws IllegalArgumentException {
//        BitMatrix bitMatrix = null;
//        if (!CodeHelper.isEmpty(url)) {
//            //配置参数
//            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            //容错级别
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//            //设置空白边距的宽度
//            hints.put(EncodeHintType.MARGIN, 0); //default is 4
//            // 图像数据转换，使用了矩阵转换
//            try {
//                bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, 0, 0, hints);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            }
//        }
//        if (bitMatrix != null) {
//            valid = formatBitMatrix(bitMatrix);
//            Log.i(TAG, "bitMatrix:" + bitMatrix);
//        } else {
//            throw new IllegalArgumentException("bitMatrix should not be null!");
//        }
//    }
//
//    /** 格式化BitMatrix，找出其中所有的码元点   */
//    private boolean formatBitMatrix(BitMatrix bitMatrix) {
//        boolean success = false;
//        int matrixLength = bitMatrix.getWidth();
//        int matrixHeight = bitMatrix.getHeight();
//        if (matrixLength == matrixHeight && matrixLength>(LOCATOR_WIDTH+1)*2) {
//            success = true;
//            codeNumPerLine = matrixLength;
//            leftLocator.set(0, 0, LOCATOR_WIDTH, LOCATOR_WIDTH);
//            rightLocator.set(matrixLength - LOCATOR_WIDTH, 0, matrixLength, LOCATOR_WIDTH);
//            bottomLocator.set(0, matrixLength - LOCATOR_WIDTH, LOCATOR_WIDTH, matrixLength);
//            //遍历，得出所有的码元
//            for (int x = 0; x < matrixLength; x++) {
//                StringBuilder sb = new StringBuilder("列"+x);
//                StringBuilder sb_p = new StringBuilder("point");
//                //先列遍历，再行遍历
//                for (int y = 0; y < matrixLength; y++) {
//                    //查看此element是否为有信息的码元点
//                    boolean info = bitMatrix.get(x, y);
//                    Point point = new Point(x, y);
//                    if (info) {
//                        String pointStr = "["+y+","+x+"]";
//                        if (!isElementInRect(leftLocator, point)
//                                && !isElementInRect(rightLocator, point)
//                                && !isElementInRect(bottomLocator, point)) {//不属于定位符的点
//                            //以列优先的顺序存放，故需要把下标的行列反过来，便于后续的按列顺序查找
//                            int columnRow = y + x*matrixLength;
//                            //存储该码元在可用码元矩阵中的列行坐标
//                            this.codeElements.put(columnRow, point);
//                            sb.append(pointStr);
//                        } else {
//                            sb_p.append(pointStr);
//                        }
//                    }
//                }
//                Log.w(TAG, sb.toString());
//                sb_p.append(" in locator");
//                Log.w(TAG, sb_p.toString());
//            }
//        }
//        return success;
//    }
//}
//
