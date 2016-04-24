package cn.tm.ms.nerver.route.loadbalance;

import java.util.Random;

/**
 * 随机负载
 * @author lry
 */
public class RandLoadBalance {

	/**
	 * 负载选择
	 * @param urls
	 * @return
	 */
	public String loadBalance(String[] urls) {
		return urls[new Random().nextInt(urls.length)];
	}
	
}
