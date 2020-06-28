
package com.medical.exam.common.resp;
/**
 * 通用泛型响应类型
 * @author Wu
 * 2018年5月20日
 */
public enum ResultDataType
{
	/**
	 * normal
	 */
	NORMAL(0, "处理成功"),
	/**
	 * error
	 */
	ERROR(1, "请稍后再尝试"),
	/**
	 * required
	 */
	REQUIRED(2, "缺失必要参数");

	private final int status;
	private final String desc;

	private ResultDataType(int status, String desc)
	{
		this.status = status;
		this.desc = desc;
	}

	public int getStatus()
	{
		return status;
	}

	public String getDesc()
	{
		return desc;
	}

	@Override
    public String toString()
	{
		return status + "";
	}

}
