package com.example.john.myapplication.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密,不可逆 Hash<br>
 * 采用MD5 算法对String 进行摘要<br>
 * 对文件流进行摘要<br>
 * 
 * @author qianjunping
 * 
 */
public class MD5Util {

	public static final String MD5 = "MD5";
	private static final char[] hexadecimal = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * 获取md5值，该方法适用于该项目
	 * @param msg
	 * @return
     */
	public static String getMD5(String msg){
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance(MD5);
			return encode(md.digest(msg.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	private static String encode(byte[] binaryData) {
		if(binaryData.length != 16) {
			return null;
		} else {
			char[] buffer = new char[32];

			for(int i = 0; i < 16; ++i) {
				int low = binaryData[i] & 15;
				int high = (binaryData[i] & 240) >> 4;
				buffer[i * 2] = hexadecimal[high];
				buffer[i * 2 + 1] = hexadecimal[low];
			}

			return new String(buffer);
		}
	}


	/**
	 * MD5 String 加密
	 * 
	 * @param input
	 * @return
	 */
	public static String MD5Hashing(String input) {
		String output = "";
		byte[] original = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			original = input.getBytes("UTF-8");
			byte[] bytes = md.digest(original);
			for (int i = 0; i < bytes.length; i++) {
				output += Integer.toHexString((bytes[i] & 0xff) + 0x100)
						.substring(1);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * 输入流进行MD5散列
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] md5(InputStream input) {
		return digest(input, MD5);
	}

	public static byte[] digest(InputStream input, String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
