package com.mh.blog.util;

import org.junit.Test;

public class AESUtilTest {

	private String sourceText = "Hello Jason Yao";
	private String encryptText = "CEvp78Qrz9K6SlzLVUQKaQ==";

	@Test
	public void testEncrypt() {
		String encryptText = AESUtil.encrypt(sourceText);
		System.out.println(encryptText);
	}

	@Test
	public void testDecrypt() {
		String sourceText = AESUtil.decrypt(encryptText);
		System.out.println(sourceText);
	}

}
