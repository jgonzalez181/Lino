package com.cclip.gui.geo.cadastre;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.text.WordUtils;

import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.geo.Neighbourhood;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.person.UserSystem;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JDialogInmAddressCustom extends JDialogInmAddress implements ActionListener {

	private static final long serialVersionUID = 1361395090519503752L;

	private JPanelCadastreViewCustom jPanelCadastreViewCustom;

	private Object[][] neighbourhoodList;
	private Cadastre cadastre;

	private JDialogInmAddressCustom(Frame parent, boolean modal) {
		super(parent, modal);
	}

	public JDialogInmAddressCustom(Cadastre cadastre, JPanelCadastreViewCustom jPanelCadastreViewCustom) {
		super(null, true);

		this.jPanelCadastreViewCustom = jPanelCadastreViewCustom;

		this.cadastre = cadastre;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon((JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		jComboBoxInmNeighbourhood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				jComboBoxInmNeighbourhoodItemStateChanged();
			}
		});

		jComboBoxInmNeighbourhoodLoadData();

		printClear();
		print();

		pack();

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(jButtonSave)) {

			if (validateForm() == false) {
				return;
			}

			try {
				formToDto();
				update();
				if (cadastre != null && cadastre instanceof CadastreCensus) {
					jPanelCadastreViewCustom.setCadastreCensusId(cadastre.getId());
				} else {
					jPanelCadastreViewCustom.setCadastreId(cadastre.getId());
				}
			} catch (Exception e1) {
				return;
			}
		}

		setVisible(false);
		dispose();

	}

	private void print() {

		if (cadastre == null) {
			return;
		}

		if (cadastre.getInmPostalCode() != null) {
			jLabelInmPostalCode.setText(cadastre.getInmPostalCode());
		}

		jLabelInmPostalCode.setText(neighbourhoodList[0][0].toString().substring(0, 4));

		if (cadastre.getInmNeighbourhood() != null) {
			for (int i = 0; i < neighbourhoodList.length; i++) {
				if (neighbourhoodList[i][0].equals(cadastre.getInmNeighbourhood().getId())) {
					jComboBoxInmNeighbourhood.setSelectedIndex(i);
					jLabelInmPostalCode.setText(cadastre.getInmNeighbourhood().getId().substring(0, 4));
					break;
				}
			}
		}

		if (cadastre.getInmStreet() != null) {
			jTextFieldInmStreet.setText(cadastre.getInmStreet());
		}

		if (cadastre.getInmStreetNumber() != null) {
			jTextFieldInmStreetNumber.setText(cadastre.getInmStreetNumber());
		}

		if (cadastre.getInmStreetNumberEstimated() != null && cadastre.getInmStreetNumberEstimated() == true) {
			jCheckBoxInmStreetNumberEstimated.setSelected(true);
		}

		if (cadastre.getInmBuilding() != null) {
			jTextFieldInmBuilding.setText(cadastre.getInmBuilding());
		}

		if (cadastre.getInmCommentAddress() != null) {
			jTextPaneInmCommentAddress.setText(cadastre.getInmCommentAddress());
		}

	}

	private void printClear() {
		jLabelInmPostalCode.setText("");
		if (jComboBoxInmNeighbourhood.getItemCount() > 0) {
			jComboBoxInmNeighbourhood.setSelectedIndex(0);
		}
		jTextFieldInmStreet.setText("");
		jTextFieldInmStreetNumber.setText("");
		jCheckBoxInmStreetNumberEstimated.setSelected(false);
		jTextFieldInmBuilding.setText("");
		jTextPaneInmCommentAddress.setText("");
	}

	private void formToDto() {

		if (cadastre == null) {
			return;
		}

		if (jLabelInmPostalCode.getText() != null && jLabelInmPostalCode.getText().trim().length() > 0) {
			cadastre.setInmPostalCode(jLabelInmPostalCode.getText().trim());
		} else {
			cadastre.setInmPostalCode(null);
		}

		if (jComboBoxInmNeighbourhood.getItemCount() > 0 && jComboBoxInmNeighbourhood.getSelectedIndex() > -1) {
			Neighbourhood neighbourhood = new Neighbourhood();
			neighbourhood.setId(neighbourhoodList[jComboBoxInmNeighbourhood.getSelectedIndex()][0].toString());
			neighbourhood.setName(neighbourhoodList[jComboBoxInmNeighbourhood.getSelectedIndex()][1].toString());
			cadastre.setInmNeighbourhood(neighbourhood);

			cadastre.setInmPostalCode(neighbourhood.getId().substring(0, 4));
		} else {
			cadastre.setInmNeighbourhood(null);

			cadastre.setInmPostalCode(null);
		}

		if (jTextFieldInmStreet.getText() != null && jTextFieldInmStreet.getText().trim().length() > 0) {
			cadastre.setInmStreet(WordUtils.capitalizeFully(jTextFieldInmStreet.getText().trim()));
		} else {
			cadastre.setInmStreet(null);
		}

		if (jTextFieldInmStreetNumber.getText() != null && jTextFieldInmStreetNumber.getText().trim().length() > 0) {
			cadastre.setInmStreetNumber(jTextFieldInmStreetNumber.getText().trim());
		} else {
			cadastre.setInmStreetNumber(null);
		}

		cadastre.setInmStreetNumberEstimated(jCheckBoxInmStreetNumberEstimated.isSelected());

		if (jTextFieldInmBuilding.getText() != null && jTextFieldInmBuilding.getText().trim().length() > 0) {
			cadastre.setInmBuilding(WordUtils.capitalizeFully(jTextFieldInmBuilding.getText().trim()));
		} else {
			cadastre.setInmBuilding(null);
		}

		if (jTextPaneInmCommentAddress.getText() != null && jTextPaneInmCommentAddress.getText().trim().length() > 0) {
			cadastre.setInmCommentAddress(jTextPaneInmCommentAddress.getText().trim());
		} else {
			cadastre.setInmCommentAddress(null);
		}

	}

	private boolean validateForm() {

		if (jTextFieldInmStreetNumber.getText() != null && jTextFieldInmStreetNumber.getText().trim().length() > 0) {
			try {
				Integer.valueOf(jTextFieldInmStreetNumber.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "El número de la calle debe ser un valor entero", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}

	private void jComboBoxInmNeighbourhoodLoadData() {

		try {

			jComboBoxInmNeighbourhood.removeAllItems();

			neighbourhoodList = Services.getInstance().findNeighbourhood();

			if (neighbourhoodList != null) {

				for (int i = 0; i < neighbourhoodList.length; i++) {
					jComboBoxInmNeighbourhood.addItem(new ComboItem("(" + neighbourhoodList[i][0] + ") " + neighbourhoodList[i][1], neighbourhoodList[i][0].toString()));
				}

				if (neighbourhoodList.length > 0) {
					jComboBoxInmNeighbourhood.setSelectedIndex(0);

				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBoxInmNeighbourhoodItemStateChanged() {
		if (jComboBoxInmNeighbourhood.getSelectedIndex() > 0) {
			jLabelInmPostalCode.setText(neighbourhoodList[jComboBoxInmNeighbourhood.getSelectedIndex()][0].toString().substring(0, 4));
		}
	}

	private void update() {

		if(cadastre instanceof CadastreCensus){
			Services.getInstance().updateCadastreCensusInmAddres(cadastre, Context.getBean("userSystem", UserSystem.class));
		} else {
			Services.getInstance().updateCadastreInmAddres(cadastre, Context.getBean("userSystem", UserSystem.class));
		}
		
	}
}
