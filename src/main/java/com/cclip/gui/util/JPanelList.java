package com.cclip.gui.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.utiljdbc.ResultList;

import com.cclip.gui.JFrameMainGui;
import com.cclip.util.UtilComponent;

public abstract class JPanelList extends JPanel {

	private static final long serialVersionUID = -3193984553678654584L;

	public JPanelToolbarPageable jPanelToolbarPageable;
	public JToggleButton jToggleButton;
	public JPanel jPanelHeader;
	public JLabel jLabelTitle;
	private JLabel jLabelTitle2;
	public JPanel jPanelHeaderRow;
	private JPanel jPanelHeaderRow1;
	private JPanel jPanelHeaderRow2;
	public JSplitPane jSplitPane;
	public JScrollPaneJTable jScrollPaneJTable;
	public JPanelForm jPanelForm;

	protected ResultList rl;
	protected Object[][] model;
	
	public boolean showAlertZeroItems = true; 

	public JPanelList() {
		jScrollPaneJTable = new JScrollPaneJTable();
		jScrollPaneJTable.getjTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (jScrollPaneJTable.getjTable().getSelectedRow() > -1 && event.getValueIsAdjusting() == false) {
					setDto();
				}
				
			}
		});
	}

	protected void initComponents(String title, JPanelForm jPanelForm, Component[] headerRowList, Component[] headerRowList2, Color color) {
		initComponents(title, jPanelForm, headerRowList, headerRowList2, color, Color.BLACK);
	}

	protected void initComponents(String title, JPanelForm jPanelForm, Component[] headerRowList, Component[] headerRowList2, Color color, Color color2) {

		this.jPanelForm = jPanelForm;

		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.WHITE);

		// ---------------------------------------------------------------------

		ActionListener actionListenerSearch = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				searchData();
			}
		};

		ActionListener actionListenerClear = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clearFilter();
			}
		};

		jPanelToolbarPageable = new JPanelToolbarPageable(actionListenerSearch, actionListenerClear, color2);
		jPanelToolbarPageable.setBackground(color);

		// ---------------------------------------

		jToggleButton = new JToggleButton();
		jToggleButton.setToolTipText("Mostrar/ocultar listado.");

		jToggleButton.setIcon(new ImageIcon(JFrameMainGui.iconPath + "view-split-left-right.png")); // NOI18N
		jToggleButton.setSelectedIcon(new ImageIcon(JFrameMainGui.iconPath + "view-left-close.png")); // NOI18N
		jToggleButton.setRolloverIcon(new ImageIcon(JFrameMainGui.iconPath + "view-split-left-right.png")); // NOI18N
		jToggleButton.setPreferredSize(new Dimension(27, 27));

		jToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jToggleButtonEvt();
			}
		});

		jToggleButton.setSelected(true);

		// ---------------------------------------------------------------------

		JPanel jPanelJToggleButton = new JPanel();
		jPanelJToggleButton.setBackground(color);
		jPanelJToggleButton.add(jToggleButton);

		// -----------------------------------------------------------

		jLabelTitle2 = UtilComponent.buildJLabelField(title);
		jLabelTitle2.setFont(new Font("Dialog", 0, 18)); // NOI18N
		// jLabelTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle2.setForeground(color2);
		jPanelJToggleButton.add(jLabelTitle2);

		// -----------------------------------------------------------

		jLabelTitle = UtilComponent.buildJLabelField(title);
		jLabelTitle.setFont(new Font("Dialog", 0, 18)); // NOI18N
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setForeground(color2);

		// ---------------------------------------------------------------------

		jPanelHeaderRow1 = new JPanel();
		jPanelHeaderRow1.setBackground(color);

		if (headerRowList != null) {
			for (Component comp : headerRowList) {
				jPanelHeaderRow1.add(comp);
			}
		}

		// ---------------------------------------------------------------------

		jPanelHeaderRow2 = new JPanel();
		jPanelHeaderRow2.setBackground(color);

		if (headerRowList2 != null) {
			for (Component comp : headerRowList2) {
				jPanelHeaderRow2.add(comp);
			}
		}

		// ---------------------------------------------------------------------

		jPanelHeaderRow = new JPanel();
		jPanelHeaderRow.setBackground(color);
		jPanelHeaderRow.setLayout(new BorderLayout(3, 3));
		jPanelHeaderRow.add(jPanelHeaderRow1, BorderLayout.NORTH);
		jPanelHeaderRow.add(jPanelHeaderRow2, BorderLayout.CENTER);

		// ---------------------------------------------------------------------

		jPanelHeader = new JPanel();
		jPanelHeader.setLayout(new BorderLayout(3, 3));
		jPanelHeader.setBackground(color);
		jPanelHeader.add(jPanelJToggleButton, BorderLayout.WEST);
		jPanelHeader.add(jPanelHeaderRow, BorderLayout.CENTER);
		jPanelHeader.add(jPanelToolbarPageable, BorderLayout.EAST);

		this.add(jPanelHeader, BorderLayout.NORTH);

		// -----------------------------------------------------------

//		jScrollPaneJTable.getjTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent event) {
//				setDto();
//			}
//		});

		if (jScrollPaneJTable.getjTable().getModel().getRowCount() > 0) {

			jScrollPaneJTable.getjTable().getSelectionModel().setSelectionInterval(0, 0);
			setDto();
		}

		jScrollPaneJTable.showData(null);

		// ---------------------------------------------------------------------

		jSplitPane = new JSplitPane();
		jSplitPane.setDividerSize(5);
		jSplitPane.setLeftComponent(jScrollPaneJTable);
		jSplitPane.setRightComponent(jPanelForm);
		jSplitPane.setDividerLocation(jScrollPaneJTable.getWidthSumColumn());
		this.add(jSplitPane, BorderLayout.CENTER);

	}

	public void setDto() {
		try {
			if (rl != null && jScrollPaneJTable.getjTable().getSelectedRow() > -1) {

				if (jPanelForm != null) {
					jPanelForm.setDto(rl.getList()[jScrollPaneJTable.getjTable().getSelectedRow()], jScrollPaneJTable.getjTable(), jScrollPaneJTable.getjTable().getSelectedRow());
				}

			} else {
//				jPanelForm.
			}
		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	private void jToggleButtonEvt() {
		try {

			if (jToggleButton.isSelected() == true) {

				jSplitPane.setDividerLocation(jScrollPaneJTable.getWidthSumColumn());
				jSplitPane.setLeftComponent(jScrollPaneJTable);

				jPanelHeader.add(jPanelHeaderRow, BorderLayout.CENTER);

				jPanelHeader.remove(jLabelTitle);

			} else {
				jSplitPane.setLeftComponent(null);

				jPanelHeader.add(jLabelTitle, BorderLayout.CENTER);

			}

			jPanelHeaderRow.setVisible(jToggleButton.isSelected());
			jPanelToolbarPageable.setVisible(jToggleButton.isSelected());

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("rawtypes")
	protected void addColumnMetaData(String name, Class type, int width) {
		jScrollPaneJTable.addColumnMetaData(name, type, width);
	}

	protected void resetSearch() {
		jPanelToolbarPageable.resetSearch();
	}

	public void searchData() {
		try {
			
			jScrollPaneJTable.clearData();

			if (jPanelForm != null) {
				jPanelForm.setDto(null, null, null);
			}

			
			find(jPanelToolbarPageable.getOffSet(), jPanelToolbarPageable.getLimit());
			jScrollPaneJTable.showData(model);

			if (rl != null) {
				jPanelToolbarPageable.setCantPages(rl.getCantPages());
				jPanelToolbarPageable.setTotalTumberOfRecords(rl.getNumberOfRecords());
				jPanelToolbarPageable.setNumberOfRecords(rl.getList().length);
			}

			jPanelToolbarPageable.reset();

			if (showAlertZeroItems && (rl == null || rl.getList() == null || rl.getList().length == 0)) {
				JOptionPane.showMessageDialog(null, "No se encontraron resultados. Resultados 0 registros encontrados.", "Resultado de la Búsqueda", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	protected abstract void find(int offSet, int limit) throws Exception;

	protected abstract void clearFilter();

}
