package cn.tm.ms.nerver.restful;

import java.util.Map;

import cn.tm.ms.nerver.restful.annotation.API;
import cn.tm.ms.nerver.restful.annotation.Ctrl;

@Ctrl("test")
public class Test {

	@API("name")
	public String name() {
		return "";
	}
	
	@SuppressWarnings("rawtypes")
	@API("test/{name}")
	public Map test(String name) {
		return null;
	}
	
}
