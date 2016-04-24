package cn.tm.ms.nerver.route.entry;

/**
 * 路由规则
 * @author lry
 */
public class RouteRule {

	/**
	 * 规则顺序
	 */
	private int order=0;
	/**
	 * 规则状态
	 */
	private boolean state = false;
	/**
	 * 规则
	 */
	private String rule;
	/**
	 * 路由地址列表,多个地址之间使用英文逗号隔开
	 */
	private String originUrls;

	public RouteRule() {
	}
	
	public RouteRule(int order, boolean state, String rule, String originUrls) {
		this.order = order;
		this.state = state;
		this.rule = rule;
		this.originUrls = originUrls;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getOriginUrls() {
		return originUrls;
	}

	public void setOriginUrls(String originUrls) {
		this.originUrls = originUrls;
	}

}
