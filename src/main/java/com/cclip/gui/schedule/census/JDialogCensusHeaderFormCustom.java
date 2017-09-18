package com.cclip.gui.schedule.census;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.person.UserSystem;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.model.schedule.census.ScheduleCensus;
import com.cclip.model.schedule.census.ScheduleCensusResult;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JDialogCensusHeaderFormCustom extends JDialogCensusHeaderForm
		implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleCensus scheduleCensus;
	private JPanelCensusViewCustom jPanelCensusViewCustom;
	private Schedule[] scheduleList;
	private ScheduleBatch[] scheduleBatchList;
	private CensusTaker[] censusTakerList;
	private ScheduleCensusResult[] scheduleCensusResultList;

	public JDialogCensusHeaderFormCustom(Frame parent, boolean modal) {
		super(parent, modal);
		// TODO Auto-generated constructor stub
	}

	public JDialogCensusHeaderFormCustom(ScheduleCensus scheduleCensus,
			JPanelCensusViewCustom jPanelCensusViewCustom) {
		super(null, true);

		this.scheduleCensus = scheduleCensus;
		this.jPanelCensusViewCustom = jPanelCensusViewCustom;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon(
				(JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon(
				(JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		scheduleLoadData();
		if (jComboBox2.getItemCount() > 0) {
			jComboBox2.setSelectedIndex(0);
		}

		batchLoadData();

		class ItemChangeListener1 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					batchLoadData();
				}
			}
		}

		jComboBox2.addItemListener(new ItemChangeListener1());

		scheduleCensusResultLoadData();
		if (jComboBoxScheduleCensusResult.getItemCount() > 0) {
			jComboBoxScheduleCensusResult.setSelectedIndex(0);
		}

		// censistas
		censusTakerLoadData();
		if (jComboBoxCensusTaker.getItemCount() > 0) {
			jComboBoxCensusTaker.setSelectedIndex(0);
		}

		printClear();
		print();

		pack();

		setVisible(true);
	}

	private void printClear() {
		jTextField1.setText("");

		jRadioButton1.setSelected(true);

		if (jComboBox2.getItemCount() > 0) {
			jComboBox2.setSelectedIndex(0);
		}
		if (jComboBox5.getItemCount() > 0) {
			jComboBox5.setSelectedIndex(0);
		}
		if (jComboBoxScheduleCensusResult.getItemCount() > 0) {
			jComboBoxScheduleCensusResult.setSelectedIndex(0);
		}
		if (jComboBoxCensusTaker.getItemCount() > 0) {
			jComboBoxCensusTaker.setSelectedIndex(0);
		}

	}

	private void print() {
		if (scheduleCensus == null) {
			return;
		}

		if (scheduleCensus.getUpdateCadastre() != null
				&& scheduleCensus.getUpdateCadastre() == true) {
			jRadioButton1.setSelected(true);
		} else {
			jRadioButton1.setSelected(false);
		}

		if (scheduleCensus.getInsertCadastre() != null
				&& scheduleCensus.getInsertCadastre() == true) {
			jRadioButton2.setSelected(true);
		} else {
			jRadioButton2.setSelected(false);
		}

		if (scheduleCensus.getDeleteCadastre() != null
				&& scheduleCensus.getDeleteCadastre() == true) {
			jRadioButton3.setSelected(true);
		} else {
			jRadioButton3.setSelected(false);
		}

		if (scheduleCensus.getSchedule() != null) {
			for (int i = 0; i < scheduleList.length; i++) {

				if (scheduleList[i].getId().equals(
						scheduleCensus.getSchedule().getId())) {

					jComboBox2.setSelectedIndex(i);
					break;
				}
			}
		}
		if (scheduleCensus.getCensusTaker() != null) {

			for (int i = 0; i < censusTakerList.length; i++) {

				if (censusTakerList[i].getId().equals(
						scheduleCensus.getCensusTaker().getId())) {

					jComboBoxCensusTaker.setSelectedIndex(i);
					break;
				}
			}
		}

		if (scheduleCensus.getScheduleCensusResult() != null) {
			for (int i = 0; i < scheduleCensusResultList.length; i++) {

				if (scheduleCensusResultList[i].getId().equals(
						scheduleCensus.getScheduleCensusResult().getId())) {

					jComboBoxScheduleCensusResult.setSelectedIndex(i);
					break;
				}
			}
		}

		if (scheduleCensus.getCensused() != null) {
			jTextField1.setText(new SimpleDateFormat("ddMMyyyy")
					.format(scheduleCensus.getCensused()));
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(jButtonSave)) {

			if (validateForm() == false) {
				return;
			}

			try {
				formToDto();
				update();
				if (scheduleCensus != null) {
					jPanelCensusViewCustom.setScheduleCensusId(scheduleCensus
							.getId());
				}
			} catch (Exception e1) {
				return;
			}
		}

		setVisible(false);
		dispose();

	}

	private void update() {
		
		// hacer el servicio de update en la base si es M o B
		// el insert no lo hagas !!
		
		if (scheduleCensus.getUpdateCadastre()
				|| scheduleCensus.getDeleteCadastre()) {
			
			Services.getInstance().updateScheduleCensus(scheduleCensus, Context.getBean("userSystem", UserSystem.class));

			// Services.getInstance().updateCadastreCensusGeneral(cadastre,
			// Context.getBean("userSystem", UserSystem.class));
		}
	}

	private void formToDto() throws Exception {

		if (scheduleCensus == null) {
			return;
		}
		//Cronograma
		if (jComboBox2.getItemCount() > 0 && jComboBox2.getSelectedIndex() > -1
				&& scheduleList.length > 0) {
			Schedule schedule = new Schedule();
			schedule.setId(scheduleList[jComboBox2.getSelectedIndex()].getId());
			schedule.setYear(scheduleList[jComboBox2.getSelectedIndex()]
					.getYear());
			scheduleCensus.setSchedule(schedule);
		} else {
			scheduleCensus.setSchedule(null);
		}
		//Lote
		if (jComboBox5.getItemCount() > 0 && jComboBox5.getSelectedIndex() > -1
				&& scheduleBatchList.length > 0) {
			ScheduleBatch scheduleBatch = new ScheduleBatch();
			scheduleBatch
					.setId(scheduleBatchList[jComboBox5.getSelectedIndex()]
							.getId());
			scheduleBatch.setNumberBatch(scheduleBatchList[jComboBox5
					.getSelectedIndex()].getNumberBatch());
			scheduleCensus.setScheduleBatch(scheduleBatch);
		} else {
			scheduleCensus.setScheduleBatch(null);
		}
		//Resultado
		if (jComboBoxScheduleCensusResult.getItemCount() > 0
				&& jComboBoxScheduleCensusResult.getSelectedIndex() > -1
				&& scheduleCensusResultList.length > 0) {
			ScheduleCensusResult scheduleCensusResult = new ScheduleCensusResult();
			scheduleCensusResult
					.setId(scheduleCensusResultList[jComboBoxScheduleCensusResult
							.getSelectedIndex()].getId());
			scheduleCensusResult
					.setName(scheduleCensusResultList[jComboBoxScheduleCensusResult
							.getSelectedIndex()].getName());
			scheduleCensus.setScheduleCensusResult(scheduleCensusResult);
		} else {
			scheduleCensus.setScheduleCensusResult(null);
		}
		//Censista
		if (jComboBoxCensusTaker.getItemCount() > 0
				&& jComboBoxCensusTaker.getSelectedIndex() > -1
				&& censusTakerList.length > 0) {
			CensusTaker censusTaker = new CensusTaker();
			censusTaker.setId(censusTakerList[jComboBoxCensusTaker
					.getSelectedIndex()].getId());
			censusTaker.setCode(censusTakerList[jComboBoxCensusTaker
					.getSelectedIndex()].getCode());
			scheduleCensus.setCensusTaker(censusTaker);
		} else {
			scheduleCensus.setCensusTaker(null);
		}
		//Fecha
		if (jTextField1.getText() != null
				&& jTextField1.getText().trim().length() > 0) {
			scheduleCensus.setCensused(new Date(
					new SimpleDateFormat("ddMMyyyy").parse(
							jTextField1.getText().trim()).getTime()));
		} else {
			scheduleCensus.setCensused(null);
		}
		//Motivo
		scheduleCensus.setUpdateCadastre(jRadioButton1.isSelected());
		scheduleCensus.setInsertCadastre(jRadioButton2.isSelected());
		scheduleCensus.setDeleteCadastre(jRadioButton3.isSelected());

	}

	private boolean validateForm() {

		if (jComboBox2.getSelectedIndex() < 0) {
			return false;
		}
		if (jComboBox5.getSelectedIndex() < 0) {
			return false;
		}
		if (jComboBoxScheduleCensusResult.getSelectedIndex() < 0) {
			return false;
		}
		if (jComboBoxCensusTaker.getSelectedIndex() < 0) {
			return false;
		}

		if (jTextField1.getText() != null
				&& jTextField1.getText().trim().length() > 0) {
			try {
				new Timestamp(new SimpleDateFormat("ddMMyyyy").parse(
						jTextField1.getText().trim()).getTime());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"La fecha de Baja debe tener un formato ddMMyyyy",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}

		return true;
	}

	private void scheduleCensusResultLoadData() {

		try {

			// jComboBoxScheduleCensusResult.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance()
					.findScheduleCensusResult();
			scheduleCensusResultList = new ScheduleCensusResult[table.length];

			for (int i = 0; i < table.length; i++) {
				ScheduleCensusResult scheduleCensusResult = new ScheduleCensusResult();
				scheduleCensusResult.setId(table[i][0].toString());
				if (table[i][1] != null) {
					scheduleCensusResult.setName(table[i][1].toString());
				}
				scheduleCensusResultList[i] = scheduleCensusResult;
			}

			if (scheduleCensusResultList != null) {

				for (int i = 0; i < scheduleCensusResultList.length; i++) {
					jComboBoxScheduleCensusResult.addItem(new ComboItem("("
							+ scheduleCensusResultList[i].getId() + ") "
							+ scheduleCensusResultList[i].getName(),
							scheduleCensusResultList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void censusTakerLoadData() {

		try {

			// jComboBoxCensusTaker.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance().findCensusTaker();
			censusTakerList = new CensusTaker[table.length];

			for (int i = 0; i < table.length; i++) {
				CensusTaker censusTaker = new CensusTaker();
				censusTaker.setId(table[i][0].toString());
				if (table[i][1] != null) {
					censusTaker.setFamilyName(table[i][1].toString());
					censusTaker.setGivenName(table[i][2].toString());
				}
				censusTakerList[i] = censusTaker;
			}

			if (censusTakerList != null) {

				for (int i = 0; i < censusTakerList.length; i++) {
					jComboBoxCensusTaker.addItem(new ComboItem(
							censusTakerList[i].getFamilyName() + " "
									+ censusTakerList[i].getGivenName(),
							censusTakerList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void scheduleLoadData() {

		try {

			jComboBox2.removeAllItems();

			Object[][] table = Services.getInstance().findSchedule();
			scheduleList = new Schedule[table.length];

			for (int i = 0; i < table.length; i++) {
				Schedule schedule = new Schedule();
				schedule.setId(table[i][0].toString());
				if (table[i][1] != null) {
					schedule.setYear(Integer.valueOf(table[i][1].toString()));
				}
				scheduleList[i] = schedule;
			}

			if (scheduleList != null) {

				for (int i = 0; i < scheduleList.length; i++) {

					jComboBox2.addItem(new ComboItem(scheduleList[i].getYear()
							.toString(), scheduleList[i].getId()));

				}
			}

			// jComboBox2.addItem(new ComboItem("", null));

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	private void batchLoadData() {

		try {

			ComboItem scheduleComboItem = (ComboItem) jComboBox2
					.getItemAt(jComboBox2.getSelectedIndex());

			jComboBox5.removeAllItems();

			Object[][] table = Services.getInstance()
					.findScheduleBatchByscheduleId(scheduleComboItem.getId());
			scheduleBatchList = new ScheduleBatch[table.length];

			for (int i = 0; i < table.length; i++) {
				ScheduleBatch scheduleBatch = new ScheduleBatch();
				scheduleBatch.setId(table[i][0].toString());
				if (table[i][1] != null) {
					scheduleBatch.setNumberBatch(Integer.valueOf(table[i][1]
							.toString()));
				}
				scheduleBatchList[i] = scheduleBatch;
			}

			if (scheduleBatchList != null) {

				for (int i = 0; i < scheduleBatchList.length; i++) {

					jComboBox5.addItem(new ComboItem(scheduleBatchList[i]
							.getNumberBatch().toString(), scheduleBatchList[i]
							.getId()));

				}
			}

			// jComboBox5.addItem(new ComboItem("", null));

			if (jComboBox5.getItemCount() > 0) {
				jComboBox5.setSelectedIndex(0);
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

}
