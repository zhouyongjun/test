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
	 * ����telent����
	 * @author zhouyongjun
	 *
	 */
public class Telnet {
	Server server;
	TelnetClient client;//telnet������
	PrintStream pw;//д��
	BufferedReader br;//����
	public Telnet(Server server) {
		this.server = server; 
	}
	/**
	 * �ӷ�������ȡ����
	 */
	public ExecuteResult receiveMsgFromServer(Cmd cmd) {
		String line = null;
		String msg = "�ɹ���";
		 try {
			 boolean isTrue = true;
			while ((line = br.readLine()) != null) {
				AppLog.info(server,toString()+line);
				if (line.contains("find result:")) {
					int index = line.indexOf(":");
					if (index < 0) {
						msg ="��ѯʧ��";
					}else {
						msg = line.substring(line.indexOf(":")+1);
					}
					TelnetMain.mainFrame.setjtextFieldValue(cmd.getParam_list().size()-1,msg);
					
				}
				if (line.toLowerCase().contains("error") || line.toLowerCase().contains("exception")) {
					isTrue = false;
					msg = "ʧ�ܣ��о�����ôô���ˣ�";
				}
				if (line.contains(TelnetManager.NOT_CMD_SIGN)) {
					isTrue = false;
					msg = "ʧ�ܣ�������ڣ��о�����ôô���ˣ�";
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
			return ExecuteResult.createFailResult("���շ�����Ϣ�����о�����ôô���ˣ�");
		}
	}
	
	/**
	 * �������д����
	 * @param cmd
	 */
	public ExecuteResult sendCmdToServer(Cmd cmd,Object... values) {
		try {
			AppLog.info(server,"telnet send cmd :" + cmd.toExecCmd(values));
			pw.println(cmd.toExecCmd(values));
			pw.flush();
			return ExecuteResult.createSuccResult("�ɹ�");
		} catch (Exception e) {
			AppLog.error(server,toString()+" sendCmdToServer["+cmd+"] is error...",e);
//			SSHMain.mainFrame.addErrorResultMsg(server,"�������������������������Ϣ����ִ��["+cmd.getShowName()+"ʧ�ܣ��뾡����ѯ��ط�������Ա������");
//			server.setTelnetReturnSucc(false);
			return ExecuteResult.createFailResult("ʧ��");
		}
	}
	/**
	 * ִ������������ͺͽ�����Ϣ
	 * @param cmd
	 */
	public ExecuteResult execCmd(Cmd cmd,Object... values) {
		ExecuteResult result = sendCmdToServer(cmd,values);
		if (!result.isSucc()) {
			return ExecuteResult.createFailResult("ִ�С�"+cmd.getShowName()+"��ʧ�ܣ��о�����ôô���ˣ�");
		}
		result = receiveMsgFromServer(cmd);
		if (result.isSucc()) {
			return ExecuteResult.createSuccResult("ִ�С�"+cmd.getShowName()+"��"+result.getMsg());
		}else {
			return ExecuteResult.createFailResult("ִ�С�"+cmd.getShowName()+"��"+result.getMsg());
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
		String name ="«Ԫɺ";
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

