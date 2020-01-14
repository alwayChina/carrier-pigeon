package com.alway.carrierpigeon.wrapper;

import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.dto.ResultDto;
import com.alway.carrierpigeon.entity.MyMailAttribute;
import com.alway.carrierpigeon.entity.MyMailConfig;
import com.alway.carrierpigeon.util.OperateFileUtil;
import com.alway.carrierpigeon.util.SentMailUtil;
import com.alway.carrierpigeon.util.SetMailAttributeUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author wangwei
 * @date 2020-01-09 14:31
 * @desc 包装类
 **/
@AllArgsConstructor
public class MailInfoWrapper {
	public MailInfoDto entityVO(MyMailConfig myMailConfig, MyMailAttribute myMailAttribute, String mailTemplate) {
		MailInfoDto paramVO = new MailInfoDto();
		BeanUtils.copyProperties(myMailConfig, paramVO);
		BeanUtils.copyProperties(myMailAttribute, paramVO);
		paramVO.setMailTemplate(SetMailAttributeUtil.setMyAttributeOfMail(paramVO, mailTemplate));
		return paramVO;
	}

	public static void main(String[] args) {
		String filePath = "config/mailConfig.xml";
		MyMailConfig myMailConfig = OperateFileUtil.readMailConfig(filePath);
		MyMailAttribute myMailAttribute = OperateFileUtil.readMailAttribute(myMailConfig.getMailAttributePath());
		String mailTemplate = OperateFileUtil.readHtml(myMailConfig.getMailTemplatePath());
		MailInfoWrapper mailInfoWrapper = new MailInfoWrapper();
		MailInfoDto mailInfoDto = mailInfoWrapper.entityVO(myMailConfig, myMailAttribute, mailTemplate);
		SentMailUtil sentMailUtil = new SentMailUtil();
		List<ResultDto> resultList = sentMailUtil.sendMail(mailInfoDto);
		for (ResultDto resultDto : resultList) {
			System.out.println("给 " + resultDto.getReceiveMail() + " 发送邮件 " + resultDto.getResCode());
		}
	}
}
