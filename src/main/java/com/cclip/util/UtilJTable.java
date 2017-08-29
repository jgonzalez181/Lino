package com.cclip.util;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UtilJTable {
	
	public static void setWidthColumn(TableColumnModel tableColumnModel, int[] width) {
		
		for(int i = 0; i < width.length; i++){
			setWidthColumn(tableColumnModel.getColumn(i), width[i]);
		}
		
	}

	public static void setWidthColumn(TableColumn tableColumn, int width) {

		if (width == 0) {
			tableColumn.setMinWidth(0);
			tableColumn.setMaxWidth(0);
			tableColumn.setWidth(0);
			tableColumn.setPreferredWidth(0);
		} else if (width > -1){
			tableColumn.setWidth(width);
			tableColumn.setPreferredWidth(width);
			tableColumn.setMaxWidth(width);
		}

	}

}
