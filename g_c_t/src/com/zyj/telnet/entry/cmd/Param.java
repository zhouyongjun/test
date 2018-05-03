package com.zyj.telnet.entry.cmd;

import org.dom4j.Element;

import com.zyj.telnet.uitl.TelnetUtil;

public class Param {
	private String name;
	private String value;
	
	public void load(Element e) {
		name = TelnetUtil.getAttriValue(e, "name");
		value = TelnetUtil.getAttriValue(e, "default_value");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
