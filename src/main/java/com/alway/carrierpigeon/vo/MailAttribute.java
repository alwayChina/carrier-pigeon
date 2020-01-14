package com.alway.carrierpigeon.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 10:21
 * @desc 自定义邮件配置数据
 **/
@Data
public class MailAttribute {
	/**
	 * mailConfig.xml文件的路径
	 */
	private String filePath;
	private List<String> receiveMailAccounts;
	private Map<String, String> attributes;
}
