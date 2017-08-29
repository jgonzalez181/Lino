package com.cclip.gui.geo.cadastre;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.schedule.census.JDialogCensusView;
import com.cclip.gui.util.ComboItem;
import com.cclip.gui.util.JPanelList;
import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.cadastre.CadastreSituation;
import com.cclip.model.geo.cadastre.CadastreType;
import com.cclip.services.Services;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

public class JPanelCadastreList extends JPanelList {

	private static final long serialVersionUID = 8740413335514099146L;

	private JPanelCadastreViewCustomW jPanelCadastreViewCustomW;

	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;

	private JComboBox<ComboItem> jComboBoxCadastreType;
	private JComboBox<ComboItem> jComboBoxCadastreSituation;
	private JComboBox<ComboItem> jComboBoxCityArea;

	private CadastreType[] cadastreTypeList;
	private CadastreSituation[] cadastreSituationList;
	private CityArea[] cityAreaList;

	private JButton jButtonBuild;

	public JPanelCadastreList() {

		initComponents();
	}

	private void initComponents() {

		jPanelCadastreViewCustomW = new JPanelCadastreViewCustomW();
		jPanelCadastreViewCustomW.setBackground(new Color(0, 32, 96));

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("DDZZMMMPPPppp", String.class, 140);
		addColumnMetaData("U.F", Integer.class, 70);
		addColumnMetaData("Cta. Cli.", Integer.class, 100);
		addColumnMetaData("Tipo", String.class, 60);
		// addColumnMetaData("Sit.", String.class, 60);
		addColumnMetaData("MT", Double.class, 60);
		addColumnMetaData("MC", Double.class, 60);

		super.initComponents("Catastro", jPanelCadastreViewCustomW, initToolBar1(), initToolBar2(), new Color(0, 32, 96), Color.WHITE);

	}

	private Component[] initToolBar1() {

		jButtonBuild = new JButton();
		jButtonBuild.setToolTipText("Nueva Planilla de Censo");
		jButtonBuild.setIcon(new ImageIcon(JFrameMainGui.iconPath + "run-build.png")); // NOI18N
		jButtonBuild.setPreferredSize(new Dimension(27, 27));
		jButtonBuild.setEnabled(true);

		jButtonBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buildScheduleCensus();
			}
		});

		JLabel jLabel1 = new JLabel("Nom.Cat.:");
		jLabel1.setForeground(Color.WHITE);
		JLabel jLabel2 = new JLabel("UF:");
		jLabel2.setForeground(Color.WHITE);
		JLabel jLabel3 = new JLabel("Cta Cli:");
		jLabel3.setForeground(Color.WHITE);

		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetSearch();
			}
		};

		jTextField1 = UtilComponent.buildFieldJTextField(140, "Buscar por nomeclatura catastral", actionListener1);
		jTextField2 = UtilComponent.buildFieldJTextField(100, "Buscar por unidad de facturación", actionListener1);
		jTextField3 = UtilComponent.buildFieldJTextField(100, "Buscar por cuenta cliente", actionListener1);

		jComboBoxCityArea = new JComboBox<ComboItem>();
		jComboBoxCityArea.setToolTipText("Buscar por zona");
		cityAreaLoadData();
		if (jComboBoxCityArea.getItemCount() > 0) {
			jComboBoxCityArea.setSelectedIndex(0);
		}

		Component[] headerRowList = { jButtonBuild, jLabel1, jTextField1, jLabel2, jTextField2, jLabel3, jTextField3, jComboBoxCityArea };

		return headerRowList;
	}

	private Component[] initToolBar2() {

		JLabel jLabel1 = new JLabel("Persona:");
		jLabel1.setForeground(Color.WHITE);
		JLabel jLabel2 = new JLabel("Tipo:");
		jLabel2.setForeground(Color.WHITE);
		JLabel jLabel3 = new JLabel("Situación:");
		jLabel3.setForeground(Color.WHITE);

		jComboBoxCadastreType = new JComboBox<ComboItem>();
		jComboBoxCadastreType.setToolTipText("Buscar por tipo de parcela");
		cadastreTypeLoadData();
		if (jComboBoxCadastreType.getItemCount() > 0) {
			jComboBoxCadastreType.setSelectedIndex(0);
		}

		jComboBoxCadastreSituation = new JComboBox<ComboItem>();
		jComboBoxCadastreSituation.setToolTipText("Buscar por situación de parcela");
		cadastreSituationLoadData();
		if (jComboBoxCadastreSituation.getItemCount() > 0) {
			jComboBoxCadastreSituation.setSelectedIndex(0);
		}

		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetSearch();
			}
		};

		jTextField4 = UtilComponent.buildFieldJTextField(200, "Buscar por Razón (U.F) ó Usuario del Servicio de Agua", actionListener1);

		Component[] headerRowList = { jLabel2, jComboBoxCadastreType, jLabel3, jComboBoxCadastreSituation, jLabel1, jTextField4 };

		return headerRowList;
	}

	protected void find(int offSet, int limit) throws Exception {

		String cadastralCode = jTextField1.getText();
		String uf = jTextField2.getText();
		String ctaCli = jTextField3.getText();
		String cityAreaId = null;
		String cadastreTypeId = null;
		String cadastreSituationId = null;
		String persona = jTextField4.getText();

		if (jComboBoxCadastreType.getSelectedItem() != null) {

			Object id = ((ComboItem) jComboBoxCadastreType.getSelectedItem()).getId();

			if (id != null && id.toString().trim().length() > 0) {
				cadastreTypeId = id.toString();
			}

		}

		if (jComboBoxCadastreSituation.getSelectedItem() != null) {
			Object id = ((ComboItem) jComboBoxCadastreSituation.getSelectedItem()).getId();
			if (id != null && id.toString().trim().length() > 0) {
				cadastreSituationId = id.toString();
			}

		}

		if (jComboBoxCityArea.getSelectedItem() != null) {
			Object id = ((ComboItem) jComboBoxCityArea.getSelectedItem()).getId();
			if (id != null && id.toString().trim().length() > 0) {
				cityAreaId = id.toString();
			}

		}

		rl = Services.getInstance().findCadastreListByExample(cadastralCode, uf, ctaCli, cityAreaId, cadastreTypeId, cadastreSituationId, persona, offSet, limit);

		model = (Object[][]) rl.getList();

		for (int i = 0; i < model.length; i++) {
			if (model[i][1] != null) {
				model[i][1] = UtilCadastre.formatCadastralCode(model[i][1].toString().trim());
			}
		}

	}

	private void cadastreTypeLoadData() {

		try {

			jComboBoxCadastreType.removeAllItems();

			jComboBoxCadastreType.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance().findCadastreType();
			cadastreTypeList = new CadastreType[table.length];

			for (int i = 0; i < table.length; i++) {
				CadastreType cadastreType = new CadastreType();
				cadastreType.setId(table[i][0].toString());
				if (table[i][1] != null) {
					cadastreType.setName(table[i][1].toString());
				}
				cadastreTypeList[i] = cadastreType;
			}

			if (cadastreTypeList != null) {

				for (int i = 0; i < cadastreTypeList.length; i++) {

					jComboBoxCadastreType.addItem(new ComboItem("(" + cadastreTypeList[i].getId() + ") " + cadastreTypeList[i].getName(), cadastreTypeList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void cadastreSituationLoadData() {

		try {

			jComboBoxCadastreSituation.removeAllItems();

			jComboBoxCadastreSituation.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance().findCadastreSituation();
			cadastreSituationList = new CadastreSituation[table.length];

			for (int i = 0; i < table.length; i++) {
				CadastreSituation cadastreSituation = new CadastreSituation();
				cadastreSituation.setId(table[i][0].toString());
				if (table[i][1] != null) {
					cadastreSituation.setName(table[i][1].toString());
				}
				cadastreSituationList[i] = cadastreSituation;
			}

			if (cadastreSituationList != null) {

				for (int i = 0; i < cadastreSituationList.length; i++) {
					jComboBoxCadastreSituation.addItem(new ComboItem("(" + cadastreSituationList[i].getId() + ") " + cadastreSituationList[i].getName(), cadastreSituationList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void cityAreaLoadData() {

		try {
			jComboBoxCityArea.removeAllItems();

			jComboBoxCityArea.addItem(new ComboItem("", null));

			Object[][] table = Services.getInstance().findCityArea();
			cityAreaList = new CityArea[table.length];

			for (int i = 0; i < table.length; i++) {
				CityArea cityArea = new CityArea();
				cityArea.setId(table[i][0].toString());
				if (table[i][1] != null) {
					cityArea.setName(table[i][1].toString());
				}
				cityAreaList[i] = cityArea;
			}

			if (cityAreaList != null) {

				for (int i = 0; i < cityAreaList.length; i++) {
					jComboBoxCityArea.addItem(new ComboItem("(" + cityAreaList[i].getId() + ") " + cityAreaList[i].getName(), cityAreaList[i].getId()));
				}
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@Override
	protected void clearFilter() {

		cadastreTypeLoadData();
		cadastreSituationLoadData();
		cityAreaLoadData();

		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");

		if (jComboBoxCadastreType.getItemCount() > 0) {
			jComboBoxCadastreType.setSelectedIndex(0);
		}
		if (jComboBoxCadastreSituation.getItemCount() > 0) {
			jComboBoxCadastreSituation.setSelectedIndex(0);
		}
		if (jComboBoxCityArea.getItemCount() > 0) {
			jComboBoxCityArea.setSelectedIndex(0);
		}

	}

	private void buildScheduleCensus() {

		try {

			if (this.jScrollPaneJTable.getjTable().getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar una parcela !");
				return;
			}

			String censusId = null;
			String cadastreId = null;
			String cadastralCode = null;
			String ufId = null;

			String tmp = "para " + UtilCadastre.formatCadastralCode(model[this.jScrollPaneJTable.getjTable().getSelectedRow()][1].toString());

			cadastreId = model[this.jScrollPaneJTable.getjTable().getSelectedRow()][0].toString();
			cadastralCode = model[this.jScrollPaneJTable.getjTable().getSelectedRow()][1].toString().replace("-", "");
			ufId = model[this.jScrollPaneJTable.getjTable().getSelectedRow()][2].toString();

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
