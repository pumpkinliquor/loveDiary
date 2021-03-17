package com.plushih.common.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @PackageName	: com.plushih.common.utils
 * @ClassName	: AesCryptoUtils.java
 * @Date		: 2021. 1. 14. 
 * @author		: dev.yklee
 * @Description	: 암호화 유틸
 */
public class AesCryptoUtils {
	
	private static final String TRANSFORM = "AES/ECB/PKCS5Padding";
	private static final String STATIC_KEY = "swatch_crypto_ps";  // must be 16bytes
//	private static final String STATIC_KEY = "aigo_crypto_code";  // must be 16bytes
	
	/**
	 * @ClassName	: AesCryptoUtils.java
	 * @Method		: encrypt
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 암호화
	 */
	public static String encrypt(String plainText) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		
		byte[] raw = AesCryptoUtils.STATIC_KEY.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance(TRANSFORM);
		
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(plainText.getBytes());
		
		return asHex(encrypted);
	}
	
	/**
	 * @ClassName	: AesCryptoUtils.java
	 * @Method		: decrypt
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 복호화
	 */
	public static String decrypt(String cipherText) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		
		byte[] raw = AesCryptoUtils.STATIC_KEY.getBytes("UTF-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance(TRANSFORM);
		
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] original = cipher.doFinal(fromString(cipherText));
		String originalString = new String(original);
		
		return originalString;
	}
	
	/**
	 * @ClassName	: AesCryptoUtils.java
	 * @Method		: encryptOnly
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 단방향 암호화
	 */
	public static String encryptOnly(String plainText) throws Exception {
		String output = "";
		
		StringBuffer sb = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		md.update(plainText.getBytes());
		byte[] msgb = md.digest();
		
		for (int i = 0; i < msgb.length; i++) {
			byte temp = msgb[i];
			String str = Integer.toHexString(temp & 0xFF);
			while (str.length() < 2) {
			    str = "0" + str;
			}
			str = str.substring(str.length() - 2);
			sb.append(str);
		}
		
		output = sb.toString();
		
		return output;
	}
	
	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}
	
	private static byte[] fromString(String hex) {
		int len = hex.length();
		byte[] buf = new byte[((len + 1) / 2)];
		
		int i = 0, j = 0;
		if ((len % 2) == 1)
			buf[j++] = (byte) fromDigit(hex.charAt(i++));
		while (i < len) {
			buf[j++] = (byte) ((fromDigit(hex.charAt(i++)) << 4) | fromDigit(hex.charAt(i++)));
		}
		return buf;
	}
	
	private static int fromDigit(char ch) {
		if (ch >= '0' && ch <= '9')
			return ch - '0';
		if (ch >= 'A' && ch <= 'F')
			return ch - 'A' + 10;
		if (ch >= 'a' && ch <= 'f')
			return ch - 'a' + 10;
		throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
	}
}