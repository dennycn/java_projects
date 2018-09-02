package security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 实现AES加密解密 keefe 2018-9-1
 */
public class AESTest {
	// 加密算法
	private static String ALGO = "AES";
	private static String ALGO_MODE = "AES/CBC/NoPadding";
	private static String akey = "B31F2A75FBF94099"; // 私钥
	private static String aiv = "1234567890123456";

	public static void main(String[] args) {
		// 创建加解密
		// AESTest aes = new AESTest();
		// 要进行加密的密码
		String password = "password^*(&( 09-8ADF";
		// 进行加密后的字符串
		try {
			String passwordEnc = encrypt(password);
			String passwordDec = decrypt(passwordEnc);
			System.out.println("原来的密码 : " + password);
			System.out.println("加密后的密码 : " + passwordEnc);
			System.out.println("解密后的原密码 : " + passwordDec);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 用来进行加密的操作
	 * 
	 * @param Data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String Data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(ALGO_MODE);
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = Data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(akey.getBytes("utf-8"), ALGO);
			IvParameterSpec ivspec = new IvParameterSpec(aiv.getBytes("utf-8"));
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			String EncStr = new sun.misc.BASE64Encoder().encode(encrypted);
			//String EncStr = new String(encrypted); 
			return EncStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用来进行解密的操作
	 * 
	 * @param encryptedData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
		try {
			byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(encryptedData);
			//byte[] encrypted1 =  encryptedData.getBytes();
			// new add 
/*			byte[] encrypted1 = null;
            if (akey != null) {
            	encrypted1 = akey[0].getBytes();
            } else {
            	encrypted1 = akey.getBytes("ASCII");
            }*/
            
			Cipher cipher = Cipher.getInstance(ALGO_MODE);
			SecretKeySpec keyspec = new SecretKeySpec(akey.getBytes("utf-8"), ALGO);
			IvParameterSpec ivspec = new IvParameterSpec(aiv.getBytes("utf-8"));

			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
