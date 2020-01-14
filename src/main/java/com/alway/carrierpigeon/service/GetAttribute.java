package com.alway.carrierpigeon.service;

import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.vo.MailAttribute;

/**
 * @author wangwei
 * @date 2020-01-09 14:06
 * @desc 获取变量类型
 **/
public interface GetAttribute {
	/**
	 * 这是以读配置文件的方式来注入值，默认读config/mailConfig.xml文件
	 * 通过mailConfig.xml中配置的文件路径来读取模板文件和参数文件
	 *
	 * @return MyMailAttribute
	 */
	MailInfoDto getAttribute();

	/**
	 * 通过半接口调用的方式来注入值，读传过来的config文件
	 *
	 * @param mailAttribute mailConfig.xml文件的路径以及与mailAttribute.xml文件等效的参数值
	 * @return MailInfoDto
	 */
	MailInfoDto getAttribute(MailAttribute mailAttribute);

}
