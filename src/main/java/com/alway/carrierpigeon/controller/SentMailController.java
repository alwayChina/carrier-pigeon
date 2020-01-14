package com.alway.carrierpigeon.controller;

import com.alway.carrierpigeon.commom.Result;
import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.service.GetAttribute;
import com.alway.carrierpigeon.util.SentMailUtil;
import com.alway.carrierpigeon.vo.MailAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwei
 * @date 2020-01-13 11:40
 * @desc 邮件发送接口
 **/

@RestController
public class SentMailController {

	@Autowired
	private GetAttribute getAttribute;

	@GetMapping("/sentMail")
	public Result sentMail() {
		MailInfoDto mailInfoDto = getAttribute.getAttribute();
		if (null == mailInfoDto) {
			return Result.failed("配置文件路径错误");
		}
		SentMailUtil sentMailUtil = new SentMailUtil();
		return Result.success(sentMailUtil.sendMail(mailInfoDto));

	}

	@PostMapping("/batchSentMail")
	public Result batchSentMail(@RequestBody MailAttribute mailAttribute) {
		MailInfoDto mailInfoDto = getAttribute.getAttribute(mailAttribute);
		if (null == mailInfoDto) {
			return Result.failed("配置文件路径错误");
		}
		SentMailUtil sentMailUtil = new SentMailUtil();
		return Result.success(sentMailUtil.sendMail(mailInfoDto));

	}

}
