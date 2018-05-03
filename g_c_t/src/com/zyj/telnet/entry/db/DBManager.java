package com.zyj.telnet.entry.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 数据库管理
 * 
 * @author zhouyongjun
 * 
 */
public class DBManager {
	private static DBManager dbManager = null;
	private ApplicationContext context = null;
	private GameDAO gameDao = null;

	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gameDao = (GameDAO) context.getBean("gameDao");
	}
	
	public GameDAO getGameDao() {
		return gameDao;
	}

}
