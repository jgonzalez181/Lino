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
import com.cclip.model.person.Iva;
import com.cclip.model.person.Uf;
import com.cclip.model.person.UfCensus;
import com.cclip.model.person.UserSystem;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JDialogUfCustom extends JDialogUf implements ActionListener {

	private static final long serialVersionUID = 1361395090519503752L;

	private JPanelCadastreViewCustom jPanelCadastreViewCustom;

	private Object[][] neighbourhoodList;
	private Object[][] ivaList;
	private Cadastre cadastre;
	private Uf uf;

	private JDialogUfCustom(Frame parent, boolean modal) {
		super(parent, modal);
	}

	public JDialogUfCustom(Cadastre cadastre, JPanelCadastreViewCustom jPanelCadastreViewCustom) {
		super(null, true);

		this.jPanelCadastreViewCustom = jPanelCadastreViewCustom;

		this.cadastre = cadastre;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon((JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		jButtonFindUf.setIcon(new ImageIcon((JFrameMainGui.iconPath + "edit-find.png"))); // NOI18N
		jButtonCopyInmAddress.setIcon(new ImageIcon((JFrameMainGui.iconPath + "go-home.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		jComboBoxNeighbourhood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				jComboBoxNeighbourhoodItemStateChanged();
			}
		});

		jButtonFindUf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findUfById();
			}
		});

		jButtonCopyInmAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				copyInmAddress();
			}
		});

		jComboBoxNeighbourhoodLoadData();
		jComboBoxIvaLoadData();

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

		if (cadastre.getUf() == null) {
			return;
		}

		uf = cadastre.getUf();

		print(uf);

	}

	private void print(Uf uf) {

		if (uf == null) {
			return;
		}

		if (uf instanceof UfCensus && uf.getId() != null) {
			jTextFieldId.setText(((UfCensus) uf).getUf().getId());
		} else if (cadastre instanceof CadastreCensus == false && uf.getId() != null) {
			jTextFieldId.setText(uf.getId());
		}

		if (uf.getName() != null) {
			jTextFieldName.setText(uf.getName());
		}

		if (uf.getDni() != null) {
			jTextFieldDni.setText(uf.getDni());
		}

		if (uf.getCuit() != null) {
			jTextFieldCuit.setText(uf.getCuit());
		}

		if (uf.getIva() != null) {
			for (int i = 0; i < ivaList.length; i++) {
				if (ivaList[i][0].equals(uf.getIva().getId())) {
					jComboBoxIva.setSelectedIndex(i);
					break;
				}
			}
		}

		if (uf.getPhone() != null) {
			jTextFieldPhone.setText(uf.getPhone());
		}

		printAddress(uf);
	}

	private void printAddress(Uf uf) {

		if (uf == null) {
			return;
		}

		jLabelPostalCode.setText(neighbourhoodList[0][0].toString().substring(0, 4));

		if (uf.getNeighbourhood() != null) {
			for (int i = 0; i < neighbourhoodList.length; i++) {
				if (neighbourhoodList[i][0].equals(uf.getNeighbourhood().getId())) {
					jComboBoxNeighbourhood.setSelectedIndex(i);
					jLabelPostalCode.setText(uf.getNeighbourhood().getId().substring(0, 4));
					break;
				}
			}
		}

		if (uf.getStreet() != null) {
			jTextFieldStreet.setText(uf.getStreet());
		}

		if (uf.getStreetNumber() != null) {
			jTextFieldStreetNumber.setText(uf.getStreetNumber());
		}

		if (uf.getBuildingFloor() != null) {
			jTextFieldBuildingFloor.setText(uf.getBuildingFloor());
		}

		if (uf.getBuildingRoom() != null) {
			jTextFieldBuildingRoom.setText(uf.getBuildingRoom());
		}

		if (uf.getBuilding() != null) {
			jTextFieldBuilding.setText(uf.getBuilding());
		}

		if (uf.getCommentAddress() != null) {
			jTextPaneCommentAddress.setText(uf.getCommentAddress());
		}

	}

	private void printClear() {
		jTextFieldId.setText("");

		jTextFieldName.setText("");

		jTextFieldDni.setText("");

		jTextFieldCuit.setText("");

		if (jComboBoxIva.getItemCount() > 0) {
			jComboBoxIva.setSelectedIndex(0);
		}

		jTextFieldPhone.setText("");

		printClearAddress();
	}

	private void printClearAddress() {

		jLabelPostalCode.setText("");
		if (jComboBoxNeighbourhood.getItemCount() > 0) {
			jComboBoxNeighbourhood.setSelectedIndex(0);
		}
		jTextFieldStreet.setText("");
		jTextFieldStreetNumber.setText("");
		jTextFieldBuilding.setText("");
		jTextFieldBuildingFloor.setText("");
		jTextFieldBuildingRoom.setText("");
		jTextPaneCommentAddress.setText("");
	}

	private void formToDto() {

		if (cadastre == null) {
			return;
		}

		if (uf == null) {

			findUfById();

			if (uf == null) {
				return;
			}
		}

		cadastre.setUf(uf);

		if (uf instanceof UfCensus && uf.getId() != null) {
			if (jTextFieldId.getText() != null && jTextFieldId.getText().trim().length() > 0) {
				((UfCensus) cadastre.getUf()).getUf().setId(jTextFieldId.getText().trim());
			} else {
				((UfCensus) cadastre.getUf()).getUf().setId(null);
			}
		} else if (cadastre instanceof CadastreCensus == false && uf.getId() != null) {
			if (jTextFieldId.getText() != null && jTextFieldId.getText().trim().length() > 0) {
				cadastre.getUf().setId(jTextFieldId.getText().trim());
			} else {
				cadastre.getUf().setId(null);
			}
		}

		if (jTextFieldName.getText() != null && jTextFieldName.getText().trim().length() > 0) {
			cadastre.getUf().setName(WordUtils.capitalizeFully(jTextFieldName.getText().trim()));
		} else {
			cadastre.getUf().setName(null);
		}

		if (jTextFieldDni.getText() != null && jTextFieldDni.getText().trim().length() > 0) {
			cadastre.getUf().setDni(jTextFieldDni.getText().trim());
		} else {
			cadastre.getUf().setDni(null);
		}

		if (jTextFieldCuit.getText() != null && jTextFieldCuit.getText().trim().length() > 0) {
			cadastre.getUf().setCuit(jTextFieldCuit.getText().trim());
		} else {
			cadastre.getUf().setCuit(null);
		}

		if (jComboBoxIva.getItemCount() > 0 && jComboBoxIva.getSelectedIndex() > -1) {
			Iva iva = new Iva();
			iva.setId(ivaList[jComboBoxIva.getSelectedIndex()][0].toString());
			iva.setName(ivaList[jComboBoxIva.getSelectedIndex()][1].toString());
			cadastre.getUf().setIva(iva);
		} else {
			cadastre.getUf().setIva(null);
		}

		if (jTextFieldPhone.getText() != null && jTextFieldPhone.getText().trim().length() > 0) {
			cadastre.getUf().setPhone(jTextFieldPhone.getText().trim());
		} else {
			cadastre.getUf().setPhone(null);
		}

		if (jComboBoxNeighbourhood.getItemCount() > 0 && jComboBoxNeighbourhood.getSelectedIndex() > -1) {
			Neighbourhood neighbourhood = new Neighbourhood();
			neighbourhood.setId(neighbourhoodList[jComboBoxNeighbourhood.getSelectedIndex()][0].toString());
			neighbourhood.setName(neighbourhoodList[jComboBoxNeighbourhood.getSelectedIndex()][1].toString());
			cadastre.getUf().setNeighbourhood(neighbourhood);
		} else {
			cadastre.getUf().setNeighbourhood(null);
		}

		if (jTextFieldStreet.getText() != null && jTextFieldStreet.getText().trim().length() > 0) {
			cadastre.getUf().setStreet((jTextFieldStreet.getText().trim()));
		} else {
			cadastre.getUf().setStreet(null);
		}

		if (jTextFieldStreetNumber.getText() != null && jTextFieldStreetNumber.getText().trim().length() > 0) {
			cadastre.getUf().setStreetNumber(jTextFieldStreetNumber.getText().trim());
		} else {
			cadastre.getUf().setStreetNumber(null);
		}

		if (jTextFieldBuildingFloor.getText() != null && jTextFieldBuildingFloor.getText().trim().length() > 0) {
			cadastre.getUf().setBuildingFloor(jTextFieldBuildingFloor.getText().trim());
		} else {
			cadastre.getUf().setBuildingFloor(null);
		}

		if (jTextFieldBuildingRoom.getText() != null && jTextFieldBuildingRoom.getText().trim().length() > 0) {
			cadastre.getUf().setBuildingRoom(jTextFieldBuildingRoom.getText().trim());
		} else {
			cadastre.getUf().setBuildingRoom(null);
		}

		if (jTextFieldBuilding.getText() != null && jTextFieldBuilding.getText().trim().length() > 0) {
			cadastre.getUf().setBuilding(WordUtils.capitalizeFully(jTextFieldBuilding.getText().trim()));
		} else {
			cadastre.getUf().setBuilding(null);
		}

		if (jTextPaneCommentAddress.getText() != null && jTextPaneCommentAddress.getText().trim().length() > 0) {
			cadastre.getUf().setCommentAddress(jTextPaneCommentAddress.getText().trim());
		} else {
			cadastre.getUf().setCommentAddress(null);
		}

	}

	private boolean validateForm() {

		if (jTextFieldId.getText() != null && jTextFieldId.getText().trim().length() > 0) {
			if (jTextFieldId.getText().trim().length() != 6) {
				JOptionPane.showMessageDialog(this, "El código de UF debe contener 6 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			if (Services.getInstance().isExitsUfId(jTextFieldId.getText().trim()) == false) {
				JOptionPane.showMessageDialog(this, "La UF " + cadastre.getUf().getId() + " no existe.");
				return false;
			}

		} else {
			JOptionPane.showMessageDialog(this, "El código de UF no debe estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (jTextFieldDni.getText() != null && jTextFieldDni.getText().trim().length() > 0) {
			try {
				Integer.valueOf(jTextFieldDni.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "El DNI debe ser un valor entero", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (jTextFieldCuit.getText() != null && jTextFieldCuit.getText().trim().length() > 0) {
			try {
				Long.valueOf(jTextFieldCuit.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "El CUIT debe ser un valor entero", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		if (jTextFieldStreetNumber.getText() != null && jTextFieldStreetNumber.getText().trim().length() > 0) {
			try {
				Integer.valueOf(jTextFieldStreetNumber.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "El número de la calle debe ser un valor entero", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}

	private void jComboBoxNeighbourhoodLoadData() {

		try {

			jComboBoxNeighbourhood.removeAllItems();

			neighbourhoodList = Services.getInstance().findNeighbourhood();

			if (neighbourhoodList != null) {

				for (int i = 0; i < neighbourhoodList.length; i++) {
					jComboBoxNeighbourhood.addItem(new ComboItem("(" + neighbourhoodList[i][0] + ") " + neighbourhoodList[i][1], neighbourhoodList[i][0].toString()));
				}

				if (neighbourhoodList.length > 0) {
					jComboBoxNeighbourhood.setSelectedIndex(0);

				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBoxIvaLoadData() {

		try {

			jComboBoxIva.removeAllItems();

			ivaList = Services.getInstance().findIva();

			if (ivaList != null) {

				for (int i = 0; i < ivaList.length; i++) {
					jComboBoxIva.addItem(new ComboItem("(" + ivaList[i][0] + ") " + ivaList[i][1], ivaList[i][0].toString()));
				}

				if (ivaList.length > 0) {
					jComboBoxIva.setSelectedIndex(0);

				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void jComboBoxNeighbourhoodItemStateChanged() {
		if (jComboBoxNeighbourhood.getSelectedIndex() > 0) {
			jLabelPostalCode.setText(neighbourhoodList[jComboBoxNeighbourhood.getSelectedIndex()][0].toString().substring(0, 4));
		}
	}

	private void findUfById() {

		if (jTextFieldId.getText() != null && jTextFieldId.getText().trim().length() > 0) {
			if (jTextFieldId.getText().trim().length() != 6) {
				JOptionPane.showMessageDialog(this, "El código de UF debe contener 6 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (Services.getInstance().isExitsUfId(jTextFieldId.getText().trim()) == false) {
				JOptionPane.showMessageDialog(this, "La UF " + cadastre.getUf().getId() + " no existe.");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, "El código de UF no debe estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		uf = Services.getInstance().findUfById(jTextFieldId.getText().trim());
		if (uf == null) {
			return;
		}
		printClear();
		print(uf);
	}

	private void copyInmAddress() {
		if (cadastre != null && uf != null) {
			uf.setNeighbourhood(cadastre.getInmNeighbourhood());
			uf.setStreet(cadastre.getInmStreet());
			uf.setStreetNumber(cadastre.getInmStreetNumber());
			uf.setBuilding(cadastre.getInmBuilding());
			uf.setCommentAddress(cadastre.getInmCommentAddress());

			printClearAddress();
			printAddress(uf);
		}
	}

	private void update() {

		if (cadastre instanceof CadastreCensus) {
			Services.getInstance().updateCadastreUfCensus((CadastreCensus) cadastre, Context.getBean("userSystem", UserSystem.class));
		} else {
			Services.getInstance().updateCadastreUf(cadastre, Context.getBean("userSystem", UserSystem.class));
		}

	}

}
