package com.fufang.cloud.utils.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

import com.fufang.cloud.core.exception.BusinessException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码工具
 */
public class QrCode {
	
	/**
	 * 写二维码 返回BufferedImage 
	 * @param content 二维码的内容
	 * @param width 
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage writeToImage(String content, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	/**
	 * 写二维码 
	 * @param content 二维码的内容
	 * @param path  生成后的完整路径 如"D:/test.jpg"
	 * @param width  如 80
	 * @param height 如 80
	 * @throws Exception
	 */
	public static void writeToFile(String content, String path, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(path));
	}
	
	/**
	 * 写二维码 返回流
	 * @param content 二维码的内容
	 * @param width  如 80
	 * @param height 如 80
	 * @throws Exception
	 */
	public static ByteArrayOutputStream writeToStream(String content, int width, int height) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "jpg", stream);
		return stream;
	}
	
	/**
	 * 写二维码 返回base64编码字符串
	 * @param content 二维码的内容
	 * @param width  如 80
	 * @param height 如 80
	 * @throws Exception
	 */
	public static String writeToBase64(String content, int width, int height) throws Exception {
		ByteArrayOutputStream out = writeToStream(content, width, height);
		return Base64.encodeBase64String(out.toByteArray());
	}

	/**
	 * 读取二维码信息
	 * @param path 读取的完整路径 如"D:/test.jpg"
	 * @return
	 * @throws Exception
	 */
	public static String read(String path) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();

		File file = new File(path);
		BufferedImage image = ImageIO.read(file);// 读取文件，识别成一个图片
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		// 二维码的参数
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap, hints);

		return result.getText();
	}
	
	/**
	 * 读取二维码信息
	 * @param path 从流中读取二维码信息
	 * @return
	 * @throws Exception
	 */
	public static String readFromStream(InputStream inputStream) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();

		BufferedImage image = ImageIO.read(inputStream);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		// 二维码的参数
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap, hints);

		return result.getText();
	}
	
	/**
	 * 写二维码 返回流
	 * @param content 二维码的内容
	 * @param width  如 80
	 * @param height 如 80
	 * @param format
	 * @throws Exception
	 */
	public static ByteArrayOutputStream writeToStream(String content, int width, int height, String format) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
		return stream;
	}
	
	/**
	 * 写二维码 返回base64编码字符串
	 * @param content 二维码的内容
	 * @param width  如 80
	 * @param height 如 80
	 * @param format jpg png
	 * @throws Exception
	 */
	public static String writeToBase64(String content, int width, int height, String format) throws Exception {
		ByteArrayOutputStream out = writeToStream(content, width, height, format);
		return Base64.encodeBase64String(out.toByteArray());
	}
	
	/**
	 * 将base64转图片并保存到文件
	 * @param imgBase64
	 * @param bathPath 根路径
	 * @param savePath 保存路径
	 * @param fileName 文件名称
	 * @return
	 */
	public static String generateImageByBase64(String imgBase64, String bathPath, String savePath, String fileName) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgBase64 == null) {// 图像数据为空
			throw new BusinessException("imgBase64 is empty");
		}
					
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] img = decoder.decodeBuffer(imgBase64);
			for (int i = 0; i < img.length; ++i) {
				if (img[i] < 0) {// 调整异常数据
					img[i] += 256;
				} 
			}
			
			return writeImageToDisk(img, bathPath, savePath, fileName);
		}catch (Exception e) {
			throw new BusinessException("imgstr is empty");
		}	
	}
	
	 /** 
     * 将图片写入到磁盘 
     * @param img 图片数据流 
     * @param fileName 文件保存时的名称 
     */  
    public static String writeImageToDisk(byte[] img, String bathPath, String savePath, String fileName){ 
    	
        try {  
        	
            String filePath = savePath + fileName;  
                      
            File file1 = new File(bathPath + savePath);
            if (!file1.exists()) {
            	file1.mkdirs();
    		}
            
            if(null == img || img.length < 0) {
            	throw new BusinessException("图片为空");
            }
            
            String relPath = bathPath + filePath;
            
            File file2 = new File(relPath);
            FileOutputStream fops = new FileOutputStream(file2);
            fops.write(img);  
            fops.flush();  
            fops.close();
     
            return filePath;

        } catch (Exception e) {  
        	throw new BusinessException("保存图片失败");
        }  
    }  
}