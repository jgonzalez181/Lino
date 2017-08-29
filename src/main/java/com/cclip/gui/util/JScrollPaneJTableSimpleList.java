package com.cclip.gui.util;

import com.cclip.util.UtilComponent;

public abstract class JScrollPaneJTableSimpleList extends JScrollPaneJTable {

	private static final long serialVersionUID = -108892394745709445L;

	protected Object[][] model = null;
	protected Object dto;

	public void find(Object dto) {

		try {

			this.dto = dto;

			clearData();

			if (dto == null) {

				return;
			}

			find();

			showData(model);

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	protected abstract void find() throws Exception;
}
