package com.cclip.gui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.cclip.gui.JFrameMainGui;

public class JPanelToolbarPageable extends JPanel {

	private static final long serialVersionUID = 6653705132796453602L;

	private JLabel jLabelPageableData;
	private JSpinner jSpinnerPages;
	private SpinnerNumberModel spinnerModelPages;
	private JButton jButtonSearch;
	private JButton jButtonClear;
	private ActionListener actionListenerClear;
	private ActionListener actionListenerSearch;

	private int page = 0;
	private long cantPages = 0;
	private int limit = 100;
	private int offSet = 0;
	private long numberOfRecords;
	private long totalTumberOfRecords;

	public JPanelToolbarPageable(ActionListener actionListenerSearch, ActionListener actionListenerClear, Color color2) {
		this.actionListenerSearch = actionListenerSearch;
		this.actionListenerClear = actionListenerClear;
		initComponents(color2);
	}

	private void initComponents(Color color2) {

		setBackground(Color.WHITE);

		jButtonClear = new JButton();
		jButtonClear.setToolTipText("Limpiar filtro");
		jButtonClear.setIcon(new ImageIcon(JFrameMainGui.iconPath + "run-build-clean.png")); // NOI18N
		jButtonClear.setPreferredSize(new Dimension(27, 27));
		jButtonClear.addActionListener(actionListenerClear);
		jButtonClear.setVisible(actionListenerClear != null);

		jButtonSearch = new JButton();
		jButtonSearch.setToolTipText("Buscar.");
		jButtonSearch.setIcon(new ImageIcon(JFrameMainGui.iconPath + "edit-find.png")); // NOI18N
		jButtonSearch.setPreferredSize(new Dimension(27, 27));

		jLabelPageableData = new JLabel();
		jLabelPageableData.setForeground(color2);
		jSpinnerPages = new JSpinner();

		jSpinnerPages.setPreferredSize(new Dimension(70, 28));
		jSpinnerPages.setSize(100, 28);
		jSpinnerPages.setToolTipText("0 páginas");
		spinnerModelPages = new SpinnerNumberModel(0, 0, 0, 1);
		jSpinnerPages.setModel(spinnerModelPages);
		jSpinnerPages.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				if (jSpinnerPages.getValue() instanceof Integer) {
					page = ((Integer) jSpinnerPages.getValue()).intValue();
				} else if (jSpinnerPages.getValue() instanceof Double) {
					page = ((Double) jSpinnerPages.getValue()).intValue();
				}
				offSet = page * limit;

				jButtonSearch.doClick();
			}
		});

		jButtonSearch.addActionListener(actionListenerSearch);
		jSpinnerPages.setToolTipText("0 páginas");

		jLabelPageableData.setText("0 - 0 de 0");
		jLabelPageableData.setToolTipText("0 páginas");

		add(jButtonClear);
		add(jButtonSearch);
		add(jSpinnerPages);
		add(jLabelPageableData);

	}

	public void resetSearch() {
		page = 0;
		offSet = 0;
		spinnerModelPages = new SpinnerNumberModel(0, 0, 0, 1);
		jSpinnerPages.setModel(spinnerModelPages);
		search();
	}

	public void search() {

		jButtonSearch.doClick();
	}

	public void reset() {
		if (numberOfRecords > 0) {

			if (cantPages == 1) {
				setToolTipText(cantPages + " página");
				jSpinnerPages.setToolTipText(cantPages + " página");
				jLabelPageableData.setText((offSet + 1) + " - " + numberOfRecords + " de " + totalTumberOfRecords);
			} else {

				setToolTipText(cantPages + " páginas");
				jSpinnerPages.setToolTipText(cantPages + " páginas");

				if ((offSet + limit) > (offSet + numberOfRecords)) {
					jLabelPageableData.setText((offSet + 1) + " - " + totalTumberOfRecords + " de " + totalTumberOfRecords);
				} else {
					jLabelPageableData.setText((offSet + 1) + " - " + (offSet + limit) + " de " + totalTumberOfRecords);
				}
			}

			if (jSpinnerPages.getValue() instanceof Integer) {
				page = ((Integer) jSpinnerPages.getValue()).intValue();
			} else if (jSpinnerPages.getValue() instanceof Double) {
				page = ((Double) jSpinnerPages.getValue()).intValue();
			}

			spinnerModelPages = new SpinnerNumberModel(page, 0, cantPages - 1, 1);
			jSpinnerPages.setModel(spinnerModelPages);

		} else {
			jLabelPageableData.setText("0 - 0 de 0");
			setToolTipText("0 páginas");
			jSpinnerPages.setToolTipText("0 páginas");

			page = 0;
			offSet = 0;

			spinnerModelPages = new SpinnerNumberModel(0, 0, 0, 1);
			jSpinnerPages.setModel(spinnerModelPages);
		}
	}

	public void setCantPages(long cantPages) {
		this.cantPages = cantPages;
	}

	public void setNumberOfRecords(long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public void setTotalTumberOfRecords(long totalTumberOfRecords) {
		this.totalTumberOfRecords = totalTumberOfRecords;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	

}
