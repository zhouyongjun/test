package com.zyj.telnet.uitl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class TelnetUtil {
	
	

	/**
	 * ��ȡ�ļ���Docment����
	 * @param fileName
	 * @return
	 */
	public static Document load(String fileName) {
		try {
		File file = new File(fileName);
		SAXReader reader = new SAXReader();
			return reader.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ȡָ�����ֵ�List �ڵ���
	 * @param baseE
	 * @param elementName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Element e,String elementName) {
		return e.elements(elementName);
	}
	/**
	 * ��ȡָ�����ֵĵ����ڵ�
	 * @param baseE
	 * @param elementName
	 * @return
	 */
	public static Element getElement(Element e,String elementName) {
		return e.element(elementName);
	}

	public static String getAttriValue(Element e, String attrName) {
		return e.attributeValue(attrName);
	}

	public static void SetCompomentBound(Component component,int width,int hight) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int HIGHT = screenSize.height;
		int WIDTH = screenSize.width;
		component.setBounds(new Rectangle((WIDTH - width) / 2, (HIGHT - hight) / 2, width,hight));
	}
	
}
