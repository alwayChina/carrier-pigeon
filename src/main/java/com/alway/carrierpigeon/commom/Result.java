package com.alway.carrierpigeon.commom;


import lombok.Data;

import java.io.Serializable;

/**
 * @author :wangwei
 * @desc ：项目统一响应结果类
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4559672604634663976L;
    /**
     * 返回码
     */
    private String statusCode;
    /**
     * 返回基础信息
     */
    private String statusMessage;

    /**
     * 返回数据
     */
    private T data;


    /**------------------------------------------------------操作类方法-----------------------------------------------------**/
    /**
     * 操作成功 - 构造方法
     *
     * @return
     */
    public static <T> Result success() {
        return new Result(ResModel.CODE_SUCCESS, ResModel.INFO_SUCCESS);
    }

    /**
     * 操作成功 - 构造方法
     *
     * @param data
     * @return
     */
    public static <T> Result success(Object data) {
        return new Result(ResModel.CODE_SUCCESS, ResModel.INFO_SUCCESS, data);
    }

    /**
     * 操作失败 - 构造方法
     *
     * @return
     */
    public static <T> Result failed() {
        return new Result(ResModel.CODE_FAILED, ResModel.INFO_FAILED);
    }

    /**
     * 操作失败 - 构造方法 自定义返回基础信息
     *
     * @return
     */
    public static <T> Result failed(String statusMessage) {
        return new Result(ResModel.CODE_FAILED, statusMessage);
    }

    /**
     * 操作是否成功 - 构造方法
     *
     * @return
     */
    public static <T> Result isSuccess(boolean isSuccess, String errorMessage, T data) {
        return isSuccess ? Result.<T>success(data) : Result.<T>failed(errorMessage);
    }
    /**------------------------------------------------------操作类方法-----------------------------------------------------**/


    /**----------------------------------------------------服务/业务类方法----------------------------------------------------**/
    /**
     * 服务异常 - 构造方法
     *
     * @return
     */
    public static <T> Result serverException() {
        return new Result(ResModel.CODE_SERVER_EXCEPTION, ResModel.INFO_SERVER_EXCEPTION);
    }

    /**
     * 服务异常 - 构造方法 自定义返回基础信息
     *
     * @return
     */
    public static <T> Result serverException(String statusMessage) {
        return new Result(ResModel.CODE_SERVER_EXCEPTION, statusMessage);
    }

    /**
     * 业务异常 - 构造方法
     *
     * @return
     */
    public static <T> Result businessException() {
        return new Result(ResModel.CODE_BUSINESS_EXCEPTION, ResModel.INFO_BUSINESS_EXCEPTION);
    }

    /**
     * 业务异常 - 构造方法 自定义返回基础信息
     *
     * @param statusMessage
     * @return
     */
    public static <T> Result businessException(String statusMessage) {
        return new Result(ResModel.CODE_BUSINESS_EXCEPTION, statusMessage);
    }

    /**
     * 参数无意义 - 构造方法
     *
     * @return
     */
    public static <T> Result<T> paramsEmpty() {
        return new Result(ResModel.CODE_PARAM_EMPTY, ResModel.INFO_PARAM_EMPTY);
    }
    /**----------------------------------------------------服务/业务类方法----------------------------------------------------**/


    /**-----------------------------------------------------自定义类方法------------------------------------------------------**/
    /**
     * 无参构造方法 - 构造方法
     */
    public Result() {
        statusCode = "";
        statusMessage = "";
    }

    /**
     * 自定义返回码, 返回基础信息 - 构造方法
     *
     * @param statusCode
     * @param statusMessage
     */
    public Result(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /**
     * 自定义返回码, 返回基础信息, 返回数据 - 构造方法
     *
     * @param statusCode    状态码
     * @param statusMessage 状态信息
     * @param data          数据
     */
    public Result(String statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    /**
     * 自定义返回码, 返回基础信息 - Set方法
     *
     * @param statusCode
     * @param statusMessage
     */
    public void setCodeInfo(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /**------------------------------------------------------自定义类方法----------------------------------------------------**/

}
