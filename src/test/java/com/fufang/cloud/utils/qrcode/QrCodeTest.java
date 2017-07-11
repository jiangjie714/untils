package com.fufang.cloud.utils.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.fufang.cloud.utils.qrcode.QrCode;

public class QrCodeTest {
	
	@Test
	public void test01_writeToFile() throws Exception {
		QrCode.writeToFile("www.baidu.com", "d://test.jpg", 80, 80);
	}
	
	@Test
	public void test02_writeToBase64() throws Exception {
		String base64 = QrCode.writeToBase64("www.baidu.com", 80, 80);
		Assert.assertNotNull(base64);
	}
	
	@Test
	public void test03_writeToStream() throws Exception {
		ByteArrayOutputStream stream = QrCode.writeToStream("www.baidu.com", 80, 80);
		Assert.assertNotNull(stream);
	}
	
	@Test
	public void test04_writeToImage() throws Exception {
		BufferedImage image = QrCode.writeToImage("www.baidu.com", 80, 80);
		Assert.assertNotNull(image);
	}
	
	@Test
	public void test05_read() throws Exception {
		String content = QrCode.read("d://test.jpg");
		Assert.assertEquals("www.baidu.com", content);
	}
	
	

}
