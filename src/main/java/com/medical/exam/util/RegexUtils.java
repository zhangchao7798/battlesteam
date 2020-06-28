package com.medical.exam.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * . 可以匹配所有字符
 * \d 匹配0到9的所有数字
 * \D 匹配非数字
 * \s 匹配所有的空白字符。包含空格，制表符，回车符，换页符，换行符等
 * \S 匹配所有的非空白字符
 * \w 匹配所有的单词字符。包括0到9所有数字，26个英文字母和下划线
 * \W 匹配所有的非单词字符
 * </p>
 */
public class RegexUtils
{
	static final Pattern P_CHINESE = Pattern.compile("[\u4e00-\u9fa5]");
	static final Pattern P_DOMAIN= Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);

	private RegexUtils()
	{
		// 阻止实例化
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param regex 匹配的正则
	 * @相关参数： @param content 被匹配的内容
	 * @相关参数： @param groupIndex 匹配正则的分组序号
	 * @相关参数： @return 匹配后的字符串
	 * @功能描述： 获得匹配的字符串
	 */
	public static String get(String regex, String content, int groupIndex)
	{
		Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(content);
		if (matcher.find())
		{
			return matcher.group(groupIndex);
		}
		return null;
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param pattern 相关要匹配的正则表达式
	 * @相关参数： @param content 相关要检查的内容
	 * @相关参数： @return true：匹配正则。false：未匹配
	 * @功能描述： 校验一个字符串内容是否匹配指定正则
	 */
	public static boolean isPattern(String pattern, String content)
	{
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(content);
		return m.matches();
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param regex 正则
	 * @相关参数： @param content 被匹配的内容
	 * @相关参数： @return 删除后剩余的内容
	 * @功能描述： 删除第一个匹配的内容
	 */
	public static String delFirst(String regex, String content)
	{
		if (content == null)
		{
			return null;
		}
		return content.replaceFirst(regex, "");
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param regex 定位正则
	 * @相关参数： @param content 被查找的内容
	 * @相关参数： @return 删除前缀后的新内容
	 * @功能描述： 删除正则匹配到的内容之前的字符 如果没有找到，则返回原文
	 */
	public static String delPreLocation(String regex, String content)
	{
		Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(content);
		if (matcher.find())
		{
			return content.substring(matcher.end(), content.length());
		}
		return content;
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param regex 正则
	 * @相关参数： @param content 被查找的内容
	 * @相关参数： @param group 正则的分组
	 * @相关参数： @param collection 返回的集合类型
	 * @相关参数： @return 结果集
	 * @功能描述： 取得内容中匹配的所有结果
	 */
	public static <T extends Collection<String>> T findAll(String regex, String content, int group, T collection)
	{
		Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(content);
		while (matcher.find())
		{
			collection.add(matcher.group(group));
		}
		return collection;
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param StringWithNumber 带数字的字符串
	 * @相关参数： @return 返回第一个匹配的整数
	 * @功能描述： 从字符串中获得第一个整数
	 */
	public static int getFirstNumber(String StringWithNumber)
	{
		return Integer.parseInt(get("\\d+", StringWithNumber, 0));
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param ip IP地址
	 * @相关参数： @return 是否是IPV4
	 * @功能描述： 判断该字符串是否是IPV4地址
	 */
	public static boolean isIpv4(String ip)
	{
		if (LangUtils.isEmpty(ip))
		{
			return false;
		}
		String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		return Pattern.matches(regex, ip);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param email email地址，格式：qq@zuidaima.com，Wu@xxx.com.cn，xxx代表邮件服务商
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证Email
	 */
	public static boolean checkEmail(String email)
	{
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证身份证号码
	 */
	public static boolean checkIdCard(String idCard)
	{
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		return Pattern.matches(regex, idCard);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param mobile 移动、联通、电信运营商的号码段
	 * <p>
	 * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 * </p>
	 * <p>
	 * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 * </p>
	 * <p>
	 * 电信的号段：133、153、180（未启用）、189
	 * </p>
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证手机号码。支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 */
	public static boolean checkMobile(String mobile)
	{
		String regex = "(\\+\\d+)?1[34578]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}

	public static boolean checkNickname(String name)
	{
		String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
		return Pattern.matches(regex, name);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	 * <p>
	 * <b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	 * 数字之后是空格分隔的国家（地区）代码。
	 * </p>
	 * <p>
	 * <b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	 * 对不使用地区或城市代码的国家（地区），则省略该组件。
	 * </p>
	 * <p>
	 * <b>电话号码：</b>这包含从 0 到 9 的一个或多个数字
	 * </p>
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证固定电话号码
	 */
	public static boolean checkPhone(String phone)
	{
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
		return Pattern.matches(regex, phone);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param digit 一位或多位0-9之间的整数
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证整数（正整数和负整数）
	 */
	public static boolean checkDigit(String digit)
	{
		String regex = "\\-?[1-9]\\d+";
		return Pattern.matches(regex, digit);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证整数和浮点数（正整数和正浮点数）
	 */
	public static boolean checkMoney(String decimals)
	{
		String regex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
		return Pattern.matches(regex, decimals);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证空白字符
	 */
	public static boolean checkBlankSpace(String blankSpace)
	{
		String regex = "\\s+";
		return Pattern.matches(regex, blankSpace);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param chinese 中文字符
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证中文
	 */
	public static boolean checkChinese(String chinese)
	{
		String regex = "^[\u4E00-\u9FA5]+$";
		return Pattern.matches(regex, chinese);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param chinese 中文字符
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证是否包含中文
	 */
	public static boolean checkContainsChinese(String str)
	{
		Matcher m = P_CHINESE.matcher(str);
		return m.find();
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param birthday 日期，格式：1992-09-03，或1992.09.03
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证日期（年月日）
	 */
	public static boolean checkBirthday(String birthday)
	{
		String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
		return Pattern.matches(regex, birthday);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param url 格式：http://blog.csdn.net/u011794238或 http://www.csdn.net:80
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 验证URL地址
	 */
	public static boolean checkURL(String url)
	{
		String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
		return Pattern.matches(regex, url);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param url http://www.zuidaima.com/share/1550463379442688.htm ->> zuidaima.com
	 * @相关参数： @return URL的一级域名
	 * @功能描述： 获取网址URL的一级域名
	 */
	public static String getDomain(String url)
	{
		// 获取完整的域名
		Matcher matcher = P_DOMAIN.matcher(url);
		matcher.find();
		return matcher.group();
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param postcode 邮政编码
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 匹配中国邮政编码
	 */
	public static boolean checkPostcode(String postcode)
	{
		String regex = "[1-9]\\d{5}";
		return Pattern.matches(regex, postcode);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param ipAddress IPv4标准地址
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
	 */
	public static boolean checkIpAddress(String ipAddress)
	{
		String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
		return Pattern.matches(regex, ipAddress);
	}

	/**
	 * @创建时间： 2016年1月20日
	 * @相关参数： @param number 6位数字
	 * @相关参数： @return 验证成功返回true，验证失败返回false
	 * @功能描述： 匹配6位纯数字
	 */
	public static boolean check6Number(String number)
	{
		String regex = "\\d{6}";
		return Pattern.matches(regex, number);
	}

	/**
	 * 匹配年月 日期格式
	 * 
	 * @param YearAndMonth YYYY-MM 时间格式
	 * @return
	 */
	public static boolean checkYearAndMonth(String YearAndMonth)
	{
		String regex = "\\d{4}-\\d{2}";
		return Pattern.matches(regex, YearAndMonth);
	}
	
	/**
	 * 匹配登录密码  8-16位英文字母或数字
	 * 
	 * @param loginPwd 登录密码
	 * @return
	 */
	public static boolean checkLoginPwd(String loginPwd)
	{
		String regex = "[0-9A-Za-z]{8,16}";
		return Pattern.matches(regex, loginPwd);
	}

	/**
	 * 验证整数和浮点数（正负整数和正负浮点数）
	 * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDecimals(String decimals) {
		String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
		return Pattern.matches(regex,decimals);
	}

	public static void main(String[] args)
	{
		System.out.println(RegexUtils.checkYearAndMonth("2017-03-10 20:06:16"));
		System.out.println(RegexUtils.checkYearAndMonth("2017-03-10"));
		System.out.println(RegexUtils.checkYearAndMonth("2017-03"));
		System.out.println(RegexUtils.checkLoginPwd("123456erse78"));


		System.out.println(RegexUtils.checkPhone("17992183372"));
	}

}
