package com.cclip.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.utiljdbc.SO;

import com.cclip.Context;
import com.cclip.gui.geo.cadastre.JPanelCadastreList;
import com.cclip.gui.geo.cadastre.aacc.JPanelAacc;
import com.cclip.gui.schedule.JPanelSchedule;
import com.cclip.gui.schedule.census.JPanelScheduleCensusList;
import com.cclip.gui.schedule.scanning.JPanelScheduleScanning;
import com.cclip.model.person.UserSystem;
import com.cclip.util.LinoProperties;

public class JFrameMainGui extends JFrame {

	private static final long serialVersionUID = -2966765155617332742L;

	// 1002002040 att op
	// FALTA CAMPO PARCR
	// cp POR cb
	// 0101040001 REV PV

	private JTabbedPane jTabbedPane1;
	private JPanelScheduleScanning jPanelScheduleScanning1;
	private JPanelScheduleCensusList jPanelScheduleCensusList;
	private JPanelCadastreList jPanelCadastreList;

	private JPanelSchedule jPanelSchedule;
	private JPanelAacc jPanelAacc1;

	public static String iconPath = null;

	public boolean login = false;

	public JFrameMainGui() {

		try {

			LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

			SO.createFile(linoProperties.getUrlFiles(), "lino.log", "");

			iconPath = linoProperties.getUrlFiles() + File.separatorChar + "Icon" + File.separatorChar;

			initComponents();

		} catch (IOException e) {

			e.printStackTrace();
		}

		// http://www.codejava.net/java-se/swing/6-techniques-for-sorting-jtable-you-should-know
	}

	private void initComponents() {

		new JDialogLogin(this);

		if (login == false) {
			System.exit(0);

		}

		jTabbedPane1 = new JTabbedPane();

		jPanelScheduleScanning1 = new JPanelScheduleScanning();
		jPanelScheduleCensusList = new JPanelScheduleCensusList();
		jPanelCadastreList = new JPanelCadastreList();
		jPanelAacc1 = new JPanelAacc();
		jPanelAacc1.jFrame = this;
		jPanelSchedule = new JPanelSchedule();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jTabbedPane1.addTab("Cronograma y Lotes", jPanelSchedule);
		jTabbedPane1.addTab("Planillas de Censos", jPanelScheduleCensusList);
		jTabbedPane1.addTab("Planillas de Barridos", jPanelScheduleScanning1);

		jTabbedPane1.addTab("Catastro", jPanelCadastreList);
		jTabbedPane1.addTab("UNL (AACC)", jPanelAacc1);

		jTabbedPane1.setSelectedIndex(3);

		jTabbedPane1.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		String tmp = "";

		if (userSystem.getFamilyName() != null) {
			tmp += userSystem.getFamilyName() + " ";
		}

		if (userSystem.getGivenName() != null) {
			tmp += userSystem.getGivenName() + " ";
		}

		if (userSystem.getMiddleName() != null) {
			tmp += userSystem.getMiddleName() + " ";
		}

		if (userSystem.getCuil() != null) {
			tmp += "(CUIL) " + userSystem.getCuil() + " ";
		}

		if (userSystem.getDni() != null) {
			tmp += "(DNI) " + userSystem.getDni() + " ";
		}

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		this.setTitle("Sistema de Gestión de Barridos y Censos - " + tmp + " - " + df.format(new Date(System.currentTimeMillis())));

		this.setIconImage(new ImageIcon(JFrameMainGui.iconPath + "cclip.jpg").getImage());

		Dimension userScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) userScreen.getWidth();
		int screenHeight = (int) userScreen.getHeight();

		this.setSize(new Dimension(screenWidth, screenHeight - 30));

		this.getContentPane().setBackground(Color.WHITE);

		this.add(jTabbedPane1);
		this.getContentPane().setBackground(Color.WHITE);

	}

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(JFrameMainGui.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(JFrameMainGui.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(JFrameMainGui.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(JFrameMainGui.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JFrameMainGui().setVisible(true);
			}
		});
	}

}
