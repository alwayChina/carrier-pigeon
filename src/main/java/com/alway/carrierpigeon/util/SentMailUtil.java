package com.alway.carrierpigeon.util;

import com.alway.carrierpigeon.constant.CommonConstants;
import com.alway.carrierpigeon.dto.MailInfoDto;
import com.alway.carrierpigeon.dto.ResultDto;
import com.alway.carrierpigeon.vo.SentMailVo;
import com.alway.carrierpigeon.wrapper.SentMailWrapper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author wangwei
 * @date 2020-01-09 15:56
 * @desc 发送邮件的工具类
 **/
public class SentMailUtil {

	public List<ResultDto> sendMail(MailInfoDto mailInfoDto) {
		SentMailUtil s = new SentMailUtil();
		SentMailWrapper sentMailWrapper = new SentMailWrapper();
		SentMailVo sentMailVo = sentMailWrapper.entityVO(mailInfoDto);
		List<ResultDto> resultList = new ArrayList<>();
		for (String receiveMailAccount : mailInfoDto.getReceiveMailAccounts()) {
			ResultDto resultDto = new ResultDto();
			sentMailVo.setReceiveMail(receiveMailAccount);
			if (s.sendEmail(sentMailVo)) {
				String verificationCode = sentMailVo.getAttributes().get("verificationCode");
				System.out.println("给 " + receiveMailAccount + " 发送邮件成功,验证码为: " + verificationCode);
				resultDto.setReceiveMail(receiveMailAccount);
				resultDto.setResCode(CommonConstants.RESULT_SUCCESS);
			} else {
				System.out.println("给 " + receiveMailAccount + " 发送邮件失败");
				resultDto.setReceiveMail(receiveMailAccount);
				resultDto.setResCode(CommonConstants.RESULT_FAIL);
			}
			resultList.add(resultDto);
		}
		return resultList;
	}

	/**
	 * 发送邮件
	 *
	 * @param sentMailVo 邮件信息实体
	 * @return 发送结果
	 */
	private boolean sendEmail(SentMailVo sentMailVo) {
		// 参数配置
		Properties props = new Properties();
		// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.transport.protocol", "smtp");
		// 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.host", sentMailVo.getMyEmailSMTPHost());
		// 需要请求认证
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		try {
			Session session = Session.getInstance(props);
			MimeMessage message = createMimeMessage(session, sentMailVo);
			Transport transport = session.getTransport();
			transport.connect(sentMailVo.getMyEmailAccount(), sentMailVo.getMyEmailPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @param session    和服务器交互的会话
	 * @param sentMailVo 邮件信息实体
	 * @return MimeMessage
	 */
	private static MimeMessage createMimeMessage(Session session, SentMailVo sentMailVo) {
		String title;
		String receiveName;
		String sentName;
		MimeMessage message = new MimeMessage(session);
		if (StringUtils.isEmpty(sentMailVo.getTitle())) {
			title = "注册验证";
		} else {
			title = sentMailVo.getTitle();
		}

		if (StringUtils.isEmpty(sentMailVo.getReceiveName())) {
			receiveName = "词友";
		} else {
			receiveName = sentMailVo.getReceiveName();
		}
		if (StringUtils.isEmpty(sentMailVo.getSentName())) {
			sentName = "alway";
		} else {
			sentName = sentMailVo.getSentName();
		}
		try {
			message.setFrom(new InternetAddress(sentMailVo.getMyEmailAccount(), sentName, "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sentMailVo.getReceiveMail(), receiveName, "UTF-8"));
			message.setSubject(title, "UTF-8");
			String mailHtml = sentMailVo.getMailHtml();
			message.setContent(mailHtml, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

		return message;
	}

	public static void main(String[] args) {
		SentMailUtil sendMail = new SentMailUtil();
		MailInfoDto mailInfoDto = new MailInfoDto();
		List<ResultDto> resultList = sendMail.sendMail(mailInfoDto);
		for (ResultDto resultDto : resultList) {
			System.out.println("给 " + resultDto.getReceiveMail() + " 发送邮件 " + resultDto.getResCode());
		}
	}
}
