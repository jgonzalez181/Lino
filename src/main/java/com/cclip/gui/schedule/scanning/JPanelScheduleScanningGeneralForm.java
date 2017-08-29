/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cclip.gui.schedule.scanning;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.utiljdbc.SO;

import com.cclip.Context;
import com.cclip.bo.schedule.scanning.CustomScheduleScanningBo;
import com.cclip.gui.JFrameMainGui;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.util.LinoProperties;
import com.cclip.util.Plancheta;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

/**
 *
 * @author java
 */
public class JPanelScheduleScanningGeneralForm extends javax.swing.JPanel {

	private static final long serialVersionUID = -7501461245060031858L;

	private Plancheta plancheta;
	private LinoProperties linoProperties;
	private JFileChooser jFileChooser1;
	private ScheduleScanning scheduleScanning;

	private JPanelScheduleScanning jPanelScheduleScanning;

	public JPanelScheduleScanningForm jPanelScheduleScanningForm;

	private JTable jTable;
	private Integer row;

	public void setScheduleScanning(ScheduleScanning scheduleScanning, JTable jTable, Integer row) {

		this.jTable = jTable;
		this.row = row;

		this.scheduleScanning = scheduleScanning;

		if (scheduleScanning == null) {
			clear();
			return;
		}

		jButton1.setEnabled(true);
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
		jButton4.setEnabled(true);

		/*
		 * if (scheduleScanning.getScheduleBatch() != null &&
		 * scheduleScanning.getScheduleBatch().getClose() != null) {
		 * 
		 * jButton5.setEnabled(false);
		 * 
		 * // setBackground(new Color(204, 255, 204)); //
		 * jPanel1.setBackground(new Color(204, 255, 204)); //
		 * jPanel2.setBackground(new Color(204, 255, 204)); //
		 * jPanel3.setBackground(new Color(204, 255, 204)); //
		 * jPanel9.setBackground(new Color(204, 255, 204)); //
		 * jTextPane1.setOpaque(true); // jTextPane1.setBackground(new
		 * Color(204, 255, 204)); // jScrollPane1.setBackground(new Color(204,
		 * 255, 204)); // jScrollPane1.setViewportView(jTextPane1);
		 * 
		 * } else
		 */if (scheduleScanning.getRecorded() != null) {

			jButton5.setEnabled(false);
			jLabel4.setForeground(Color.RED);

			// setBackground(new Color(204, 255, 204));
			// jPanel1.setBackground(new Color(204, 255, 204));
			// jPanel2.setBackground(new Color(204, 255, 204));
			// jPanel3.setBackground(new Color(204, 255, 204));
			// jPanel9.setBackground(new Color(204, 255, 204));
			// jTextPane1.setOpaque(true);
			// jTextPane1.setBackground(new Color(204, 255, 204));
			// jScrollPane1.setBackground(new Color(204, 255, 204));
			// jScrollPane1.setViewportView(jTextPane1);

		} else {

			jButton5.setEnabled(true);
			jLabel4.setForeground(new Color(0, 176, 80));

			// this.setBackground(Color.WHITE);
			// jPanel1.setBackground(Color.WHITE);
			// jPanel2.setBackground(Color.WHITE);
			// jPanel3.setBackground(Color.WHITE);
			// jPanel9.setBackground(Color.WHITE);
			// jScrollPane1.setBackground(Color.WHITE);
			// jTextPane1.setOpaque(false);

		}

		// jButton6.setEnabled(jButton5.isEnabled());

		if (scheduleScanning.getSchedule() != null && scheduleScanning.getSchedule().getYear() != null) {
			jLabel2.setText(scheduleScanning.getSchedule().getYear().toString());
		} else {
			jLabel2.setText("");
		}

		if (scheduleScanning.getCadastralCode() != null) {
			jLabel4.setText(UtilCadastre.formatCadastralCode(scheduleScanning.getCadastralCode()));
		} else {
			jLabel4.setText("");
		}

		if (scheduleScanning.getScanning() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			jLabel14.setText(df.format(scheduleScanning.getScanning()));
			jLabel14.setOpaque(false);
			jLabel14.setBackground(Color.WHITE);
		} else {
			jLabel14.setText("     ");
			jLabel14.setOpaque(true);
			jLabel14.setBackground(Color.RED);
		}

		/*
		 * if (scheduleScanning.getScheduleBatch() != null &&
		 * scheduleScanning.getScheduleBatch().getCode() != null) {
		 * 
		 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * if (scheduleScanning.getScheduleBatch().getClose() != null) {
		 * 
		 * jLabel6.setText("[C] " +
		 * df.format(scheduleScanning.getScheduleBatch() .getClose()) + " (" +
		 * scheduleScanning.getScheduleBatch().getCode() + ")");
		 * 
		 * } else {
		 * 
		 * jLabel6.setText("[P] " +
		 * df.format(scheduleScanning.getScheduleBatch() .getPlannedDelivered())
		 * + " (" + scheduleScanning.getScheduleBatch().getCode() + ")"); }
		 * 
		 * } else { jLabel6.setText(""); }
		 */

		if (scheduleScanning.getCensusTaker() != null && scheduleScanning.getCensusTaker().getDni() != null) {
			jLabel28.setText(scheduleScanning.getCensusTaker().getDni());
		} else {
			jLabel28.setText("");
		}

		if (scheduleScanning.getCensusTaker() != null && scheduleScanning.getCensusTaker().getCuil() != null) {
			jLabel30.setText(scheduleScanning.getCensusTaker().getCuil());
		} else {
			jLabel30.setText("");
		}

		if (scheduleScanning.getCensusTaker() != null && scheduleScanning.getCensusTaker().getFamilyName() != null) {
			jLabel32.setText(scheduleScanning.getCensusTaker().getFamilyName());
		} else {
			jLabel32.setText("");
		}

		if (scheduleScanning.getCensusTaker() != null && scheduleScanning.getCensusTaker().getGivenName() != null) {
			jLabel58.setText(scheduleScanning.getCensusTaker().getGivenName());
		} else {
			jLabel58.setText("");
		}

		if (scheduleScanning.getCensusTaker() != null && scheduleScanning.getCensusTaker().getCode() != null) {
			jLabel34.setText(scheduleScanning.getCensusTaker().getCode());
		} else {
			jLabel34.setText("");
		}

		if (scheduleScanning.getCreateDate() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			jLabel16.setText(df.format(scheduleScanning.getCreateDate()));
		} else {
			jLabel16.setText("");
		}

		if (scheduleScanning.getNumberScanning() != null) {
			jLabel5.setText(scheduleScanning.getNumberScanning().toString());
		} else {
			jLabel5.setText("");
		}

		if (scheduleScanning.getPlannedDelivered() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			jLabel7.setText(df.format(scheduleScanning.getPlannedDelivered()));
		} else {
			jLabel7.setText("");
		}

		// if (scheduleScanning.getInProgress() != null) {
		// DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//
		// jLabel18.setText(df.format(scheduleScanning.getInProgress()));
		// } else {
		// jLabel18.setText("");
		// }

		if (scheduleScanning.getScanning() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			jLabel20.setText(df.format(scheduleScanning.getScanning()));
			jLabel20.setOpaque(false);
			jLabel20.setBackground(Color.WHITE);
		} else {

			jLabel20.setText("     ");
			jLabel20.setOpaque(true);
			jLabel20.setBackground(Color.RED);
		}

		// if (scheduleScanning.getDelivered() != null) {
		// DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//
		// jLabel22.setText(df.format(scheduleScanning.getDelivered()));
		// } else {
		// jLabel22.setText("");
		// }

		if (scheduleScanning.getRecorded() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			jLabel24.setText(df.format(scheduleScanning.getRecorded()));
		} else {
			jLabel24.setText("");
		}

		/*
		 * if (scheduleScanning.getScheduleBatch() != null &&
		 * scheduleScanning.getScheduleBatch().getPlannedDelivered() != null) {
		 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * jLabel8.setText(df.format(scheduleScanning.getScheduleBatch()
		 * .getPlannedDelivered())); } else { jLabel8.setText(""); }
		 * 
		 * if (scheduleScanning.getScheduleBatch() != null &&
		 * scheduleScanning.getScheduleBatch().getClose() != null) { DateFormat
		 * df = new SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * jLabel10.setText(df.format(scheduleScanning.getScheduleBatch()
		 * .getClose())); } else { jLabel10.setText(""); }
		 * 
		 * if (scheduleScanning.getScheduleBatch() != null &&
		 * scheduleScanning.getScheduleBatch().getDelivered() != null) {
		 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * jLabel12.setText(df.format(scheduleScanning.getScheduleBatch()
		 * .getDelivered())); } else { jLabel12.setText(""); }
		 */

		if (scheduleScanning.getComment() != null) {
			jTextPane1.setText(scheduleScanning.getComment());
		} else {
			jTextPane1.setText("");
		}

		jPanelScheduleScanningForm.jTabbedPane1.setTitleAt(0, "Barrido " + UtilCadastre.formatCadastralCode(scheduleScanning.getCadastralCode()) + "/" + scheduleScanning.getNumberScanning() + "/"
				+ scheduleScanning.getSchedule().getYear());

	}

	public void clear() {

		jButton1.setEnabled(false);
		jButton2.setEnabled(false);
		jButton3.setEnabled(false);
		jButton4.setEnabled(false);
		jButton5.setEnabled(false);
		// jButton6.setEnabled(false);

		jLabel2.setText("");

		jLabel4.setText("");

		jLabel14.setText("");

		// jLabel6.setText("");

		jLabel28.setText("");

		jLabel30.setText("");

		jLabel32.setText("");

		jLabel58.setText("");

		jLabel34.setText("");

		jLabel16.setText("");

		jLabel5.setText("");

		jLabel7.setText("");

		// jLabel18.setText("");

		jLabel20.setText("");

		// jLabel22.setText("");

		jLabel24.setText("");

		// jLabel8.setText("");

		// jLabel10.setText("");

		// jLabel12.setText("");

		jTextPane1.setText("");

	}

	/**
	 * Creates new form JPanelScheduleScanningGeneralForm
	 */
	public JPanelScheduleScanningGeneralForm(JPanelScheduleScanning jPanelScheduleScanning) {

		this.jPanelScheduleScanning = jPanelScheduleScanning;

		initComponents();

		linoProperties = Context.getBean("linoProperties", LinoProperties.class);
		plancheta = new Plancheta();
		jFileChooser1 = new JFileChooser();
		jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser1.setMultiSelectionEnabled(false);

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

		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				downloadDDZZMMFull();
				// printDDZZMMFull();

			}
		});

		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				evtJButton5();

			}
		});

		// jButton6.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent evt) {
		// save();
		// }
		// });

		clear();

		jLabel3.setText("Manzana:");
		jButton3.setToolTipText("Descargar Planillas de Barrido sin Barrer");
		jLabel22.setText("Planillas sin barrer de la zona");
	}

	public void save() {

		try {

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			scheduleScanning = bo.update(scheduleScanning);

			jPanelScheduleScanning.jPanelScheduleScanningForm1.setDto(scheduleScanning, jTable, row);

			// JOptionPane.showMessageDialog(null,
			// "La planilla de barrido se guardo con éxito !");

		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	private void evtJButton5() {
		JDialogScheduleScanningGeneralForm x = new JDialogScheduleScanningGeneralForm(scheduleScanning, this, jTable, row);
	}

	private void downloadDD() {
		try {

			// String path = confSystem.getUrlFiles() + File.separatorChar +
			// "parcelarios_mapas";
			String path = linoProperties.getUrlDistritalesFrom();

			String dd = scheduleScanning.getCadastralCode().substring(0, 2);

			byte[] b;
			b = plancheta.getDistrictCatastro(path, dd);
			String ext = new Plancheta().getDistrictCatastroExt(path, dd);

			int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningGeneralForm.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				File file = jFileChooser1.getSelectedFile();
				System.out.println(file);

				SO.writeSmallBinaryFile(b, file.getAbsolutePath() + File.separatorChar + "D" + dd + "." + ext);

				JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + file.getAbsolutePath() + File.separatorChar + "D" + dd + "." + ext, "Descarga de Archivo",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void downloadDDZZ() {
		try {

			// String path = confSystem.getUrlFiles() + File.separatorChar +
			// "parcelarios_mapas";

			String path = linoProperties.getUrlPlanchetasFrom();

			String dd = scheduleScanning.getCadastralCode().substring(0, 2);

			String zz = scheduleScanning.getCadastralCode().substring(2, 4);

			int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningGeneralForm.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = jFileChooser1.getSelectedFile();

				File fileResult = plancheta.getZoneCatastroZip(linoProperties.getUrlDistritalesFrom(), path, dd, zz, file.getPath());

				JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + fileResult.getAbsolutePath(), "Descarga de Archivo", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void downloadDDZZMM() {

		try {

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			bo.buildScheduleScanningJasper(scheduleScanning);

			int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningGeneralForm.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = jFileChooser1.getSelectedFile();

				String path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

				byte[] b = plancheta.getBlockCatastroFileReport(path, scheduleScanning.getCadastralCode(), scheduleScanning.getSchedule().getYear(), scheduleScanning.getNumberScanning());

				SO.writeSmallBinaryFile(b, file.getAbsolutePath() + File.separatorChar + UtilCadastre.formatCadastralCodeB(scheduleScanning.getCadastralCode()) + "_"
						+ scheduleScanning.getSchedule().getYear() + "_" + scheduleScanning.getNumberScanning());

				JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + file.getAbsolutePath() + File.separatorChar

				+ UtilCadastre.formatCadastralCodeB(scheduleScanning.getCadastralCode() + "_" + scheduleScanning.getSchedule().getYear() + "_" + scheduleScanning.getNumberScanning()

				), "Descarga de Archivo", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void downloadDDZZMMFull() {

		try {

			jLabel22.setText("Genrando planillas PDF ...");
			setEnableY(false);

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(null, "Esta operación puede tardar ¿ Esta seguro de proseguir ? ", "¿ Generar PDF de planillas de barrido ?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				setEnableY(true);
				// jLabel8.setText("Planillas");

				return;
			}

			buildJasper();

			// System.out.println("314252 : " + "314252".substring(0, 2) );
			// System.out.println("314252 : " +"314252".substring(2, 4) );

			String districtCode = scheduleScanning.getCadastralCode().substring(0, 2);

			String zoneCode = scheduleScanning.getCadastralCode().substring(2, 4);

			int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningGeneralForm.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser1.getSelectedFile();

				String year = scheduleScanning.getSchedule().getYear().toString();
				String path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

				File fileResult = plancheta.getZoneCatastroReportZip(path, districtCode, zoneCode, year, file.getPath());

				JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + fileResult.getAbsolutePath(), "Descarga de Archivo", JOptionPane.INFORMATION_MESSAGE);

			}

			jLabel22.setText("Planillas de la Zona");
			setEnableY(true);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void printDDZZMMFull() {

		try {

			jLabel22.setText("Imprimiendo planillas PDF ...");
			setEnableY(false);

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(null, "Esta operación puede tardar ¿ Esta seguro de proseguir ? ", "¿ Generar PDF de planillas de barrido ?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				setEnableY(true);
				// jLabel8.setText("Planillas");

				return;
			}

			buildJasper();

			// System.out.println("314252 : " + "314252".substring(0, 2) );
			// System.out.println("314252 : " +"314252".substring(2, 4) );

			String districtCode = scheduleScanning.getCadastralCode().substring(0, 2);

			String zoneCode = scheduleScanning.getCadastralCode().substring(2, 4);

			int returnVal = jFileChooser1.showOpenDialog(JPanelScheduleScanningGeneralForm.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser1.getSelectedFile();

				String year = scheduleScanning.getSchedule().getYear().toString();
				String path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

				File fileResult = plancheta.printZoneCatastroReport(path, districtCode, zoneCode, year, file.getPath());

				JOptionPane.showMessageDialog(null, "El archivo se descargo con éxito en " + fileResult.getAbsolutePath(), "Descarga de Archivo", JOptionPane.INFORMATION_MESSAGE);

			}

			jLabel22.setText("Planillas de la Zona");
			setEnableY(true);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void buildJasper() {
		try {

			CustomScheduleScanningBo bo = Context.getBean("customScheduleScanningBo", CustomScheduleScanningBo.class);

			ScheduleScanning[] scheduleScanningList = bo.find7(scheduleScanning.getCadastralCode().substring(0, 4), scheduleScanning.getSchedule().getId());

			bo.buildScheduleScanningJasper(scheduleScanningList);

		} catch (Exception e) {
			UtilComponent.logger(e);
		}

	}

	private void setEnableY(boolean b) {

		// jButton1.setEnabled(b);
		// jButton2.setEnabled(b);
		// jButton3.setEnabled(b);
		// // jButton4.setEnabled(b);
		// jButton5.setEnabled(b);
		// jButton6.setEnabled(b);
	}

	// ==============================================================

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		jLabel57 = new javax.swing.JLabel();
		jLabel58 = new javax.swing.JLabel();
		jLabel27 = new javax.swing.JLabel();
		jLabel28 = new javax.swing.JLabel();
		jLabel29 = new javax.swing.JLabel();
		jLabel30 = new javax.swing.JLabel();
		jLabel31 = new javax.swing.JLabel();
		jLabel32 = new javax.swing.JLabel();
		jLabel33 = new javax.swing.JLabel();
		jLabel34 = new javax.swing.JLabel();
		jLabel25 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jPanel3 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jButton4 = new javax.swing.JButton();
		jLabel22 = new javax.swing.JLabel();
		jToolBar1 = new javax.swing.JToolBar();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));

		jLabel1.setForeground(new java.awt.Color(0, 102, 153));
		jLabel1.setText("Cronograma (Año):");

		jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel2.setText("jLabel2");

		jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(0, 102, 153));
		jLabel3.setText("Planilla:");
		jLabel3.setToolTipText("Código de planilla de barrido");

		jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
		jLabel4.setText("jLabel4");
		jLabel4.setToolTipText("Código de planilla de barrido");

		jLabel13.setForeground(new java.awt.Color(0, 102, 153));
		jLabel13.setText("Barrido el:");

		jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel14.setText("jLabel14");

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Historial de la Planilla",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 153)));

		jLabel15.setForeground(new java.awt.Color(0, 102, 153));
		jLabel15.setText("Creada el:");

		jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel16.setText("jLabel16");

		jLabel19.setForeground(new java.awt.Color(0, 102, 153));
		jLabel19.setText("Barrido el:");

		jLabel20.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel20.setText("jLabel20");

		jLabel23.setForeground(new java.awt.Color(0, 102, 153));
		jLabel23.setText("Cerrada el:");

		jLabel24.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel24.setText("jLabel24");

		jLabel6.setForeground(new java.awt.Color(0, 102, 153));
		jLabel6.setText("Planificada:");

		jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel7.setText("jLabel7");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addComponent(jLabel15).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel16).addGap(18, 18, 18)
						.addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel7).addGap(18, 18, 18).addComponent(jLabel19)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel20).addGap(18, 18, 18).addComponent(jLabel23)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel24).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addGroup(
								jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel23).addComponent(jLabel24))
										.addGroup(
												jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15).addComponent(jLabel16).addComponent(jLabel19)
														.addComponent(jLabel20).addComponent(jLabel6).addComponent(jLabel7))).addGap(7, 7, 7)));

		jPanel9.setBackground(new java.awt.Color(255, 255, 255));
		jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Censista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 153)));

		jLabel57.setForeground(new java.awt.Color(0, 102, 153));
		jLabel57.setText("Nombre:");

		jLabel58.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel58.setText("Diego");

		jLabel27.setBackground(new java.awt.Color(255, 255, 255));
		jLabel27.setForeground(new java.awt.Color(0, 102, 153));
		jLabel27.setText("DNI:");

		jLabel28.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel28.setText("27656133");

		jLabel29.setBackground(new java.awt.Color(255, 255, 255));
		jLabel29.setForeground(new java.awt.Color(0, 102, 153));
		jLabel29.setText("CUIT:");

		jLabel30.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel30.setText("20-27656133-3");

		jLabel31.setBackground(new java.awt.Color(255, 255, 255));
		jLabel31.setForeground(new java.awt.Color(0, 102, 153));
		jLabel31.setText("Apellido:");

		jLabel32.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel32.setText("Mansilla");

		jLabel33.setBackground(new java.awt.Color(255, 255, 255));
		jLabel33.setForeground(new java.awt.Color(0, 102, 153));
		jLabel33.setText("Código:");

		jLabel34.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel34.setText("27656133");

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel9Layout
						.createSequentialGroup()
						.addGroup(
								jPanel9Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel9Layout.createSequentialGroup().addContainerGap().addComponent(jLabel27).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jLabel28))
										.addGroup(
												jPanel9Layout.createSequentialGroup().addComponent(jLabel29).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jLabel30)))
						.addGap(139, 139, 139)
						.addGroup(
								jPanel9Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel9Layout.createSequentialGroup().addComponent(jLabel31).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel32)
														.addGap(18, 18, 18).addComponent(jLabel57).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel58)
														.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(
												jPanel9Layout.createSequentialGroup().addComponent(jLabel33).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)))));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel9Layout
						.createSequentialGroup()
						.addGroup(
								jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel27).addComponent(jLabel28).addComponent(jLabel31)
										.addComponent(jLabel32).addComponent(jLabel57).addComponent(jLabel58))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel29).addComponent(jLabel30))
										.addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel33).addComponent(jLabel34))).addContainerGap()));

		jLabel25.setForeground(new java.awt.Color(0, 102, 153));
		jLabel25.setText("Comentario de la Planilla de Barrido:");

		jTextPane1.setEditable(false);
		jScrollPane1.setViewportView(jTextPane1);

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Archivos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
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

		jButton3.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-pdf.png")); // NOI18N
		jButton3.setToolTipText("Descargar Planillas de Barrido");
		jButton3.setEnabled(false);
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel17.setForeground(new java.awt.Color(0, 102, 153));
		jLabel17.setText("Distrito");

		jLabel18.setForeground(new java.awt.Color(0, 102, 153));
		jLabel18.setText("Manzanas");

		jLabel21.setForeground(new java.awt.Color(0, 102, 153));
		jLabel21.setText("Planilla");

		jButton4.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "application-zip.png")); // NOI18N
		jButton4.setEnabled(false);
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel22.setForeground(new java.awt.Color(0, 102, 153));
		jLabel22.setText("Planillas de la Zona");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel17).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel18).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel21).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel22).addContainerGap(101, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel3Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel22)
										.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel17)
										.addComponent(jLabel18)
										.addComponent(jLabel21)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jButton5.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "document-edit.png")); // NOI18N
		jButton5.setToolTipText("Editar");
		jButton5.setEnabled(false);
		jButton5.setFocusable(false);
		jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(jButton5);

		jButton6.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "document-save.png")); // NOI18N
		jButton6.setToolTipText("Guardar");
		jButton6.setEnabled(false);
		jButton6.setFocusable(false);
		jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(jButton6);

		jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
		jLabel5.setText("jLabel5");

		jLabel8.setForeground(new java.awt.Color(0, 102, 153));
		jLabel8.setText("N°:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup().addContainerGap().addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel5).addGap(18, 18, 18).addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2)
								.addGap(18, 18, 18).addComponent(jLabel13).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel14)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(jScrollPane1)
				.addGroup(layout.createSequentialGroup().addComponent(jLabel25).addGap(0, 0, Short.MAX_VALUE))
				.addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4)
										.addComponent(jLabel13).addComponent(jLabel14).addComponent(jLabel5).addComponent(jLabel8)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel25).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel32;
	private javax.swing.JLabel jLabel33;
	private javax.swing.JLabel jLabel34;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel57;
	private javax.swing.JLabel jLabel58;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JToolBar jToolBar1;
	// End of variables declaration
}
