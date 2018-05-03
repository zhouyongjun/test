/**
 * 
 */
package com.zyj.telnet.entry.db;

import java.util.Map;


/**
 * ��Ҫ���浽���ݿ�Ľӿڣ�
 * ֻ����update
 * @author Dream
 *
 */
public interface DaoData {
	//server
	String SERVER_ID = "id";
	String SERVER_NAME = "name";
	String SERVER_TELNET_HOST = "telnet_host";
	String SERVER_TELNET_PORT = "telnet_port";
		
		
	void loadFromData(Map<String,Object> map);
	void saveToData(Map<String,Object> map);
	
}
