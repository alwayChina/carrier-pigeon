package com.alway.carrierpigeon.dto;

import com.alway.carrierpigeon.entity.MatchRule;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 14:28
 * @desc 发送邮件所需的全部信息
 **/
@Data
public class MailInfoDto {
	private String myEmailAccount;
	private String myEmailPassword;
	private String myEmailSMTPHost;
	private MatchRule matchRule;
	List<String> receiveMailAccounts;
	Map<String, String> attributes;
	private String mailTemplate;
}
