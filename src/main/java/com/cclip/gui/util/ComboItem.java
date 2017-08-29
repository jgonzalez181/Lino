package com.cclip.gui.util;

public class ComboItem {

	private String name;
	private String id;

	public ComboItem(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		if (name != null) {
			return name;
		}
		if (id != null) {
			return id;
		}

		return null;
	}

}
