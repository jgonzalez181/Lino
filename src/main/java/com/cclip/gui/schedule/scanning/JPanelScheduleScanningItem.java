package com.cclip.gui.schedule.scanning;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.cclip.Context;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningBo;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningItemBo;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningResultBo;
import com.cclip.gui.util.JScrollPaneJTableSimpleList;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

public class JPanelScheduleScanningItem extends JScrollPaneJTableSimpleList {

	private static final long serialVersionUID = 8740413335514099146L;
	
	public JPanelScheduleScanningForm jPanelScheduleScanningForm;

	private ScheduleScanningItem[] list;

	private ScheduleScanningResult[] scheduleScanningResultList;

	private JPanelScheduleScanning jPanelScheduleScanning;

	public JPanelScheduleScanningItem(JPanelScheduleScanning jPanelScheduleScanning) {

		// setAutoCreateRowSorter(false);

		this.jPanelScheduleScanning = jPanelScheduleScanning;

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("DDZZMMMPPPppp", String.class, 140);
		addColumnMetaData("Código", String.class, 60);
		addColumnMetaData("Resultado de Barrido", String.class, 250);
		addColumnMetaData("Sup.", String.class, 70);
		addColumnMetaData("% Av", String.class, 70);
		addColumnMetaData("Tipo", String.class, 40);
		addColumnMetaData("Cta.Cli", String.class, 100);
		addColumnMetaData("DV", String.class, 40);
		addColumnMetaData("Área", String.class, 40);
		addColumnMetaData("UF", String.class, 100);
		addColumnMetaData("SubTipo", String.class, 40);
		addColumnMetaData("Unid.(65)", String.class, 40);
		addColumnMetaData("C (-1)", String.class, 60);
		addColumnMetaData("C (-2)", String.class, 60);
		addColumnMetaData("C (-3)", String.class, 60);
		addColumnMetaData("Comentario", String.class, -1);

		getjTable().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				if ("tableCellEditor".equals(evt.getPropertyName())) {

					if (getjTable().isEditing() == false) {

						setCode();

					}
				}
			}
		});

		showData(null);

		searchCodes();

	}

	public void find(ScheduleScanning scheduleScanning) {

		list = null;

		if (scheduleScanning != null) {
			/*
			 * if (scheduleScanning.getScheduleBatch() != null &&
			 * scheduleScanning.getScheduleBatch().getClose() != null) {
			 * 
			 * setEditable(false, 2); setEditable(false, 4); setEditable(false,
			 * 5); setEditable(false, 6);
			 * 
			 * } else
			 */

			if (scheduleScanning.getRecorded() != null) {

				setEditable(false, 2);
				setEditable(false, 4);
				setEditable(false, 5);
				setEditable(false, 16);

			} else {

				setEditable(true, 2);
				setEditable(true, 4);
				setEditable(true, 5);
				setEditable(true, 16);
			}
		}

		super.find(scheduleScanning);
	}

	private void setCode() {
		try {

			int row = getjTable().getSelectedRow();
			
			int col = getjTable().getSelectedColumn();


			if (list != null && row > -1) {

				if(col==2)
					setCodeScanning(row);
				else if(col==4)
					setM2Value(row);
				else if(col==5)
					setAvValue(row);
				else if(col==16)
					setComment(row);

				

			}
		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	private void setCodeScanning(int row) {
		// if (getjTable().getValueAt(row, 3) != null
		// && getjTable().getValueAt(row, 3).toString().length() > 0) {
		// return;
		// }

		// Código de Barrido

		Object value = getjTable().getValueAt(row, 2);

		if (value != null && value.toString().trim().length() > 0) {

			String code = value.toString().trim();
			String code2 = null;
			String name2 = null;

			code = code.replace(".", "");

			if (code.length() == 2 || code.length() == 1) {

				boolean b = false;

				for (ScheduleScanningResult scheduleScanningResult : scheduleScanningResultList) {

					code2 = scheduleScanningResult.getId().trim().replace(".", "");

					if (code.equalsIgnoreCase(code2)) {

						code = scheduleScanningResult.getId().trim();

						name2 = scheduleScanningResult.getName().trim();

						ScheduleScanningItem scheduleScanningItem = list[row];

						scheduleScanningItem.setScheduleScanningResult(scheduleScanningResult);

						// save();
						
						saveItem(row);

						b = true;
						
						break;

					}
				}

				if (b == false) {
					code = null;
				}

			} else {

				code = null;
				name2 = null;
			}

			getjTable().setValueAt(code, row, 2);

			getjTable().setValueAt(name2, row, 3);

		}

	}

	private void setAvValue(int row) {
		// Controlo el % Av

		Object value = getjTable().getValueAt(row, 5);

		if (value != null && value.toString().trim().length() > 0) {

			Double d = 0.0;

			try {
				d = new Double(value.toString());
				
				
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "El valor del campo % Av debe ser de 0 - 100. Ejemplo 53.7",
						"Error: " + UtilCadastre.formatCadastralCode(list[row].getCadastre().getCadastralCode()), JOptionPane.ERROR_MESSAGE);

				list[row].setProgressConstruction(null);

				getjTable().setValueAt(null, row, 5);

				return;
			}

			if (d < 0 || d > 100) {

				JOptionPane.showMessageDialog(null, "El valor del campo % Av debe ser de 0 - 100. Ejemplo 53.7",
						"Error: " + UtilCadastre.formatCadastralCode(list[row].getCadastre().getCadastralCode()), JOptionPane.ERROR_MESSAGE);

				list[row].setProgressConstruction(null);

				getjTable().setValueAt(null, row, 5);

				return;

			}

			list[row].setProgressConstruction(d);

			getjTable().setValueAt(d.toString(), row, 5);
			
			saveItem(row);

		}

	}

	private void setComment(int row) {
		// Controlo el M2

		Object value = getjTable().getValueAt(row, 16);

		if (value != null && value.toString().trim().length() > 0) {

			list[row].setComment(value.toString());

			getjTable().setValueAt(value.toString(), row, 16);
			
			saveItem(row);

		} else {
			list[row].setComment(null);

			getjTable().setValueAt("", row, 16);
		}

	}

	private void setM2Value(int row) {
		// Controlo el M2

		Object value = getjTable().getValueAt(row, 4);

		if (value != null && value.toString().trim().length() > 0) {

			try {
				Double d = new Double(value.toString());

				list[row].setM2Construction(d);

				getjTable().setValueAt(d.toString(), row, 4);
				
				saveItem(row);

			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "El valor del campo Sup. debe ser un número. Ejemplo 285.44",
						"Error: " + UtilCadastre.formatCadastralCode(list[row].getCadastre().getCadastralCode()), JOptionPane.ERROR_MESSAGE);

				return;
			}

		}

	}

	private void save() {

		try {

			ScheduleScanning scheduleScanning = (ScheduleScanning) dto;

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			dto = bo.update(scheduleScanning);

			jPanelScheduleScanning.jPanelScheduleScanningForm1.jPanelScheduleScanningTotal1.find(dto);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}
	
	private void saveItem(int row) {

		try {

			ScheduleScanning scheduleScanning = (ScheduleScanning) dto;
			
			ScheduleScanningItem scheduleScanningItem = list[row];  
			
			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			dto = bo.updateItem(scheduleScanningItem);

			jPanelScheduleScanning.jPanelScheduleScanningForm1.jPanelScheduleScanningTotal1.find(dto);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	protected void find() throws Exception {

		if (dto != null) {
			ScheduleScanning scheduleScanning = (ScheduleScanning) dto;

			String scheduleScanningId = scheduleScanning.getId();

			CustomScheduleScanningItemBo bo = Context.getBean("customScheduleScanningItemBo", CustomScheduleScanningItemBo.class);

			list = bo.find1(scheduleScanningId);

			model = new Object[list.length][17];

			for (int i = 0; i < list.length; i++) {

				ScheduleScanningItem scheduleScanningItem = (ScheduleScanningItem) list[i];

				scheduleScanning.addScheduleScanningItemList(scheduleScanningItem);

				model[i][0] = scheduleScanningItem.getId();

				if (scheduleScanningItem.getCadastre() != null && scheduleScanningItem.getCadastre().getCadastralCode() != null) {

					model[i][1] = UtilCadastre.formatCadastralCode(scheduleScanningItem.getCadastre().getCadastralCode());

				} else {
					model[i][1] = null;

				}

				if (scheduleScanningItem.getScheduleScanningResult() != null) {

					model[i][2] = scheduleScanningItem.getScheduleScanningResult().getId();

					model[i][3] = scheduleScanningItem.getScheduleScanningResult().getName();

				} else {

					model[i][2] = null;
					model[i][3] = null;

				}

				if (scheduleScanningItem.getM2Construction() != null && scheduleScanningItem.getM2Construction() > 0) {
					model[i][4] = scheduleScanningItem.getM2Construction();
				} else {
					model[i][4] = null;
				}

				if (scheduleScanningItem.getProgressConstruction() != null && scheduleScanningItem.getProgressConstruction() > 0) {
					model[i][5] = scheduleScanningItem.getProgressConstruction();
				} else {
					model[i][5] = null;
				}

				if (scheduleScanningItem.getCadastre().getCadastreType() != null) {
					model[i][6] = scheduleScanningItem.getCadastre().getCadastreType().getId();
				} else {
					model[i][6] = null;
				}

				if (scheduleScanningItem.getCadastre().getCtaCli() != null) {
					model[i][7] = scheduleScanningItem.getCadastre().getCtaCli();
				} else {
					model[i][7] = null;
				}

				if (scheduleScanningItem.getCadastre().getDv() != null) {
					model[i][8] = scheduleScanningItem.getCadastre().getDv();
				} else {
					model[i][8] = null;
				}

				if (scheduleScanningItem.getCadastre().getCityArea() != null) {
					model[i][9] = scheduleScanningItem.getCadastre().getCityArea().getId();
				} else {
					model[i][9] = null;
				}

				if (scheduleScanningItem.getCadastre().getUf() != null) {
					model[i][10] = scheduleScanningItem.getCadastre().getUf().getId();
				} else {
					model[i][10] = null;
				}

				if (scheduleScanningItem.getCadastre().getCadastreSubDivisionType() != null) {
					model[i][11] = scheduleScanningItem.getCadastre().getCadastreSubDivisionType().getId();
				} else {
					model[i][11] = null;
				}

				if (scheduleScanningItem.getCadastre().getCantPh() != null) {
					model[i][12] = scheduleScanningItem.getCadastre().getCantPh();
				} else {
					model[i][12] = null;
				}

				if (scheduleScanningItem.getCadastre().getScheduleScanningResult0() != null && scheduleScanningItem.getCadastre().getScheduleScanningResult0().getId() != null) {
					model[i][13] = scheduleScanningItem.getCadastre().getScheduleScanningResult0().getId();
				} else {
					model[i][13] = null;
				}

				if (scheduleScanningItem.getCadastre().getScheduleScanningResult1() != null && scheduleScanningItem.getCadastre().getScheduleScanningResult1().getId() != null) {
					model[i][14] = scheduleScanningItem.getCadastre().getScheduleScanningResult1().getId();
				} else {
					model[i][14] = null;
				}

				if (scheduleScanningItem.getCadastre().getScheduleScanningResult2() != null && scheduleScanningItem.getCadastre().getScheduleScanningResult2().getId() != null) {
					model[i][15] = scheduleScanningItem.getCadastre().getScheduleScanningResult2().getId();
				} else {
					model[i][15] = null;
				}

				model[i][16] = scheduleScanningItem.getComment();

			}
			
			jPanelScheduleScanningForm.jTabbedPane1.setTitleAt(1, "Parcelas (" + model.length + ")");

		} else {
			model = new Object[0][17];
			
			jPanelScheduleScanningForm.jTabbedPane1.setTitleAt(1, "Parcelas (" + 0 + ")");
		}

	}

	private void searchCodes() {

		try {

			CustomScheduleScanningResultBo bo = Context.getBean("customScheduleScanningResultBo", CustomScheduleScanningResultBo.class);

			scheduleScanningResultList = bo.find1();

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}
}
