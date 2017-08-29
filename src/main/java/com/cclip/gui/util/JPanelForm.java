package com.cclip.gui.util;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTable;

public class JPanelForm extends JPanel {

	private static final long serialVersionUID = 2549606207248885665L;

	private Object dto;

	public JPanelForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JPanelForm(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public void setDto(Object dto, JTable jTable, Integer row) {
		this.dto = dto;
	}

}
