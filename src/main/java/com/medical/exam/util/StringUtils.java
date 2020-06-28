package com.medical.exam.util;


import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StringUtils extends org.apache.commons.lang3.StringUtils
{

	public static boolean notEquals(String str1, String str2)
	{
		return !equals(str1, str2);
	}

	public static boolean notEqualsIgnoreCase(String str1, String str2)
	{
		return !equalsIgnoreCase(str1, str2);
	}

	public static boolean isAnyBlank(String... css)
	{
		if (ArrayUtils.isEmpty(css))
		{
			return true;
		}
		for (final String cs : css)
		{
			if (isBlank(cs))
			{
				return true;
			}
		}
		return false;
	}

	public static String getAnyBlank(String[] strName, Object[] objValue)
	{
		if (objValue == null || strName == null || objValue.length - strName.length != 0)
		{
			return null;
		}
		for (int i = 0; i < objValue.length; i++)
		{
			if (objValue[i] == null || StringUtils.isBlank(String.valueOf(objValue[i])))
			{
				return strName[i];
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * formatName("典韦") = 典*
	 * formatName("司马懿") = 司**
	 * formatName("诸葛孔明")诸***
	 * </pre>
	 */
	public static String formatName(String name)
	{
		if (StringUtils.isBlank(name))
		{
			return null;
		}
		int length = name.length() - 1;
		String format = name.substring(0, 1);
		for (int i = 0; i < length; i++)
		{
			format += "*";
		}
		return format;
	}

	/**
	 * 如王大牛,返回王医生
	 * @param name
	 * @return
	 */
	public static String getDoctorName(String name)
	{
		if (StringUtils.isBlank(name))
		{
			return null;
		}
		String surname=name.substring(0,1);

		return surname+"医生";
	}

    /**
     * 获取商户眼镜店名称
     * @param name
     * @return
     */
    public static String getMerchantShopName(String name)
    {
        if (StringUtils.isBlank(name))
        {
            return null;
        }

        return name+"眼镜店";
    }

	/**
	 * <pre>
	 * formatCardId("140321198905240013") = 1403211989****0013
	 * </pre>
	 */
	public static String formatCardId(String cardId)
	{
		if (StringUtils.isBlank(cardId))
		{
			return null;
		}
		if (!RegexUtils.checkIdCard(cardId))
		{
			throw new RuntimeException(cardId + "身份证正则不匹配");
		}
		return cardId.substring(0, cardId.length() - 8) + "****" + cardId.substring(cardId.length() - 4, cardId.length());
	}

	/**
	 * <pre>
	 * formatPhone("15168445176") = 151****5176
	 * </pre>
	 */
	public static String formatPhone(String phone)
	{
		if (StringUtils.isBlank(phone))
		{
			return null;
		}
		if (!RegexUtils.checkPhone(phone))
		{
			throw new RuntimeException(phone + "号码格式正则不匹配");
		}
		return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4, phone.length());
	}

	public static String createLinkString(Map<String, String> params)
	{

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1)
			{// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			}
			else
			{
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

    /**
     * 首字母转小写
     * @param s
     * @return
     */
	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0))) {
			return s;
		}
		else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

    /**
     * 首字母转大写
     * @param s
     * @return
     */
	public static String toUpperCaseFirstOne(String s){
		if(Character.isUpperCase(s.charAt(0))) {
			return s;
		}
		else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 是否为正整数
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str) {
		return str.matches("[0-9]+");
	}

	/**
	 * 如果为NULL输出空字符串
	 * @param s .
	 * @return .
	 */
	public static String getNonNullStr(String s){
		if(isBlank(s)) {
			return "";
		}
		return s;
	}
}
