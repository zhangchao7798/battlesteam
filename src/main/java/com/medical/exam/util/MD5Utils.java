package com.medical.exam.util;

import java.security.MessageDigest;

public final class MD5Utils
{

	public static String GetMD5(String str)
	{
		String word = null;
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte[] data = md5.digest();
			StringBuffer sb = new StringBuffer(data.length * 2);
			for (byte b : data)
			{
				if ((b & 0xFF) < 0x10)
				{
					sb.append("0");
				}
				sb.append(Integer.toHexString(b & 0xFF));
			}
			word = sb.toString();
		}
		catch (Exception e)
		{
			word = null;
		}
		return word;
	}

	public static String getRandomMd5(){
		return GetMD5(IDUtils.getUUID());
	}

}
