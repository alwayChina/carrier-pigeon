package com.alway.carrierpigeon.util;

import com.alway.carrierpigeon.entity.MatchRule;
import com.alway.carrierpigeon.entity.MyMailAttribute;
import com.alway.carrierpigeon.entity.MyMailConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020-01-09 09:06
 * @desc 读取文件工具类
 **/

public class OperateFileUtil {

	/**
	 * 读 html文件
	 *
	 * @param filePath 文件路径
	 * @return 返回一个字符串
	 */
	public static String readHtml(String filePath) {
		StringBuilder res = new StringBuilder();
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				res.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res.toString();
	}

	public static MyMailAttribute readMailAttribute(String filePath) {
		MyMailAttribute myMailAttribute = new MyMailAttribute();
		Document document = getDocument(filePath);
		if (document != null) {
//			获取receiveMailAccounts下的所有receiveAccount
			List<String> receiveMailAccounts = new ArrayList<>(5);
			NodeList mailNodeList = document.getElementsByTagName("receiveMailAccounts");
			for (int i = 0; i < mailNodeList.getLength(); i++) {
				//解析receiveMailAccounts节点的子节点
				NodeList receiveAccountList = mailNodeList.item(i).getChildNodes();
				for (int t = 0; t < receiveAccountList.getLength(); t++) {
					//区分出text类型的node以及element类型的node
					if (receiveAccountList.item(t).getNodeType() == Node.ELEMENT_NODE) {
						receiveMailAccounts.add(receiveAccountList.item(t).getTextContent());
					}
				}
			}
			myMailAttribute.setReceiveMailAccounts(receiveMailAccounts);

//			获取attributes下的所有attribute
			Map<String, String> attributes = new HashMap<>(5);
			NodeList attributeNodeList = document.getElementsByTagName("attribute");
			for (int i = 0; i < attributeNodeList.getLength(); i++) {
//				解析attributeNodeList节点的子节点
				NodeList attributeList = attributeNodeList.item(i).getChildNodes();
				String key = null;
				String value = null;
				for (int t = 0; t < attributeList.getLength(); t++) {
//					区分出text类型的node以及element类型的node
					if (attributeList.item(t).getNodeType() == Node.ELEMENT_NODE) {
						if ("key".equals(attributeList.item(t).getNodeName())) {
							key = attributeList.item(t).getTextContent();
						} else {
							value = attributeList.item(t).getTextContent();
						}
						attributes.put(key, value);
					}
				}
			}
			myMailAttribute.setAttributes(attributes);
		}
		return myMailAttribute;
	}

	public static MyMailConfig readMailConfig(String filePath) {
		MyMailConfig myMailConfig = new MyMailConfig();
		Document document = getDocument(filePath);
		if (document != null) {
			myMailConfig.setMyEmailAccount(getValueByTagName(document, "myEmailAccount"));
			myMailConfig.setMyEmailPassword(getValueByTagName(document, "myEmailPassword"));
			myMailConfig.setMyEmailSMTPHost(getValueByTagName(document, "myEmailSMTPHost"));
			myMailConfig.setMailAttributePath(getValueByTagName(document, "mailAttributePath"));
			myMailConfig.setMailTemplatePath(getValueByTagName(document, "mailTemplatePath"));
			MatchRule matchRule = new MatchRule();
			matchRule.setPrefix(getValueByTagName(document, "prefix"));
			matchRule.setSuffix(getValueByTagName(document, "suffix"));
			myMailConfig.setMatchRule(matchRule);
		}
		return myMailConfig;
	}

	private static Document getDocument(String filePath) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
//		    创建文档对象
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			return documentBuilder.parse(new File(filePath));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getValueByTagName(Document document, String nodeName) {
		NodeList nodeList = document.getElementsByTagName(nodeName);
		Node classNode = nodeList.item(0).getFirstChild();
		return classNode.getNodeValue();
	}


//	public static void main(String[] args) {
////		String path = "files/mail.html";
////		readHtml(path);
//
//		String filePath = "config/mailConfig.xml";
//		MyMailConfig myMailConfig = readMailConfig(filePath);
//		System.out.println(myMailConfig);
//		System.out.println(readMailAttribute(myMailConfig.getMailAttributePath()));
//	}
}
