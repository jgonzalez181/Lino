package com.cclip.gui.schedule.scanning;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cclip.Context;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningBo;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.gui.util.JPanelList;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.services.Services;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

public class JPanelScheduleScanning extends JPanelList {

	private static final long serialVersionUID = 8740413335514099146L;

	public JPanelScheduleScanningForm jPanelScheduleScanningForm1;

	public JTextField jTextField1;
	public JTextField jTextField2;

	public JComboBox<ComboItem> jComboBoxSchedule;
	public JComboBox<ComboItem> jComboBox2;
	public JComboBox<ComboItem> jComboBox3;
	public JComboBox<ComboItem> jComboBox4;
	public JComboBox<ComboItem> jComboBox5;
	public JComboBox<ComboItem> jComboBox6;

	public JCheckBox jCheckBox1;
	public JCheckBox jCheckBox2;
	public JCheckBox jCheckBox3;
	public JCheckBox jCheckBox4;
	// public JCheckBox jCheckBox5;

	private JButton jButtonBuild;

	private CensusTaker[] censusTakerList;
	private Schedule[] scheduleList;

	public JPanelScheduleScanning() {

		initComponents();

	}

	private void initComponents() {

		jPanelScheduleScanningForm1 = new JPanelScheduleScanningForm(this);

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("Año", Integer.class, 70);
		addColumnMetaData("DDZZMMM", String.class, 90);
		addColumnMetaData("N°", Integer.class, 50);
		addColumnMetaData("Barrido", Date.class, 90);
		addColumnMetaData("Censista", String.class, 170);
		// addColumnMetaData("Lote Planificado", Date.class, 90);
		// addColumnMetaData("Lote Cerrado", Date.class, 90);

		super.initComponents("Planilla de Barrido", jPanelScheduleScanningForm1, initToolBar1(), initToolBar2(), new Color(49, 132, 155), Color.WHITE);

	}

	public void clearFilter() {

		scheduleLoadData();
		jComboBox2LoadData();
		jComboBox4LoadData();
		jComboBox6LoadData();

		jTextField1.setText("");
		jTextField2.setText("");

		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}
		if (jComboBox2.getItemCount() > 0) {
			jComboBox2.setSelectedIndex(0);
		}
		if (jComboBox3.getItemCount() > 0) {
			jComboBox3.setSelectedIndex(0);
		}
		if (jComboBox4.getItemCount() > 0) {
			jComboBox4.setSelectedIndex(0);
		}
		if (jComboBox5.getItemCount() > 0) {
			jComboBox5.setSelectedIndex(0);
		}
		if (jComboBox6.getItemCount() > 0) {
			jComboBox6.setSelectedIndex(0);
		}

		jCheckBox1.setSelected(true);
		jCheckBox2.setSelected(true);
		jCheckBox3.setSelected(true);
		jCheckBox4.setSelected(true);
		// jCheckBox5.setSelected(false);

	}

	@SuppressWarnings("unchecked")
	private Component[] initToolBar1() {

		jButtonBuild = new JButton();
		jButtonBuild.setToolTipText("Nuevo barrido de zona.");
		jButtonBuild.setIcon(new ImageIcon(JFrameMainGui.iconPath + "run-build.png")); // NOI18N
		jButtonBuild.setPreferredSize(new Dimension(27, 27));

		jButtonBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buildScheduleScanning();
			}
		});

		jComboBoxSchedule = new JComboBox<ComboItem>();
		jComboBox2 = new JComboBox<ComboItem>();
		jComboBox3 = UtilComponent.buildJPanelFieldM(0);
		jComboBox3.setToolTipText("Buscar por mes en que se barrio (desde)");
		jComboBox4 = new JComboBox<ComboItem>();
		jComboBox5 = UtilComponent.buildJPanelFieldM(0);
		jComboBox5.setToolTipText("Buscar por mes en que se barrio (hasta)");
		jComboBox6 = new JComboBox<ComboItem>();

		JLabel jLabel1 = new JLabel("Cronograma:");
		jLabel1.setForeground(Color.WHITE);
		JLabel jLabel2 = new JLabel("Manzana (DDZZMMM)");
		jLabel2.setForeground(Color.WHITE);
		JLabel jLabel4 = new JLabel("N°:");
		jLabel4.setForeground(Color.WHITE);
		JLabel jLabel3 = new JLabel("Censista:");
		jLabel3.setForeground(Color.WHITE);

		jComboBox2.setToolTipText("Buscar por censista");
		jComboBox2LoadData();
		if (jComboBox2.getItemCount() > 0) {
			jComboBox2.setSelectedIndex(0);
		}

		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetSearch();
			}
		};

		jTextField1 = UtilComponent.buildFieldJTextField(100, "Buscar por código de planilla de barrido", actionListener1);

		jTextField2 = UtilComponent.buildFieldJTextField(100, "Buscar por código de lote", actionListener1);

		jComboBoxSchedule.setToolTipText("Buscar por cronograma (Año)");
		scheduleLoadData();
		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}

		jCheckBox3 = new JCheckBox("Planilla Atrazada:");
		jCheckBox3.setHorizontalTextPosition(SwingConstants.LEFT);
		jCheckBox3.setSelected(true);
		jCheckBox3.setToolTipText("Buscar las planillas que no se terminaron de cerrar");
		jCheckBox3.setForeground(Color.WHITE);

		Component[] headerRowList = { jButtonBuild, jLabel1, jComboBoxSchedule, jLabel2, jTextField1, jLabel3, jComboBox2 };

		return headerRowList;

	}

	private Component[] initToolBar2() {

		JLabel jLabel1 = new JLabel("Barrido Desde:");
		jLabel1.setForeground(Color.WHITE);
		JLabel jLabel2 = new JLabel("Hasta:");
		jLabel2.setForeground(Color.WHITE);

		jComboBox4.setToolTipText("Buscar por año en que se barrio (desde)");
		jComboBox4LoadData();
		if (jComboBox4.getItemCount() > 0) {
			jComboBox4.setSelectedIndex(0);
		}

		jComboBox6.setToolTipText("Buscar por año en que se barrio (hasta)");
		jComboBox6LoadData();
		if (jComboBox6.getItemCount() > 0) {
			jComboBox6.setSelectedIndex(0);
		}

		class ItemChangeListener3 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (jComboBox3.getSelectedIndex() == 0) {
						jComboBox4.setSelectedIndex(0);
					}

				}
			}
		}

		jComboBox3.addItemListener(new ItemChangeListener3());

		class ItemChangeListener4 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (jComboBox4.getSelectedIndex() == 0) {
						jComboBox3.setSelectedIndex(0);
					}
				}
			}
		}

		jComboBox4.addItemListener(new ItemChangeListener4());

		class ItemChangeListener5 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (jComboBox5.getSelectedIndex() == 0) {
						jComboBox6.setSelectedIndex(0);
					}
				}
			}
		}

		jComboBox5.addItemListener(new ItemChangeListener5());

		class ItemChangeListener6 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (jComboBox6.getSelectedIndex() == 0) {
						jComboBox5.setSelectedIndex(0);
					}
				}
			}
		}

		jComboBox6.addItemListener(new ItemChangeListener6());

		jCheckBox1 = new JCheckBox("Planilla sin Barrer:");
		jCheckBox1.setHorizontalTextPosition(SwingConstants.LEFT);
		jCheckBox1.setSelected(true);
		jCheckBox1.setToolTipText("Buscar las planillas que no se cargó la fecha de barrido");
		jCheckBox1.setForeground(Color.WHITE);

		jCheckBox2 = new JCheckBox("Planilla sin Cerrar:");
		jCheckBox2.setHorizontalTextPosition(SwingConstants.LEFT);
		jCheckBox2.setSelected(true);
		jCheckBox2.setToolTipText("Buscar las planillas que no se terminaron de cerrar");
		jCheckBox2.setForeground(Color.WHITE);

		jCheckBox4 = new JCheckBox("Con Lote:");
		jCheckBox4.setHorizontalTextPosition(SwingConstants.LEFT);
		jCheckBox4.setSelected(true);
		jCheckBox4.setToolTipText("Buscar las planillas asignadas a un lote");
		jCheckBox4.setForeground(Color.WHITE);

		// jCheckBox5 = new JCheckBox("Con Lote Cerrado:");
		// jCheckBox5.setHorizontalTextPosition(SwingConstants.LEFT);
		// jCheckBox5.setSelected(false);
		// jCheckBox5
		// .setToolTipText("Buscar las planillas asignadas a un lote y que el lote este cerrado");

		Component[] headerRowList = { jLabel1, jComboBox3, jComboBox4, jLabel2, jComboBox5, jComboBox6, jCheckBox1, jCheckBox2 /*
																																 * , jCheckBox5
																																 */};

		return headerRowList;

	}

	public void find(int offSet, int limit) throws Exception {

		String scheduleId = null;
		String cadastralCode = jTextField1.getText();
		Date scanningFrom = null;
		Date scanningTo = null;
		String scheduleBatchCode = jTextField2.getText();
		Boolean scheduleBatch = null; // jCheckBox4.isSelected();
		// Boolean scheduleBatchClose = jCheckBox5.isSelected();
		Boolean scanning = jCheckBox1.isSelected();
		Boolean recorded = jCheckBox2.isSelected();
		Boolean delay = null; // jCheckBox3.isSelected();
		String censusTakerId = null;
		String scheduleScanningId = null;

		if (jComboBoxSchedule.getSelectedItem() != null) {
			scheduleId = ((ComboItem) jComboBoxSchedule.getSelectedItem()).getId();

		}

		if (jComboBox2.getSelectedItem() != null) {
			censusTakerId = ((ComboItem) jComboBox2.getSelectedItem()).getId();

		}

		if (jComboBox3.getSelectedItem() != null && jComboBox4.getSelectedItem() != null) {

			scanningFrom = UtilComponent.getDate(((ComboItem) jComboBox3.getSelectedItem()).getId(), ((ComboItem) jComboBox4.getSelectedItem()).getId());

		}

		if (jComboBox5.getSelectedItem() != null && jComboBox6.getSelectedItem() != null) {

			scanningTo = UtilComponent.getDate(((ComboItem) jComboBox5.getSelectedItem()).getId(), ((ComboItem) jComboBox6.getSelectedItem()).getId());

		}

		CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

		rl = bo.find1(scheduleId, cadastralCode, scanningFrom, scanningTo, scheduleBatchCode, scheduleBatch, /*
																											 * scheduleBatchClose ,
																											 */scanning, recorded, delay, censusTakerId, scheduleScanningId, offSet, limit);

		model = new Object[rl.getList().length][8];

		for (int i = 0; i < rl.getList().length; i++) {

			ScheduleScanning scheduleScanning = (ScheduleScanning) rl.getList()[i];

			model[i][0] = scheduleScanning.getId();

			if (scheduleScanning.getSchedule() != null) {

				model[i][1] = scheduleScanning.getSchedule().getYear();
			} else {

				model[i][1] = null;
			}

			model[i][2] = UtilCadastre.formatCadastralCode(scheduleScanning.getCadastralCode());

			model[i][3] = scheduleScanning.getNumberScanning();

			model[i][4] = scheduleScanning.getScanning();

			if (scheduleScanning.getCensusTaker() != null) {

				model[i][5] = scheduleScanning.getCensusTaker().getFamilyName() + " " + scheduleScanning.getCensusTaker().getGivenName();

			} else {

				model[i][6] = null;
			}

		}

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

	public void jComboBox2LoadData() {

		try {

			jComboBox2.removeAllItems();

			jComboBox2.addItem(new ComboItem("", null));

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			censusTakerList = bo.find3();

			if (censusTakerList != null) {

				for (int i = 0; i < censusTakerList.length; i++) {
					jComboBox2.addItem(new ComboItem(censusTakerList[i].getFamilyName() + " " + censusTakerList[i].getGivenName() + " (" + censusTakerList[i].getCuil() + ")", censusTakerList[i]
							.getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBox4LoadData() {

		try {

			jComboBox4.removeAllItems();

			jComboBox4.addItem(new ComboItem("", null));

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			Integer[] rl = bo.find2(true);

			if (rl != null) {

				for (int i = 0; i < rl.length; i++) {
					jComboBox4.addItem(new ComboItem(rl[i].toString(), rl[i].toString()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBox6LoadData() {

		try {

			jComboBox6.removeAllItems();

			jComboBox6.addItem(new ComboItem("", null));

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			Integer[] rl = bo.find2(false);

			if (rl != null) {

				for (int i = 0; i < rl.length; i++) {
					jComboBox6.addItem(new ComboItem(rl[i].toString(), rl[i].toString()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void buildScheduleScanning() {

		new JDialogScheduleScanningBuild(this);

	}

}
