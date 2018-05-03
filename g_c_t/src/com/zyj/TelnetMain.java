package com.zyj;

import com.zyj.telnet.TelnetManager;
import com.zyj.telnet.entry.db.DBManager;
import com.zyj.telnet.entry.gui.MainFrame;

public class TelnetMain {
	public static MainFrame mainFrame;
	public static void main(String[] args) {
		DBManager.getInstance().init();
		TelnetManager.getInstance().init();
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}
