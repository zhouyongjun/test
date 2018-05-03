package com.zyj.telnet.entry;
	/**
	 * 执行结果类
	 * 结果如何、相关的信息报错
	 * @author zhouyongjun
	 *
	 */
public class ExecuteResult {
	private boolean isSucc;
	private String msg;
	public ExecuteResult(boolean isSucc,String msg){
		this.isSucc =isSucc;
		this.msg = msg;
	}
	public boolean isSucc() {
		return isSucc;
	}
	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static ExecuteResult createSuccResult(String msg) {
		return new ExecuteResult(true,msg);
	}
	
	public static ExecuteResult createFailResult(String msg) {
		return new ExecuteResult(false,msg);
	}
	@Override
	public String toString() {
		return isSucc +","+msg;
	}
	
	public static String getMsg(Server server,String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("【").append(server.toString()).append("】").append(msg);
		return sb.toString();
	}
}
