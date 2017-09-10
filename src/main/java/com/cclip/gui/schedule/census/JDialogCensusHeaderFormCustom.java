package com.cclip.gui.schedule.census;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.geo.cadastre.JPanelCadastreViewCustom;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.CadastreSituation;
import com.cclip.model.geo.cadastre.CadastreType;
import com.cclip.model.geo.cadastre.WaterMeterType;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.census.ScheduleCensusResult;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JDialogCensusHeaderFormCustom extends JDialogCensusHeaderForm implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelCadastreViewCustom jPanelCadastreViewCustom;
	private Cadastre cadastre;
	private Schedule[] scheduleList;
	private ScheduleCensusResult[] scheduleCensusResultList;
	private Object[][] reasonLowList;
	private CensusTaker[] censusTakerList;
	

	public JDialogCensusHeaderFormCustom(Frame parent, boolean modal) {
		super(parent, modal);
		// TODO Auto-generated constructor stub
	}
	
	public JDialogCensusHeaderFormCustom(Cadastre cadastre, JPanelCadastreViewCustom jPanelCadastreViewCustom) {
		super(null, true);

		this.jPanelCadastreViewCustom = jPanelCadastreViewCustom;

		this.cadastre = cadastre;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon((JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);
		
		//razon
		jComboBoxReasonLowLoadData();
		//cronograma
		scheduleLoadData();
		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}
		//rtado censo
		jComboBoxScheduleCensusResult = new JComboBox<ComboItem>();
		scheduleCensusResultLoadData();
		if (jComboBoxScheduleCensusResult.getItemCount() > 0) {
			jComboBoxScheduleCensusResult.setSelectedIndex(0);
		}
		//censistas
		jComboBoxCensusTaker = new JComboBox<ComboItem>();
		jComboBoxCensusTaker.setToolTipText("Buscar por censista");
		censusTakerLoadData();
		if (jComboBoxCensusTaker.getItemCount() > 0) {
			jComboBoxCensusTaker.setSelectedIndex(0);
		}
		

		//printClear();
		//print();

		pack();

		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(jButtonSave)) {

			if (validateForm() == false) {
				return;
			}

			try {
				formToDto();
				//update();
				if (cadastre != null && cadastre instanceof CadastreCensus) {
					jPanelCadastreViewCustom.setCadastreCensusId(cadastre.getId());
				} else {
					jPanelCadastreViewCustom.setCadastreId(cadastre.getId());
				}
			} catch (Exception e1) {
				return;
			}
		}

		setVisible(false);
		dispose();

	}
	
	private void formToDto() throws Exception {

		if (cadastre == null) {
			return;
		}
		
		if (jComboBoxReasonLow.getItemCount() > 0 && jComboBoxReasonLow.getSelectedIndex() > -1 && reasonLowList.length > 0) {

			if (jComboBoxReasonLow.getSelectedIndex() > 0) {
				cadastre.setCodeReasonLow(reasonLowList[jComboBoxReasonLow.getSelectedIndex() - 1][0].toString());
				cadastre.setReasonLow(reasonLowList[jComboBoxReasonLow.getSelectedIndex() - 1][1].toString());
			} else {
				cadastre.setCodeReasonLow(null);
				cadastre.setReasonLow(null);
			}
		}

//
//		if (jComboBoxCityArea.getItemCount() > 0 && jComboBoxCityArea.getSelectedIndex() > -1 && cityAreaList.length > 0) {
//			CityArea cityArea = new CityArea();
//			cityArea.setId(cityAreaList[jComboBoxCityArea.getSelectedIndex()][0].toString());
//			cityArea.setName(cityAreaList[jComboBoxCityArea.getSelectedIndex()][1].toString());
//			cadastre.setCityArea(cityArea);
//		} else {
//			cadastre.setCityArea(null);
//		}
//
//		if (jTextFieldCadastralCode.getText() != null && jTextFieldCadastralCode.getText().trim().length() > 0) {
//			cadastre.setCadastralCode(jTextFieldCadastralCode.getText().trim());
//			cadastre.setSubCtaCli(jTextFieldCadastralCode.getText().trim().substring(10, 13));
//		} else {
//			cadastre.setCadastralCode(null);
//			cadastre.setSubCtaCli(null);
//		}
//
//		if (jTextFieldCtaCli.getText() != null && jTextFieldCtaCli.getText().trim().length() > 0) {
//			cadastre.setCtaCli(jTextFieldCtaCli.getText().trim());
//		} else {
//			cadastre.setCtaCli(null);
//		}
//
//		if (jTextFieldDv.getText() != null && jTextFieldDv.getText().trim().length() > 0) {
//			cadastre.setDv(jTextFieldDv.getText().trim());
//		} else {
//			cadastre.setDv(null);
//		}
//
//		if (jTextFieldM2.getText() != null && jTextFieldM2.getText().trim().length() > 0) {
//			cadastre.setM2(Double.parseDouble(jTextFieldM2.getText().trim()));
//		} else {
//			cadastre.setM2(0.0);
//		}
//
//		if (jTextFieldM2Covered.getText() != null && jTextFieldM2Covered.getText().trim().length() > 0) {
//			cadastre.setM2Covered(Double.parseDouble(jTextFieldM2Covered.getText().trim()));
//		} else {
//			cadastre.setM2Covered(0.0);
//		}
//
//		if (jTextFieldM2CoveredShared.getText() != null && jTextFieldM2CoveredShared.getText().trim().length() > 0) {
//			cadastre.setM2CoveredShared(Double.parseDouble(jTextFieldM2CoveredShared.getText().trim()));
//		} else {
//			cadastre.setM2CoveredShared(0.0);
//		}
//
//		if (jComboBoxCadastreType.getItemCount() > 0 && jComboBoxCadastreType.getSelectedIndex() > -1 && cadastreTypeList.length > 0) {
//			CadastreType cadastreType = new CadastreType();
//			cadastreType.setId(cadastreTypeList[jComboBoxCadastreType.getSelectedIndex()][0].toString());
//			cadastreType.setName(cadastreTypeList[jComboBoxCadastreType.getSelectedIndex()][1].toString());
//			cadastre.setCadastreType(cadastreType);
//		} else {
//			cadastre.setCadastreType(null);
//		}
//
//		if (jComboBoxCadastreSituation.getItemCount() > 0 && jComboBoxCadastreSituation.getSelectedIndex() > -1 && cadastreSituationList.length > 0) {
//			CadastreSituation cadastreSituation = new CadastreSituation();
//			cadastreSituation.setId(cadastreSituationList[jComboBoxCadastreSituation.getSelectedIndex()][0].toString());
//			cadastreSituation.setName(cadastreSituationList[jComboBoxCadastreSituation.getSelectedIndex()][1].toString());
//			cadastre.setCadastreSituation(cadastreSituation);
//		} else {
//			cadastre.setCadastreSituation(null);
//		}
//
//		if (jComboBoxWaterMeterType.getItemCount() > 0 && jComboBoxWaterMeterType.getSelectedIndex() > -1 && waterMeterTypeList.length > 0) {
//			WaterMeterType waterMeterType = new WaterMeterType();
//			waterMeterType.setId(waterMeterTypeList[jComboBoxWaterMeterType.getSelectedIndex()][0].toString());
//			waterMeterType.setName(waterMeterTypeList[jComboBoxWaterMeterType.getSelectedIndex()][1].toString());
//			cadastre.setWaterMeterType(waterMeterType);
//		} else {
//			cadastre.setWaterMeterType(null);
//		}
//
//
//		if (jTextFieldDateCreate.getText() != null && jTextFieldDateCreate.getText().trim().length() > 0) {
//			cadastre.setDateCreate(new Timestamp(new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateCreate.getText().trim()).getTime()));
//		} else {
//			cadastre.setDateCreate(null);
//		}
//
//		if (jTextFieldDateDelete.getText() != null && jTextFieldDateDelete.getText().trim().length() > 0) {
//			cadastre.setDateDelete(new Timestamp(new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateDelete.getText().trim()).getTime()));
//		} else {
//			cadastre.setDateDelete(null);
//		}
//
//		cadastre.setWaterMeter(jCheckBoxWaterMeter.isSelected());

	}
	
	private boolean validateForm() {

//		// CODIGO CATASTRAL ---------------------------------------------------------------------------------
//
//		if (jTextFieldCadastralCode.getText() != null && jTextFieldCadastralCode.getText().trim().length() > 0) {
//
//			if (jTextFieldCadastralCode.getText().trim().length() != "DDZZMMMPPPppp".length()) {
//				JOptionPane.showMessageDialog(this, "El código catastral deben contener 13 caracteres, ej DDZZMMMPPPppp = 0102003004005", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			try {
//
//				char[] chars = jTextFieldCadastralCode.getText().trim().toCharArray();
//				for (char c : chars) {
//					Integer.valueOf(c);
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "El código catastral debe contener solo caracteres numéricos, ej. 0-9", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		} else {
//			JOptionPane.showMessageDialog(this, "El código catastral no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
//
//		// CUENTA CLIENTE ---------------------------------------------------------------------------------
//
//		if (jTextFieldCtaCli.getText() != null && jTextFieldCtaCli.getText().trim().length() > 0) {
//
//			if (jTextFieldCtaCli.getText().trim().length() != "123456".length()) {
//				JOptionPane.showMessageDialog(this, "La cuenta cliente deben contener 6 caracteres, ej 1234567", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			try {
//
//				char[] chars = jTextFieldCtaCli.getText().trim().toCharArray();
//				for (char c : chars) {
//					Integer.valueOf(c);
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "La cuenta cliente debe contener solo caracteres numéricos, ej. 0-9", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		} else {
//			JOptionPane.showMessageDialog(this, "La cuenta cliente no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
//
//		// DIGITO VERIFICADOR ---------------------------------------------------------------------------------
//
//		if (jTextFieldDv.getText() != null && jTextFieldDv.getText().trim().length() > 0) {
//			if (jTextFieldDv.getText().trim().length() != 1) {
//				JOptionPane.showMessageDialog(this, "El dígito verificador deben ser un valor entero de un caracter", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			try {
//				Integer.valueOf(jTextFieldDv.getText());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "El dígito verificador deben ser un valor entero de un caracter", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//
//		// METROS TERRENO ---------------------------------------------------------------------------------
//
//		if (jTextFieldM2.getText() != null && jTextFieldM2.getText().trim().length() > 0) {
//			try {
//				Double.valueOf(jTextFieldM2.getText());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "Los metros cuadrados de terreno deben ser un valor decimal, ej. 459.1078", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//
//		// METROS CUBIERTOS ---------------------------------------------------------------------------------
//
//		if (jTextFieldM2Covered.getText() != null && jTextFieldM2Covered.getText().trim().length() > 0) {
//			try {
//				Double.valueOf(jTextFieldM2Covered.getText());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "Los metros cuadrados cubiertos deben ser un valor decimal, ej. 459.1078", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//
//		// METROS COMPARTIDOS ---------------------------------------------------------------------------------
//
//		if (jTextFieldM2CoveredShared.getText() != null && jTextFieldM2CoveredShared.getText().trim().length() > 0) {
//			try {
//				Double.valueOf(jTextFieldM2CoveredShared.getText());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "Los metros cuadrados compartidos deben ser un valor decimal, ej. 459.1078", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//
//		// ALTA ---------------------------------------------------------------------------------
//
//		if (jTextFieldDateCreate.getText() != null && jTextFieldDateCreate.getText().trim().length() > 0) {
//			try {
//				new Timestamp(new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateCreate.getText().trim()).getTime());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "La fecha de Alta debe tener un formato ddMMyyyy", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//
//		// BAJA ---------------------------------------------------------------------------------
//
//		if (jTextFieldDateDelete.getText() != null && jTextFieldDateDelete.getText().trim().length() > 0) {
//			try {
//				new Timestamp(new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateDelete.getText().trim()).getTime());
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "La fecha de Baja debe tener un formato ddMMyyyy", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//
//			if (jComboBoxReasonLow.getSelectedIndex() < 1) {
//				JOptionPane.showMessageDialog(this, "El motivode Baja es requerida para poder cargar una fecha de baja", "Error", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		} else if (jComboBoxReasonLow.getSelectedIndex() > 0) {
//			JOptionPane.showMessageDialog(this, "La fecha de Baja requerida para poder cargar un motivo de baja", "Error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
//
//		// ALTA < BAJA ---------------------------------------------------------------------------------
//
//		if (jTextFieldDateCreate.getText() != null && jTextFieldDateCreate.getText().trim().length() > 0 && jTextFieldDateDelete.getText() != null
//				&& jTextFieldDateDelete.getText().trim().length() > 0) {
//
//			try {
//				Date d1 = new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateCreate.getText().trim());
//				Date d2 = new SimpleDateFormat("ddMMyyyy").parse(jTextFieldDateDelete.getText().trim());
//
//				int r = d1.compareTo(d2);
//
//				if (r > 0) {
//					JOptionPane.showMessageDialog(this, "La fecha de Baja debe ser mayor o igual a la de Alta", "Error", JOptionPane.ERROR_MESSAGE);
//					return false;
//				}
//
//			} catch (ParseException e) {
//
//			}
//
//		}

		return true;
	}
	
	public void scheduleLoadData() {

		try {

			jComboBoxSchedule.removeAllItems();

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
					jComboBoxSchedule.addItem(new ComboItem(scheduleList[i].getYear().toString(), scheduleList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

		jComboBoxSchedule.addItem(new ComboItem("", null));
	}
	
	private void jComboBoxReasonLowLoadData() {

		try {

			jComboBoxReasonLow.removeAllItems();

			reasonLowList = Services.getInstance().findReasonLow();

			if (reasonLowList != null) {

				jComboBoxReasonLow.addItem(new ComboItem("", ""));

				for (int i = 0; i < reasonLowList.length; i++) {
					jComboBoxReasonLow.addItem(new ComboItem("(" + reasonLowList[i][0] + ") " + reasonLowList[i][1], reasonLowList[i][0].toString()));
				}

				if (reasonLowList.length > 0) {
					jComboBoxReasonLow.setSelectedIndex(0);

				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}
	
	private void scheduleCensusResultLoadData() {

		try {

			jComboBoxScheduleCensusResult.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance().findScheduleCensusResult();
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
					jComboBoxScheduleCensusResult.addItem(new ComboItem("(" + scheduleCensusResultList[i].getId() + ") " + scheduleCensusResultList[i].getName(), scheduleCensusResultList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void censusTakerLoadData() {

		try {

			jComboBoxCensusTaker.addItem(new ComboItem("", null));

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
					jComboBoxCensusTaker.addItem(new ComboItem(censusTakerList[i].getFamilyName() + " " + censusTakerList[i].getGivenName(), censusTakerList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

}
