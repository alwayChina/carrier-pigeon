package com.alway.carrierpigeon.service.impl;

import com.alway.carrierpigeon.constant.CommonConstants;
import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.entity.MyMailAttribute;
import com.alway.carrierpigeon.entity.MyMailConfig;
import com.alway.carrierpigeon.service.GetAttribute;
import com.alway.carrierpigeon.util.OperateFileUtil;
import com.alway.carrierpigeon.vo.MailAttribute;
import com.alway.carrierpigeon.wrapper.MailInfoWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author wangwei
 * @date 2020-01-09 14:21
 * @desc 具体实现获取邮件参数功能
 **/
@Service
public class GetAttributeImpl implements GetAttribute {
	@Override
	public MailInfoDto getAttribute() {
//		读取邮箱配置文件
		MyMailConfig myMailConfig = OperateFileUtil.readMailConfig(CommonConstants.MAIL_CONFIG_FILE_PATH);
//		读取邮件参数文件
		MyMailAttribute myMailAttribute = OperateFileUtil.readMailAttribute(myMailConfig.getMailAttributePath());
//		读取邮件模板
		String mailTemplate = OperateFileUtil.readHtml(myMailConfig.getMailTemplatePath());
		//设值
		MailInfoWrapper mailInfoWrapper = new MailInfoWrapper();
		return mailInfoWrapper.entityVO(myMailConfig, myMailAttribute, mailTemplate);
	}

	@Override
	public MailInfoDto getAttribute(MailAttribute mailAttribute) {
//		判断路径是否为空，暂不考虑路径是否正确
		if (StringUtils.isEmpty(mailAttribute.getFilePath())) {
			return null;
		}
		if (!new File(mailAttribute.getFilePath()).exists()) {
			return null;
		}
//		读取邮箱配置文件
		String filePath = mailAttribute.getFilePath();
		MyMailConfig myMailConfig = OperateFileUtil.readMailConfig(filePath);
//		读取邮件参数文件
		MyMailAttribute myMailAttribute = new MyMailAttribute();
		BeanUtils.copyProperties(mailAttribute, myMailAttribute);

//		读取邮件模板
		String mailTemplate = OperateFileUtil.readHtml(myMailConfig.getMailTemplatePath());

		//设值
		MailInfoWrapper mailInfoWrapper = new MailInfoWrapper();
		return mailInfoWrapper.entityVO(myMailConfig, myMailAttribute, mailTemplate);
	}

}
