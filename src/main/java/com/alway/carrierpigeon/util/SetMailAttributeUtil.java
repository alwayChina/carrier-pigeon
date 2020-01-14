package com.alway.carrierpigeon.util;

import com.alway.carrierpigeon.dto.MailInfoDto;

import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 14:03
 * @desc 向模板邮件里面插入变量的工具类
 **/
public class SetMailAttributeUtil {
	public static String setMyAttributeOfMail(MailInfoDto mailInfoDto, String mailTemplate) {
		Map<String, String> attributes = mailInfoDto.getAttributes();
		String prefix = mailInfoDto.getMatchRule().getPrefix();
		String suffix = mailInfoDto.getMatchRule().getSuffix();
		for (Map.Entry<String, String> map : attributes.entrySet()) {
			mailTemplate = mailTemplate.replace(prefix + map.getKey() + suffix, map.getValue());
		}
		return mailTemplate;
	}
}
