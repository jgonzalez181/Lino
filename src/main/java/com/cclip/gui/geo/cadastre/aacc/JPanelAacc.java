package com.cclip.gui.geo.cadastre.aacc;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JPanelAacc extends JPanel {

	private static final long serialVersionUID = 8740413335514099146L;
	
	public JFrame jFrame;

	private JTabbedPane jTabbedPane1;
	private JTabbedPane jTabbedPane2;
	private JTabbedPane jTabbedPane3;

	private JPanelUnidad jPanelUnidad1;
	private JPanelInmueble jPanelInmueble1;
	private JPanelSuperficie jPanelSuperficie1;
	private JPanelSubcuenta jPanelSubcuenta1;
	private JPanelDatosPostales jPanelDatosPostales1;

	private JPanelImportIceDb jPanelImportIceDb;

	private JPanelUnidadNov jPanelUnidad2;
	private JPanelInmuebleNov jPanelInmueble2;
	private JPanelSuperficieNov jPanelSuperficie2;
	private JPanelSubcuentaNov jPanelSubcuenta2;

	// private JPanelDatosPostalesNov jPanelDatosPostales2;

	public JPanelAacc() {
		initComponents();
	}

	private void initComponents() {

		jTabbedPane1 = new JTabbedPane();
		jTabbedPane1.setBackground(Color.WHITE);
		jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

		jTabbedPane2 = new JTabbedPane();
		jTabbedPane2.setBackground(Color.WHITE);
		jTabbedPane2.setBackground(new Color(192, 0, 0));
		jTabbedPane2.setOpaque(true);

		jTabbedPane3 = new JTabbedPane();
		jTabbedPane3.setBackground(Color.WHITE);
		jTabbedPane3.setBackground(new Color(0, 176, 80));
		jTabbedPane3.setOpaque(true);

		jPanelUnidad1 = new JPanelUnidad();
		jPanelInmueble1 = new JPanelInmueble();
		jPanelSuperficie1 = new JPanelSuperficie();
		jPanelSubcuenta1 = new JPanelSubcuenta();
		jPanelDatosPostales1 = new JPanelDatosPostales();

		jPanelUnidad2 = new JPanelUnidadNov();
		jPanelInmueble2 = new JPanelInmuebleNov();
		jPanelSuperficie2 = new JPanelSuperficieNov();
		jPanelSubcuenta2 = new JPanelSubcuentaNov();
		// jPanelDatosPostales2 = new JPanelDatosPostalesNov();

		jPanelImportIceDb = new JPanelImportIceDb();
		jPanelImportIceDb.jFrame = jFrame;

		jTabbedPane2.addTab("1 UNIDAD", jPanelUnidad1);
		jTabbedPane2.addTab("2 INMUEBLE", jPanelInmueble1);
		jTabbedPane2.addTab("3 SUPERFICIE", jPanelSuperficie1);
		jTabbedPane2.addTab("4 SUBCUENTA", jPanelSubcuenta1);
		jTabbedPane2.addTab("5 DATOS_POSTALES", jPanelDatosPostales1);

		jTabbedPane3.addTab("1 UNIDAD", jPanelUnidad2);
		jTabbedPane3.addTab("2 INMUEBLE", jPanelInmueble2);
		jTabbedPane3.addTab("3 SUPERFICIE", jPanelSuperficie2);
		jTabbedPane3.addTab("4 SUBCUENTA", jPanelSubcuenta2);
		// jTabbedPane3.addTab("5 DATOS_POSTALES", jPanelDatosPostales2);

		jTabbedPane1.addTab("Base de Datos Congelada", jTabbedPane2);
		//jTabbedPane1.addTab("Novedades", jTabbedPane3);
		jTabbedPane1.addTab("Importar Archivos", jPanelImportIceDb);

		// jTabbedPane1.setBackgroundAt(0, new Color(192, 0, 0));
		// jTabbedPane1.setBackgroundAt(1, new Color(0, 176, 80));
		// jTabbedPane1.setBackgroundAt(2, new Color(0, 176, 80));
		// tabbedPane.setBackgroundAt(i,colors[i]);

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(3, 3));
		this.add(jTabbedPane1);

	}

}
