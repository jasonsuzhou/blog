package com.mh.blog.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author jasonyao
 * 
 */
public class AESUtil {

	/**
	 * this one shall be 16,24,32 byte
	 */
	private static final byte[] KEY = new byte[] { 19, 20, 38, 55, 23, 56, 78, 34, 12, 98, 112, 67, 43, 33, 15, 16 };
	/**
	 * this one shall be 16,24,32 byte
	 */
	private static final byte[] IV = new byte[] { 19, 20, 38, 55, 23, 56, 78, 34, 12, 98, 112, 67, 43, 33, 15, 16 };
	private static final String ALGORITHMS = "AES";
	private static final String FILL_TYPE = "AES/CBC/PKCS5Padding";

	private AESUtil() {
	}

	public static String encrypt(String srcText) {
		return encrypt(srcText, KEY, IV);
	}

	public static String decrypt(String encryptText) {
		return decrypt(encryptText, KEY, IV);
	}

	public static String encrypt(String srcText, byte[] key, byte[] iv) {
		String encryptText = null;
		SecretKey secretKey = new SecretKeySpec(key, ALGORITHMS);
		IvParameterSpec ivPara = new IvParameterSpec(iv);
		//SecureRandom random=new SecureRandom();
		//random.nextBytes(iv);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(FILL_TYPE);
			cipher.init(cipher.ENCRYPT_MODE, secretKey, ivPara);
			encryptText = new String(new Base64().encode(cipher.doFinal(srcText.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	public static String decrypt(String encryptText, byte[] key, byte[] iv) {
		String srcText = null;
		SecretKey secretKey = new SecretKeySpec(key, ALGORITHMS);
		IvParameterSpec ivPara = new IvParameterSpec(iv);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(FILL_TYPE);
			cipher.init(cipher.DECRYPT_MODE, secretKey, ivPara);
			srcText = new String(cipher.doFinal(new Base64().decode(encryptText.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srcText;
	}
}
