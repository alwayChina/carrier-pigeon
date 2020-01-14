package com.alway.carrierpigeon.dto;

import lombok.Data;

/**
 * @author wangwei
 * @date 2020-01-13 08:32
 * @desc 结果
 **/
@Data
public class ResultDto {
	private String receiveMail;
	/**
	 * 结果代码，SUCCESS；成功；FAIL：失败。
	 */
	private String resCode;
}
