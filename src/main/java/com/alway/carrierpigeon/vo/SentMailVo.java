package com.alway.carrierpigeon.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 16:46
 * @desc
 **/
@Data
public class SentMailVo {
	private String myEmailAccount;
	private String myEmailPassword;
	private String myEmailSMTPHost;
	private String sentName;
	private String receiveName;
	private String title;
	private String receiveMail;
	private String mailHtml;
	private Map<String, String> attributes;
}
