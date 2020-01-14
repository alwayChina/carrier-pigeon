package com.alway.carrierpigeon.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 10:21
 * @desc 自定义邮件配置数据
 **/
@Data
public class MyMailAttribute {
	private List<String> receiveMailAccounts;
	private Map<String, String> attributes;
}
