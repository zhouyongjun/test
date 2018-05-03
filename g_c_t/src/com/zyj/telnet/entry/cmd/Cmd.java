package com.zyj.telnet.entry.cmd;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.zyj.telnet.uitl.TelnetUtil;
	/**
	 * CMD√¸¡Ó
	 * @author zhouyongjun
	 *
	 */
public class Cmd {
	private String cmd;
	private String showName;
	private String split;
	List<String> type_names = new ArrayList<String>();
	List<Param> param_list = new ArrayList<Param>();
	public Cmd() {
	}
	public void load(Element e) throws Exception {
		showName = TelnetUtil.getAttriValue(e,"name");
		cmd =  TelnetUtil.getAttriValue(e,"cmd");
		split = TelnetUtil.getAttriValue(e, "split");
		if (split == null || split.length() == 0) {
			split = ",";
		}
		if (cmd == null || cmd.length() == 0) {
			throw new Exception("cmd["+showName+"] cmd == "+cmd +", is error...");
		}
		loadTypes(e);
		loadParams(e);
		
	}
	private void loadTypes(Element e) {
		List<Element> els = TelnetUtil.getElements(e, "type");
		if (els == null || els.size() == 0) {
			return;
		}
		for (Element subE : els) {
			String msg  = TelnetUtil.getAttriValue(subE, "name").trim();
			if (msg == null || msg.length() <= 0) {
				continue;
			}
			type_names.add(msg);
		}		
	}
	private void loadParams(Element e) {
		List<Element> els = TelnetUtil.getElements(e, "param");
		if (els == null || els.size() == 0) {
			return;
		}
		for (Element subE : els) {
			Param param = new Param();
			param.load(subE);
			param_list.add(param);
		}		
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	@Override
	public String toString() {
		return showName;
	}
	public List<Param> getParam_list() {
		return param_list;
	}
	public void setParam_list(List<Param> param_list) {
		this.param_list = param_list;
	}
	
	
	public String getSplit() {
		return split;
	}
	public void setSplit(String split) {
		this.split = split;
	}
	public List<String> getType_names() {
		return type_names;
	}
	public void setType_names(List<String> type_names) {
		this.type_names = type_names;
	}
	public String toExecCmd(Object... values) {
		StringBuffer sb = new StringBuffer();
		sb.append(cmd);
		if (type_names.size() > 0) {
			if (param_list != null  && param_list.size() > 1) {
				sb.append(split);
				for (int i=0;i<param_list.size()-1 ;i++) {
					Param p  = param_list.get(i);
					StringBuffer p_sb = new StringBuffer();
					for (char ch : p.getValue().toCharArray()) {
						p_sb.append("\\u"+Integer.toHexString(ch));
					}
					sb.append(p_sb.toString()).append(split);
				}
			}	
			if (values != null && values.length > 0) {
				for (Object obj : values) {
					sb.append(obj).append(split);
				}	
				sb.deleteCharAt(sb.lastIndexOf(split));
			}
			
		}else {
			if (param_list != null  && param_list.size() > 0) {
				sb.append(split);
				for (Param p : param_list) {
					sb.append(p.getValue()).append(split);
				}
				sb.deleteCharAt(sb.lastIndexOf(split));
			}	
		}
		
		return sb.toString();
	}
}
