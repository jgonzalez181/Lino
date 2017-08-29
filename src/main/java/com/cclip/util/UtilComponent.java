package com.cclip.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import org.utiljdbc.SO;
import org.utiljdbcdao.ex.ExFindDao;

import com.cclip.Context;
import com.cclip.gui.util.ComboItem;

public class UtilComponent {

	@SuppressWarnings({ "rawtypes" })
	public static JComboBox buildJPanelFieldM(int index) {
		JComboBox jComboBox1 = new JComboBox();
		return buildJPanelFieldM(index, jComboBox1, true);
	}

	@SuppressWarnings({ "rawtypes" })
	public static JComboBox buildJPanelFieldM(int index, boolean initNull) {
		JComboBox jComboBox1 = new JComboBox();
		return buildJPanelFieldM(index, jComboBox1, initNull);
	}

	public static JComboBox buildJPanelFieldM(int index, JComboBox jComboBox1) {
		return buildJPanelFieldM(index, jComboBox1, true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox buildJPanelFieldM(int index, JComboBox jComboBox1, boolean initNull) {

		if (initNull == true) {
			jComboBox1.addItem(new ComboItem("", ""));
		}

		jComboBox1.addItem(new ComboItem("Enero", "0"));
		jComboBox1.addItem(new ComboItem("Febrero", "1"));
		jComboBox1.addItem(new ComboItem("Marzo", "2"));
		jComboBox1.addItem(new ComboItem("Abril", "3"));
		jComboBox1.addItem(new ComboItem("Mayo", "4"));
		jComboBox1.addItem(new ComboItem("Junio", "5"));
		jComboBox1.addItem(new ComboItem("Julio", "6"));
		jComboBox1.addItem(new ComboItem("Agosto", "7"));
		jComboBox1.addItem(new ComboItem("Septiembre", "8"));
		jComboBox1.addItem(new ComboItem("Octubre", "9"));
		jComboBox1.addItem(new ComboItem("Noviembre", "10"));
		jComboBox1.addItem(new ComboItem("Diciembre", "11"));

		jComboBox1.setSelectedIndex(index);

		return jComboBox1;

	}

	public static JLabel buildJLabelField(String text) {
		JLabel jLabel = new JLabel(text);

		jLabel.setForeground(new Color(0, 102, 153));

		return jLabel;
	}

	public static JCheckBox buildCheckBoxField(String text) {
		JCheckBox jCheckBox = new JCheckBox(text);
		jCheckBox.setSelected(false);

		jCheckBox.setForeground(new Color(0, 102, 153));

		return jCheckBox;
	}
	
	public static void logger(Exception e) {
		logger(e, null);
	}

	public static void logger(Exception e, String msg) {

		try {
			e.printStackTrace();

			LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

			String content = "";

			content += "\n" + "================================================================================================================================================";

			content += "\n" + e.toString();
			
			content += "\n" + "================================================================================================================================================";

			for (int i = 0; i < e.getStackTrace().length; i++) {
				content += "\n" + e.getStackTrace()[i];
			}

			SO.createFile(linoProperties.getUrlFiles(), "lino.log", content);

			if(msg == null){
				msg = "";
			}
			
			JOptionPane.showMessageDialog(null, msg + "\nPara mas información " + linoProperties.getUrlFiles() + File.separatorChar + "lino.log", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e1) {

			e1.printStackTrace();
		}

	}

	public static JTextField buildFieldJTextField(int width, String toolTip, ActionListener actionListener) {
		JTextField jTextField = new JTextField();
		jTextField.setPreferredSize(new Dimension(width, 28));
		jTextField.setToolTipText(toolTip);
		jTextField.addActionListener(actionListener);

		return jTextField;
	}

	public static JFormattedTextField buildJFormattedTextField(int width, String toolTip, ActionListener actionListener) {

		JFormattedTextField jFormattedTextField = new JFormattedTextField();

		jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))));
		jFormattedTextField.setPreferredSize(new Dimension(width, 28));
		jFormattedTextField.setToolTipText(toolTip);
		jFormattedTextField.addActionListener(actionListener);

		return jFormattedTextField;
	}

	public static Date getDate(String m, String y) {

		if (m != null && m.trim().length() > 0 && y != null && y.trim().length() > 0) {

			GregorianCalendar gc = new GregorianCalendar(new Integer(y).intValue(), new Integer(m).intValue(), 1, 0, 0, 0);

			return new Date(gc.getTimeInMillis());
		}
		return null;

	}

	public static Date getDate(String d, String m, String y) {

		if (d != null && d.trim().length() > 0 && m != null && m.trim().length() > 0 && y != null && y.trim().length() > 0) {

			GregorianCalendar gc = new GregorianCalendar(new Integer(y).intValue(), new Integer(m).intValue(), new Integer(d).intValue(), 0, 0, 0);

			return new Date(gc.getTimeInMillis());
		}
		return null;

	}

	public static Date getDate(JFormattedTextField jFormattedTextField) {

		String v = jFormattedTextField.getText();

		if (v == null) {
			return null;
		}

		v = v.trim();

		if (v.length() == 0) {
			return null;
		}

		if (v.length() < "dd/MM/yyyy".length()) {

			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			jFormattedTextField.setText(null);

			return null;
		}

		if (v.split("/").length != 3) {
			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			jFormattedTextField.setText(null);

			return null;
		}

		if (v.split("/")[0].length() < 0 || v.split("/")[0].length() > "dd".length()) {

			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			return null;
		}

		if (v.split("/")[1].length() < 0 || v.split("/")[1].length() > "MM".length()) {

			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			jFormattedTextField.setText(null);

			return null;
		}

		if (v.split("/")[2].length() < 0 || v.split("/")[2].length() > "yyyy".length()) {

			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			jFormattedTextField.setText(null);

			return null;
		}

		if (v.split("/")[2].length() == "yy".length()) {

			v.split("/")[2] = "20" + v.split("/")[2];

		}

		try {
			int y = new Integer(v.split("/")[2]).intValue();
			int m = new Integer(v.split("/")[1]).intValue();
			int d = new Integer(v.split("/")[0]).intValue();

			GregorianCalendar gc = new GregorianCalendar(y, m, d, 0, 0, 0);

			return new Date(gc.getTimeInMillis());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Debe ingresar un formato de fecha dd/MM/yyyy", "Formato de Fecha Inválido. Fecha descartada.", JOptionPane.ERROR_MESSAGE);

			jFormattedTextField.setText(null);

			return null;
		}

	}

}
