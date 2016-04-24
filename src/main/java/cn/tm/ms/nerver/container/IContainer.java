package cn.tm.ms.nerver.container;

/**
 * 容器
 * @author lry
 */
public interface IContainer {

	/**
	 * 启动
	 * @param port
	 * @throws Throwable
	 */
	public void cstart(int port);
	
	/**
	 * 销毁
	 * @throws Throwable
	 */
	public void cstop();
}
