package com.medical.exam.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public final class IDUtils
{

	public static String getUUID()
	{
		return UUID.randomUUID().toString();
	}

	public static String get4Number()
	{
		String rand = "";
		while (rand.length() < 4)
		{
			rand += new Random().nextInt(10);
		}
		return rand;
	}

	// 由于数字的0，1，2易和字母的o，l, z混淆
	public static String generateShortUuid()
	{
		String[] chars = new String[] {
				"a",
				"b",
				"c",
				"d",
				"e",
				"f",
				"g",
				"h",
				"i",
				"j",
				"k",
				"m",
				"n",
				"p",
				"q",
				"r",
				"s",
				"t",
				"u",
				"v",
				"w",
				"x",
				"y",
				"0",
				"1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9" };
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 6; i++)
		{
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 33]);
		}
		return shortBuffer.toString();
	}

	public static String genNewRandomCode(int codeLen)
	{
		Random randomCode = new Random();
		String strCode = "";
		while (codeLen > 0)
		{
			int charCode = randomCode.nextInt(9);
			strCode += charCode;
			codeLen--;
		}
		return strCode;
	}

	public static String gen14NewRandomCode()
	{
		StringBuffer buf = new StringBuffer();
		Calendar calc = Calendar.getInstance();
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		buf.append(fmt.format(calc.getTime()));
		return buf.toString();
	}

	/**
	 * 生成睿眼专用序列号,用于相机上传
	 * @param patientName
	 * @return
	 */
	public static String getSerialNumber(String patientName){
		SnowflakeIdWorker snowflakeIdWorker=SpringUtil.getBean(SnowflakeIdWorker.class);
		return "ry"+patientName+"@"+snowflakeIdWorker.nextId();
	}

	public static String getSerialNumber(){
		SnowflakeIdWorker snowflakeIdWorker=SpringUtil.getBean(SnowflakeIdWorker.class);
		return "ry"+snowflakeIdWorker.nextId();
	}




	public static void main(String[] args){
		System.out.println(generateShortUuid());

	}

}
