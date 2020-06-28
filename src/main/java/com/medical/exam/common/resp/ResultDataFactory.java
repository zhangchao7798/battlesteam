package com.medical.exam.common.resp;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/08
 */
public class ResultDataFactory {
    private static ResultData<?> resultData = new ResultData<>();

    public static ResultData getResultData() {
        return resultData.clone();

    }
}
