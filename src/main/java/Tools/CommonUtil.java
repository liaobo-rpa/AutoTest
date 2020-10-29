package Tools;

import net.sf.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

public class CommonUtil {
	public static String config_file = "config.properties";
	
	public static String getProperity(String config_file, String properity_name)
	{
		Properties pro = new Properties();	
		try {
			pro.load(new FileInputStream(config_file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro.getProperty(properity_name).trim();
	}
	
	public static String RSAEncipher3(String randomKey, 
			String key1, Object value1, 
			String key2, Object value2, 
			String key3, Object value3) 
	{
		Map<String, Object> RSAMap = new HashMap<String, Object>();
		RSAMap.put("key", randomKey);
		RSAMap.put(key1, value1);
		RSAMap.put(key2, value2);
		RSAMap.put(key3, value3);
		String param = JSONObject.fromObject(RSAMap).toString();
		
		return param;
	}
	
	public static String RSAEncipher2(String randomKey, 
			String key1, Object value1, 
			String key2, Object value2) 
	{
		Map<String, Object> RSAMap = new HashMap<String, Object>();
		RSAMap.put("key", randomKey);
		RSAMap.put(key1, value1);
		RSAMap.put(key2, value2);
		String param = JSONObject.fromObject(RSAMap).toString();
		
		return param;
	}
	
	public static String createSignature(String...args)
	{
		String signature = "";
		
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) 
		{
			data.add(args[i] != null? args[i] : "");
		}
       Collections.sort(data);
       
       // ������ƴ�ӳ�һ���ַ�
       StringBuffer paicString= new StringBuffer();
       for(int i=0; i<data.size(); i++)
       {
         paicString=paicString.append(data.get(i));
       }
//       System.out.println(paicString);
       
		try {
			  MessageDigest md = null;
		      md = MessageDigest.getInstance("SHA-1");
		       // �������ַ�ƴ�ӳ�һ���ַ����sha1����
		       byte[] digest = md.digest(paicString.toString().getBytes());
		       // ��sha1���ܺ���ַ�ǩ��
		       signature = ThreeDes.bytesToHexString(digest);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return signature;
	}
	
	public static String getValueFromBody(String bodyStr, String randomKey, String keyName)
	{
//		String bodyStr = "GuvnyNnfEKY8GWQr38WLYkbPhxTNUl2HwnXB3vHSu/nfPu0EXxCLW7N6MZb75d26gRpjMIBVXnrypZszN8Zbh1Vqfa+PWKslTm+6wc3lYl6gXf1iUWz0AlgT4/oNgdPgwzVT1NF0R5bbzlDLhVZBFg==";
//		String randomKey = "uEahZtXcx86ICxngcJg62yr0";
		String value = "";
		
		try {
			bodyStr = Des3.decode(bodyStr, randomKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(bodyStr);
		
		Map<String, Object> bodyMap = (Map<String, Object>) JSONObject.fromObject(bodyStr);
		value = (String) bodyMap.get(keyName);
//		System.out.println(safecode);
		return value;
	}
	
	public static Object getValueFromBody2(String bodyStr, String randomKey, String keyName)
	{
//		String bodyStr = "GuvnyNnfEKY8GWQr38WLYkbPhxTNUl2HwnXB3vHSu/nfPu0EXxCLW7N6MZb75d26gRpjMIBVXnrypZszN8Zbh1Vqfa+PWKslTm+6wc3lYl6gXf1iUWz0AlgT4/oNgdPgwzVT1NF0R5bbzlDLhVZBFg==";
//		String randomKey = "uEahZtXcx86ICxngcJg62yr0";
		Object value;
		
		try {
			bodyStr = Des3.decode(bodyStr, randomKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(bodyStr);
		
		Map<String, Object> bodyMap = (Map<String, Object>) JSONObject.fromObject(bodyStr);
		value = bodyMap.get(keyName);
//		System.out.println(safecode);
		return value;
	}

	public static void main(String args[])
	{
		System.out.println("Testing");
	}
	
}
