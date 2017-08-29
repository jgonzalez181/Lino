/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cclip.gui.schedule.scanning;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;
import com.cclip.util.UtilDate;

/**
 *
 * @author java
 */
public class JDialogScheduleScanningGeneralForm extends javax.swing.JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 226614053449352232L;
	private ScheduleScanning scheduleScanning;
	private CensusTaker[] censusTakerList;
	private Schedule[] scheduleList;

	private JPanelScheduleScanningGeneralForm jPanelScheduleScanningGeneralForm;

	private JTable jTable;
	private Integer row;

	/**
	 * Creates new form JDialogScheduleScanningGeneralForm
	 */
	public JDialogScheduleScanningGeneralForm(ScheduleScanning scheduleScanning, JPanelScheduleScanningGeneralForm jPanelScheduleScanningGeneralForm, JTable jTable, Integer row) {

		this.jTable = jTable;
		this.row = row;

		this.scheduleScanning = scheduleScanning;
		this.jPanelScheduleScanningGeneralForm = jPanelScheduleScanningGeneralForm;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		this.setTitle("Generar Planillas de Barrido de Zona");
		this.setIconImage(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "cclip.jpg").getImage());
		this.setModal(true);
		this.setMinimumSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500, 500));
		this.setSize(500, 300);
		this.setResizable(false);
		setUndecorated(false);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 500) / 2);
		int y = (int) ((dimension.getHeight() - 500) / 2);
		// setLocation(x - 250, y - 200);
		setLocation(x, y);

		initComponents();

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButton1.addActionListener(this);

		scheduleLoadData();

		censusTakerLoadData();
		// jComboBox5LoadData();
		jComboBox6LoadData();

		this.jTextArea1.setText(scheduleScanning.getComment());

		class ItemChangeListener6 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					jCheckBox1.setText("");
					jCheckBox1.setSelected(false);

					jComboBox4.removeAllItems();
					jComboBox4.setEnabled(false);
					jComboBox5.removeAllItems();
					jComboBox5.setEnabled(false);

					if (jComboBox6.getSelectedIndex() > 0) {
						jComboBox5LoadData();
					}
				}
			}
		}

		jComboBox6.addItemListener(new ItemChangeListener6());

		class ItemChangeListener5 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					jComboBox4.removeAllItems();
					jComboBox4.setEnabled(false);

					if (jComboBox5.getSelectedIndex() > -1) {
						jComboBox4LoadData();
					}
				}
			}
		}

		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setRecorded();
			}
		});

		jComboBox5.addItemListener(new ItemChangeListener5());

		// scheduleScanning.setScanning(new Date(System.currentTimeMillis()));

		setScanning();

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (jComboBoxSchedule.getSelectedIndex() > -1) {

			scheduleScanning.setSchedule(scheduleList[jComboBoxSchedule.getSelectedIndex()]);

			// scheduleScanning.setScheduleBatch(scheduleBatchList[jComboBox2
			// .getSelectedIndex()]);
		}

		if (jComboBox3.getSelectedIndex() > -1) {
			scheduleScanning.setCensusTaker(censusTakerList[jComboBox3.getSelectedIndex()]);
		} else {
			scheduleScanning.setCensusTaker(null);
		}

		if (jComboBox4.getSelectedIndex() > -1 && jComboBox5.getSelectedIndex() > -1 && jComboBox6.getSelectedIndex() > 0) {

			ComboItem d = (ComboItem) jComboBox4.getSelectedItem();
			ComboItem m = (ComboItem) jComboBox5.getSelectedItem();
			ComboItem y = (ComboItem) jComboBox6.getSelectedItem();

			Date date = UtilComponent.getDate(d.getId(), m.getId(), y.getId());

			scheduleScanning.setScanning(date);

		} else {
			scheduleScanning.setScanning(null);
		}

		if (scheduleScanning.getRecorded() != null) {
			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(this, "Usted esta a punto de cerrar esta planilla, si la cierra no podra modificarla nuevamente", "¿ Cerrar Planilla de Barrido ?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {
				scheduleScanning.setRecorded(null);

			}
		}

		scheduleScanning.setComment(jTextArea1.getText());

		jPanelScheduleScanningGeneralForm.setScheduleScanning(scheduleScanning, jTable, row);

		jPanelScheduleScanningGeneralForm.save();

		setVisible(false);
		dispose();
	}

	private void setRecorded() {
		if (jCheckBox1.isSelected() && jComboBox4.getSelectedIndex() > -1 && jComboBox5.getSelectedIndex() > -1 && jComboBox6.getSelectedIndex() > 0) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date d = new Date(System.currentTimeMillis());

			scheduleScanning.setRecorded(d);

			jCheckBox1.setText(df.format(d));
		} else {
			jCheckBox1.setText("");
			jCheckBox1.setSelected(false);
		}
	}

	private void setScanning() {

		if (scheduleScanning.getScanning() != null) {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(scheduleScanning.getScanning());

			int y = calendar.get(Calendar.YEAR);
			int m = calendar.get(Calendar.MONTH);
			int d = calendar.get(Calendar.DAY_OF_MONTH);

			for (int i = 1; i < jComboBox6.getItemCount(); i++) {
				int yy = new Integer(((ComboItem) (jComboBox6.getItemAt(i))).getId()).intValue();

				if (y == yy) {
					jComboBox6.setSelectedIndex(i);
				}
			}

			for (int i = 0; i < jComboBox5.getItemCount(); i++) {
				int mm = new Integer(((ComboItem) (jComboBox5.getItemAt(i))).getId()).intValue();

				if (m == mm) {
					jComboBox5.setSelectedIndex(i);
				}
			}

			for (int i = 0; i < jComboBox4.getItemCount(); i++) {
				int dd = new Integer(((ComboItem) (jComboBox4.getItemAt(i))).getId()).intValue();

				if (d == dd) {
					jComboBox4.setSelectedIndex(i);
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
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

				if (scheduleList.length > 0) {
					jComboBoxSchedule.setSelectedIndex(0);

					for (int i = 0; i < scheduleList.length; i++) {
						if (scheduleList[i].getId().equals(scheduleScanning.getSchedule().getId())) {

							jComboBoxSchedule.setSelectedIndex(i);

						}
					}

				} else {
					jComboBoxSchedule.addItem(new ComboItem(scheduleScanning.getSchedule().getYear().toString(), scheduleScanning.getSchedule().getId()));
				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	@SuppressWarnings("unchecked")
	private void censusTakerLoadData() {

		try {

			jComboBox3.removeAllItems();

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
					jComboBox3.addItem(new ComboItem(censusTakerList[i].getFamilyName() + " " + censusTakerList[i].getGivenName() + " (" + censusTakerList[i].getCuil() + ")", censusTakerList[i]
							.getId()));
				}

				if (censusTakerList.length > 0) {
					jComboBox3.setSelectedIndex(0);

					for (int i = 0; i < censusTakerList.length; i++) {
						if (censusTakerList[i].getId().equals(scheduleScanning.getCensusTaker().getId())) {

							jComboBox3.setSelectedIndex(i);

						}
					}

				} else {
					jComboBox3.addItem(new ComboItem(scheduleScanning.getCensusTaker().getFamilyName() + " " + scheduleScanning.getCensusTaker().getGivenName() + " ("
							+ scheduleScanning.getCensusTaker().getCuil() + ")", scheduleScanning.getCensusTaker().getId()));
				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void jComboBox4LoadData() {

		try {

			jComboBox4.removeAllItems();
			jComboBox4.setEnabled(true);

			if (jComboBox5.getSelectedItem() != null && jComboBox6.getSelectedItem() != null) {

				int y = new Integer(((ComboItem) (jComboBox6.getSelectedItem())).getId()).intValue();

				int m = new Integer(((ComboItem) (jComboBox5.getSelectedItem())).getId()).intValue();

				int d = UtilDate.cantDias(m + 1, y);

				for (int i = 0; i < d; i++) {
					jComboBox4.addItem(new ComboItem((i + 1) + "", (i + 1) + ""));
				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBox5LoadData() {

		try {

			jComboBox4.removeAllItems();
			jComboBox4.setEnabled(false);
			jComboBox5.removeAllItems();
			jComboBox5.setEnabled(true);

			jComboBox5 = UtilComponent.buildJPanelFieldM(0, jComboBox5, false);

			jComboBox4LoadData();

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void jComboBox6LoadData() {

		try {

			jComboBox4.setEnabled(false);
			jComboBox4.removeAllItems();
			jComboBox5.removeAllItems();
			jComboBox5.setEnabled(false);
			jComboBox6.removeAllItems();

			if (jComboBoxSchedule.getSelectedIndex() > -1) {
				jComboBox6.addItem(new ComboItem(null, ""));
				String y = ((ComboItem) jComboBoxSchedule.getSelectedItem()).getName();
				jComboBox6.addItem(new ComboItem(y, y));
				int y2 = new Integer(y).intValue() + 1;
				jComboBox6.addItem(new ComboItem(y2 + "", y2 + ""));
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jComboBoxSchedule = new javax.swing.JComboBox();
		jLabel3 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jLabel6 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jComboBox4 = new javax.swing.JComboBox();
		jComboBox5 = new javax.swing.JComboBox();
		jComboBox6 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jLabel1.setForeground(new java.awt.Color(0, 102, 153));
		jLabel1.setText("Cronograma (Año):");

		jComboBoxSchedule.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel3.setForeground(new java.awt.Color(0, 102, 153));
		jLabel3.setText("Censista:");

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel4.setForeground(new java.awt.Color(0, 102, 153));
		jLabel4.setText("Fecha de Barrido:");

		jLabel5.setForeground(new java.awt.Color(0, 102, 153));
		jLabel5.setText("Cerrada:");

		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox1ActionPerformed(evt);
			}
		});

		jLabel6.setForeground(new java.awt.Color(0, 102, 153));
		jLabel6.setText("Comentario de la Planilla de Barrido:");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jButton1.setIcon(new javax.swing.ImageIcon("/home/java/jaspe/cclip/icon/application-exit.png")); // NOI18N
		jButton1.setText("Salir");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel6).addGap(0, 0, Short.MAX_VALUE))
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addGroup(
										jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(230, 230, 230).addComponent(jButton1))
												.addGroup(
														jPanel1Layout
																.createSequentialGroup()
																.addGroup(
																		jPanel1Layout
																				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						jPanel1Layout
																								.createSequentialGroup()
																								.addContainerGap()
																								.addGroup(
																										jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(jLabel5).addComponent(jLabel4).addComponent(jLabel3)))
																				.addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel1)))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		jPanel1Layout
																				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jComboBoxSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																								.addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(jCheckBox1))
																				.addGroup(
																						jPanel1Layout
																								.createSequentialGroup()
																								.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))).addContainerGap(209, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jComboBoxSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3)
												.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel4)
												.addGroup(
														jPanel1Layout
																.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(13, 13, 13)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5).addComponent(jCheckBox1))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton1).addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JComboBox jComboBoxSchedule;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JComboBox jComboBox6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration
}
