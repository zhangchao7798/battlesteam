package com.medical.exam.web;


import com.medical.exam.common.resp.ResultData;
import com.medical.exam.common.resp.ResultDataType;

/**
 * controller基类 提供一些便捷方法
 * @author Wu
 * 2018年5月20日
 */
public class BaseController
{
	/**
	 * 缺失必要参数响应
	 * 
	 * @param resultData
	 * @return
	 */
	protected <T> ResultData<T> requireData(ResultData<T> resultData)
	{
		resultData.setStatus(ResultDataType.REQUIRED.getStatus());
		resultData.setMsg(ResultDataType.REQUIRED.getDesc());
		resultData.setData(null);
		return resultData;
	}

	/**
	 * 错误响应
	 * 
	 * @param resultData
	 * @return
	 */
	protected <T> ResultData<T> errorData(ResultData<T> resultData)
	{
		resultData.setStatus(ResultDataType.ERROR.getStatus());
		resultData.setMsg(ResultDataType.ERROR.getDesc());
		resultData.setData(null);
		return resultData;
	}

	/**
	 * 成功响应
	 * 
	 * @param resultData
	 * @return
	 */
	protected <T> ResultData<T> normalData(ResultData<T> resultData)
	{
		resultData.setStatus(ResultDataType.NORMAL.getStatus());
		resultData.setMsg(ResultDataType.NORMAL.getDesc());
		return resultData;
	}
}
