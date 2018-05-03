package com.zyj.telnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.Element;

import com.zyj.telnet.entry.Server;
import com.zyj.telnet.entry.cmd.Cmd;
import com.zyj.telnet.entry.db.DBManager;
import com.zyj.telnet.entry.db.DaoData;
import com.zyj.telnet.log.AppLog;
import com.zyj.telnet.uitl.TelnetUtil;

public class TelnetManager {
	public static final String FILE_NAME = "conf/cmd_config.xml";
	private static TelnetManager instance = new TelnetManager();
	public static String TELNET_END_SIGN = "please input command:";
	public static String NOT_CMD_SIGN = "NO RESULT exist for";
	public static String TABLE_SERVERS = "server_telnet";
	private List<Cmd> cmd_list = new ArrayList<Cmd>();
	private List<Server> server_list = new ArrayList<Server>();
	private TelnetManager() {
		
	}
	public static TelnetManager getInstance() {
		return instance;
	}
	public void init() {
		loadCmds();	
		loadServerList();
	}

	/**
	 * 加载服务器列表
	 */
	public void loadServerList() {
		try {
			List<Map<String,Object>> datasList = DBManager.getInstance().getGameDao().getServerList();
			List<Server> temp_list = new ArrayList<Server>(); 
			for (Map<String,Object> map : datasList) {
				Server server = new Server();
				server.loadFromData(map);
				//初始化CMD信息
				temp_list.add(server);				
			}
			this.server_list = temp_list;
		} catch (Exception e) {
			AppLog.error(e);
			JOptionPane.showMessageDialog(null, "服务器列表["+TABLE_SERVERS+"]加载错误，无法启动!");
			System.exit(0);
		}
	}
	private void loadCmds() {
		try {
			List<Cmd> temp_list = new ArrayList<Cmd>();
			Document doc = TelnetUtil.load(FILE_NAME);
			Element telnetE = TelnetUtil.getElement(doc.getRootElement(), "telnet");
			TELNET_END_SIGN = TelnetUtil.getAttriValue(telnetE, "end_sign");
			TABLE_SERVERS = TelnetUtil.getAttriValue(telnetE, "db_sever_name");
			List<Element> els = TelnetUtil.getElements(doc.getRootElement(), "cmd");
			if (els == null || els.size() == 0) {
				JOptionPane.showMessageDialog(null, "没有配置命令，无法启动！");
				System.exit(0);
			}
			for (Element e : els) {
				Cmd cmd = new Cmd();
				cmd.load(e);
				temp_list.add(cmd);
			}
			this.cmd_list = temp_list;
		} catch (Exception e) {
			AppLog.error(e);
			JOptionPane.showMessageDialog(null, "命令加载失败，无法启动!");
			System.exit(0);
		}
	}
	public List<Cmd> getCmd_list() {
		return cmd_list;
	}
	public void setCmd_list(List<Cmd> cmd_list) {
		this.cmd_list = cmd_list;
	}
	public List<Server> getServer_list() {
		return server_list;
	}
	public void setServer_list(List<Server> server_list) {
		this.server_list = server_list;
	}
	
}	
