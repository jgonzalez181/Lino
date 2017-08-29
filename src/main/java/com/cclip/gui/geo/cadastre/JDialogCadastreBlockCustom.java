package com.cclip.gui.geo.cadastre;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.block.CadastreBlock;
import com.cclip.model.geo.cadastre.block.CadastreConstructiveType;
import com.cclip.model.geo.cadastre.block.CadastreDestinationType;
import com.cclip.model.person.UserSystem;
import com.cclip.services.Services;

public class JDialogCadastreBlockCustom extends JDialogCadastreBlock implements ActionListener {

	private static final long serialVersionUID = 1243725307522178693L;

	private JPanelCadastreViewCustom jPanelCadastreViewCustom;

	public Object[][] cadastreConstructiveTypeTable = new Object[0][0];
	private Object[][] cadastreActivityTypeTable = new Object[0][0];

	private String[] headerG = { "Año", "Mes", "Superficie", "Fachada", "Techos/Estructura", "Pisos", "Muros interiores", "Cielorrasos", "Cocina", "Baños", "Hall de ingreso", "Instalaciones",
			"Carpintería", "Puntos", "APC", "APC Comentario", "Tarifa", "Tipo Constructivo", "Actividad", "Destino", "Id" };

	private String[] headerC = { "Año", "Mes", "Superficie", "Fachada", "Est/Cubierta", "Pisos", "Muros interiores", "Cielorrasos", "Ornamentación", "Vidrieras/Iluminacion", "Comedor", "Baños",
			"Instalaciones", "Carpintería", "Puntos", "APC", "APC Comentario", "Tarifa", "Tipo Constructivo", "Actividad", "Destino", "Id" };

	private String[] headerI = { "Año", "Mes", "Superficie", "Fachada", "Est/Cubierta", "Pisos", "Muros interiores", "Cocina", "Baños", "Instalaciones", "Carpintería", "Puntos", "APC",
			"APC Comentario", "Tarifa", "Tipo Constructivo", "Actividad", "Destino", "Id" };

	private Cadastre cadastre;

	private JDialogCadastreBlockCustom(Frame parent, boolean modal) {
		super(parent, modal);
	}

	public JDialogCadastreBlockCustom(Cadastre cadastre, JPanelCadastreViewCustom jPanelCadastreViewCustom) {
		super(null, false);

		this.setResizable(true);
		this.setModal(false);

		// jTableG.setSelectionBackground(new java.awt.Color(51, 255, 0));
		// jTableG.setSelectionForeground(new java.awt.Color(0, 0, 153));

		this.jPanelCadastreViewCustom = jPanelCadastreViewCustom;

		this.cadastre = cadastre;

		// this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jTableG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTableI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTableC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// jTableG.setShowVerticalLines(true);
		// jTableI.setShowVerticalLines(true);
		// jTableC.setShowVerticalLines(true);

		jButtonSave.setIcon(new ImageIcon((JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y - 15);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		jTableG.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {

				int row = jTableG.getSelectedRow();
				int column = jTableG.getSelectedColumn();

				if (jTableG.getModel().isCellEditable(row, column) && evt.getKeyCode() == KeyEvent.VK_DELETE) {
					jTableG.setValueAt(null, row, column);
				}

			}
		});

		jTableI.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {

				int row = jTableI.getSelectedRow();
				int column = jTableI.getSelectedColumn();

				if (jTableI.getModel().isCellEditable(row, column) && evt.getKeyCode() == KeyEvent.VK_DELETE) {
					jTableI.setValueAt(null, row, column);
				}

			}
		});

		jTableC.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {

				int row = jTableC.getSelectedRow();
				int column = jTableC.getSelectedColumn();

				if (jTableC.getModel().isCellEditable(row, column) && evt.getKeyCode() == KeyEvent.VK_DELETE) {
					jTableC.setValueAt(null, row, column);
				}

			}
		});

		jTableG.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				if ("tableCellEditor".equals(evt.getPropertyName())) {

					int row = jTableG.getSelectedRow();
					int column = jTableG.getSelectedColumn();

					if (jTableG.isEditing() == false && column > 0) {

						replic(jTableG.getValueAt(row, column), 'G', row, column);
						pointsG(column);

						setConstructiveType("G", column);

					}
				}
			}
		});

		jTableI.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				if ("tableCellEditor".equals(evt.getPropertyName())) {

					int row = jTableI.getSelectedRow();
					int column = jTableI.getSelectedColumn();

					if (jTableI.isEditing() == false && column > 0) {

						replic(jTableI.getValueAt(row, column), 'I', row, column);
						pointsI(column);

						setConstructiveType("I", column);
					}
				}
			}
		});

		jTableC.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				if ("tableCellEditor".equals(evt.getPropertyName())) {

					int row = jTableC.getSelectedRow();
					int column = jTableC.getSelectedColumn();

					if (jTableC.isEditing() == false && column > 0) {

						replic(jTableC.getValueAt(row, column), 'C', row, column);
						pointsC(column);

						setConstructiveType("C", column);

					}
				}
			}
		});

		// =================================================================
		final JPopupMenu popupMenuI = new JPopupMenu();

		JMenuItem itemIToG = new JMenuItem("Cambiara a (G) General");
		itemIToG.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableI.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("G", headerG.length - 2, column);
					jTableI.setValueAt("G", headerI.length - 2, column);
					jTableC.setValueAt("G", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuI.add(itemIToG);

		JMenuItem itemIToC = new JMenuItem("Cambiara a (C) Comercio");
		itemIToC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableI.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("C", headerG.length - 2, column);
					jTableI.setValueAt("C", headerI.length - 2, column);
					jTableC.setValueAt("C", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuI.add(itemIToC);

		JMenuItem itemAddI = new JMenuItem("Añadir Bloque");
		itemAddI.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-insert-column-right.png"));
		itemAddI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String[] rowG = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I", "" };

				((DefaultTableModel) jTableG.getModel()).addColumn(new TitleHeader("", ""), rowG);

				setWidthColumn(jTableG.getColumnModel().getColumn(0), 150);

				jTableG.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableG.getColumnModel().getColumnCount(); i++) {
					jTableG.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableG.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowI = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I", "" };

				((DefaultTableModel) jTableI.getModel()).addColumn(new TitleHeader("", ""), rowI);

				setWidthColumn(jTableI.getColumnModel().getColumn(0), 150);

				jTableI.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableI.getColumnModel().getColumnCount(); i++) {
					jTableI.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableI.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowC = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I", "" };

				((DefaultTableModel) jTableC.getModel()).addColumn(new TitleHeader("", ""), rowC);

				setWidthColumn(jTableC.getColumnModel().getColumn(0), 150);

				jTableC.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableC.getColumnModel().getColumnCount(); i++) {
					jTableC.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableC.repaint();

				// ---------------------------------------------------------------------------------------
			}
		});
		popupMenuI.add(itemAddI);

		JMenuItem itemRemoveI = new JMenuItem("Eliminar Bloque");
		itemRemoveI.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-delete-column.png"));
		itemRemoveI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int column = jTableI.getSelectedColumn();

				if (column > 0) {
					removeColumn(column, jTableG);
					jTableG.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableI);
					jTableI.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableC);
					jTableC.repaint();
					// ---------------------------------------------------------------------------------------
				}

			}
		});
		popupMenuI.add(itemRemoveI);

		jTableI.setComponentPopupMenu(popupMenuI);
		// =================================================================
		final JPopupMenu popupMenuG = new JPopupMenu();

		JMenuItem itemGToI = new JMenuItem("Cambiara a (I) Industria");
		itemGToI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableG.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("I", headerG.length - 2, column);
					jTableI.setValueAt("I", headerI.length - 2, column);
					jTableC.setValueAt("I", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuG.add(itemGToI);

		JMenuItem itemGToC = new JMenuItem("Cambiara a (C) Comercio");
		itemGToC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableG.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("C", headerG.length - 2, column);
					jTableI.setValueAt("C", headerI.length - 2, column);
					jTableC.setValueAt("C", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuG.add(itemGToC);

		JMenuItem itemAddG = new JMenuItem("Añadir Bloque");
		itemAddG.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-insert-column-right.png"));
		itemAddG.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String[] rowG = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "G", "" };

				((DefaultTableModel) jTableG.getModel()).addColumn(new TitleHeader("", ""), rowG);

				setWidthColumn(jTableG.getColumnModel().getColumn(0), 150);

				jTableG.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableG.getColumnModel().getColumnCount(); i++) {
					jTableG.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableG.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowI = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "G", "" };

				((DefaultTableModel) jTableI.getModel()).addColumn(new TitleHeader("", ""), rowI);

				setWidthColumn(jTableI.getColumnModel().getColumn(0), 150);

				jTableI.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableI.getColumnModel().getColumnCount(); i++) {
					jTableI.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableI.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowC = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "G", "" };

				((DefaultTableModel) jTableC.getModel()).addColumn(new TitleHeader("", ""), rowC);

				setWidthColumn(jTableC.getColumnModel().getColumn(0), 150);

				jTableC.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableC.getColumnModel().getColumnCount(); i++) {
					jTableC.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableC.repaint();

				// ---------------------------------------------------------------------------------------
			}
		});
		popupMenuG.add(itemAddG);

		JMenuItem itemRemoveG = new JMenuItem("Eliminar Bloque");
		itemRemoveG.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-delete-column.png"));
		itemRemoveG.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int column = jTableG.getSelectedColumn();

				if (column > 0) {
					removeColumn(column, jTableG);
					jTableG.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableI);
					jTableI.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableC);
					jTableC.repaint();
					// ---------------------------------------------------------------------------------------
				}

			}
		});
		popupMenuG.add(itemRemoveG);

		jTableG.setComponentPopupMenu(popupMenuG);

		// =================================================================

		final JPopupMenu popupMenuC = new JPopupMenu();

		JMenuItem itemCToG = new JMenuItem("Cambiara a (G) General");
		itemCToG.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableC.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("G", headerG.length - 2, column);
					jTableI.setValueAt("G", headerI.length - 2, column);
					jTableC.setValueAt("G", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuC.add(itemCToG);

		JMenuItem itemCToI = new JMenuItem("Cambiara a (I) Industria");
		itemCToI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = jTableC.getSelectedColumn();

				if (column > 0) {
					jTableG.setValueAt("I", headerG.length - 2, column);
					jTableI.setValueAt("I", headerI.length - 2, column);
					jTableC.setValueAt("I", headerC.length - 2, column);

					jTableG.repaint();
					jTableI.repaint();
					jTableC.repaint();

				}

			}
		});
		popupMenuC.add(itemCToI);

		JMenuItem itemAddC = new JMenuItem("Añadir Bloque");
		itemAddC.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-insert-column-right.png"));
		itemAddC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String[] rowG = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "C", "" };

				((DefaultTableModel) jTableG.getModel()).addColumn(new TitleHeader("", ""), rowG);

				setWidthColumn(jTableG.getColumnModel().getColumn(0), 150);

				jTableG.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableG.getColumnModel().getColumnCount(); i++) {
					jTableG.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableG.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowI = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "C", "" };

				((DefaultTableModel) jTableI.getModel()).addColumn(new TitleHeader("", ""), rowI);

				setWidthColumn(jTableI.getColumnModel().getColumn(0), 150);

				jTableI.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableI.getColumnModel().getColumnCount(); i++) {
					jTableI.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableI.repaint();

				// ---------------------------------------------------------------------------------------

				String[] rowC = { Calendar.getInstance().get(Calendar.YEAR) + "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "C", "" };

				((DefaultTableModel) jTableC.getModel()).addColumn(new TitleHeader("", ""), rowC);

				setWidthColumn(jTableC.getColumnModel().getColumn(0), 150);

				jTableC.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

				for (int i = 0; i < jTableC.getColumnModel().getColumnCount(); i++) {
					jTableC.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
				}
				jTableC.repaint();

				// ---------------------------------------------------------------------------------------
			}
		});
		popupMenuC.add(itemAddC);

		JMenuItem itemRemoveC = new JMenuItem("Eliminar Bloque");
		itemRemoveC.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-table-delete-column.png"));
		itemRemoveC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int column = jTableC.getSelectedColumn();

				if (column > 0) {

					removeColumn(column, jTableG);
					jTableG.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableI);
					jTableI.repaint();
					// ---------------------------------------------------------------------------------------
					removeColumn(column, jTableC);
					jTableC.repaint();
					// ---------------------------------------------------------------------------------------
				}

			}
		});
		popupMenuC.add(itemRemoveC);

		jTableC.setComponentPopupMenu(popupMenuC);

		// ------------------------------------------------------------------------

		printClear();
		print();

		find();

		pack();

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(jButtonSave)) {
			System.out.println("A");

			if (validateForm() == false) {
				System.out.println("B");
				return;
			}

			try {
				System.out.println("C");
				formToDto();
				System.out.println("D");
				update();
				System.out.println("E");
				if (cadastre != null && cadastre instanceof CadastreCensus) {
					jPanelCadastreViewCustom.setCadastreCensusId(cadastre.getId());
				} else {
					jPanelCadastreViewCustom.setCadastreId(cadastre.getId());
				}
			} catch (Exception e1) {
				System.out.println("F");
				e1.printStackTrace();
				return;
			}
		}

		setVisible(false);
		dispose();

	}

	private void formToDto() throws Exception {

	}

	private void print() {

	}

	private void printClear() {

	}

	private boolean validateForm() {
		return true;
	}

	private void update() {
		if (cadastre instanceof CadastreCensus) {
			Services.getInstance().saveCadastreBlockCensus(cadastre, getCadastreBlockList(), Context.getBean("userSystem", UserSystem.class));
		} else {
			Services.getInstance().saveCadastreBlock(cadastre, getCadastreBlockList(), Context.getBean("userSystem", UserSystem.class));
		}

	}

	private CadastreBlock[] getCadastreBlockList() {

		String[][] tableG = getTable(jTableG, "G");
		String[][] tableI = getTable(jTableI, "I");
		String[][] tableC = getTable(jTableC, "C");

		List<CadastreBlock> cadastreBlockList = new ArrayList<CadastreBlock>();

		String[][] table = tableG;

		for (int i = 0; i < table.length; i++) {

			CadastreBlock cadastreBlock = new CadastreBlock();

			cadastreBlock.setErased(false);

			int j = 0;
			String v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setYearBuilding(Integer.valueOf(v.trim()));
				/** Año Bloque */
			}

			j++;// 1
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setMonthBuilding(Integer.valueOf(v.trim()));
				/** Mes Bloque */
			}

			j++;// 2
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setM2Covered(Double.valueOf(v.trim()));
				/** Metros Cuadrados Cubiertos */
			}

			j++;// 3
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacade(v.trim());
				/** Fachada */
			}

			j++;// 4
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setRoofStructure(v.trim());
				/** Techos Estructura */
			}

			j++;// 5
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setHomes(v.trim());
				/** Pisos */
			}

			j++;// 6
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setInteriorWalls(v.trim());
				/** Muros Interiores */
			}

			j++;// 7
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCeiling(v.trim());
				/** Cielorrasos */
			}

			j++;// 8
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setKitchen(v.trim());
				/** Cocina */
			}

			j++;// 9
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setToilets(v.trim());
				/** Baños */
			}

			j++;// 10
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setEntranceHall(v.trim());
				/** Hall Ingreso */
			}

			j++;// 11
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacilities(v.trim());
				/** Instalaciones */
			}

			j++;// 12
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCarpentry(v.trim());
				/** Carpinteria */
			}

			j++;// 13
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setPoints(Integer.valueOf(v.trim()));
				/** Puntos */
			}

			j++;// 14
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApc(v.equalsIgnoreCase("s") || v.equalsIgnoreCase("si") || v.equalsIgnoreCase("true") || v.equalsIgnoreCase("t") || v.equalsIgnoreCase("y"));
				/** APC */
			}

			j++;// 15
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApcDesc(v.trim());
				/** Detalle de APC */
			}

			j++;// 16
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setRate(Integer.valueOf(v.trim()));
				/** Tarifa */
			}

			j++;// 17
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreConstructiveType cadastreConstructiveType = new CadastreConstructiveType();
				cadastreConstructiveType.setId(v.trim());

				cadastreBlock.setCadastreConstructiveType(cadastreConstructiveType);
			}

			j++;// 18
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreActivityType cadastreActivityType = new CadastreActivityType();
				cadastreActivityType.setId(v.trim());

				cadastreBlock.setCadastreActivityType(cadastreActivityType);
			}

			j++;// 19
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreDestinationType cadastreDestinationType = new CadastreDestinationType();
				cadastreDestinationType.setId(v.trim());

				cadastreBlock.setCadastreDestinationType(cadastreDestinationType);
			}

			j++;// 20
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setId(v.trim());
			}

			cadastreBlock.setCadastre(cadastre);

			cadastreBlockList.add(cadastreBlock);

		}

		table = tableI;

		for (int i = 0; i < table.length; i++) {

			CadastreBlock cadastreBlock = new CadastreBlock();

			cadastreBlock.setErased(false);

			int j = 0;
			String v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setYearBuilding(Integer.valueOf(v.trim()));
				/** Año Bloque */
			}

			j++;// 1
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setMonthBuilding(Integer.valueOf(v.trim()));
				/** Mes Bloque */
			}

			j++;// 2
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setM2Covered(Double.valueOf(v.trim()));
				/** Metros Cuadrados Cubiertos */
			}

			j++;// 3
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacade(v.trim());
				/** Fachada */
			}

			j++;// 4
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCoverStructure(v.trim());
				/** Estructura Cubierta */
			}

			j++;// 5
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setHomes(v.trim());
				/** Pisos */
			}

			j++;// 6
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setInteriorWalls(v.trim());
				/** Muros Interiores */
			}

			j++;// 7
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setKitchen(v.trim());
				/** Cocina */
			}

			j++;// 8
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setToilets(v.trim());
				/** Baños */
			}

			j++;// 9
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacilities(v.trim());
				/** Instalaciones */
			}

			j++;// 10
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCarpentry(v.trim());
				/** Carpinteria */
			}

			j++;// 11
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setPoints(Integer.valueOf(v.trim()));
				/** Puntos */
			}

			j++;// 12
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApc(v.equalsIgnoreCase("s") || v.equalsIgnoreCase("si") || v.equalsIgnoreCase("true") || v.equalsIgnoreCase("t") || v.equalsIgnoreCase("y"));
				/** APC */
			}

			j++;// 13
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApcDesc(v.trim());
				/** Detalle de APC */
			}

			j++;// 14
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setRate(Integer.valueOf(v.trim()));
				/** Tarifa */
			}

			j++;// 15
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreConstructiveType cadastreConstructiveType = new CadastreConstructiveType();
				cadastreConstructiveType.setId(v.trim());

				cadastreBlock.setCadastreConstructiveType(cadastreConstructiveType);
			}

			j++;// 16
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreActivityType cadastreActivityType = new CadastreActivityType();
				cadastreActivityType.setId(v.trim());

				cadastreBlock.setCadastreActivityType(cadastreActivityType);
			}

			j++;// 17
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreDestinationType cadastreDestinationType = new CadastreDestinationType();
				cadastreDestinationType.setId(v.trim());

				cadastreBlock.setCadastreDestinationType(cadastreDestinationType);
			}

			j++;// 18
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setId(v.trim());
			}

			cadastreBlock.setCadastre(cadastre);

			cadastreBlockList.add(cadastreBlock);

		}

		table = tableC;

		for (int i = 0; i < table.length; i++) {

			CadastreBlock cadastreBlock = new CadastreBlock();

			cadastreBlock.setErased(false);

			int j = 0;
			String v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setYearBuilding(Integer.valueOf(v.trim()));
				/** Año Bloque */
			}

			j++;// 1
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setMonthBuilding(Integer.valueOf(v.trim()));
				/** Mes Bloque */
			}

			j++;// 2
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setM2Covered(Double.valueOf(v.trim()));
				/** Metros Cuadrados Cubiertos */
			}

			j++;// 3
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacade(v.trim());
				/** Fachada */
			}

			j++;// 4
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCoverStructure(v.trim());
				/** Estructura Cubierta */
			}

			j++;// 5
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setHomes(v.trim());
				/** Pisos */
			}

			j++;// 6
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setInteriorWalls(v.trim());
				/** Muros Interiores */
			}

			j++;// 7
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCeiling(v.trim());
				/** Cielorrasos */
			}

			j++;// 8
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setOrnamentation(v.trim());
				/** Ornamentacion */
			}

			j++;// 9
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setStainedGlassLighting(v.trim());
				/** Vidrieras Iluminacion */
			}

			j++;// 10
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setBuffetDining(v.trim());
				/** Buffet Comedor */
			}

			j++;// 11
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setToilets(v.trim());
				/** Baños */
			}

			j++;// 12
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setFacilities(v.trim());
				/** Instalaciones */
			}

			j++;// 13
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setCarpentry(v.trim());
				/** Carpinteria */
			}

			j++;// 14
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setPoints(Integer.valueOf(v.trim()));
				/** Puntos */
			}

			j++;// 15
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApc(v.equalsIgnoreCase("s") || v.equalsIgnoreCase("si") || v.equalsIgnoreCase("true") || v.equalsIgnoreCase("t") || v.equalsIgnoreCase("y"));
				/** APC */
			}

			j++;// 16
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setApcDesc(v.trim());
				/** Detalle de APC */
			}

			j++;// 17
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setRate(Integer.valueOf(v.trim()));
				/** Tarifa */
			}

			j++;// 18
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreConstructiveType cadastreConstructiveType = new CadastreConstructiveType();
				cadastreConstructiveType.setId(v.trim());

				cadastreBlock.setCadastreConstructiveType(cadastreConstructiveType);
			}

			j++;// 19
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreActivityType cadastreActivityType = new CadastreActivityType();
				cadastreActivityType.setId(v.trim());

				cadastreBlock.setCadastreActivityType(cadastreActivityType);
			}

			j++;// 20
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				CadastreDestinationType cadastreDestinationType = new CadastreDestinationType();
				cadastreDestinationType.setId(v.trim());

				cadastreBlock.setCadastreDestinationType(cadastreDestinationType);
			}

			j++;// 21
			v = table[i][j];
			if (v != null && v.trim().length() > 0) {
				cadastreBlock.setId(v.trim());
			}

			cadastreBlock.setCadastre(cadastre);

			cadastreBlockList.add(cadastreBlock);

		}

		CadastreBlock[] r = new CadastreBlock[cadastreBlockList.size()];
		r = cadastreBlockList.toArray(r);

		return r;
	}

	private String[][] getTable(JTable jTable, String c) {

		String[][] table = new String[jTable.getRowCount()][jTable.getColumnCount()];

		for (int i = 0; i < jTable.getRowCount(); i++) {

			for (int j = 0; j < jTable.getColumnCount(); j++) {
				Object obj = jTable.getValueAt(i, j);

				if (obj != null && obj.toString().trim().length() > 0) {
					table[i][j] = obj.toString().trim();
				}
			}
		}

		String[][] matriz = table;
		String[][] matrizT = new String[matriz[0].length][matriz.length];

		for (int x = 0; x < matriz.length; x++) {
			for (int y = 0; y < matriz[x].length; y++) {
				matrizT[y][x] = matriz[x][y];
			}
		}

		table = removeRowFrom2dArray(matrizT, 0);

		int index = 0;

		while (index < table.length) {
			String v = table[index][table[index].length - 2];
			if (v == null || v.trim().equalsIgnoreCase(c) == false) {
				table = removeRowFrom2dArray(table, index);
				index = 0;
			} else {
				index++;
			}

		}

		// for (int i = 0; i < table.length; i++) {
		// if (table[i][table[i].length - 1] == null || table[i][table[i].length - 1].trim().length() == 0) {
		// table[i][table[i].length - 1] = UUID.randomUUID().toString();
		// }
		// }

		return table;

	}

	private String[][] removeRowFrom2dArray(String[][] array, int row) {
		String[][] r = new String[array.length - 1][array[0].length];

		List<List<String>> listM = new ArrayList<List<String>>();

		for (int i = 0; i < array.length; i++) {
			List<String> listR = new ArrayList<String>();

			for (int j = 0; j < array[i].length; j++) {
				listR.add(array[i][j]);
			}

			listM.add(listR);
		}

		listM.remove(row);

		for (int i = 0; i < listM.size(); i++) {
			for (int j = 0; j < listM.get(i).size(); j++) {
				r[i][j] = listM.get(i).get(j);
			}
		}

		return r;

	}

	private void find() {
		if (cadastre == null || cadastre.getId() == null || cadastre.getId().trim().length() == 0) {
			return;
		}

		cadastreConstructiveTypeTable = Services.getInstance().findCadastreConstructiveType();
		cadastreActivityTypeTable = Services.getInstance().findCadastreActivityType();

		findG();
		findI();
		findC();
	}

	private void findG() {

		if (cadastre == null || cadastre.getId() == null || cadastre.getId().trim().length() == 0) {
			return;
		}

		Object[][] blocksO = null;
		if (cadastre instanceof CadastreCensus) {
			blocksO = Services.getInstance().findCadastreBlockCensusG(cadastre.getId().trim());
		} else {
			blocksO = Services.getInstance().findCadastreBlockG(cadastre.getId().trim());
		}

		String[][] blocksF = new String[headerG.length][blocksO.length + 1];

		for (int i = 0; i < headerG.length; i++) {
			blocksF[i][0] = headerG[i] + ":";
		}

		for (int i = 0; i < blocksO.length; i++) {

			if (blocksO[i][2] != null) {
				blocksF[blocksF.length - 2][i + 1] = blocksO[i][2].toString();
			}
			blocksF[blocksF.length - 1][i + 1] = blocksO[i][1].toString();

			for (int j = 3; j < blocksO[i].length; j++) {
				if (blocksO[i][j] != null) {

					blocksF[j - 3][i + 1] = blocksO[i][j].toString();
				}
			}
		}

		String[] header = new String[blocksO.length + 1];

		header[0] = "General";
		for (int i = 0; i < blocksO.length; i++) {
			header[i + 1] = "";
		}

		// ===============================================================================================

		setDataG(blocksF, header);

	}

	private void setDataG(Object[][] blocksF, String[] header) {

		jTableG.setModel(new DefaultTableModel(blocksF, header) {

			public Class getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (rowIndex == headerG.length - 2 || rowIndex == headerG.length - 1) {
					return false;
				}
				return jTableG.getValueAt(headerG.length - 2, columnIndex).equals("G");
			}
		});

		setWidthColumn(jTableG.getColumnModel().getColumn(0), 150);

		jTableG.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

		for (int i = 0; i < jTableG.getColumnModel().getColumnCount(); i++) {
			jTableG.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
		}
	}

	private void findI() {

		if (cadastre == null || cadastre.getId() == null || cadastre.getId().trim().length() == 0) {
			return;
		}

		Object[][] blocksO = null;
		if (cadastre instanceof CadastreCensus) {
			blocksO = Services.getInstance().findCadastreBlockCensusI(cadastre.getId().trim());
		} else {
			blocksO = Services.getInstance().findCadastreBlockI(cadastre.getId().trim());
		}

		String[][] blocksF = new String[headerI.length][blocksO.length + 1];

		for (int i = 0; i < headerI.length; i++) {
			blocksF[i][0] = headerI[i] + ":";
		}

		// if (blocksO[0][2] != null) {
		// blocksF[0][1] = blocksO[0][2].toString();
		// }
		// if (blocksO[0][3] != null) {
		// blocksF[1][1] = blocksO[0][3].toString();
		// }
		// if (blocksO[0][4] != null) {
		// blocksF[2][1] = blocksO[0][4].toString();
		// }

		for (int i = 0; i < blocksO.length; i++) {

			if (blocksO[i][2] != null) {
				blocksF[blocksF.length - 2][i + 1] = blocksO[i][2].toString();
			}

			blocksF[blocksF.length - 1][i + 1] = blocksO[i][1].toString();

			for (int j = 3; j < blocksO[i].length; j++) {
				if (blocksO[i][j] != null) {

					blocksF[j - 3][i + 1] = blocksO[i][j].toString();
				}
			}
		}

		String[] header = new String[blocksO.length + 1];

		header[0] = "Industria";
		for (int i = 0; i < blocksO.length; i++) {
			header[i + 1] = "";
		}

		setDataI(blocksF, header);

	}

	private void setDataI(Object[][] blocksF, String[] header) {

		jTableI.setModel(new DefaultTableModel(blocksF, header) {

			public Class getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (rowIndex == headerI.length - 2 || rowIndex == headerI.length - 1) {
					return false;
				}
				return jTableI.getValueAt(headerI.length - 2, columnIndex).equals("I");
			}
		});

		setWidthColumn(jTableI.getColumnModel().getColumn(0), 150);

		jTableI.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

		for (int i = 0; i < jTableI.getColumnModel().getColumnCount(); i++) {
			jTableI.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
		}
	}

	private void findC() {

		if (cadastre == null || cadastre.getId() == null || cadastre.getId().trim().length() == 0) {
			return;
		}

		Object[][] blocksO = null;
		if (cadastre instanceof CadastreCensus) {
			blocksO = Services.getInstance().findCadastreBlockCensusC(cadastre.getId().trim());
		} else {
			blocksO = Services.getInstance().findCadastreBlockC(cadastre.getId().trim());
		}

		String[][] blocksF = new String[headerC.length][blocksO.length + 1];

		for (int i = 0; i < headerC.length; i++) {
			blocksF[i][0] = headerC[i] + ":";
		}

		// if (blocksO[0][2] != null) {
		// blocksF[0][1] = blocksO[0][2].toString();
		// }
		// if (blocksO[0][3] != null) {
		// blocksF[1][1] = blocksO[0][3].toString();
		// }
		// if (blocksO[0][4] != null) {
		// blocksF[2][1] = blocksO[0][4].toString();
		// }

		for (int i = 0; i < blocksO.length; i++) {

			blocksF[blocksF.length - 2][i + 1] = blocksO[i][2].toString();
			blocksF[blocksF.length - 1][i + 1] = blocksO[i][1].toString();

			for (int j = 3; j < blocksO[i].length; j++) {
				if (blocksO[i][j] != null) {

					blocksF[j - 3][i + 1] = blocksO[i][j].toString();
				}
			}
		}

		String[] header = new String[blocksO.length + 1];

		header[0] = "Comercio";
		for (int i = 0; i < blocksO.length; i++) {
			header[i + 1] = "";
		}

		setDataC(blocksF, header);

	}

	private void setDataC(Object[][] blocksF, String[] header) {
		jTableC.setModel(new DefaultTableModel(blocksF, header) {

			public Class getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				if (rowIndex == headerC.length - 2 || rowIndex == headerC.length - 1) {
					return false;
				}

				return jTableC.getValueAt(headerC.length - 2, columnIndex).equals("C");
			}
		});

		setWidthColumn(jTableC.getColumnModel().getColumn(0), 150);

		jTableC.getTableHeader().setDefaultRenderer(new TableHeaderCellRenderer());

		for (int i = 0; i < jTableC.getColumnModel().getColumnCount(); i++) {
			jTableC.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
		}
	}

	private void setWidthColumn(TableColumn tableColumn, int width) {

		if (width == 0) {
			tableColumn.setMinWidth(0);
			tableColumn.setMaxWidth(0);
			tableColumn.setWidth(0);
			tableColumn.setPreferredWidth(0);
		} else if (width > -1) {
			tableColumn.setWidth(width);
			tableColumn.setPreferredWidth(width);
			tableColumn.setMaxWidth(width);
		}

	}

	private class TableHeaderCellRenderer extends JLabel implements TableCellRenderer {

		public TableHeaderCellRenderer() {
			this.setOpaque(true);
			setBackground(new Color(0, 102, 153));
			setForeground(Color.WHITE);

			// this.setFont(new Font("Abyssinica SIL", 0, 12));
			this.setFont(new Font("Arial", 1, 12));

			setBorder(null);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			setText(value.toString());

			if (this instanceof JLabel) {
				((JLabel) this).setHorizontalAlignment(SwingConstants.CENTER);
			}

			return this;
		}

	}

	private class TitleHeader {

		private String value;
		private String label;

		public TitleHeader(String value, String label) {
			super();
			this.value = value;
			this.label = label;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String toString() {
			return this.label;
		}

	}

	private class ColorRenderer extends DefaultTableCellRenderer {

		public ColorRenderer() {

			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			char type = 'X';

			if (table == jTableG) {
				type = 'G';
			} else if (table == jTableI) {
				type = 'I';
			} else if (table == jTableC) {
				type = 'C';
			}

			if (column == 0) {

				if (cellComponent instanceof JLabel) {
					((JLabel) cellComponent).setHorizontalAlignment(SwingConstants.RIGHT);
				}

				setForeground(Color.WHITE);
				setBackground(new Color(0, 102, 153));
			} else {

				// if (table.getModel().isCellEditable(row, column) == false) {
				if (type == 'G' && table.getValueAt(headerG.length - 2, column).equals("G") == false) {
					setBackground(new Color(0, 102, 153));
					setForeground(Color.WHITE);
				} else if (type == 'I' && table.getValueAt(headerI.length - 2, column).equals("I") == false) {
					setBackground(new Color(0, 102, 153));
					setForeground(Color.WHITE);
				} else if (type == 'C' && table.getValueAt(headerC.length - 2, column).equals("C") == false) {
					setBackground(new Color(0, 102, 153));
					setForeground(Color.WHITE);
				} else {

					setBackground(Color.WHITE);
					setForeground(Color.BLACK);

					if (column > 0 && value != null && isValid(value.toString(), type, row) == false) {
						setForeground(Color.RED);
					} else {
						setForeground(Color.BLACK);
					}
				}
			}

			if (value != null) {
				if (row == 1 && isValidMonth(value.toString())) {
					switch (Integer.valueOf(value.toString())) {

					case 1:
						setToolTipText("(1) Enero");
						break;
					case 2:
						setToolTipText("(2) Febrero");
						break;
					case 3:
						setToolTipText("(3) Marzo");
						break;
					case 4:
						setToolTipText("(4) Abril");
						break;
					case 5:
						setToolTipText("(5) Mayo");
						break;
					case 6:
						setToolTipText("(6) Junio");
						break;
					case 7:
						setToolTipText("7) Julio");
						break;
					case 8:
						setToolTipText("(8) Agosto");
						break;
					case 9:
						setToolTipText("(9) Septiembre");
						break;
					case 10:
						setToolTipText("(10) Octubre");
						break;
					case 11:
						setToolTipText("(11) Noviembre");
						break;
					case 12:
						setToolTipText("(12) Diciembre");
						break;
					}

				} else if (type == 'G' && (headerG.length - 5) == row && isValidRate(value.toString())) {

					setToolTipText(buildTarifa(Integer.valueOf(value.toString())));

				} else if (type == 'I' && (headerI.length - 5) == row && isValidRate(value.toString())) {

					setToolTipText(buildTarifa(Integer.valueOf(value.toString())));

				} else if (type == 'C' && (headerC.length - 5) == row && isValidRate(value.toString())) {

					setToolTipText(buildTarifa(Integer.valueOf(value.toString())));

				} else if (type == 'G' && (headerG.length - 4) == row /* && isValidConstructiveType(value.toString()) */) {

					setToolTipText(findNameConstructiveType(value.toString()));

				} else if (type == 'I' && (headerI.length - 4) == row /* && isValidConstructiveType(value.toString()) */) {

					setToolTipText(findNameConstructiveType(value.toString()));

				} else if (type == 'C' && (headerC.length - 4) == row /* && isValidConstructiveType(value.toString()) */) {

					setToolTipText(findNameConstructiveType(value.toString()));

				} else if (type == 'G' && (headerG.length - 3) == row /* && isValidActivityType(value.toString()) */) {

					setToolTipText(findNameActivityType(value.toString()));

				} else if (type == 'I' && (headerI.length - 3) == row /* && isValidActivityType(value.toString()) */) {

					setToolTipText(findNameActivityType(value.toString()));

				} else if (type == 'C' && (headerC.length - 3) == row /* && isValidActivityType(value.toString()) */) {

					setToolTipText(findNameActivityType(value.toString()));

				} else {

					setToolTipText(value.toString());
				}

			} else {
				setToolTipText("");
			}

			return cellComponent;
		}

		private boolean isValidCat(String value) {
			try {
				Integer.valueOf(value);
			} catch (Exception e) {
				return false;
			}

			char[] values = value.toCharArray();

			for (char c : values) {

				try {
					int n = Integer.valueOf(c + "");
					if (n > 4) {
						return false;
					}

					if (n < 1) {
						return false;
					}

				} catch (Exception e) {
					return false;
				}
			}

			return true;
		}

		private boolean isValidSup(String value) {
			try {
				Double.valueOf(value);
			} catch (Exception e) {
				return false;
			}

			return true;
		}

		private boolean isValidPoints(String value) {
			try {
				Integer.valueOf(value);
			} catch (Exception e) {
				return false;
			}

			return true;
		}

		private boolean isValidYear(String value) {

			try {
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int y = Integer.valueOf(value);
				return y <= year;
			} catch (Exception e) {
				return false;
			}
		}

		private boolean isValidMonth(String value) {

			try {

				int m = Integer.valueOf(value);
				return (0 < m && m < 13);
			} catch (Exception e) {
				return false;
			}
		}

		private boolean isValidRate(String value) {
			try {
				int t = Integer.valueOf(value);
				return (t < 11);
			} catch (Exception e) {
				return false;
			}

		}

		private boolean isValidApc(String value) {

			return (value.trim().equalsIgnoreCase("s") || value.equalsIgnoreCase("si") || value.equalsIgnoreCase("n") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("true")
					|| value.equalsIgnoreCase("false") || value.equalsIgnoreCase("t") || value.equalsIgnoreCase("f") || value.equalsIgnoreCase("y"));
		}

		private boolean isValidConstructiveType(String value) {

			for (int i = 0; i < cadastreConstructiveTypeTable.length; i++) {
				if (cadastreConstructiveTypeTable[i][0].toString().trim().equals(value.trim())) {
					return true;
				}
			}

			return false;
		}

		private boolean isValidActivityType(String value) {

			for (int i = 0; i < cadastreActivityTypeTable.length; i++) {
				if (cadastreActivityTypeTable[i][0].toString().trim().equals(value.trim())) {
					return true;
				}
			}

			return false;
		}

		private boolean isValid(String value, char type, int row) {

			if (type == 'G') {

				switch (row) {

				case 0:
					return isValidYear(value);
				case 1:
					return isValidMonth(value);
				case 2:
					return isValidSup(value);
					// ---------------------------------------
				case 3:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 4:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 5:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 6:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 7:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 8:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 9:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 10:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 11:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);
				case 12:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);

					// ---------------------------------------
				case 13:

					return isValidPoints(value);
				case 14:

					return isValidApc(value);
				case 15:

					break;
				case 16:
					return isValidRate(value);

				case 17:

					return isValidConstructiveType(value);
				case 18:
					return isValidActivityType(value);

					// ---------------------------------------
				default:
					break;
				}

			} else if (type == 'I') {
				switch (row) {

				case 0:
					return isValidYear(value);
				case 1:
					return isValidMonth(value);
				case 2:
					return isValidSup(value);
					// ---------------------------------------

				case 3:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 4:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 5:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 6:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 7:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 8:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 9:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);
				case 10:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);
					// ---------------------------------------
				case 11:

					return isValidPoints(value);
				case 12:

					return isValidApc(value);
				case 13:

					break;
				case 14:
					return isValidRate(value);

				case 15:

					return isValidConstructiveType(value);
				case 16:
					return isValidActivityType(value);

					// ---------------------------------------
				default:
					break;
				}
				return true;

			} else if (type == 'C') {
				switch (row) {

				case 0:
					return isValidYear(value);
				case 1:
					return isValidMonth(value);
				case 2:
					return isValidSup(value);
					// ---------------------------------------
				case 3:
					if (isValidCat(value) == false) {

						return false;
					}
					return (value.length() == 1);
				case 4:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 5:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 6:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 7:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 8:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 9:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 2);
				case 10:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 11:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 1);
				case 12:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);
				case 13:
					if (isValidCat(value) == false) {
						return false;
					}
					return (value.length() == 3);

					// ---------------------------------------
				case 14:

					return isValidPoints(value);
				case 15:

					return isValidApc(value);
				case 16:

					break;
				case 17:
					return isValidRate(value);

				case 18:

					return isValidConstructiveType(value);
				case 19:
					return isValidActivityType(value);

					// ---------------------------------------
				default:
					break;
				}
				return true;
			}

			return true;

		}

	}

	private String findNameConstructiveType(String value) {

		for (int i = 0; i < cadastreConstructiveTypeTable.length; i++) {

			if (cadastreConstructiveTypeTable[i][0].toString().trim().equals(value.trim())) {
				if (cadastreConstructiveTypeTable[i][1] != null) {
					return "(" + cadastreConstructiveTypeTable[i][0] + ") " + cadastreConstructiveTypeTable[i][1];
				}

			}
		}

		return "";
	}

	private String findNameActivityType(String value) {

		for (int i = 0; i < cadastreActivityTypeTable.length; i++) {

			if (cadastreActivityTypeTable[i][0].toString().trim().equals(value.trim())) {
				if (cadastreActivityTypeTable[i][1] != null) {
					return "(" + cadastreActivityTypeTable[i][0] + ") " + cadastreActivityTypeTable[i][1];
				}

			}
		}

		return "";
	}

	private boolean replic(Object value, char type, int row, int column) {
		//
		// if (row == 3) {
		// jTableG.setValueAt(value, 3, column);
		// } else {
		// if (row == 3) {
		// }

		if (type == 'G') {

			switch (row) {

			case 0:
				jTableI.setValueAt(value, 0, column);
				jTableC.setValueAt(value, 0, column);
				break;
			case 1:
				jTableI.setValueAt(value, 1, column);
				jTableC.setValueAt(value, 1, column);
				break;
			case 2:
				jTableI.setValueAt(value, 2, column);
				jTableC.setValueAt(value, 2, column);
				break;
			// ---------------------------------------

			case 3:
				jTableI.setValueAt(value, 3, column);
				jTableC.setValueAt(value, 3, column);
				break;
			case 4:

				break;
			case 5:
				jTableI.setValueAt(value, 5, column);
				jTableC.setValueAt(value, 5, column);
				break;
			case 6:
				jTableI.setValueAt(value, 6, column);
				jTableC.setValueAt(value, 6, column);
				break;
			case 7:
				jTableC.setValueAt(value, 7, column);
				break;
			case 8:
				jTableI.setValueAt(value, 7, column);
				break;
			case 9:
				jTableI.setValueAt(value, 8, column);
				jTableC.setValueAt(value, 11, column);
				break;
			case 10:
				break;
			case 11:
				jTableI.setValueAt(value, 9, column);
				jTableC.setValueAt(value, 12, column);
				break;
			case 12:
				jTableI.setValueAt(value, 10, column);
				jTableC.setValueAt(value, 13, column);
				break;

			// ---------------------------------------
			case 13:
				jTableI.setValueAt(value, 11, column);
				jTableC.setValueAt(value, 14, column);
				break;
			case 14:
				jTableI.setValueAt(value, 12, column);
				jTableC.setValueAt(value, 15, column);
				break;
			case 15:
				jTableI.setValueAt(value, 13, column);
				jTableC.setValueAt(value, 16, column);
				break;
			case 16:
				jTableI.setValueAt(value, 14, column);
				jTableC.setValueAt(value, 17, column);
				break;
			case 17:
				jTableI.setValueAt(value, 15, column);
				jTableC.setValueAt(value, 18, column);
				break;
			case 18:
				jTableI.setValueAt(value, 16, column);
				jTableC.setValueAt(value, 19, column);
				break;

			// ---------------------------------------
			default:
				break;
			}

		} else if (type == 'I') {
			switch (row) {

			case 0:
				jTableG.setValueAt(value, 0, column);
				jTableC.setValueAt(value, 0, column);
				break;
			case 1:
				jTableG.setValueAt(value, 1, column);
				jTableC.setValueAt(value, 1, column);
				break;
			case 2:
				jTableG.setValueAt(value, 2, column);
				jTableC.setValueAt(value, 2, column);
				break;
			// ---------------------------------------

			case 3:
				jTableG.setValueAt(value, 3, column);
				jTableC.setValueAt(value, 3, column);
				break;
			case 4:

				jTableC.setValueAt(value, 4, column);
				break;
			case 5:
				jTableG.setValueAt(value, 5, column);
				jTableC.setValueAt(value, 5, column);
				break;
			case 6:
				jTableG.setValueAt(value, 6, column);
				jTableC.setValueAt(value, 6, column);
				break;
			case 7:
				jTableG.setValueAt(value, 8, column);
				break;
			case 8:
				jTableG.setValueAt(value, 9, column);
				jTableC.setValueAt(value, 11, column);
				break;
			case 9:
				jTableG.setValueAt(value, 11, column);
				jTableC.setValueAt(value, 12, column);
				break;
			case 10:
				jTableG.setValueAt(value, 12, column);
				jTableC.setValueAt(value, 13, column);
				break;
			// ---------------------------------------
			case 11:
				jTableG.setValueAt(value, 13, column);
				jTableC.setValueAt(value, 14, column);
				break;
			case 12:
				jTableG.setValueAt(value, 14, column);
				jTableC.setValueAt(value, 15, column);
				break;
			case 13:
				jTableG.setValueAt(value, 15, column);
				jTableC.setValueAt(value, 16, column);
				break;
			case 14:
				jTableG.setValueAt(value, 16, column);
				jTableC.setValueAt(value, 17, column);
				break;

			case 15:
				jTableG.setValueAt(value, 17, column);
				jTableC.setValueAt(value, 18, column);
				break;
			case 16:
				jTableG.setValueAt(value, 18, column);
				jTableC.setValueAt(value, 19, column);
				break;

			// ---------------------------------------
			default:
				break;
			}
			return true;

		} else if (type == 'C') {
			switch (row) {

			case 0:
				jTableG.setValueAt(value, 0, column);
				jTableI.setValueAt(value, 0, column);
				break;
			case 1:
				jTableG.setValueAt(value, 1, column);
				jTableI.setValueAt(value, 1, column);
				break;
			case 2:
				jTableG.setValueAt(value, 2, column);
				jTableI.setValueAt(value, 2, column);
				break;

			case 3:
				jTableG.setValueAt(value, 3, column);
				jTableI.setValueAt(value, 3, column);
				break;
			case 4:
				jTableI.setValueAt(value, 4, column);
				break;
			case 5:
				jTableG.setValueAt(value, 5, column);
				jTableI.setValueAt(value, 5, column);
				break;
			case 6:
				jTableG.setValueAt(value, 6, column);
				jTableI.setValueAt(value, 6, column);
				break;
			case 7:
				jTableG.setValueAt(value, 7, column);
				break;
			case 8:

				break;
			case 9:

				break;
			case 10:

				break;
			case 11:
				jTableG.setValueAt(value, 9, column);
				jTableI.setValueAt(value, 8, column);
				break;
			case 12:
				jTableG.setValueAt(value, 11, column);
				jTableI.setValueAt(value, 9, column);
				break;
			case 13:
				jTableG.setValueAt(value, 12, column);
				jTableI.setValueAt(value, 10, column);
				break;

			// ---------------------------------------
			case 14:
				jTableG.setValueAt(value, 13, column);
				jTableI.setValueAt(value, 11, column);
				break;
			case 15:
				jTableG.setValueAt(value, 14, column);
				jTableI.setValueAt(value, 12, column);
				break;
			case 16:
				jTableG.setValueAt(value, 15, column);
				jTableI.setValueAt(value, 13, column);
				break;
			case 17:
				jTableG.setValueAt(value, 16, column);
				jTableI.setValueAt(value, 14, column);
				break;

			case 18:
				jTableG.setValueAt(value, 17, column);
				jTableI.setValueAt(value, 15, column);
				break;
			case 19:
				jTableG.setValueAt(value, 18, column);
				jTableI.setValueAt(value, 16, column);
				break;

			// ---------------------------------------
			default:
				break;
			}
			return true;
		}

		return true;

	}

	private int pointsG(int column) {
		// 3-12

		List<Integer> valuesList = new ArrayList<Integer>();

		points(jTableG.getValueAt(3, column), column, valuesList);
		points(jTableG.getValueAt(4, column), column, valuesList);
		points(jTableG.getValueAt(5, column), column, valuesList);
		points(jTableG.getValueAt(6, column), column, valuesList);
		points(jTableG.getValueAt(7, column), column, valuesList);
		points(jTableG.getValueAt(8, column), column, valuesList);
		points(jTableG.getValueAt(9, column), column, valuesList);
		points(jTableG.getValueAt(10, column), column, valuesList);
		points(jTableG.getValueAt(11, column), column, valuesList);
		points(jTableG.getValueAt(12, column), column, valuesList);

		int[] values = new int[valuesList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = valuesList.get(i);
		}

		int p = points(values);

		jTableG.setValueAt(p, 13, column);
		jTableI.setValueAt(p, 11, column);
		jTableC.setValueAt(p, 14, column);

		return p;
	}

	private int pointsI(int column) {
		// 3-10

		List<Integer> valuesList = new ArrayList<Integer>();

		points(jTableI.getValueAt(3, column), column, valuesList);
		points(jTableI.getValueAt(4, column), column, valuesList);
		points(jTableI.getValueAt(5, column), column, valuesList);
		points(jTableI.getValueAt(6, column), column, valuesList);
		points(jTableI.getValueAt(7, column), column, valuesList);
		points(jTableI.getValueAt(8, column), column, valuesList);
		points(jTableI.getValueAt(9, column), column, valuesList);
		points(jTableI.getValueAt(10, column), column, valuesList);

		int[] values = new int[valuesList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = valuesList.get(i);
		}

		int p = points(values);

		jTableG.setValueAt(p, 13, column);
		jTableI.setValueAt(p, 11, column);
		jTableC.setValueAt(p, 14, column);

		return p;
	}

	private int pointsC(int column) {
		// 3-10

		List<Integer> valuesList = new ArrayList<Integer>();

		points(jTableC.getValueAt(3, column), column, valuesList);
		points(jTableC.getValueAt(4, column), column, valuesList);
		points(jTableC.getValueAt(5, column), column, valuesList);
		points(jTableC.getValueAt(6, column), column, valuesList);
		points(jTableC.getValueAt(7, column), column, valuesList);
		points(jTableC.getValueAt(8, column), column, valuesList);
		points(jTableC.getValueAt(9, column), column, valuesList);
		points(jTableC.getValueAt(10, column), column, valuesList);
		points(jTableC.getValueAt(11, column), column, valuesList);
		points(jTableC.getValueAt(12, column), column, valuesList);
		points(jTableC.getValueAt(13, column), column, valuesList);

		int[] values = new int[valuesList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = valuesList.get(i);
		}

		int p = points(values);

		jTableG.setValueAt(p, 13, column);
		jTableI.setValueAt(p, 11, column);
		jTableC.setValueAt(p, 14, column);

		return p;
	}

	private int points(Object value, int column, List<Integer> valuesList) {
		int n = 0;

		if (jTableG.getValueAt(3, column) != null && jTableG.getValueAt(3, column).toString().trim().length() > 0) {
			try {
				n = Integer.valueOf(value.toString());
			} catch (Exception e) {
				return 0;
			}

			char[] chars = value.toString().toCharArray();
			for (char c : chars) {
				valuesList.add(Integer.valueOf(c + ""));
			}
		}

		return n;
	}

	private int points(int[] values) {

		int p = 0;

		int categoria4 = 0;
		int categoria3 = 0;
		int categoria2 = 0;
		int categoria1 = 0;

		for (int n : values) {
			if (n == 1) {
				categoria1++;
			} else if (n == 2) {
				categoria2++;
			} else if (n == 3) {
				categoria3++;
			} else if (n == 4) {
				categoria4++;
			}
		}

		p += categoria4 * 1;
		p += categoria3 * 2;
		p += categoria2 * 3;
		p += categoria1 * 4;

		return p;

	}

	private void removeColumn(int index, JTable myTable) {

		int nRow = myTable.getRowCount();
		int nCol = myTable.getColumnCount() - 1;
		Object[][] cells = new Object[nRow][nCol];
		String[] names = new String[nCol];

		for (int j = 0; j < nCol; j++) {
			if (j < index) {
				names[j] = myTable.getColumnName(j);
				for (int i = 0; i < nRow; i++) {
					cells[i][j] = myTable.getValueAt(i, j);
				}
			} else {
				names[j] = myTable.getColumnName(j + 1);
				for (int i = 0; i < nRow; i++) {
					cells[i][j] = myTable.getValueAt(i, j + 1);
				}
			}
		}

		if (myTable == jTableG) {
			setDataG(cells, names);
		} else if (myTable == jTableI) {
			setDataI(cells, names);
		} else if (myTable == jTableC) {
			setDataC(cells, names);
		}

		// DefaultTableModel newModel = new DefaultTableModel(cells, names);
		// myTable.setModel(newModel);
	}

	private String buildTarifa(int t) {
		if (t == 1) {
			return "1 Tinglados en gral. y galpones de materiales metálicos, asbesto_cemento, madera o similares";
		} else if (t == 2) {
			return "2 Galpones SIN estructura resistente de hormigón armado, cubierta de techo de metal, madera, asbesto_cemento y muros de mampostería. Vivienda prefabricada de asbesto-cemento, madera o similar";
		} else if (t == 3) {
			return "3 Galpones CON estructuras resistentes de H°A° y muros de mampostería";
		} else if (t == 4) {
			return "4 Edificios en gral. para viviendas, comercios, industrias, oficinas públicas y privadas, colegios, hospitales, etc, SIN estructura resistente de H°A°";
		} else if (t == 5) {
			return "5 Edificios en gral. para viviendas, comercios, industrias, oficinas públicas y privadas, colegios, hospitales, etc, CON estructura resistente de H°A°";
		} else if (t == 6) {
			return "6 Edificios para espectáculos públicos, teatros, cines teatros y cinematógrafos, grandes salones y similares SIN estructura resist. de H°";
		} else if (t == 7) {
			return "7 Edificios para espectáculos públicos, teatros, cines teatros y cinematógrafos, grandes salones y similares CON estructura resist. de H°";
		} else if (t == 8) {
			return "8 Calzadas de hormigón o con base de hormigón";
		} else if (t == 9) {
			return "9 Cordón y cuneta de hormigón";
		} else if (t == 10) {
			return "10 Aceras y solados de mosaicos alisados de mortero, etc";
		}

		return "";

	}

	public void setConstructiveType(String cadastreDestinationTypeId, int column) {

		if (cadastreDestinationTypeId.equalsIgnoreCase("I")) {

			Object objPoints = jTableI.getValueAt(11, column);
			int x = 0;
			if (objPoints != null && isValidPoints(objPoints.toString())) {
				x = Integer.valueOf(objPoints.toString());
			}

			if (x >= 49 && x <= 56) {
				jTableI.setValueAt(1, 15, column);
			} else if (x >= 35 && x <= 48) {
				jTableI.setValueAt(2, 15, column);
			} else if (x >= 21 && x <= 34) {
				jTableI.setValueAt(3, 15, column);
			} else if (x >= 14 && x <= 20) {
				jTableI.setValueAt(4, 15, column);
			} else {

			}

		} else if (cadastreDestinationTypeId.equalsIgnoreCase("C")) {

			Object objPoints = jTableC.getValueAt(14, column);
			int x = 0;
			if (objPoints != null && isValidPoints(objPoints.toString())) {
				x = Integer.valueOf(objPoints.toString());
			}

			if (x >= 56 && x <= 64) {
				jTableC.setValueAt(1, 18, column);
			} else if (x >= 40 && x <= 55) {
				jTableC.setValueAt(2, 18, column);
			} else if (x >= 24 && x <= 39) {
				jTableC.setValueAt(3, 18, column);
			} else {

			}

		} else if (cadastreDestinationTypeId.equalsIgnoreCase("G") && cadastre.getCadastreType().getId().equalsIgnoreCase("PH")) {

			Object objPoints = jTableG.getValueAt(13, column);
			int x = 0;
			if (objPoints != null && isValidPoints(objPoints.toString())) {
				x = Integer.valueOf(objPoints.toString());
			}

			if (x >= 56 && x <= 64) {
				jTableG.setValueAt(1, 17, column);
			} else if (x >= 40 && x <= 55) {
				jTableG.setValueAt(2, 17, column);
			} else if (x >= 24 && x <= 39) {
				jTableG.setValueAt(3, 17, column);
			} else {

			}

		} else if (cadastreDestinationTypeId.equalsIgnoreCase("G") && cadastre.getCadastreType().getId().equalsIgnoreCase("PV")) {

			Object objPoints = jTableG.getValueAt(13, column);
			int x = 0;
			if (objPoints != null && isValidPoints(objPoints.toString())) {
				x = Integer.valueOf(objPoints.toString());
			}

			if (x >= 56 && x <= 64) {
				jTableG.setValueAt(1, 17, column);
			} else if (x >= 40 && x <= 55) {
				jTableG.setValueAt(2, 17, column);
			} else if (x >= 24 && x <= 39) {
				jTableG.setValueAt(3, 17, column);
			} else if (x >= 16 && x <= 23) {
				jTableG.setValueAt(4, 17, column);
			} else {

			}
		} else {

		}

	}

	private boolean isValidPoints(String value) {
		try {
			Integer.valueOf(value);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
