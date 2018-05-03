package com.zyj.telnet.entry.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.net.telnet.TelnetClient;

import com.zyj.TelnetMain;
import com.zyj.telnet.TelnetManager;
import com.zyj.telnet.entry.ExecuteResult;
import com.zyj.telnet.entry.Server;
import com.zyj.telnet.log.AppLog;
/**
	 * 本地telent链接
	 * @author zhouyongjun
	 *
	 */
public class Telnet {
	Server server;
	TelnetClient client;//telnet链接类
	PrintStream pw;//写流
	BufferedReader br;//读流
	public Telnet(Server server) {
		this.server = server; 
	}
	/**
	 * 从服务器读取命令
	 */
	public ExecuteResult receiveMsgFromServer(Cmd cmd) {
		String line = null;
		String msg = "成功！";
		 try {
			 boolean isTrue = true;
			while ((line = br.readLine()) != null) {
				AppLog.info(server,toString()+line);
				if (line.contains("find result:")) {
					int index = line.indexOf(":");
					if (index < 0) {
						msg ="查询失败";
					}else {
						msg = line.substring(line.indexOf(":")+1);
					}
					TelnetMain.mainFrame.setjtextFieldValue(cmd.getParam_list().size()-1,msg);
					
				}
				if (line.toLowerCase().contains("error") || line.toLowerCase().contains("exception")) {
					isTrue = false;
					msg = "失败，感觉都不么么哒了！";
				}
				if (line.contains(TelnetManager.NOT_CMD_SIGN)) {
					isTrue = false;
					msg = "失败，命令不存在，感觉都不么么哒了！";
				}
				if (line.trim().equals(TelnetManager.TELNET_END_SIGN)) {
					break;
				}
			 }
			if (isTrue) {
				return ExecuteResult.createSuccResult(msg);
			}else {
				return ExecuteResult.createFailResult(msg);
			}
		} catch (Exception e) {
			AppLog.error(server,"receiveMsgFromServer["+line+"] is error",e);
			return ExecuteResult.createFailResult("接收反馈信息报错，感觉都不么么哒了！");
		}
	}
	
	/**
	 * 向服务器写命令
	 * @param cmd
	 */
	public ExecuteResult sendCmdToServer(Cmd cmd,Object... values) {
		try {
			AppLog.info(server,"telnet send cmd :" + cmd.toExecCmd(values));
			pw.println(cmd.toExecCmd(values));
			pw.flush();
			return ExecuteResult.createSuccResult("成功");
		} catch (Exception e) {
			AppLog.error(server,toString()+" sendCmdToServer["+cmd+"] is error...",e);
//			SSHMain.mainFrame.addErrorResultMsg(server,"保存数据命令向服务器发送信息报错，执行["+cmd.getShowName()+"失败，请尽快质询相关服务器人员。。。");
//			server.setTelnetReturnSucc(false);
			return ExecuteResult.createFailResult("失败");
		}
	}
	/**
	 * 执行命令，包括发送和接受消息
	 * @param cmd
	 */
	public ExecuteResult execCmd(Cmd cmd,Object... values) {
		ExecuteResult result = sendCmdToServer(cmd,values);
		if (!result.isSucc()) {
			return ExecuteResult.createFailResult("执行【"+cmd.getShowName()+"】失败，感觉都不么么哒了！");
		}
		result = receiveMsgFromServer(cmd);
		if (result.isSucc()) {
			return ExecuteResult.createSuccResult("执行【"+cmd.getShowName()+"】"+result.getMsg());
		}else {
			return ExecuteResult.createFailResult("执行【"+cmd.getShowName()+"】"+result.getMsg());
		}
	}
	
	public void disconect() {
		try {
			if (pw != null) {
				pw.close();	
			}
			if (br != null) {
				br.close();
			}
			if (client != null) {
				client.disconnect();				
			}
			pw = null;
			br = null;
			client = null;
		} catch (IOException e) {
			AppLog.error(server,toString() + " disconect is error...",e);
		}
	}
	
	
	public boolean connection(String host,int port) {
		client = new TelnetClient();
		try {
			AppLog.info(server,"telnet connect host["+host+"] port["+port+"] ....");
			client.connect(host, port);
			AppLog.info(server,"telnet isConnected :" + client.isConnected());
			pw = new PrintStream(client.getOutputStream());
			br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
			return receiveMsgFromServer(null).isSucc();
		} catch (Exception e) {
			AppLog.error(server, toString() +" connection is error...", e);
			return false;
		}
	}

	public boolean isConnect() {
		return client != null && client.isConnected();
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public TelnetClient getClient() {
		return client;
	}
	public void setClient(TelnetClient client) {
		this.client = client;
	}
	public PrintStream getPw() {
		return pw;
	}
	public void setPw(PrintStream pw) {
		this.pw = pw;
	}
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader br) {
		this.br = br;
	}
	
	public String toString() {
		return "TELNET"; 
	}
	public static void main(String[] args) {
		String name ="芦元珊";
		StringBuffer sb = new StringBuffer();
		for (char ch : name.toCharArray()) {
			sb.append("\\u"+Integer.toHexString(ch));
		}
		System.out.println(sb.toString());
		String[] values = sb.toString().split("\\\\u");
		for (String s : values) {
			System.out.println(s);	
		}
		
	}
}

