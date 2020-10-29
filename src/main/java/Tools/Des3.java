package Tools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * 3DES加密工具
 *
 */
public class Des3 {
	
	private static final String shop_3deskey = CommonUtil.getProperity(CommonUtil.config_file, "shop_3deskey");
	
	// 密钥
	private static String secretKey = "5462249415ed84d7768ab5ae2e2f0abd";
	// private static String secretKey = "";
	// 向量
	private static String iv = "01234567";
	// 加解密统丄1�7使用的编码方弄1�7
	private static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, String key) throws Exception {
		Key deskey = null;
		if (key == null) {
			key = secretKey;
		}
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText, String key)
			throws Exception {
		Key deskey = null;
		if (key == null) {
			key = secretKey;
		}
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

		return new String(decryptData, encoding);
	}

	// wifi01234567899876543210
	public static void main(String[] args) throws Exception {

		Des3 aa = new Des3();
//		String strr="u4FbHTqEPBIxRJpe9jPqj2T/vZa6LN2Yn/WCU2jotK1rnUrQEreNc492HaRIRdW0iullFZAt3R4MHd9/yzBRgEqcrEwN97aIxT7eOkK2po36qQZDuJEbvjAOhHzQzjA5hWyNWIoZi4MBCiSSa09WnNmKxsnSbx6eX5dKyAeGkip7sTQOSYfUBBaIjPeqnXfLp6975su0dgHUtTrOPBeGKUGr9hwppg43zTE2OqfkJCZvxF18Dp3GMibqfFl76ga0/25NuAL7aaghmnisTr82H9HEEy9Wt5yxL+mPLu77iLjTcMcE8++MDWM3o6D9ngPKItncc+4FF6yPFAmi5zMhrLLetj8otVSbuQyIlagJ0RH9AAZPyQLf5ef3O1REgYqvmPKo7OF3bYw6PqjvYqf7HlYvOAeL8gpt4IUniCfZiBnflioPni1Sc8HwB0e78yY8DVSWqohZCrdXhegKeIK/ClBGzeA9bF7y2SwF9hywHd3o5dld2l5hSNeVzldyftCbIl4+5splwGAml6D105y3A9pljVr0TICZh8u3LKRdUI46aU08wMVbB1J6xnEnsbaA7y8Z5UtQkDI=";
//		String ss= aa.decode(strr, secretKey);
//		System.out.println("parma = " + shop_3deskey);
		String ss1= aa.decode("T+8t0OLuOfM=", "jEdASkf0SvJA4PAGPWBcwfKI");
		System.out.println("de = " + ss1);
				
				
		 
	}
}
