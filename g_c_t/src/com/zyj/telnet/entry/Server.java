package com.zyj.telnet.entry;

import java.util.Map;

import com.zyj.telnet.entry.cmd.Cmd;
import com.zyj.telnet.entry.cmd.Telnet;
import com.zyj.telnet.entry.db.DaoData;

public class Server implements DaoData{
	private int id;//服务器id
	private String name;// 服务器名称
	private String telnet_host;//telnet地址
	private int telnet_port;//telnet端口号
	Telnet telnet = new Telnet(this);
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getTelnet_host() {
		return telnet_host;
	}

	public void setTelnet_host(String telnet_host) {
		this.telnet_host = telnet_host;
	}

	public int getTelnet_port() {
		return telnet_port;
	}

	public void setTelnet_port(int telnet_port) {
		this.telnet_port = telnet_port;
	}

	@Override
	public void loadFromData(Map<String, Object> datas) {
		id = (Integer) datas.get(SERVER_ID);
		name = (String) datas.get(SERVER_NAME);
		telnet_host = (String)datas.get(SERVER_TELNET_HOST);
		telnet_port = (Integer)datas.get(SERVER_TELNET_PORT);
	}
	
	@Override
	public void saveToData(Map<String, Object> datas) {
		
	}

	public String toString() {
		return id + ":"+name;
	}

	public ExecuteResult cmd_telnet(Cmd cmd,Object... values) {
		telnet.disconect();
		if(!telnet.connection(telnet_host, telnet_port)) {
			return ExecuteResult.createFailResult(toString()+"链接失败，速速去沟通吧！");
		}
		return telnet.execCmd(cmd,values);
	}

	public void close() {
		if (telnet.isConnect()){
			telnet.disconect();
		}
	}
}
