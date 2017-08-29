package com.cclip.gui.geo.cadastre.aacc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.Context;
import com.cclip.bo.geo.cadastre.aacc.UnlBo;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.schedule.scanning.JPanelScheduleScanningGeneralForm;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.geo.cadastre.aacc.Unl;
import com.cclip.util.SO;
import com.cclip.util.UtilComponent;
import com.cclip.util.UtilCsv;
import com.csvreader.CsvWriter;

public class JPanelUNL extends JPanel {

	private static final long serialVersionUID = -7296928040553846328L;

	private JPanel jPanelHeaderRow;
	private JPanel jPanelHeaderRow1;
	private JPanel jPanelHeaderRow2;

	private JPanel jPanelHeader;
	private JPanel jPanel4;

	private JTabbedPane jTabbedPane1;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;

	private JTextField jTextFieldFilter1;
	private JTextField jTextFieldFilter2;
	private JTextField jTextFieldFilter3;

	private JComboBox jComboBoxFilter1;
	private JComboBox jComboBoxFilter2;
	private JComboBox jComboBoxFilter3;

	private JComboBox jComboBoxOrder1;
	private JComboBox jComboBoxOrder2;
	private JComboBox jComboBoxOrder3;

	private JSpinner jSpinner1;
	private SpinnerNumberModel spinnerModel1;
	private JToggleButton jToggleButton;
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	private JButton jButtonSearch;

	private JButton jButtonCsv;

	private JCheckBox[] jCheckBoxList;
	private Map<String, Integer> jCheckBoxMap;

	private JSplitPane jSplitPane1;

	private Object[][] model;
	private String[] columnNames;
	@SuppressWarnings("rawtypes")
	private Class[] columnTypes;
	private boolean[] columnEdit;

	private int page = 0;
	private long cantPages = 0;
	protected int limit = 100;
	protected int offSet = 0;

	protected String table;
	private int[] showColumn;
	private String[] columnNamesCustom;
	private int filterColumn1;
	private int filterColumn2;
	private int filterColumn3;
	private int orderColumn1;
	private int orderColumn2;
	private int orderColumn3;

	private Color color;
	private String title;

	public JPanelUNL(String title, String table, int[] showColumn, String[] columnNamesCustom, int filterColumn1, int filterColumn2, int filterColumn3, int orderColumn1, int orderColumn2,
			int orderColumn3, Color color) {

		this.title = title;
		this.table = table;
		this.showColumn = showColumn;
		this.columnNamesCustom = columnNamesCustom;
		this.filterColumn1 = filterColumn1;
		this.filterColumn2 = filterColumn2;
		this.filterColumn3 = filterColumn3;
		this.orderColumn1 = orderColumn1;
		this.orderColumn2 = orderColumn2;
		this.orderColumn3 = orderColumn3;

		this.color = color;

		columnNames = new String[] { "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "c11", "c12", "c13", "c14", "c15", "c16", "c17", "c18", "c19", "c20", "c21", "c22", "c23", "c24",
				"c25", "c26", "c27", "c28", "c29", "c30", "c31", "c32", "c33", "c34", "c35", "c36", "c37", "c38", "c39", "c40", "c41", "c42", "c43", "c44", "c45", "c46", "c47", "c48", "c49", "c50",
				"c51", "c52", "c53", "c54", "c55", "c56", "c57", "c58", "c59", "c60", "c61", "c62", "c63", "c64", "c65", "c66", "c67", "c68", "c69", "c70", "c71", "c72", "c73", "c74", "c75", "c76",
				"c77", "c78", "c79", "c80", "c81", "c82", "c83", "c84", "c85", "c86", "c87", "c88", "c89", "c90", "c91", "c92", "c93", "c94", "c95", "c96", "c97", "c98", "c99", "c100", "c101",
				"c102", "c103", "c104", "c105", "c106", "c107", "c108", "c109", "c110", "c111", "c112", "c113", "c114", "c115", "c116", "c117", "c118", "c119", "c120", "c121", "c122", "c123", "c124",
				"c125", "c126", "c127", "c128", "c129", "c130", "c131", "c132", "c133", "c134", "c135", "c136", "c137", "c138", "c139", "c140", "c141", "c142", "c143", "c144", "c145", "c146", "c147",
				"c148", "c149", "c150", "c151", "c152", "c153", "c154", "c155", "c156", "c157", "c158", "c159", "c160", "c161", "c162", "c163", "c164", "c165", "c166", "c167", "c168", "c169", "c170",
				"c171", "c172", "c173", "c174", "c175", "c176", "c177", "c178", "c179", "c180", "c181", "c182", "c183", "c184", "c185", "c186", "c187", "c188", "c189", "c190", "c191", "c192", "c193",
				"c194", "c195", "c196", "c197", "c198", "c199", "c200", "c201", "c202", "c203", "c204", "c205", "c206", "c207", "c208", "c209", "c210", "c211", "c212", "c213", "c214", "c215", "c216",
				"c217", "c218", "c219", "c220", "c221", "c222", "c223", "c224", "c225", "c226", "c227", "c228", "c229", "c230", "c231", "c232", "c233", "c234", "c235", "c236", "c237", "c238", "c239",
				"c240", "c241", "c242", "c243", "c244", "c245", "c246", "c247", "c248", "c249", "c250", "c251", "c252", "c253", "c254", "c255", "c256", "c257", "c258", "c259", "c260", "c261", "c262",
				"c263", "c264", "c265", "c266", "c267", "c268", "c269", "c270", "c271", "c272", "c273", "c274", "c275", "c276", "c277", "c278", "c279", "c280", "c281", "c282", "c283", "c284", "c285",
				"c286", "c287", "c288", "c289", "c290", "c291", "c292", "c293", "c294", "c295", "c296", "c297", "c298", "c299", "c300", "c301", "c302", "c303", "c304", "c305", "c306", "c307", "c308",
				"c309", "c310", "c311", "c312", "c313", "c314", "c315", "c316", "c317", "c318", "c319", "c320", "c321", "c322", "c323", "c324", "c325", "c326", "c327", "c328", "c329", "c330", "c331",
				"c332", "c333", "c334", "c335", "c336", "c337", "c338", "c339", "c340", "c341", "c342", "c343", "c344", "c345", "c346", "c347", "c348", "c349", "c350", "c351", "c352", "c353", "c354",
				"c355", "c356", "c357", "c358", "c359", "c360", "c361", "c362", "c363", "c364", "c365", "c366", "c367", "c368", "c369", "c370", "c371", "c372", "c373", "c374", "c375", "c376", "c377",
				"c378", "c379", "c380", "c381", "c382", "c383", "c384", "c385", "c386", "c387", "c388", "c389", "c390", "c391", "c392", "c393", "c394", "c395", "c396", "c397", "c398", "c399", "c400",
				"c401", "c402", "c403", "c404", "c405", "c406", "c407", "c408", "c409", "c410", "c411", "c412", "c413", "c414", "c415", "c416", "c417", "c418", "c419", "c420", "c421", "c422", "c423",
				"c424", "c425", "c426", "c427", "c428", "c429", "c430", "c431", "c432", "c433", "c434", "c435", "c436", "c437", "c438", "c439", "c440", "c441", "c442", "c443", "c444", "c445", "c446",
				"c447", "c448", "c449", "c450", "c451", "c452", "c453", "c454", "c455", "c456", "c457", "c458", "c459", "c460", "c461", "c462", "c463", "c464", "c465", "c466", "c467", "c468", "c469",
				"c470", "c471", "c472", "c473", "c474", "c475", "c476", "c477", "c478", "c479", "c480", "c481", "c482", "c483", "c484", "c485", "c486", "c487", "c488", "c489", "c490", "c491", "c492",
				"c493", "c494", "c495", "c496", "c497", "c498", "c499", "c500" };

		if (columnNamesCustom != null && columnNamesCustom.length <= columnNames.length) {
			for (int i = 0; i < columnNamesCustom.length; i++) {
				columnNames[i] = columnNames[i] + "_" + columnNamesCustom[i];
			}
		}

		initComponents();
		// jButton1.doClick();
	}

	private void initColumnNames() {

		columnTypes = new Class[columnNames.length];

		for (int i = 0; i < columnTypes.length; i++) {
			columnTypes[i] = String.class;
		}

		columnEdit = new boolean[columnNames.length];

		for (int i = 0; i < columnEdit.length; i++) {
			columnEdit[i] = false;
		}

		DefaultTableModel defaultTableModel = new DefaultTableModel(model, columnNames) {

			private static final long serialVersionUID = 7798875997013763645L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEdit[columnIndex];
			}
		};

		jTable1.setModel(defaultTableModel);

	}

	private void initComponents() {

		jPanelHeaderRow = new JPanel();
		jPanelHeaderRow1 = new JPanel();
		jPanelHeaderRow2 = new JPanel();
		jPanelHeader = new JPanel();
		jPanel4 = new JPanel();

		jTabbedPane1 = new JTabbedPane();

		jTextFieldFilter1 = new JTextField();
		jTextFieldFilter2 = new JTextField();
		jTextFieldFilter3 = new JTextField();

		jComboBoxFilter1 = new JComboBox();
		jComboBoxFilter2 = new JComboBox();
		jComboBoxFilter3 = new JComboBox();

		jComboBoxOrder1 = new JComboBox();
		jComboBoxOrder2 = new JComboBox();
		jComboBoxOrder3 = new JComboBox();

		jLabel1 = new JLabel("Buscar por");
		jLabel2 = new JLabel("Ordenar por");
		jLabel3 = new JLabel();

		jSpinner1 = new JSpinner();

		jScrollPane1 = new JScrollPane();

		jTable1 = new JTable();

		jToggleButton = new JToggleButton();

		jButtonSearch = new JButton();

		jSplitPane1 = new JSplitPane();

		jButtonCsv = new JButton();

		// ---------------------------------------

		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.WHITE);

		// ---------------------------------------

		jToggleButton.setToolTipText("Mostrar panel de configuración de columnas visibles.");
		jToggleButton.setIcon(new ImageIcon(JFrameMainGui.iconPath + "view-split-left-right.png")); // NOI18N
		jToggleButton.setSelectedIcon(new ImageIcon(JFrameMainGui.iconPath + "view-left-close.png")); // NOI18N
		jToggleButton.setRolloverIcon(new ImageIcon(JFrameMainGui.iconPath + "view-split-left-right.png")); // NOI18N
		jToggleButton.setPreferredSize(new Dimension(27, 27));
		jToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (jToggleButton.isSelected() == true) {
					jSplitPane1.setLeftComponent(jTabbedPane1);
				} else {
					jSplitPane1.setLeftComponent(null);
				}
			}
		});

		// ---------------------------------------------------------------------

		JPanel jPanelJToggleButton = new JPanel();
		jPanelJToggleButton.setBackground(color);
		jPanelJToggleButton.add(jToggleButton);
		JLabel jLabel = new JLabel(title);
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Dialog", 1, 18)); // NOI18N
		jPanelJToggleButton.add(jLabel);
		// ---------------------------------------------------------------------

		jTextFieldFilter1.setPreferredSize(new Dimension(150, 28));
		jTextFieldFilter2.setPreferredSize(new Dimension(150, 28));
		jTextFieldFilter3.setPreferredSize(new Dimension(150, 28));

		jPanel4.setBackground(Color.WHITE);

		jSpinner1.setPreferredSize(new Dimension(70, 28));
		jSpinner1.setSize(100, 28);
		jSpinner1.setToolTipText("0 páginas");
		spinnerModel1 = new SpinnerNumberModel(0, 0, 0, 1);
		jSpinner1.setModel(spinnerModel1);
		jSpinner1.addChangeListener(new ChangeListener() {


			public void stateChanged(ChangeEvent e) {

				if (jSpinner1.getValue() instanceof Integer) {
					page = ((Integer) jSpinner1.getValue()).intValue();
				} else if (jSpinner1.getValue() instanceof Double) {
					page = ((Double) jSpinner1.getValue()).intValue();
				}
				offSet = page * limit;

				jButtonSearch.doClick();
			}
		});

		jButtonSearch.setToolTipText("Buscar.");
		jButtonSearch.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-find.png")); // NOI18N
		jButtonSearch.setPreferredSize(new Dimension(27, 27));
		jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				searchData();
			}
		});
		jSpinner1.setToolTipText("0 páginas");

		jLabel3.setText("0 - 0 de 0");
		jLabel3.setToolTipText("0 páginas");

		jButtonCsv.setIcon(new ImageIcon(JFrameMainGui.iconPath + "application-vnd.ms-excel.png")); // NOI18N
		jButtonCsv.setPreferredSize(new Dimension(27, 27));
		jButtonCsv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buildCsv();
			}
		});

		// ----------------------------------------------------------------

		jPanelHeaderRow1.add(jButtonCsv);
		jPanelHeaderRow1.add(jLabel1);
		jPanelHeaderRow1.add(jComboBoxFilter1);
		jPanelHeaderRow1.add(jTextFieldFilter1);
		jPanelHeaderRow1.add(jComboBoxFilter2);
		jPanelHeaderRow1.add(jTextFieldFilter2);
		jPanelHeaderRow1.add(jComboBoxFilter3);
		jPanelHeaderRow1.add(jTextFieldFilter3);

		jPanelHeaderRow2.add(jLabel2);
		jPanelHeaderRow2.add(jComboBoxOrder1);
		jPanelHeaderRow2.add(jComboBoxOrder2);
		jPanelHeaderRow2.add(jComboBoxOrder3);

		// ----------------------------------------------------------------

		jPanelHeaderRow1.setBackground(Color.WHITE);
		jPanelHeaderRow2.setBackground(Color.WHITE);

		jPanelHeaderRow.setBackground(Color.WHITE);
		jPanelHeaderRow.setLayout(new BorderLayout(1, 3));
		jPanelHeaderRow.add(jPanelHeaderRow1, BorderLayout.NORTH);
		jPanelHeaderRow.add(jPanelHeaderRow2, BorderLayout.CENTER);

		// ---------------------------------------------------------------------

		jPanel4.add(jButtonSearch);
		jPanel4.add(jSpinner1);
		jPanel4.add(jLabel3);

		// ---------------------------------------------------------------------

		jPanelHeader = new JPanel();
		jPanelHeader.setLayout(new BorderLayout(1, 3));
		jPanelHeader.setBackground(Color.WHITE);
		jPanelHeader.add(jPanelJToggleButton, BorderLayout.WEST);
		jPanelHeader.add(jPanelHeaderRow, BorderLayout.CENTER);
		jPanelHeader.add(jPanel4, BorderLayout.EAST);

		// ---------------------------------------------------------------------

		jTable1.setAutoCreateRowSorter(true);
		initColumnNames();

		// =======================================================================================================================

		// --------------------------------------------------------------------------------------------

		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(Color.WHITE);
		jPanel1.setLayout(new GridLayout(0, 3));

		jTabbedPane1.addTab("1 - 100 ", jPanel1);

		JPanel jPanel2 = new JPanel();
		jPanel2.setBackground(Color.WHITE);
		jPanel2.setLayout(new GridLayout(0, 3));

		jTabbedPane1.addTab("101 - 200 ", jPanel2);

		JPanel jPanel3 = new JPanel();
		jPanel3.setBackground(Color.WHITE);
		jPanel3.setLayout(new GridLayout(0, 3));

		jTabbedPane1.addTab("201 - 300 ", jPanel3);

		JPanel jPanel4 = new JPanel();
		jPanel4.setBackground(Color.WHITE);
		jPanel4.setLayout(new GridLayout(0, 3));

		jTabbedPane1.addTab("301 - 400 ", jPanel4);

		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.WHITE);
		jPanel5.setLayout(new GridLayout(0, 3));

		jTabbedPane1.addTab("401 - 500 ", jPanel5);

		// --------------------------------------------------------------------------------------------

		jCheckBoxList = new JCheckBox[columnNames.length];
		jCheckBoxMap = new HashMap<String, Integer>();

		for (int i = 0; i < jCheckBoxList.length; i++) {
			jCheckBoxList[i] = new JCheckBox(columnNames[i]);

			jCheckBoxMap.put(columnNames[i], i);

			jCheckBoxList[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					JCheckBox jCheckBox = ((JCheckBox) evt.getSource());
					int c = jCheckBoxMap.get(jCheckBox.getText());

					if (jCheckBox.isSelected()) {
						showColumn(c);
					} else {
						hidenColumn(c);
					}
				}
			});

			if (i < 100) {
				jPanel1.add(jCheckBoxList[i]);
			} else if (i < 200) {
				jPanel2.add(jCheckBoxList[i]);
			} else if (i < 300) {
				jPanel3.add(jCheckBoxList[i]);
			} else if (i < 400) {
				jPanel4.add(jCheckBoxList[i]);
			} else if (i < 500) {
				jPanel5.add(jCheckBoxList[i]);
			}

			jComboBoxFilter1.addItem(new ComboItem(columnNames[i] + ":", "c" + (i + 1)));
			jComboBoxFilter2.addItem(new ComboItem(columnNames[i] + ":", "c" + (i + 1)));
			jComboBoxFilter3.addItem(new ComboItem(columnNames[i] + ":", "c" + (i + 1)));

			jComboBoxOrder1.addItem(new ComboItem(columnNames[i], "c" + (i + 1)));
			jComboBoxOrder2.addItem(new ComboItem(columnNames[i], "c" + (i + 1)));
			jComboBoxOrder3.addItem(new ComboItem(columnNames[i], "c" + (i + 1)));

		}

		// =======================================================================================================================

		hidenColumns();

		for (int c : showColumn) {
			jCheckBoxList[c].setSelected(true);
			showColumn(c);
		}

		jComboBoxFilter1.setSelectedIndex(filterColumn1);
		jComboBoxFilter2.setSelectedIndex(filterColumn2);
		jComboBoxFilter3.setSelectedIndex(filterColumn3);

		jComboBoxOrder1.setSelectedIndex(orderColumn1);
		jComboBoxOrder2.setSelectedIndex(orderColumn2);
		jComboBoxOrder3.setSelectedIndex(orderColumn3);

		jScrollPane1.setViewportView(jTable1);
		jScrollPane1.setBackground(Color.WHITE);

		this.add(jPanelHeader, BorderLayout.NORTH);
		this.add(jSplitPane1, BorderLayout.CENTER);

		jSplitPane1.setDividerSize(5);
		jSplitPane1.setLeftComponent(null);
		jSplitPane1.setRightComponent(jScrollPane1);
		jToggleButton.setSelected(false);

	}

	private void hidenColumn(int i) {
		TableColumn column = jTable1.getColumnModel().getColumn(i);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setWidth(0);
		column.setPreferredWidth(0);
	}

	private void showColumn(int i) {
		TableColumn column = jTable1.getColumnModel().getColumn(i);
		column.setMinWidth(20);
		column.setMaxWidth(300);
		column.setWidth(50);
		column.setPreferredWidth(50);
	}

	private void hidenColumns() {
		int c = jTable1.getColumnCount();

		for (int i = 0; i < c; i++) {
			hidenColumn(i);
		}

	}

	private void showColumns() {
		int c = jTable1.getColumnCount();

		for (int i = 0; i < c; i++) {
			hidenColumn(i);
		}

	}

	protected void searchData() {
		try {

			MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg(((ComboItem) jComboBoxOrder1.getSelectedItem()).getId(), true),
					new MapperQueryOrderArg(((ComboItem) jComboBoxOrder2.getSelectedItem()).getId(), true), new MapperQueryOrderArg(((ComboItem) jComboBoxOrder3.getSelectedItem()).getId(), true) };

			List<MapperQueryArg> argListTemp = new ArrayList<MapperQueryArg>();

			if (jTextFieldFilter1.getText() != null && jTextFieldFilter1.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter1.getSelectedItem()).getId(), jTextFieldFilter1.getText().trim());
				argListTemp.add(a);

			}

			if (jTextFieldFilter2.getText() != null && jTextFieldFilter2.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter2.getSelectedItem()).getId(), jTextFieldFilter2.getText().trim());
				argListTemp.add(a);
			}

			if (jTextFieldFilter3.getText() != null && jTextFieldFilter3.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter3.getSelectedItem()).getId(), jTextFieldFilter3.getText().trim());
				argListTemp.add(a);
			}

			MapperQueryArg[] argList = new MapperQueryArg[argListTemp.size()];
			argList = argListTemp.toArray(argList);

			UnlBo bo = Context.getBean("unlBo", UnlBo.class);
			ResultList rl = bo.find(argList, orderList, offSet, limit, table);

			model = new Object[rl.getList().length][columnNames.length];

			for (int i = 0; i < rl.getList().length; i++) {
				Unl unl = (Unl) rl.getList()[i];

				model[i][0] = unl.getC1();
				model[i][1] = unl.getC2();
				model[i][2] = unl.getC3();
				model[i][3] = unl.getC4();
				model[i][4] = unl.getC5();
				model[i][5] = unl.getC6();
				model[i][6] = unl.getC7();
				model[i][7] = unl.getC8();
				model[i][8] = unl.getC9();
				model[i][9] = unl.getC10();
				model[i][10] = unl.getC11();
				model[i][11] = unl.getC12();
				model[i][12] = unl.getC13();
				model[i][13] = unl.getC14();
				model[i][14] = unl.getC15();
				model[i][15] = unl.getC16();
				model[i][16] = unl.getC17();
				model[i][17] = unl.getC18();
				model[i][18] = unl.getC19();
				model[i][19] = unl.getC20();
				model[i][20] = unl.getC21();
				model[i][21] = unl.getC22();
				model[i][22] = unl.getC23();
				model[i][23] = unl.getC24();
				model[i][24] = unl.getC25();
				model[i][25] = unl.getC26();
				model[i][26] = unl.getC27();
				model[i][27] = unl.getC28();
				model[i][28] = unl.getC29();
				model[i][29] = unl.getC30();
				model[i][30] = unl.getC31();
				model[i][31] = unl.getC32();
				model[i][32] = unl.getC33();
				model[i][33] = unl.getC34();
				model[i][34] = unl.getC35();
				model[i][35] = unl.getC36();
				model[i][36] = unl.getC37();
				model[i][37] = unl.getC38();
				model[i][38] = unl.getC39();
				model[i][39] = unl.getC40();
				model[i][40] = unl.getC41();
				model[i][41] = unl.getC42();
				model[i][42] = unl.getC43();
				model[i][43] = unl.getC44();
				model[i][44] = unl.getC45();
				model[i][45] = unl.getC46();
				model[i][46] = unl.getC47();
				model[i][47] = unl.getC48();
				model[i][48] = unl.getC49();
				model[i][49] = unl.getC50();
				model[i][50] = unl.getC51();
				model[i][51] = unl.getC52();
				model[i][52] = unl.getC53();
				model[i][53] = unl.getC54();
				model[i][54] = unl.getC55();
				model[i][55] = unl.getC56();
				model[i][56] = unl.getC57();
				model[i][57] = unl.getC58();
				model[i][58] = unl.getC59();
				model[i][59] = unl.getC60();
				model[i][60] = unl.getC61();
				model[i][61] = unl.getC62();
				model[i][62] = unl.getC63();
				model[i][63] = unl.getC64();
				model[i][64] = unl.getC65();
				model[i][65] = unl.getC66();
				model[i][66] = unl.getC67();
				model[i][67] = unl.getC68();
				model[i][68] = unl.getC69();
				model[i][69] = unl.getC70();
				model[i][70] = unl.getC71();
				model[i][71] = unl.getC72();
				model[i][72] = unl.getC73();
				model[i][73] = unl.getC74();
				model[i][74] = unl.getC75();
				model[i][75] = unl.getC76();
				model[i][76] = unl.getC77();
				model[i][77] = unl.getC78();
				model[i][78] = unl.getC79();
				model[i][79] = unl.getC80();
				model[i][80] = unl.getC81();
				model[i][81] = unl.getC82();
				model[i][82] = unl.getC83();
				model[i][83] = unl.getC84();
				model[i][84] = unl.getC85();
				model[i][85] = unl.getC86();
				model[i][86] = unl.getC87();
				model[i][87] = unl.getC88();
				model[i][88] = unl.getC89();
				model[i][89] = unl.getC90();
				model[i][90] = unl.getC91();
				model[i][91] = unl.getC92();
				model[i][92] = unl.getC93();
				model[i][93] = unl.getC94();
				model[i][94] = unl.getC95();
				model[i][95] = unl.getC96();
				model[i][96] = unl.getC97();
				model[i][97] = unl.getC98();
				model[i][98] = unl.getC99();
				model[i][99] = unl.getC100();
				model[i][100] = unl.getC101();
				model[i][101] = unl.getC102();
				model[i][102] = unl.getC103();
				model[i][103] = unl.getC104();
				model[i][104] = unl.getC105();
				model[i][105] = unl.getC106();
				model[i][106] = unl.getC107();
				model[i][107] = unl.getC108();
				model[i][108] = unl.getC109();
				model[i][109] = unl.getC110();
				model[i][110] = unl.getC111();
				model[i][111] = unl.getC112();
				model[i][112] = unl.getC113();
				model[i][113] = unl.getC114();
				model[i][114] = unl.getC115();
				model[i][115] = unl.getC116();
				model[i][116] = unl.getC117();
				model[i][117] = unl.getC118();
				model[i][118] = unl.getC119();
				model[i][119] = unl.getC120();
				model[i][120] = unl.getC121();
				model[i][121] = unl.getC122();
				model[i][122] = unl.getC123();
				model[i][123] = unl.getC124();
				model[i][124] = unl.getC125();
				model[i][125] = unl.getC126();
				model[i][126] = unl.getC127();
				model[i][127] = unl.getC128();
				model[i][128] = unl.getC129();
				model[i][129] = unl.getC130();
				model[i][130] = unl.getC131();
				model[i][131] = unl.getC132();
				model[i][132] = unl.getC133();
				model[i][133] = unl.getC134();
				model[i][134] = unl.getC135();
				model[i][135] = unl.getC136();
				model[i][136] = unl.getC137();
				model[i][137] = unl.getC138();
				model[i][138] = unl.getC139();
				model[i][139] = unl.getC140();
				model[i][140] = unl.getC141();
				model[i][141] = unl.getC142();
				model[i][142] = unl.getC143();
				model[i][143] = unl.getC144();
				model[i][144] = unl.getC145();
				model[i][145] = unl.getC146();
				model[i][146] = unl.getC147();
				model[i][147] = unl.getC148();
				model[i][148] = unl.getC149();
				model[i][149] = unl.getC150();
				model[i][150] = unl.getC151();
				model[i][151] = unl.getC152();
				model[i][152] = unl.getC153();
				model[i][153] = unl.getC154();
				model[i][154] = unl.getC155();
				model[i][155] = unl.getC156();
				model[i][156] = unl.getC157();
				model[i][157] = unl.getC158();
				model[i][158] = unl.getC159();
				model[i][159] = unl.getC160();
				model[i][160] = unl.getC161();
				model[i][161] = unl.getC162();
				model[i][162] = unl.getC163();
				model[i][163] = unl.getC164();
				model[i][164] = unl.getC165();
				model[i][165] = unl.getC166();
				model[i][166] = unl.getC167();
				model[i][167] = unl.getC168();
				model[i][168] = unl.getC169();
				model[i][169] = unl.getC170();
				model[i][170] = unl.getC171();
				model[i][171] = unl.getC172();
				model[i][172] = unl.getC173();
				model[i][173] = unl.getC174();
				model[i][174] = unl.getC175();
				model[i][175] = unl.getC176();
				model[i][176] = unl.getC177();
				model[i][177] = unl.getC178();
				model[i][178] = unl.getC179();
				model[i][179] = unl.getC180();
				model[i][180] = unl.getC181();
				model[i][181] = unl.getC182();
				model[i][182] = unl.getC183();
				model[i][183] = unl.getC184();
				model[i][184] = unl.getC185();
				model[i][185] = unl.getC186();
				model[i][186] = unl.getC187();
				model[i][187] = unl.getC188();
				model[i][188] = unl.getC189();
				model[i][189] = unl.getC190();
				model[i][190] = unl.getC191();
				model[i][191] = unl.getC192();
				model[i][192] = unl.getC193();
				model[i][193] = unl.getC194();
				model[i][194] = unl.getC195();
				model[i][195] = unl.getC196();
				model[i][196] = unl.getC197();
				model[i][197] = unl.getC198();
				model[i][198] = unl.getC199();
				model[i][199] = unl.getC200();
				model[i][200] = unl.getC201();
				model[i][201] = unl.getC202();
				model[i][202] = unl.getC203();
				model[i][203] = unl.getC204();
				model[i][204] = unl.getC205();
				model[i][205] = unl.getC206();
				model[i][206] = unl.getC207();
				model[i][207] = unl.getC208();
				model[i][208] = unl.getC209();
				model[i][209] = unl.getC210();
				model[i][210] = unl.getC211();
				model[i][211] = unl.getC212();
				model[i][212] = unl.getC213();
				model[i][213] = unl.getC214();
				model[i][214] = unl.getC215();
				model[i][215] = unl.getC216();
				model[i][216] = unl.getC217();
				model[i][217] = unl.getC218();
				model[i][218] = unl.getC219();
				model[i][219] = unl.getC220();
				model[i][220] = unl.getC221();
				model[i][221] = unl.getC222();
				model[i][222] = unl.getC223();
				model[i][223] = unl.getC224();
				model[i][224] = unl.getC225();
				model[i][225] = unl.getC226();
				model[i][226] = unl.getC227();
				model[i][227] = unl.getC228();
				model[i][228] = unl.getC229();
				model[i][229] = unl.getC230();
				model[i][230] = unl.getC231();
				model[i][231] = unl.getC232();
				model[i][232] = unl.getC233();
				model[i][233] = unl.getC234();
				model[i][234] = unl.getC235();
				model[i][235] = unl.getC236();
				model[i][236] = unl.getC237();
				model[i][237] = unl.getC238();
				model[i][238] = unl.getC239();
				model[i][239] = unl.getC240();
				model[i][240] = unl.getC241();
				model[i][241] = unl.getC242();
				model[i][242] = unl.getC243();
				model[i][243] = unl.getC244();
				model[i][244] = unl.getC245();
				model[i][245] = unl.getC246();
				model[i][246] = unl.getC247();
				model[i][247] = unl.getC248();
				model[i][248] = unl.getC249();
				model[i][249] = unl.getC250();
				model[i][250] = unl.getC251();
				model[i][251] = unl.getC252();
				model[i][252] = unl.getC253();
				model[i][253] = unl.getC254();
				model[i][254] = unl.getC255();
				model[i][255] = unl.getC256();
				model[i][256] = unl.getC257();
				model[i][257] = unl.getC258();
				model[i][258] = unl.getC259();
				model[i][259] = unl.getC260();
				model[i][260] = unl.getC261();
				model[i][261] = unl.getC262();
				model[i][262] = unl.getC263();
				model[i][263] = unl.getC264();
				model[i][264] = unl.getC265();
				model[i][265] = unl.getC266();
				model[i][266] = unl.getC267();
				model[i][267] = unl.getC268();
				model[i][268] = unl.getC269();
				model[i][269] = unl.getC270();
				model[i][270] = unl.getC271();
				model[i][271] = unl.getC272();
				model[i][272] = unl.getC273();
				model[i][273] = unl.getC274();
				model[i][274] = unl.getC275();
				model[i][275] = unl.getC276();
				model[i][276] = unl.getC277();
				model[i][277] = unl.getC278();
				model[i][278] = unl.getC279();
				model[i][279] = unl.getC280();
				model[i][280] = unl.getC281();
				model[i][281] = unl.getC282();
				model[i][282] = unl.getC283();
				model[i][283] = unl.getC284();
				model[i][284] = unl.getC285();
				model[i][285] = unl.getC286();
				model[i][286] = unl.getC287();
				model[i][287] = unl.getC288();
				model[i][288] = unl.getC289();
				model[i][289] = unl.getC290();
				model[i][290] = unl.getC291();
				model[i][291] = unl.getC292();
				model[i][292] = unl.getC293();
				model[i][293] = unl.getC294();
				model[i][294] = unl.getC295();
				model[i][295] = unl.getC296();
				model[i][296] = unl.getC297();
				model[i][297] = unl.getC298();
				model[i][298] = unl.getC299();
				model[i][299] = unl.getC300();
				model[i][300] = unl.getC301();
				model[i][301] = unl.getC302();
				model[i][302] = unl.getC303();
				model[i][303] = unl.getC304();
				model[i][304] = unl.getC305();
				model[i][305] = unl.getC306();
				model[i][306] = unl.getC307();
				model[i][307] = unl.getC308();
				model[i][308] = unl.getC309();
				model[i][309] = unl.getC310();
				model[i][310] = unl.getC311();
				model[i][311] = unl.getC312();
				model[i][312] = unl.getC313();
				model[i][313] = unl.getC314();
				model[i][314] = unl.getC315();
				model[i][315] = unl.getC316();
				model[i][316] = unl.getC317();
				model[i][317] = unl.getC318();
				model[i][318] = unl.getC319();
				model[i][319] = unl.getC320();
				model[i][320] = unl.getC321();
				model[i][321] = unl.getC322();
				model[i][322] = unl.getC323();
				model[i][323] = unl.getC324();
				model[i][324] = unl.getC325();
				model[i][325] = unl.getC326();
				model[i][326] = unl.getC327();
				model[i][327] = unl.getC328();
				model[i][328] = unl.getC329();
				model[i][329] = unl.getC330();
				model[i][330] = unl.getC331();
				model[i][331] = unl.getC332();
				model[i][332] = unl.getC333();
				model[i][333] = unl.getC334();
				model[i][334] = unl.getC335();
				model[i][335] = unl.getC336();
				model[i][336] = unl.getC337();
				model[i][337] = unl.getC338();
				model[i][338] = unl.getC339();
				model[i][339] = unl.getC340();
				model[i][340] = unl.getC341();
				model[i][341] = unl.getC342();
				model[i][342] = unl.getC343();
				model[i][343] = unl.getC344();
				model[i][344] = unl.getC345();
				model[i][345] = unl.getC346();
				model[i][346] = unl.getC347();
				model[i][347] = unl.getC348();
				model[i][348] = unl.getC349();
				model[i][349] = unl.getC350();
				model[i][350] = unl.getC351();
				model[i][351] = unl.getC352();
				model[i][352] = unl.getC353();
				model[i][353] = unl.getC354();
				model[i][354] = unl.getC355();
				model[i][355] = unl.getC356();
				model[i][356] = unl.getC357();
				model[i][357] = unl.getC358();
				model[i][358] = unl.getC359();
				model[i][359] = unl.getC360();
				model[i][360] = unl.getC361();
				model[i][361] = unl.getC362();
				model[i][362] = unl.getC363();
				model[i][363] = unl.getC364();
				model[i][364] = unl.getC365();
				model[i][365] = unl.getC366();
				model[i][366] = unl.getC367();
				model[i][367] = unl.getC368();
				model[i][368] = unl.getC369();
				model[i][369] = unl.getC370();
				model[i][370] = unl.getC371();
				model[i][371] = unl.getC372();
				model[i][372] = unl.getC373();
				model[i][373] = unl.getC374();
				model[i][374] = unl.getC375();
				model[i][375] = unl.getC376();
				model[i][376] = unl.getC377();
				model[i][377] = unl.getC378();
				model[i][378] = unl.getC379();
				model[i][379] = unl.getC380();
				model[i][380] = unl.getC381();
				model[i][381] = unl.getC382();
				model[i][382] = unl.getC383();
				model[i][383] = unl.getC384();
				model[i][384] = unl.getC385();
				model[i][385] = unl.getC386();
				model[i][386] = unl.getC387();
				model[i][387] = unl.getC388();
				model[i][388] = unl.getC389();
				model[i][389] = unl.getC390();
				model[i][390] = unl.getC391();
				model[i][391] = unl.getC392();
				model[i][392] = unl.getC393();
				model[i][393] = unl.getC394();
				model[i][394] = unl.getC395();
				model[i][395] = unl.getC396();
				model[i][396] = unl.getC397();
				model[i][397] = unl.getC398();
				model[i][398] = unl.getC399();
				model[i][399] = unl.getC400();
				model[i][400] = unl.getC401();
				model[i][401] = unl.getC402();
				model[i][402] = unl.getC403();
				model[i][403] = unl.getC404();
				model[i][404] = unl.getC405();
				model[i][405] = unl.getC406();
				model[i][406] = unl.getC407();
				model[i][407] = unl.getC408();
				model[i][408] = unl.getC409();
				model[i][409] = unl.getC410();
				model[i][410] = unl.getC411();
				model[i][411] = unl.getC412();
				model[i][412] = unl.getC413();
				model[i][413] = unl.getC414();
				model[i][414] = unl.getC415();
				model[i][415] = unl.getC416();
				model[i][416] = unl.getC417();
				model[i][417] = unl.getC418();
				model[i][418] = unl.getC419();
				model[i][419] = unl.getC420();
				model[i][420] = unl.getC421();
				model[i][421] = unl.getC422();
				model[i][422] = unl.getC423();
				model[i][423] = unl.getC424();
				model[i][424] = unl.getC425();
				model[i][425] = unl.getC426();
				model[i][426] = unl.getC427();
				model[i][427] = unl.getC428();
				model[i][428] = unl.getC429();
				model[i][429] = unl.getC430();
				model[i][430] = unl.getC431();
				model[i][431] = unl.getC432();
				model[i][432] = unl.getC433();
				model[i][433] = unl.getC434();
				model[i][434] = unl.getC435();
				model[i][435] = unl.getC436();
				model[i][436] = unl.getC437();
				model[i][437] = unl.getC438();
				model[i][438] = unl.getC439();
				model[i][439] = unl.getC440();
				model[i][440] = unl.getC441();
				model[i][441] = unl.getC442();
				model[i][442] = unl.getC443();
				model[i][443] = unl.getC444();
				model[i][444] = unl.getC445();
				model[i][445] = unl.getC446();
				model[i][446] = unl.getC447();
				model[i][447] = unl.getC448();
				model[i][448] = unl.getC449();
				model[i][449] = unl.getC450();
				model[i][450] = unl.getC451();
				model[i][451] = unl.getC452();
				model[i][452] = unl.getC453();
				model[i][453] = unl.getC454();
				model[i][454] = unl.getC455();
				model[i][455] = unl.getC456();
				model[i][456] = unl.getC457();
				model[i][457] = unl.getC458();
				model[i][458] = unl.getC459();
				model[i][459] = unl.getC460();
				model[i][460] = unl.getC461();
				model[i][461] = unl.getC462();
				model[i][462] = unl.getC463();
				model[i][463] = unl.getC464();
				model[i][464] = unl.getC465();
				model[i][465] = unl.getC466();
				model[i][466] = unl.getC467();
				model[i][467] = unl.getC468();
				model[i][468] = unl.getC469();
				model[i][469] = unl.getC470();
				model[i][470] = unl.getC471();
				model[i][471] = unl.getC472();
				model[i][472] = unl.getC473();
				model[i][473] = unl.getC474();
				model[i][474] = unl.getC475();
				model[i][475] = unl.getC476();
				model[i][476] = unl.getC477();
				model[i][477] = unl.getC478();
				model[i][478] = unl.getC479();
				model[i][479] = unl.getC480();
				model[i][480] = unl.getC481();
				model[i][481] = unl.getC482();
				model[i][482] = unl.getC483();
				model[i][483] = unl.getC484();
				model[i][484] = unl.getC485();
				model[i][485] = unl.getC486();
				model[i][486] = unl.getC487();
				model[i][487] = unl.getC488();
				model[i][488] = unl.getC489();
				model[i][489] = unl.getC490();
				model[i][490] = unl.getC491();
				model[i][491] = unl.getC492();
				model[i][492] = unl.getC493();
				model[i][493] = unl.getC494();
				model[i][494] = unl.getC495();
				model[i][495] = unl.getC496();
				model[i][496] = unl.getC497();
				model[i][497] = unl.getC498();
				model[i][498] = unl.getC499();
				model[i][499] = unl.getC500();

			}

			initColumnNames();

			cantPages = rl.getCantPages();

			if (model.length > 0) {

				if (cantPages == 1) {
					jLabel3.setToolTipText(cantPages + " página");
					jSpinner1.setToolTipText(cantPages + " página");
					jLabel3.setText((offSet + 1) + " - " + model.length + " de " + rl.getNumberOfRecords());
				} else {
					jLabel3.setToolTipText(cantPages + " páginas");
					jSpinner1.setToolTipText(cantPages + " páginas");

					if ((offSet + limit) > (offSet + model.length)) {
						jLabel3.setText((offSet + 1) + " - " + rl.getNumberOfRecords() + " de " + rl.getNumberOfRecords());
					} else {
						jLabel3.setText((offSet + 1) + " - " + (offSet + limit) + " de " + rl.getNumberOfRecords());
					}
				}

				if (jSpinner1.getValue() instanceof Integer) {
					page = ((Integer) jSpinner1.getValue()).intValue();
				} else if (jSpinner1.getValue() instanceof Double) {
					page = ((Double) jSpinner1.getValue()).intValue();
				}

				// if(page == 0){
				// page = 1;
				// }

				spinnerModel1 = new SpinnerNumberModel(page, 0, cantPages - 1, 1);
				jSpinner1.setModel(spinnerModel1);

			} else {
				jLabel3.setText("0 - 0 de 0");
				jLabel3.setToolTipText("0 páginas");
				jSpinner1.setToolTipText("0 páginas");

				page = 0;
				offSet = 0;

				spinnerModel1 = new SpinnerNumberModel(0, 0, 0, 1);
				jSpinner1.setModel(spinnerModel1);
			}

			if (jCheckBoxList[0].isSelected() == true) {
				this.showColumn(0);
			}

			resetVisibleColumns();

			if (rl == null || rl.getList() == null || rl.getList().length == 0) {
				JOptionPane.showMessageDialog(null, "No se encontraron resultados. Resultados 0 registros encontrados.", "Resultado de la Búsqueda", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void resetVisibleColumns() {

		for (JCheckBox jCheckBox : jCheckBoxList) {

			int c = jCheckBoxMap.get(jCheckBox.getText());

			if (jCheckBox.isSelected() == true) {
				this.showColumn(c);
			} else {
				this.hidenColumn(c);
			}

		}

	}

	private void buildCsv() {

		try {

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(this, "Esta operación puede tardar ¿ Esta seguro de proseguir ? ", "¿ Generar CSV de " + title + " ?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, new ImageIcon((JFrameMainGui.iconPath + "user-busy.png")), options, options[1]);

			if (choice != JOptionPane.YES_OPTION) {

				return;
			}

			// =====================================================================================================================================================================

			MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg(((ComboItem) jComboBoxOrder1.getSelectedItem()).getId(), true),
					new MapperQueryOrderArg(((ComboItem) jComboBoxOrder2.getSelectedItem()).getId(), true), new MapperQueryOrderArg(((ComboItem) jComboBoxOrder3.getSelectedItem()).getId(), true) };

			List<MapperQueryArg> argListTemp = new ArrayList<MapperQueryArg>();

			if (jTextFieldFilter1.getText() != null && jTextFieldFilter1.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter1.getSelectedItem()).getId(), jTextFieldFilter1.getText().trim());
				argListTemp.add(a);

			}

			if (jTextFieldFilter2.getText() != null && jTextFieldFilter2.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter2.getSelectedItem()).getId(), jTextFieldFilter2.getText().trim());
				argListTemp.add(a);
			}

			if (jTextFieldFilter3.getText() != null && jTextFieldFilter3.getText().trim().length() > 0) {
				MapperQueryArg a = new MapperQueryArg();
				a.setContainsIgnoreCaseTraslate(((ComboItem) jComboBoxFilter3.getSelectedItem()).getId(), jTextFieldFilter3.getText().trim());
				argListTemp.add(a);
			}

			MapperQueryArg[] argList = new MapperQueryArg[argListTemp.size()];
			argList = argListTemp.toArray(argList);

			// =====================================================================================================================================================================

			JFileChooser jFileChooser1 = new JFileChooser();
			jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jFileChooser1.setMultiSelectionEnabled(false);

			int returnVal = jFileChooser1.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser1.getSelectedFile();

				// =====================================================================================================================================================================

				UnlBo bo = Context.getBean("unlBo", UnlBo.class);

				ResultList rl = bo.find(argList, orderList, 0, 5000, table);

				long cantPages = rl.getCantPages();

				// _________________________________________________________________________________________

				String path = file.getAbsolutePath();

				SO.createFile(path, table + ".csv", "");

				String outputFile = path + File.separatorChar + table + ".csv";

				CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), '|');

				for (int i = 0; i < columnNames.length; i++) {
					csvOutput.write(columnNames[i]);
				}

				csvOutput.endRecord();

				// _________________________________________________________________________________________

				if (cantPages > 0) {

					for (int i = 0; i < cantPages; i++) {

						utilCsv(bo, csvOutput, argList, orderList, i * 5000, 5000);

					}
				}

				csvOutput.close();

				// _________________________________________________________________________________________

				JOptionPane.showMessageDialog(this, "El archivo se descargo con éxito en " + file.getAbsolutePath() + File.separatorChar + table + ".csv", "Descarga de Archivo",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon((JFrameMainGui.iconPath + "dialog-information.png")));

				// =====================================================================================================================================================================

			}

			// =====================================================================================================================================================================

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void utilCsv(UnlBo bo, CsvWriter csvOutput, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, int offSet, int limit) throws Exception {

		ResultList rl = bo.find(argList, orderList, offSet, limit, table);

		if (rl == null || rl.getList().length == 0) {
			return;
		}

		for (int i = 0; i < rl.getList().length; i++) {

			Unl unl = (Unl) rl.getList()[i];

			csvOutput.write(unl.getC1());
			csvOutput.write(unl.getC2());
			csvOutput.write(unl.getC3());
			csvOutput.write(unl.getC4());
			csvOutput.write(unl.getC5());
			csvOutput.write(unl.getC6());
			csvOutput.write(unl.getC7());
			csvOutput.write(unl.getC8());
			csvOutput.write(unl.getC9());
			csvOutput.write(unl.getC10());
			csvOutput.write(unl.getC11());
			csvOutput.write(unl.getC12());
			csvOutput.write(unl.getC13());
			csvOutput.write(unl.getC14());
			csvOutput.write(unl.getC15());
			csvOutput.write(unl.getC16());
			csvOutput.write(unl.getC17());
			csvOutput.write(unl.getC18());
			csvOutput.write(unl.getC19());
			csvOutput.write(unl.getC20());
			csvOutput.write(unl.getC21());
			csvOutput.write(unl.getC22());
			csvOutput.write(unl.getC23());
			csvOutput.write(unl.getC24());
			csvOutput.write(unl.getC25());
			csvOutput.write(unl.getC26());
			csvOutput.write(unl.getC27());
			csvOutput.write(unl.getC28());
			csvOutput.write(unl.getC29());
			csvOutput.write(unl.getC30());
			csvOutput.write(unl.getC31());
			csvOutput.write(unl.getC32());
			csvOutput.write(unl.getC33());
			csvOutput.write(unl.getC34());
			csvOutput.write(unl.getC35());
			csvOutput.write(unl.getC36());
			csvOutput.write(unl.getC37());
			csvOutput.write(unl.getC38());
			csvOutput.write(unl.getC39());
			csvOutput.write(unl.getC40());
			csvOutput.write(unl.getC41());
			csvOutput.write(unl.getC42());
			csvOutput.write(unl.getC43());
			csvOutput.write(unl.getC44());
			csvOutput.write(unl.getC45());
			csvOutput.write(unl.getC46());
			csvOutput.write(unl.getC47());
			csvOutput.write(unl.getC48());
			csvOutput.write(unl.getC49());
			csvOutput.write(unl.getC50());
			csvOutput.write(unl.getC51());
			csvOutput.write(unl.getC52());
			csvOutput.write(unl.getC53());
			csvOutput.write(unl.getC54());
			csvOutput.write(unl.getC55());
			csvOutput.write(unl.getC56());
			csvOutput.write(unl.getC57());
			csvOutput.write(unl.getC58());
			csvOutput.write(unl.getC59());
			csvOutput.write(unl.getC60());
			csvOutput.write(unl.getC61());
			csvOutput.write(unl.getC62());
			csvOutput.write(unl.getC63());
			csvOutput.write(unl.getC64());
			csvOutput.write(unl.getC65());
			csvOutput.write(unl.getC66());
			csvOutput.write(unl.getC67());
			csvOutput.write(unl.getC68());
			csvOutput.write(unl.getC69());
			csvOutput.write(unl.getC70());
			csvOutput.write(unl.getC71());
			csvOutput.write(unl.getC72());
			csvOutput.write(unl.getC73());
			csvOutput.write(unl.getC74());
			csvOutput.write(unl.getC75());
			csvOutput.write(unl.getC76());
			csvOutput.write(unl.getC77());
			csvOutput.write(unl.getC78());
			csvOutput.write(unl.getC79());
			csvOutput.write(unl.getC80());
			csvOutput.write(unl.getC81());
			csvOutput.write(unl.getC82());
			csvOutput.write(unl.getC83());
			csvOutput.write(unl.getC84());
			csvOutput.write(unl.getC85());
			csvOutput.write(unl.getC86());
			csvOutput.write(unl.getC87());
			csvOutput.write(unl.getC88());
			csvOutput.write(unl.getC89());
			csvOutput.write(unl.getC90());
			csvOutput.write(unl.getC91());
			csvOutput.write(unl.getC92());
			csvOutput.write(unl.getC93());
			csvOutput.write(unl.getC94());
			csvOutput.write(unl.getC95());
			csvOutput.write(unl.getC96());
			csvOutput.write(unl.getC97());
			csvOutput.write(unl.getC98());
			csvOutput.write(unl.getC99());
			csvOutput.write(unl.getC100());
			csvOutput.write(unl.getC101());
			csvOutput.write(unl.getC102());
			csvOutput.write(unl.getC103());
			csvOutput.write(unl.getC104());
			csvOutput.write(unl.getC105());
			csvOutput.write(unl.getC106());
			csvOutput.write(unl.getC107());
			csvOutput.write(unl.getC108());
			csvOutput.write(unl.getC109());
			csvOutput.write(unl.getC110());
			csvOutput.write(unl.getC111());
			csvOutput.write(unl.getC112());
			csvOutput.write(unl.getC113());
			csvOutput.write(unl.getC114());
			csvOutput.write(unl.getC115());
			csvOutput.write(unl.getC116());
			csvOutput.write(unl.getC117());
			csvOutput.write(unl.getC118());
			csvOutput.write(unl.getC119());
			csvOutput.write(unl.getC120());
			csvOutput.write(unl.getC121());
			csvOutput.write(unl.getC122());
			csvOutput.write(unl.getC123());
			csvOutput.write(unl.getC124());
			csvOutput.write(unl.getC125());
			csvOutput.write(unl.getC126());
			csvOutput.write(unl.getC127());
			csvOutput.write(unl.getC128());
			csvOutput.write(unl.getC129());
			csvOutput.write(unl.getC130());
			csvOutput.write(unl.getC131());
			csvOutput.write(unl.getC132());
			csvOutput.write(unl.getC133());
			csvOutput.write(unl.getC134());
			csvOutput.write(unl.getC135());
			csvOutput.write(unl.getC136());
			csvOutput.write(unl.getC137());
			csvOutput.write(unl.getC138());
			csvOutput.write(unl.getC139());
			csvOutput.write(unl.getC140());
			csvOutput.write(unl.getC141());
			csvOutput.write(unl.getC142());
			csvOutput.write(unl.getC143());
			csvOutput.write(unl.getC144());
			csvOutput.write(unl.getC145());
			csvOutput.write(unl.getC146());
			csvOutput.write(unl.getC147());
			csvOutput.write(unl.getC148());
			csvOutput.write(unl.getC149());
			csvOutput.write(unl.getC150());
			csvOutput.write(unl.getC151());
			csvOutput.write(unl.getC152());
			csvOutput.write(unl.getC153());
			csvOutput.write(unl.getC154());
			csvOutput.write(unl.getC155());
			csvOutput.write(unl.getC156());
			csvOutput.write(unl.getC157());
			csvOutput.write(unl.getC158());
			csvOutput.write(unl.getC159());
			csvOutput.write(unl.getC160());
			csvOutput.write(unl.getC161());
			csvOutput.write(unl.getC162());
			csvOutput.write(unl.getC163());
			csvOutput.write(unl.getC164());
			csvOutput.write(unl.getC165());
			csvOutput.write(unl.getC166());
			csvOutput.write(unl.getC167());
			csvOutput.write(unl.getC168());
			csvOutput.write(unl.getC169());
			csvOutput.write(unl.getC170());
			csvOutput.write(unl.getC171());
			csvOutput.write(unl.getC172());
			csvOutput.write(unl.getC173());
			csvOutput.write(unl.getC174());
			csvOutput.write(unl.getC175());
			csvOutput.write(unl.getC176());
			csvOutput.write(unl.getC177());
			csvOutput.write(unl.getC178());
			csvOutput.write(unl.getC179());
			csvOutput.write(unl.getC180());
			csvOutput.write(unl.getC181());
			csvOutput.write(unl.getC182());
			csvOutput.write(unl.getC183());
			csvOutput.write(unl.getC184());
			csvOutput.write(unl.getC185());
			csvOutput.write(unl.getC186());
			csvOutput.write(unl.getC187());
			csvOutput.write(unl.getC188());
			csvOutput.write(unl.getC189());
			csvOutput.write(unl.getC190());
			csvOutput.write(unl.getC191());
			csvOutput.write(unl.getC192());
			csvOutput.write(unl.getC193());
			csvOutput.write(unl.getC194());
			csvOutput.write(unl.getC195());
			csvOutput.write(unl.getC196());
			csvOutput.write(unl.getC197());
			csvOutput.write(unl.getC198());
			csvOutput.write(unl.getC199());
			csvOutput.write(unl.getC200());
			csvOutput.write(unl.getC201());
			csvOutput.write(unl.getC202());
			csvOutput.write(unl.getC203());
			csvOutput.write(unl.getC204());
			csvOutput.write(unl.getC205());
			csvOutput.write(unl.getC206());
			csvOutput.write(unl.getC207());
			csvOutput.write(unl.getC208());
			csvOutput.write(unl.getC209());
			csvOutput.write(unl.getC210());
			csvOutput.write(unl.getC211());
			csvOutput.write(unl.getC212());
			csvOutput.write(unl.getC213());
			csvOutput.write(unl.getC214());
			csvOutput.write(unl.getC215());
			csvOutput.write(unl.getC216());
			csvOutput.write(unl.getC217());
			csvOutput.write(unl.getC218());
			csvOutput.write(unl.getC219());
			csvOutput.write(unl.getC220());
			csvOutput.write(unl.getC221());
			csvOutput.write(unl.getC222());
			csvOutput.write(unl.getC223());
			csvOutput.write(unl.getC224());
			csvOutput.write(unl.getC225());
			csvOutput.write(unl.getC226());
			csvOutput.write(unl.getC227());
			csvOutput.write(unl.getC228());
			csvOutput.write(unl.getC229());
			csvOutput.write(unl.getC230());
			csvOutput.write(unl.getC231());
			csvOutput.write(unl.getC232());
			csvOutput.write(unl.getC233());
			csvOutput.write(unl.getC234());
			csvOutput.write(unl.getC235());
			csvOutput.write(unl.getC236());
			csvOutput.write(unl.getC237());
			csvOutput.write(unl.getC238());
			csvOutput.write(unl.getC239());
			csvOutput.write(unl.getC240());
			csvOutput.write(unl.getC241());
			csvOutput.write(unl.getC242());
			csvOutput.write(unl.getC243());
			csvOutput.write(unl.getC244());
			csvOutput.write(unl.getC245());
			csvOutput.write(unl.getC246());
			csvOutput.write(unl.getC247());
			csvOutput.write(unl.getC248());
			csvOutput.write(unl.getC249());
			csvOutput.write(unl.getC250());
			csvOutput.write(unl.getC251());
			csvOutput.write(unl.getC252());
			csvOutput.write(unl.getC253());
			csvOutput.write(unl.getC254());
			csvOutput.write(unl.getC255());
			csvOutput.write(unl.getC256());
			csvOutput.write(unl.getC257());
			csvOutput.write(unl.getC258());
			csvOutput.write(unl.getC259());
			csvOutput.write(unl.getC260());
			csvOutput.write(unl.getC261());
			csvOutput.write(unl.getC262());
			csvOutput.write(unl.getC263());
			csvOutput.write(unl.getC264());
			csvOutput.write(unl.getC265());
			csvOutput.write(unl.getC266());
			csvOutput.write(unl.getC267());
			csvOutput.write(unl.getC268());
			csvOutput.write(unl.getC269());
			csvOutput.write(unl.getC270());
			csvOutput.write(unl.getC271());
			csvOutput.write(unl.getC272());
			csvOutput.write(unl.getC273());
			csvOutput.write(unl.getC274());
			csvOutput.write(unl.getC275());
			csvOutput.write(unl.getC276());
			csvOutput.write(unl.getC277());
			csvOutput.write(unl.getC278());
			csvOutput.write(unl.getC279());
			csvOutput.write(unl.getC280());
			csvOutput.write(unl.getC281());
			csvOutput.write(unl.getC282());
			csvOutput.write(unl.getC283());
			csvOutput.write(unl.getC284());
			csvOutput.write(unl.getC285());
			csvOutput.write(unl.getC286());
			csvOutput.write(unl.getC287());
			csvOutput.write(unl.getC288());
			csvOutput.write(unl.getC289());
			csvOutput.write(unl.getC290());
			csvOutput.write(unl.getC291());
			csvOutput.write(unl.getC292());
			csvOutput.write(unl.getC293());
			csvOutput.write(unl.getC294());
			csvOutput.write(unl.getC295());
			csvOutput.write(unl.getC296());
			csvOutput.write(unl.getC297());
			csvOutput.write(unl.getC298());
			csvOutput.write(unl.getC299());
			csvOutput.write(unl.getC300());
			csvOutput.write(unl.getC301());
			csvOutput.write(unl.getC302());
			csvOutput.write(unl.getC303());
			csvOutput.write(unl.getC304());
			csvOutput.write(unl.getC305());
			csvOutput.write(unl.getC306());
			csvOutput.write(unl.getC307());
			csvOutput.write(unl.getC308());
			csvOutput.write(unl.getC309());
			csvOutput.write(unl.getC310());
			csvOutput.write(unl.getC311());
			csvOutput.write(unl.getC312());
			csvOutput.write(unl.getC313());
			csvOutput.write(unl.getC314());
			csvOutput.write(unl.getC315());
			csvOutput.write(unl.getC316());
			csvOutput.write(unl.getC317());
			csvOutput.write(unl.getC318());
			csvOutput.write(unl.getC319());
			csvOutput.write(unl.getC320());
			csvOutput.write(unl.getC321());
			csvOutput.write(unl.getC322());
			csvOutput.write(unl.getC323());
			csvOutput.write(unl.getC324());
			csvOutput.write(unl.getC325());
			csvOutput.write(unl.getC326());
			csvOutput.write(unl.getC327());
			csvOutput.write(unl.getC328());
			csvOutput.write(unl.getC329());
			csvOutput.write(unl.getC330());
			csvOutput.write(unl.getC331());
			csvOutput.write(unl.getC332());
			csvOutput.write(unl.getC333());
			csvOutput.write(unl.getC334());
			csvOutput.write(unl.getC335());
			csvOutput.write(unl.getC336());
			csvOutput.write(unl.getC337());
			csvOutput.write(unl.getC338());
			csvOutput.write(unl.getC339());
			csvOutput.write(unl.getC340());
			csvOutput.write(unl.getC341());
			csvOutput.write(unl.getC342());
			csvOutput.write(unl.getC343());
			csvOutput.write(unl.getC344());
			csvOutput.write(unl.getC345());
			csvOutput.write(unl.getC346());
			csvOutput.write(unl.getC347());
			csvOutput.write(unl.getC348());
			csvOutput.write(unl.getC349());
			csvOutput.write(unl.getC350());
			csvOutput.write(unl.getC351());
			csvOutput.write(unl.getC352());
			csvOutput.write(unl.getC353());
			csvOutput.write(unl.getC354());
			csvOutput.write(unl.getC355());
			csvOutput.write(unl.getC356());
			csvOutput.write(unl.getC357());
			csvOutput.write(unl.getC358());
			csvOutput.write(unl.getC359());
			csvOutput.write(unl.getC360());
			csvOutput.write(unl.getC361());
			csvOutput.write(unl.getC362());
			csvOutput.write(unl.getC363());
			csvOutput.write(unl.getC364());
			csvOutput.write(unl.getC365());
			csvOutput.write(unl.getC366());
			csvOutput.write(unl.getC367());
			csvOutput.write(unl.getC368());
			csvOutput.write(unl.getC369());
			csvOutput.write(unl.getC370());
			csvOutput.write(unl.getC371());
			csvOutput.write(unl.getC372());
			csvOutput.write(unl.getC373());
			csvOutput.write(unl.getC374());
			csvOutput.write(unl.getC375());
			csvOutput.write(unl.getC376());
			csvOutput.write(unl.getC377());
			csvOutput.write(unl.getC378());
			csvOutput.write(unl.getC379());
			csvOutput.write(unl.getC380());
			csvOutput.write(unl.getC381());
			csvOutput.write(unl.getC382());
			csvOutput.write(unl.getC383());
			csvOutput.write(unl.getC384());
			csvOutput.write(unl.getC385());
			csvOutput.write(unl.getC386());
			csvOutput.write(unl.getC387());
			csvOutput.write(unl.getC388());
			csvOutput.write(unl.getC389());
			csvOutput.write(unl.getC390());
			csvOutput.write(unl.getC391());
			csvOutput.write(unl.getC392());
			csvOutput.write(unl.getC393());
			csvOutput.write(unl.getC394());
			csvOutput.write(unl.getC395());
			csvOutput.write(unl.getC396());
			csvOutput.write(unl.getC397());
			csvOutput.write(unl.getC398());
			csvOutput.write(unl.getC399());
			csvOutput.write(unl.getC400());
			csvOutput.write(unl.getC401());
			csvOutput.write(unl.getC402());
			csvOutput.write(unl.getC403());
			csvOutput.write(unl.getC404());
			csvOutput.write(unl.getC405());
			csvOutput.write(unl.getC406());
			csvOutput.write(unl.getC407());
			csvOutput.write(unl.getC408());
			csvOutput.write(unl.getC409());
			csvOutput.write(unl.getC410());
			csvOutput.write(unl.getC411());
			csvOutput.write(unl.getC412());
			csvOutput.write(unl.getC413());
			csvOutput.write(unl.getC414());
			csvOutput.write(unl.getC415());
			csvOutput.write(unl.getC416());
			csvOutput.write(unl.getC417());
			csvOutput.write(unl.getC418());
			csvOutput.write(unl.getC419());
			csvOutput.write(unl.getC420());
			csvOutput.write(unl.getC421());
			csvOutput.write(unl.getC422());
			csvOutput.write(unl.getC423());
			csvOutput.write(unl.getC424());
			csvOutput.write(unl.getC425());
			csvOutput.write(unl.getC426());
			csvOutput.write(unl.getC427());
			csvOutput.write(unl.getC428());
			csvOutput.write(unl.getC429());
			csvOutput.write(unl.getC430());
			csvOutput.write(unl.getC431());
			csvOutput.write(unl.getC432());
			csvOutput.write(unl.getC433());
			csvOutput.write(unl.getC434());
			csvOutput.write(unl.getC435());
			csvOutput.write(unl.getC436());
			csvOutput.write(unl.getC437());
			csvOutput.write(unl.getC438());
			csvOutput.write(unl.getC439());
			csvOutput.write(unl.getC440());
			csvOutput.write(unl.getC441());
			csvOutput.write(unl.getC442());
			csvOutput.write(unl.getC443());
			csvOutput.write(unl.getC444());
			csvOutput.write(unl.getC445());
			csvOutput.write(unl.getC446());
			csvOutput.write(unl.getC447());
			csvOutput.write(unl.getC448());
			csvOutput.write(unl.getC449());
			csvOutput.write(unl.getC450());
			csvOutput.write(unl.getC451());
			csvOutput.write(unl.getC452());
			csvOutput.write(unl.getC453());
			csvOutput.write(unl.getC454());
			csvOutput.write(unl.getC455());
			csvOutput.write(unl.getC456());
			csvOutput.write(unl.getC457());
			csvOutput.write(unl.getC458());
			csvOutput.write(unl.getC459());
			csvOutput.write(unl.getC460());
			csvOutput.write(unl.getC461());
			csvOutput.write(unl.getC462());
			csvOutput.write(unl.getC463());
			csvOutput.write(unl.getC464());
			csvOutput.write(unl.getC465());
			csvOutput.write(unl.getC466());
			csvOutput.write(unl.getC467());
			csvOutput.write(unl.getC468());
			csvOutput.write(unl.getC469());
			csvOutput.write(unl.getC470());
			csvOutput.write(unl.getC471());
			csvOutput.write(unl.getC472());
			csvOutput.write(unl.getC473());
			csvOutput.write(unl.getC474());
			csvOutput.write(unl.getC475());
			csvOutput.write(unl.getC476());
			csvOutput.write(unl.getC477());
			csvOutput.write(unl.getC478());
			csvOutput.write(unl.getC479());
			csvOutput.write(unl.getC480());
			csvOutput.write(unl.getC481());
			csvOutput.write(unl.getC482());
			csvOutput.write(unl.getC483());
			csvOutput.write(unl.getC484());
			csvOutput.write(unl.getC485());
			csvOutput.write(unl.getC486());
			csvOutput.write(unl.getC487());
			csvOutput.write(unl.getC488());
			csvOutput.write(unl.getC489());
			csvOutput.write(unl.getC490());
			csvOutput.write(unl.getC491());
			csvOutput.write(unl.getC492());
			csvOutput.write(unl.getC493());
			csvOutput.write(unl.getC494());
			csvOutput.write(unl.getC495());
			csvOutput.write(unl.getC496());
			csvOutput.write(unl.getC497());
			csvOutput.write(unl.getC498());
			csvOutput.write(unl.getC499());
			csvOutput.write(unl.getC500());

			csvOutput.endRecord();

		}

	}

}
