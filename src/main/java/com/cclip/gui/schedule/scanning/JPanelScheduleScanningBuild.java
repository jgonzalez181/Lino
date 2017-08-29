/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cclip.gui.schedule.scanning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.cclip.Context;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningBo;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.services.Services;
import com.cclip.util.LinoProperties;
import com.cclip.util.Plancheta;
import com.cclip.util.UtilComponent;
import com.cclip.util.UtilDate;

/**
 *
 * @author java
 */
public class JPanelScheduleScanningBuild extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5583462216133030774L;

	private CensusTaker[] censusTakerList;
	private Schedule[] scheduleList;

	private JPanelScheduleScanning jPanelScheduleScanning;
	private JDialogScheduleScanningBuild jDialogScheduleScanningBuild;
	private JFileChooser jFileChooser1;

	private LinoProperties linoProperties;
	private Plancheta plancheta;

	private ScheduleScanning[] scheduleScanningList;

	/**
	 * Creates new form JPanelScheduleScanningBuild
	 */
	public JPanelScheduleScanningBuild(JPanelScheduleScanning jPanelScheduleScanning, JDialogScheduleScanningBuild jDialogScheduleScanningBuild) {

		this.jPanelScheduleScanning = jPanelScheduleScanning;
		this.jDialogScheduleScanningBuild = jDialogScheduleScanningBuild;

		initComponents();
		jTextField1.setEnabled(false);

		jComboBoxSchedule.removeAllItems();
		jComboBox4.removeAllItems();

		linoProperties = Context.getBean("linoProperties", LinoProperties.class);
		plancheta = new Plancheta();

		jFileChooser1 = new JFileChooser();
		jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser1.setMultiSelectionEnabled(false);

		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				build();
			}
		});

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				downloadDD();
			}
		});

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				downloadDDZZ();

			}
		});

		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				downloadDDZZMM();

			}
		});

		scheduleLoadData();

		class ItemChangeListener7 implements ItemListener {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					jComboBox5.removeAllItems();
					jComboBox5.setEnabled(false);
					jComboBox6.removeAllItems();
					jComboBox6.setEnabled(false);

					if (jComboBox7.getSelectedIndex() > 0) {
						jComboBox6LoadData();
					}
				}
			}
		}

		jComboBox7.addItemListener(new ItemChangeListener7());

		class ItemChangeListener6 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					jComboBox5.removeAllItems();
					jComboBox5.setEnabled(false);

					if (jComboBox6.getSelectedIndex() > -1) {
						jComboBox5LoadData();
					}
				}
			}
		}

		jComboBox6.addItemListener(new ItemChangeListener6());

		jComboBox7LoadData();

		jPanel1.setVisible(false);

	}

	private void setEnableY(boolean b) {
		jDialogScheduleScanningBuild.button.setEnabled(b);
		jButton1.setEnabled(b);
		jButton2.setEnabled(b);
		// jButton3.setEnabled(b);
		jButton4.setEnabled(b);

		jComboBoxSchedule.setEnabled(b);

		jComboBox4.setEnabled(b);

	}

	private void setEnableX(boolean b) {
		jDialogScheduleScanningBuild.button.setEnabled(b);
		jButton1.setEnabled(b);
		jButton2.setEnabled(b);
		jButton3.setEnabled(b);
		// jButton4.setEnabled(b);

		jComboBoxSchedule.setEnabled(b);

		jComboBox4.setEnabled(b);

		jComboBox5.setEnabled(b);
		jComboBox6.setEnabled(b);
		jComboBox7.setEnabled(b);

		jTextField1.setEnabled(b);

	}

	private void downloadDDZZMM() {

		try {

			jLabel8.setText("Genrando planillas PDF ...");
			setEnableY(false);

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(this, "Esta operación puede tardar ¿ Esta seguro de proseguir ? ", "¿ Gerar PDF de planillas de barrido ?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				setEnableY(true);
				jLabel8.setText("Planillas");

				return;
			}

			buildJasper();

			if (scheduleScanningList != null && scheduleScanningList.length > 0) {

				// System.out.println("314252 : " + "314252".substring(0, 2) );
				// System.out.println("314252 : " +"314252".substring(2, 4) );

				String districtCode = scheduleScanningList[0].getCadastralCode().substring(0, 2);
				String zoneCode = scheduleScanningList[0].getCadastralCode().substring(2, 4);

				int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningBuild.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser1.getSelectedFile();

					// String districtCode = jComboBox1.getSelectedItem()
					// .toString();
					// String zoneCode =
					// jComboBox5.getSelectedItem().toString();
					String year = jComboBoxSchedule.getSelectedItem().toString();
					String path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

					File fileResult = plancheta.getZoneCatastroReportZip(path, districtCode, zoneCode, year, file.getPath());

					JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + fileResult.getAbsolutePath(), "Descarga de Archivo", JOptionPane.INFORMATION_MESSAGE);

				}

			}

			jLabel8.setText("Planillas");
			setEnableY(true);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void downloadDD() {
		try {

			// if (jComboBox1.getSelectedItem() != null) {
			//
			// String path = confSystem.getUrlFiles() + File.separatorChar
			// + "parcelarios_mapas";
			//
			// String dd = jComboBox1.getSelectedItem().toString();
			//
			// byte[] b;
			// b = plancheta.getDistrictCatastro(path, dd);
			// String ext = new Plancheta().getDistrictCatastroExt(path, dd);
			//
			// int returnVal = jFileChooser1
			// .showOpenDialog(JPanelScheduleScanningBuild.this);
			//
			// if (returnVal == JFileChooser.APPROVE_OPTION) {
			// File file = jFileChooser1.getSelectedFile();
			//
			// SO.writeSmallBinaryFile(b, file.getAbsolutePath()
			// + File.separatorChar + "D" + dd + "." + ext);
			//
			// JOptionPane
			// .showMessageDialog(null,
			// "El archivo se descargo con éxito en "
			// + file.getAbsolutePath()
			// + File.separatorChar + "D" + dd
			// + "." + ext, "Descarga de Archivo",
			// JOptionPane.INFORMATION_MESSAGE);
			//
			// }
			//
			// }

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void downloadDDZZ() {
		try {

			// if (jComboBox1.getSelectedItem() != null
			// && jComboBox5.getSelectedItem() != null) {
			//
			// String path = confSystem.getUrlFiles() + File.separatorChar
			// + "parcelarios_mapas";
			//
			// String dd = jComboBox1.getSelectedItem().toString();
			//
			// String zz = jComboBox5.getSelectedItem().toString();
			//
			// int returnVal = jFileChooser1
			// .showOpenDialog(JPanelScheduleScanningBuild.this);
			//
			// if (returnVal == JFileChooser.APPROVE_OPTION) {
			//
			// File file = jFileChooser1.getSelectedFile();
			//
			// File fileResult = plancheta.getZoneCatastroZip(path, dd,
			// zz, file.getPath());
			//
			// JOptionPane.showMessageDialog(
			// null,
			// "El archivo se descargo con éxito en "
			// + fileResult.getAbsolutePath(),
			// "Descarga de Archivo",
			// JOptionPane.INFORMATION_MESSAGE);
			//
			// }
			//
			// }

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void build() {

		try {

			setEnableX(false);

			jLabel9.setText("Procesando ...");

			String cadastralCodeDDZZ = null;
			Date plannedDelivered = null;
			CensusTaker censusTaker = null;
			Schedule schedule = null;

			if (scheduleList != null) {
				schedule = scheduleList[jComboBoxSchedule.getSelectedIndex()];
			}
			if (censusTakerList != null) {
				censusTaker = censusTakerList[jComboBox4.getSelectedIndex()];
			}

			cadastralCodeDDZZ = jTextField1.getText();

			if (jComboBox5.getSelectedIndex() > -1 && jComboBox6.getSelectedIndex() > -1 && jComboBox7.getSelectedIndex() > 0) {

				ComboItem d = (ComboItem) jComboBox5.getSelectedItem();
				ComboItem m = (ComboItem) jComboBox6.getSelectedItem();
				ComboItem y = (ComboItem) jComboBox7.getSelectedItem();

				plannedDelivered = UtilComponent.getDate(d.getId(), m.getId(), y.getId());

			} else {
				plannedDelivered = null;
			}

			if (schedule == null) {

				JOptionPane.showMessageDialog(this, "Se requiere un cronograma (año) para poder generar las planillas", "Campo Requerido", JOptionPane.ERROR_MESSAGE, new ImageIcon(
						JFrameMainGui.iconPath + "dialog-error.png"));

				jLabel9.setText("");

				setEnableX(true);

				return;
			}
			if (censusTaker == null) {

				JOptionPane.showMessageDialog(this, "Se requiere un censista para poder generar las planillas", "Campo Requerido", JOptionPane.ERROR_MESSAGE, new ImageIcon(JFrameMainGui.iconPath
						+ "dialog-error.png"));

				jLabel9.setText("");

				setEnableX(true);

				return;
			}
			if (cadastralCodeDDZZ == null) {

				JOptionPane.showMessageDialog(this, "Se requiere un distrito (DD) y zona (ZZ) para poder generar las planillas", "Campo Requerido", JOptionPane.ERROR_MESSAGE, new ImageIcon(
						JFrameMainGui.iconPath + "dialog-error.png"));

				jLabel9.setText("");

				setEnableX(true);

				return;
			}

			if (cadastralCodeDDZZ.trim().length() != 4) {

				JOptionPane.showMessageDialog(this, "Se requiere un distrito (DD) y zona (ZZ) para poder generar las planillas", "El código debe estar formado por distrito (DD) y zona (ZZ)",
						JOptionPane.ERROR_MESSAGE, new ImageIcon(JFrameMainGui.iconPath + "dialog-error.png"));

				jLabel9.setText("");

				setEnableX(true);

				return;
			}

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(this, "Usted esta a punto de generar un conjunto de planillas de barridos de manzanas ¿ Esta seguro de proseguir ? ",
					"¿ Generar Barrido de Zona ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon((JFrameMainGui.iconPath + "user-busy.png")), options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				jLabel9.setText("");

				setEnableX(true);

				return;
			}

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			scheduleScanningList = bo.buildScheduleScanning(cadastralCodeDDZZ, plannedDelivered, censusTaker, schedule);

			// buildJasper();

			jLabel9.setText("Se generaron " + scheduleScanningList.length + " planillas de barrido.");

			if (scheduleScanningList != null && scheduleScanningList.length > 0) {

				jPanelScheduleScanning.clearFilter();
				jPanelScheduleScanning.jTextField1.setText(cadastralCodeDDZZ);
				jPanelScheduleScanning.jComboBox2LoadData();

				if (censusTaker != null) {
					for (int i = 0; i < jPanelScheduleScanning.jComboBox2.getItemCount(); i++) {
						ComboItem ci = (ComboItem) jPanelScheduleScanning.jComboBox2.getItemAt(i);
						if (ci.getId() != null && ci.getId().equals(censusTaker.getId())) {
							jPanelScheduleScanning.jComboBox2.setSelectedIndex(i);
							break;
						}
					}
				}

				if (schedule != null) {
					for (int i = 0; i < jPanelScheduleScanning.jComboBoxSchedule.getItemCount(); i++) {
						ComboItem ci = (ComboItem) jPanelScheduleScanning.jComboBoxSchedule.getItemAt(i);
						if (ci.getId() != null && ci.getId().equals(schedule.getId())) {
							jPanelScheduleScanning.jComboBoxSchedule.setSelectedIndex(i);
							break;
						}
					}
				}

				jPanelScheduleScanning.searchData();

				jButton1.setEnabled(true);
				jButton3.setEnabled(true);
			}

			setEnableX(true);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void buildJasper() {
		try {

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			if (scheduleScanningList != null) {

				jLabel8.setText("Genrando planillas. Creando PDF ...");

				bo.buildScheduleScanningJasper(scheduleScanningList);

			} else {

			}

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	@SuppressWarnings({ "unchecked", "unused" })
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
					jComboBox4LoadData();

				}
				jButton4.setEnabled(scheduleList.length > 0);
				jComboBoxSchedule.setEnabled(scheduleList.length > 0);
				jTextField1.setEnabled(scheduleList.length > 0);
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	@SuppressWarnings("unchecked")
	private void jComboBox4LoadData() {

		try {

			jComboBox4.removeAllItems();

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
					jComboBox4.addItem(new ComboItem(censusTakerList[i].getFamilyName() + " " + censusTakerList[i].getGivenName() + " (" + censusTakerList[i].getCuil() + ")", censusTakerList[i]
							.getId()));
				}

				if (censusTakerList.length > 0) {
					jComboBox4.setSelectedIndex(0);

				}

				boolean b = censusTakerList.length > 0;
				jButton4.setEnabled(b);
				jComboBoxSchedule.setEnabled(b);
				jComboBox4.setEnabled(b);

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void jComboBox7LoadData() {

		try {

			jComboBox5.setEnabled(false);
			jComboBox5.removeAllItems();
			jComboBox6.removeAllItems();
			jComboBox6.setEnabled(false);
			jComboBox7.removeAllItems();

			if (jComboBoxSchedule.getSelectedIndex() > -1) {
				jComboBox7.addItem(new ComboItem("", null));
				String y = ((ComboItem) jComboBoxSchedule.getSelectedItem()).getName();
				jComboBox7.addItem(new ComboItem(y, y));
				int y2 = new Integer(y).intValue() + 1;
				jComboBox7.addItem(new ComboItem(y2 + "", y2 + ""));

				jComboBox7.setSelectedIndex(0);
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBox6LoadData() {

		try {

			jComboBox5.removeAllItems();
			jComboBox5.setEnabled(false);
			jComboBox6.removeAllItems();
			jComboBox6.setEnabled(true);

			jComboBox6 = UtilComponent.buildJPanelFieldM(0, jComboBox6, false);

			jComboBox5LoadData();

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void jComboBox5LoadData() {

		try {

			jComboBox5.removeAllItems();
			jComboBox5.setEnabled(true);

			if (jComboBox6.getSelectedItem() != null && jComboBox7.getSelectedItem() != null) {

				int y = new Integer(((ComboItem) (jComboBox7.getSelectedItem())).getId()).intValue();

				int m = new Integer(((ComboItem) (jComboBox6.getSelectedItem())).getId()).intValue();

				int d = UtilDate.cantDias(m + 1, y);

				for (int i = 0; i < d; i++) {
					jComboBox5.addItem(new ComboItem((i + 1) + "", (i + 1) + ""));
				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jComboBoxSchedule = new javax.swing.JComboBox();
		jComboBox4 = new javax.swing.JComboBox();
		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jButton6 = new javax.swing.JButton();
		jLabel22 = new javax.swing.JLabel();
		jButton4 = new javax.swing.JButton();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jComboBox5 = new javax.swing.JComboBox();
		jComboBox6 = new javax.swing.JComboBox();
		jComboBox7 = new javax.swing.JComboBox();

		setBackground(new java.awt.Color(255, 255, 255));
		setForeground(new java.awt.Color(0, 153, 51));

		jLabel1.setForeground(new java.awt.Color(0, 102, 153));
		jLabel1.setText("DDZZ:");

		jLabel5.setForeground(new java.awt.Color(0, 102, 153));
		jLabel5.setText("Censista:");

		jLabel6.setForeground(new java.awt.Color(0, 102, 153));
		jLabel6.setText("Cronograma (año):");

		jComboBoxSchedule.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jComboBoxSchedule.setEnabled(false);
		jComboBoxSchedule.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}
		});

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jComboBox4.setEnabled(false);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Archivos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 10), new java.awt.Color(0, 102, 153))); // NOI18N

		jButton1.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-pdf.png")); // NOI18N
		jButton1.setEnabled(false);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-zip.png")); // NOI18N
		jButton2.setEnabled(false);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-zip.png")); // NOI18N
		jButton3.setToolTipText("Descargar Planillas de Barrido");
		jButton3.setEnabled(false);
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel3.setForeground(new java.awt.Color(0, 102, 153));
		jLabel3.setText("Distrito");

		jLabel7.setForeground(new java.awt.Color(0, 102, 153));
		jLabel7.setText("Manzanas");

		jLabel8.setForeground(new java.awt.Color(0, 102, 153));
		jLabel8.setText("Planillas");

		jButton6.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-zip.png")); // NOI18N
		jButton6.setEnabled(false);
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jLabel22.setForeground(new java.awt.Color(0, 102, 153));
		jLabel22.setText("Todo los archivos");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel22).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel22)
										.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3)
										.addComponent(jLabel7)
										.addComponent(jLabel8)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jButton4.setForeground(new java.awt.Color(0, 102, 0));
		jButton4.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "run-build 1.png")); // NOI18N
		jButton4.setToolTipText("Generar Planillas de Barrido");
		jButton4.setEnabled(false);
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel9.setForeground(new java.awt.Color(204, 51, 0));

		jLabel10.setForeground(new java.awt.Color(204, 51, 0));

		jTextField1.setToolTipText("DDZZ");

		jLabel4.setForeground(new java.awt.Color(0, 102, 153));
		jLabel4.setText("Fecha Planificada:");

		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup().addGap(72, 72, 72).addComponent(jLabel10)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jLabel9).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(12, 12, 12)
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(jLabel5)
																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(jLabel6)
																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addComponent(jComboBoxSchedule, javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addComponent(jLabel1)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addGap(34, 34, 34)
																																								.addComponent(jLabel2))
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addPreferredGap(
																																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																								.addComponent(
																																										jTextField1,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										69,
																																										javax.swing.GroupLayout.PREFERRED_SIZE))))))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addContainerGap()
																								.addComponent(jLabel4)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(0, 243, Short.MAX_VALUE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6)
										.addComponent(jComboBoxSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel2))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5)
										.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel4))
						.addGap(18, 18, 18)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
										.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}// </editor-fold>

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton6;
	private javax.swing.JComboBox jComboBoxSchedule;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JComboBox jComboBox6;
	private javax.swing.JComboBox jComboBox7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration
}