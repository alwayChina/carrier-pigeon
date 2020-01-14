package com.alway.carrierpigeon.wrapper;

import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.vo.SentMailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * @author wangwei
 * @date 2020-01-09 17:44
 * @desc
 **/
public class SentMailWrapper {
	public SentMailVo entityVO(MailInfoDto mailInfoDto) {
		SentMailVo paramVO = new SentMailVo();
		BeanUtils.copyProperties(mailInfoDto, paramVO);
		paramVO.setMailHtml(mailInfoDto.getMailTemplate());
		String sentName = mailInfoDto.getAttributes().get("sentName");
		String receiveName = mailInfoDto.getAttributes().get("receiveName");
		String title = mailInfoDto.getAttributes().get("title");
		if (StringUtils.isEmpty(title)) {
			title = "注册验证";
		}
		if (StringUtils.isEmpty(sentName)) {
			sentName = "alway";
		}
		if (StringUtils.isEmpty(receiveName)) {
			receiveName = "词友";
		}
		paramVO.setSentName(sentName);
		paramVO.setReceiveName(receiveName);
		paramVO.setTitle(title);
		return paramVO;
	}

}
