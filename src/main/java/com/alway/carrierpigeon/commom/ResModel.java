package com.alway.carrierpigeon.commom;

/**
 * 异常定义-常量
 *
 * @author :wangwei
 */

public class ResModel {

    /**----------------------------------------------------操作类--------------------------------------------------------*/
    /**
     * 操作成功
     */
    public static final String CODE_SUCCESS = "200";
    public static final String INFO_SUCCESS = "操作成功";
    /**
     * 操作失败
     */
    public static final String CODE_FAILED = "500";
    public static final String INFO_FAILED = "操作失败";
    /**-----------------------------------------------------操作类-------------------------------------------------------*/


    /**---------------------------------------------------服务/业务类-------------------------------------------------------*/
    /**
     * 业务异常
     */
    public static final String CODE_BUSINESS_EXCEPTION = "010";
    public static final String INFO_BUSINESS_EXCEPTION = "业务异常";

    /**
     * 服务异常
     */
    public static final String CODE_SERVER_EXCEPTION = "100";
    public static final String INFO_SERVER_EXCEPTION = "服务异常";
    /**---------------------------------------------------服务/业务类-------------------------------------------------------*/


    /**----------------------------------------------------请求/参数类----------------------------------------------------*/
    /**
     * HTTP请求不正确定义: 后端接口HTTP.METHOD定义与前端发起的HTTP.METHOD不符,则视为HTTP请求不正确
     */
    public static final String CODE_REQUEST_EXCEPTION = "001";
    public static final String INFO_REQUEST_EXCEPTION = "不支持当前请求方法";

    /**
     * 参数无意义empty定义: 前端请求参数无意义(null, "", []等, 视为empty)
     */
    public static final String CODE_PARAM_EMPTY = "002";
    public static final String INFO_PARAM_EMPTY = "参数无意义";
    /**-----------------------------------------------------请求/参数类----------------------------------------------------*/

    /**----------------------------------------------------登录----------------------------------------------------*/
    /**
     * 用户不存在
     */
    public static final String CODE_USER_NOT_EXIST = "421";
    public static final String INFO_USER_NOT_EXIST = "登录失败,用户不存在";

    /**
     * 用户不存在
     */
    public static final String CODE_PASSWORD_ERROR = "422";
    public static final String INFO_PASSWORD_ERROR = "登录失败,密码错误";
    /**----------------------------------------------------登录----------------------------------------------------*/
}
