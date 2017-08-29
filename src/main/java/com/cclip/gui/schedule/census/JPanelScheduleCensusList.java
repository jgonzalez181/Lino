package com.cclip.gui.schedule.census;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.gui.util.JPanelList;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.census.ScheduleCensusResult;
import com.cclip.services.Services;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

public class JPanelScheduleCensusList extends JPanelList {

	private static final long serialVersionUID = 8740413335514099146L;

	private JPanelCensusViewCustom jPanelCensusViewCustom;

	private Schedule[] scheduleList;
	private CensusTaker[] censusTakerList;
	private ScheduleCensusResult[] scheduleCensusResultList;

	private JTextField jTextField1;
	private JTextField jTextField2;
	
	private JTextField jTextFieldDateFrom;
	private JTextField jTextFieldDateTo;

	private JComboBox<ComboItem> jComboBoxSchedule;
	private JComboBox<ComboItem> jComboBoxCensusTaker;
	private JComboBox<ComboItem> jComboBoxScheduleCensusResult;

	private JCheckBox jCheckBox1;
	private JCheckBox jCheckBox2;

	private JButton jButtonBuild;

	public JPanelScheduleCensusList() {

		initComponents();
	}

	private void initComponents() {

		jPanelCensusViewCustom = new JPanelCensusViewCustom();
		jPanelCensusViewCustom.setBackground(new Color(55, 96, 145));

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("Cron.", Integer.class, 50);
		addColumnMetaData("DDZZMMMPPP", String.class, 110);
		addColumnMetaData("N°", Integer.class, 30);
		addColumnMetaData("Tipo", String.class, 30);
		addColumnMetaData("Censado", Date.class, 90);
		addColumnMetaData("Censista", String.class, 150);
		addColumnMetaData("CR", String.class, 40);
		addColumnMetaData("Cerrado", Date.class, 90);

		super.initComponents("Planilla de Censo", jPanelCensusViewCustom, initToolBar1(), initToolBar2(), new Color(55, 96, 145), Color.WHITE);

	}

	@SuppressWarnings("unchecked")
	private Component[] initToolBar1() {

		jButtonBuild = new JButton();
		jButtonBuild.setToolTipText("Nueva planilla de Censo.");
		jButtonBuild.setIcon(new ImageIcon(JFrameMainGui.iconPath + "run-build.png")); // NOI18N
		jButtonBuild.setPreferredSize(new Dimension(27, 27));

		jButtonBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buildScheduleCensus();
			}
		});

		JLabel jLabel1 = UtilComponent.buildJLabelField("Crononograma:");
		jLabel1.setForeground(Color.WHITE);
		jLabel1.setToolTipText("Buscar por año (cronograma)");
		JLabel jLabel2 = UtilComponent.buildJLabelField("Nomenclatura Catastral:");
		jLabel2.setForeground(Color.WHITE);
		jLabel2.setToolTipText("Nomenclatura Catastral");
		JLabel jLabel3 = UtilComponent.buildJLabelField("Censado Desde:");
		jLabel3.setForeground(Color.WHITE);
		JLabel jLabel4 = UtilComponent.buildJLabelField("Hasta:");
		jLabel4.setForeground(Color.WHITE);

		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetSearch();
			}
		};

		jTextField1 = UtilComponent.buildFieldJTextField(100, "Buscar por nomenclatura catastral", actionListener1);

		jTextField2 = UtilComponent.buildFieldJTextField(100, "Buscar por código de lote", actionListener1);
		
		jTextFieldDateFrom = UtilComponent.buildFieldJTextField(70, "Buscar por fecha de censado desde", actionListener1);
		jTextFieldDateTo = UtilComponent.buildFieldJTextField(70, "Buscar por fecha de censado hasta", actionListener1);

		jComboBoxSchedule = new JComboBox<ComboItem>();
		jComboBoxSchedule.setToolTipText("Buscar por cronograma");
		scheduleLoadData();
		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}

		Component[] headerRowList = { jButtonBuild, jLabel1, jComboBoxSchedule, jLabel2, jTextField1, jLabel3, jTextFieldDateFrom, jLabel4, jTextFieldDateTo };

		return headerRowList;
	}

	private Component[] initToolBar2() {

		JLabel jLabel1 = UtilComponent.buildJLabelField("Censista:");
		jLabel1.setForeground(Color.WHITE);
		JLabel jLabel2 = UtilComponent.buildJLabelField("Resultado:");
		jLabel2.setForeground(Color.WHITE);

		jCheckBox1 = UtilComponent.buildCheckBoxField("Con Lote:");
		jCheckBox1.setToolTipText("Buscar las planillas asignadas a un lote");
		jCheckBox1.setForeground(Color.WHITE);
		jCheckBox1.setSelected(true);
		jCheckBox1.setHorizontalTextPosition(SwingConstants.LEFT);

		jCheckBox2 = UtilComponent.buildCheckBoxField("Con Lote y Cerrado:");
		jCheckBox2.setForeground(Color.WHITE);
		jCheckBox2.setToolTipText("Buscar las planillas asignadas a un lote y que el lote este cerrado");
		jCheckBox2.setHorizontalTextPosition(SwingConstants.LEFT);

		jComboBoxCensusTaker = new JComboBox<ComboItem>();
		jComboBoxCensusTaker.setToolTipText("Buscar por censista");
		censusTakerLoadData();
		if (jComboBoxCensusTaker.getItemCount() > 0) {
			jComboBoxCensusTaker.setSelectedIndex(0);
		}

		jComboBoxScheduleCensusResult = new JComboBox<ComboItem>();
		jComboBoxScheduleCensusResult.setToolTipText("Buscar por resultado de censo");
		scheduleCensusResultLoadData();
		if (jComboBoxScheduleCensusResult.getItemCount() > 0) {
			jComboBoxScheduleCensusResult.setSelectedIndex(0);
		}

		Component[] headerRowList = { jLabel1, jComboBoxCensusTaker, jLabel2, jComboBoxScheduleCensusResult, jCheckBox1, jCheckBox2 };

		return headerRowList;
	}

	public void clearFilter() {

		scheduleLoadData();
		censusTakerLoadData();
		scheduleCensusResultLoadData();

		jTextField1.setText("");
		jTextField2.setText("");

		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}
		if (jComboBoxCensusTaker.getItemCount() > 0) {
			jComboBoxCensusTaker.setSelectedIndex(0);
		}
		if (jComboBoxScheduleCensusResult.getItemCount() > 0) {
			jComboBoxScheduleCensusResult.setSelectedIndex(0);
		}

		jCheckBox1.setSelected(true);
		jCheckBox2.setSelected(false);
	}

	protected void find(int offSet, int limit) throws Exception {

		String scheduleId = null;
		String cadastralCode = jTextField1.getText();
		Date censusedFrom = null;
		Date censusedTo = null;
		String scheduleBatchCode = jTextField2.getText();
		Boolean scheduleBatch = jCheckBox1.isSelected();
		Boolean scheduleBatchClose = jCheckBox2.isSelected();
		String censusTakerId = null;
		String scheduleCensusResultId = null;

		if (jComboBoxSchedule.getSelectedItem() != null) {
			scheduleId = ((ComboItem) jComboBoxSchedule.getSelectedItem()).getId();
		}
		if (jComboBoxCensusTaker.getSelectedItem() != null) {
			censusTakerId = ((ComboItem) jComboBoxCensusTaker.getSelectedItem()).getId();
		}
		if (jComboBoxScheduleCensusResult.getSelectedItem() != null) {
			scheduleCensusResultId = ((ComboItem) jComboBoxScheduleCensusResult.getSelectedItem()).getId();
		}

		rl = Services.getInstance().findScheduleCensusListByExample(offSet, limit);

		model = (Object[][]) rl.getList();

		for (int i = 0; i < model.length; i++) {
			if (model[i][1] != null) {
				model[i][2] = UtilCadastre.formatCadastralCode(model[i][2].toString().trim());
			}
		}

	}

	private void scheduleLoadData() {

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

			jComboBoxSchedule.addItem(new ComboItem("", null));

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

	private void buildScheduleCensus() {

		try {

			// -----------------------------------------------------------------------

			String cadastralCode = JOptionPane.showInputDialog(this, "DDZZMMMPPP", "Nueva planilla de Censo", JOptionPane.QUESTION_MESSAGE);

			if (cadastralCode == null) {
				return;
			} else if (cadastralCode.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Error: Debe ingresar la nomeclatura catastral DDZZMMMPPP", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (cadastralCode.trim().length() != 10) {
				JOptionPane.showMessageDialog(null, "Error: Debe ingresar la nomeclatura catastral DDZZMMMPPP", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			cadastralCode = cadastralCode.trim() + "000";

			for (char c : cadastralCode.toCharArray()) {
				if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {

				} else {
					JOptionPane.showMessageDialog(null, "Error: Debe ingresar la nomeclatura catastral DDZZMMMPPP", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// -----------------------------------------------------------------------

			boolean b = Services.getInstance().isExitsCadastrealCode(cadastralCode);
			if (b) {
				JOptionPane.showMessageDialog(null, "Error: La nomeclatura catastral " + cadastralCode + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// -----------------------------------------------------------------------

			String ufId = JOptionPane.showInputDialog(this, "Unidad de Facturación", "Nueva planilla de Censo", JOptionPane.QUESTION_MESSAGE);

			if (ufId == null) {
				return;
			} else if (ufId.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Error: Debe ingresar una UF válida", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			b = Services.getInstance().isExitsUfId(ufId);
			if (b == false) {
				JOptionPane.showMessageDialog(null, "Error: La UF " + ufId + " NO existe", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// -----------------------------------------------------------------------

			String censusId = null;
			String cadastreId = null;

			String tmp = "para " + UtilCadastre.formatCadastralCode(cadastralCode);

			cadastreId = null;

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(this, "¿ Esta seguro de generar un censo ? " + tmp, "Nueva planillas de censo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				return;
			}

			censusId = Services.getInstance().insertScheduleCensus(cadastreId, cadastralCode.substring(0, 10), ufId);

			JDialogCensusView jDialogCensusView = new JDialogCensusView(null, censusId);

			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - jDialogCensusView.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - jDialogCensusView.getHeight()) / 2);
			jDialogCensusView.setLocation(x, y);

			jDialogCensusView.setVisible(true);

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

}
