package com.rmicp.bo;

import java.util.List;
import java.util.Map;

public class Permission {

	private Map<String, List<String>> objects;

	public Map<String, List<String>> getObjects() {
		return objects;
	}

	public void setObjects(Map<String, List<String>> objects) {
		this.objects = objects;
	}

}
