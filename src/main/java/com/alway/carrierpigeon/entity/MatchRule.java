package com.alway.carrierpigeon.entity;

import lombok.Data;

/**
 * @author wangwei
 * @date 2020-01-09 10:40
 * @desc 邮件模板变量匹配规则
 **/
@Data
public class MatchRule {
	private String prefix;
	private String suffix;
}
