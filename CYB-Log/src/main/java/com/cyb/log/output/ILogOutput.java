package com.cyb.log.output;

/**
 * @author yubin chen
 * @date 2017年1月14日
 * 
 * 日志输出接口
 */
public interface ILogOutput {


	/**
	 * 输出日志,并不保证日志确实输出
	 * @param log
	 * @throws LogOutputException
	 */
	public void logOutput(Object log)throws LogOutputException;
}
