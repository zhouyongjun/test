package com.zyj.telnet.entry.db;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.zyj.TelnetMain;
import com.zyj.telnet.TelnetManager;
import com.zyj.telnet.log.AppLog;
	/**
	 * ���ݿ�����ִ����
	 * @author zhouyongjun
	 *
	 */
public class GameDAO extends SimpleJdbcDaoSupport {

	private GameDAO() {
	}

	//ȡ�÷������б�
	public List<Map<String,Object>> getServerList() {
		String sql = String.format("select * from %s  order by id ", TelnetManager.TABLE_SERVERS);
		AppLog.info(sql);
		return getSimpleJdbcTemplate().queryForList(sql);
	}

}
