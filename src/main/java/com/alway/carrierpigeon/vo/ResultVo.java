package com.alway.carrierpigeon.vo;

import lombok.Data;

/**
 * @author wangwei
 * @date 2020-01-13 08:46
 * @desc 回调接口返回结果
 **/
@Data
public class ResultVo {
	private String code;
	private Object data;
}
