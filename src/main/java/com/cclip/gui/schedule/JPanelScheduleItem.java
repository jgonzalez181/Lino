package com.cclip.gui.schedule;

import java.sql.Date;

import com.cclip.gui.util.JScrollPaneJTableSimpleList;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.services.Services;

class JPanelScheduleItem extends JScrollPaneJTableSimpleList {

	private static final long serialVersionUID = 8740413335514099146L;

	public JPanelScheduleItem() {

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("Cronograma", Integer.class, 100);
		addColumnMetaData("DDZZMMMPPPppp", String.class, 140);
		addColumnMetaData("UF", String.class, 80);
		addColumnMetaData("Cta Cli", String.class, 80);
		addColumnMetaData("Censado", Date.class, 90);
		addColumnMetaData("Censista", String.class, 150);
		addColumnMetaData("Resultado", String.class, 150);
		addColumnMetaData("Lote", Integer.class, 50);
		addColumnMetaData("Lote Cerrado", Date.class, 120);

		showData(null);

	}

	protected void find() throws Exception {

		String scheduleBatchId = null;

		if (dto != null) {
			scheduleBatchId = dto.toString();
		}

		model = Services.getInstance().findScheduleCensusCensusItem(scheduleBatchId);

	}

}
