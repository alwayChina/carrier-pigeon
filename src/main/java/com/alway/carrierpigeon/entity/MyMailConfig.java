package com.alway.carrierpigeon.entity;

import lombok.Data;

/**
 * @author wangwei
 * @date 2020-01-09 09:35
 * @desc 自定义邮箱配置
 **/
@Data
public class MyMailConfig {
	private String myEmailAccount;
	private String myEmailPassword;
	private String myEmailSMTPHost;
	private String mailAttributePath;
	private String mailTemplatePath;
	private MatchRule matchRule;
}
