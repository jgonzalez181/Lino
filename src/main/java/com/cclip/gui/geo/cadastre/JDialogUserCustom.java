package com.cclip.gui.geo.cadastre;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.text.WordUtils;

import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.util.ComboItem;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.UserWaterSituation;
import com.cclip.model.person.Iva;
import com.cclip.model.person.UserSystem;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JDialogUserCustom extends JDialogUser implements ActionListener {

	private static final long serialVersionUID = 1361395090519503752L;

	private JPanelCadastreViewCustom jPanelCadastreViewCustom;

	private Object[][] ivaList;
	private Object[][] userWaterSituationList;
	private Cadastre cadastre;

	private JDialogUserCustom(Frame parent, boolean modal) {
		super(parent, modal);
	}

	public JDialogUserCustom(Cadastre cadastre, JPanelCadastreViewCustom jPanelCadastreViewCustom) {
		super(null, true);

		this.jPanelCadastreViewCustom = jPanelCadastreViewCustom;

		this.cadastre = cadastre;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon((JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		jButtonCopyInmAddress.setIcon(new ImageIcon((JFrameMainGui.iconPath + "go-home.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		jButtonCopyInmAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				copyInmAddress();
			}
		});

		jComboBoxIvaLoadData();

		jComboBoxUserWaterSituationLoadData();

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

		if (cadastre.getUserWaterSituation() != null) {
			for (int i = 0; i < userWaterSituationList.length; i++) {
				if (userWaterSituationList[i][0].equals(cadastre.getUserWaterSituation().getId())) {
					jComboBoxUserWaterSituation.setSelectedIndex(i);
					break;
				}
			}
		}

		if (cadastre.getUserWaterService() != null) {
			jTextFieldName.setText(cadastre.getUserWaterService());
		}

		if (cadastre.getUserWaterServiceDni() != null) {
			jTextFieldDni.setText(cadastre.getUserWaterServiceDni());
		}

		if (cadastre.getUserWaterServiceCuit() != null) {
			jTextFieldCuit.setText(cadastre.getUserWaterServiceCuit());
		}

		if (cadastre.getUserIva() != null) {
			for (int i = 0; i < ivaList.length; i++) {
				if (ivaList[i][0].equals(cadastre.getUserIva().getId())) {
					jComboBoxIva.setSelectedIndex(i);
					break;
				}
			}
		}

		if (cadastre.getUserPhone() != null) {
			jTextFieldPhone.setText(cadastre.getUserPhone());
		}

		printAddress();
	}

	private void printAddress() {

		if (cadastre == null) {
			return;
		}

		if (cadastre.getUserPostalPostalCode() != null) {
			jTextFieldPostalCode.setText(cadastre.getUserPostalPostalCode());
		}

		if (cadastre.getUserPostalNeighbourhood() != null) {
			jTextFieldNeighbourhood.setText(cadastre.getUserPostalNeighbourhood());
		}

		if (cadastre.getUserPostalStreet() != null) {
			jTextFieldStreet.setText(cadastre.getUserPostalStreet());
		}

		if (cadastre.getUserPostalStreetNumber() != null) {
			jTextFieldStreetNumber.setText(cadastre.getUserPostalStreetNumber());
		}

		if (cadastre.getUserPostalBuildingFloor() != null) {
			jTextFieldBuildingFloor.setText(cadastre.getUserPostalBuildingFloor());
		}

		if (cadastre.getUserPostalBuildingRoom() != null) {
			jTextFieldBuildingRoom.setText(cadastre.getUserPostalBuildingRoom());
		}

		if (cadastre.getUserPostalBuilding() != null) {
			jTextFieldBuilding.setText(cadastre.getUserPostalBuilding());
		}

		if (cadastre.getUserPostalCommentAddress() != null) {
			jTextPaneCommentAddress.setText(cadastre.getUserPostalCommentAddress());
		}

	}

	private void printClear() {

		if (jComboBoxUserWaterSituation.getItemCount() > 0) {
			jComboBoxUserWaterSituation.setSelectedIndex(0);
		}

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

		jTextFieldPostalCode.setText("");
		jTextFieldNeighbourhood.setText("");
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

		if (jComboBoxUserWaterSituation.getItemCount() > 0 && jComboBoxUserWaterSituation.getSelectedIndex() > -1) {
			UserWaterSituation userWaterSituation = new UserWaterSituation();
			userWaterSituation.setId(userWaterSituationList[jComboBoxUserWaterSituation.getSelectedIndex()][0].toString());
			userWaterSituation.setName(userWaterSituationList[jComboBoxUserWaterSituation.getSelectedIndex()][1].toString());
			cadastre.setUserWaterSituation(userWaterSituation);
		} else {
			cadastre.setUserWaterSituation(null);
		}

		if (jTextFieldName.getText() != null && jTextFieldName.getText().trim().length() > 0) {
			cadastre.setUserWaterService(WordUtils.capitalizeFully(jTextFieldName.getText().trim()));
		} else {
			cadastre.setUserWaterService(null);
		}

		if (jTextFieldDni.getText() != null && jTextFieldDni.getText().trim().length() > 0) {
			cadastre.setUserWaterServiceDni(jTextFieldDni.getText().trim());
		} else {
			cadastre.setUserWaterServiceDni(null);
		}

		if (jTextFieldCuit.getText() != null && jTextFieldCuit.getText().trim().length() > 0) {
			cadastre.setUserWaterServiceCuit(jTextFieldCuit.getText().trim());
		} else {
			cadastre.setUserWaterServiceCuit(null);
		}

		if (jComboBoxIva.getItemCount() > 0 && jComboBoxIva.getSelectedIndex() > -1) {
			Iva iva = new Iva();
			iva.setId(ivaList[jComboBoxIva.getSelectedIndex()][0].toString());
			iva.setName(ivaList[jComboBoxIva.getSelectedIndex()][1].toString());
			cadastre.setUserIva(iva);
		} else {
			cadastre.setUserIva(null);
		}

		if (jTextFieldPhone.getText() != null && jTextFieldPhone.getText().trim().length() > 0) {
			cadastre.setUserPhone(jTextFieldPhone.getText().trim());
		} else {
			cadastre.setUserPhone(null);
		}

		if (jTextFieldPostalCode.getText() != null && jTextFieldPostalCode.getText().trim().length() > 0) {
			cadastre.setUserPostalPostalCode(jTextFieldPostalCode.getText().trim());
		} else {
			cadastre.setUserPostalPostalCode(null);
		}

		if (jTextFieldNeighbourhood.getText() != null && jTextFieldNeighbourhood.getText().trim().length() > 0) {
			cadastre.setUserPostalNeighbourhood(WordUtils.capitalizeFully(jTextFieldNeighbourhood.getText().trim()));
		} else {
			cadastre.setUserPostalNeighbourhood(null);
		}

		if (jTextFieldStreet.getText() != null && jTextFieldStreet.getText().trim().length() > 0) {
			cadastre.setUserPostalStreet((jTextFieldStreet.getText().trim()));
		} else {
			cadastre.setUserPostalStreet(null);
		}

		if (jTextFieldStreetNumber.getText() != null && jTextFieldStreetNumber.getText().trim().length() > 0) {
			cadastre.setUserPostalStreetNumber(jTextFieldStreetNumber.getText().trim());
		} else {
			cadastre.setUserPostalStreetNumber(null);
		}

		if (jTextFieldBuildingFloor.getText() != null && jTextFieldBuildingFloor.getText().trim().length() > 0) {
			cadastre.setUserPostalBuildingFloor(jTextFieldBuildingFloor.getText().trim());
		} else {
			cadastre.setUserPostalBuildingFloor(null);
		}

		if (jTextFieldBuildingRoom.getText() != null && jTextFieldBuildingRoom.getText().trim().length() > 0) {
			cadastre.setUserPostalBuildingRoom(jTextFieldBuildingRoom.getText().trim());
		} else {
			cadastre.setUserPostalBuildingRoom(null);
		}

		if (jTextFieldBuilding.getText() != null && jTextFieldBuilding.getText().trim().length() > 0) {
			cadastre.setUserPostalBuilding(WordUtils.capitalizeFully(jTextFieldBuilding.getText().trim()));
		} else {
			cadastre.setUserPostalBuilding(null);
		}

		if (jTextPaneCommentAddress.getText() != null && jTextPaneCommentAddress.getText().trim().length() > 0) {
			cadastre.setUserPostalCommentAddress(jTextPaneCommentAddress.getText().trim());
		} else {
			cadastre.setUserPostalCommentAddress(null);
		}

	}

	private boolean validateForm() {

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

	private void jComboBoxUserWaterSituationLoadData() {

		try {

			jComboBoxUserWaterSituation.removeAllItems();

			userWaterSituationList = Services.getInstance().findUserWaterSituation();

			if (userWaterSituationList != null) {

				for (int i = 0; i < userWaterSituationList.length; i++) {
					jComboBoxUserWaterSituation.addItem(new ComboItem("(" + userWaterSituationList[i][0] + ") " + userWaterSituationList[i][1], userWaterSituationList[i][0].toString()));
				}

				if (userWaterSituationList.length > 0) {
					jComboBoxUserWaterSituation.setSelectedIndex(0);

				}

			}

		} catch (Exception e) {

			UtilComponent.logger(e);
		}
	}

	private void copyInmAddress() {
		if (cadastre != null) {
			if (cadastre.getInmNeighbourhood() != null) {
				cadastre.setUserPostalNeighbourhood(cadastre.getInmNeighbourhood().getName());
				cadastre.setUserPostalPostalCode(cadastre.getInmNeighbourhood().getId().substring(0, 4));
			}
			cadastre.setUserPostalStreet(cadastre.getInmStreet());
			cadastre.setUserPostalStreetNumber(cadastre.getInmStreetNumber());
			cadastre.setUserPostalBuilding(cadastre.getInmBuilding());
			cadastre.setUserPostalCommentAddress(cadastre.getInmCommentAddress());

			printClearAddress();
			printAddress();
		}
	}

	private void update() {
		
		if(cadastre instanceof CadastreCensus){
			Services.getInstance().updateUserWaterServiceCensus(cadastre, Context.getBean("userSystem", UserSystem.class));
		} else {
			Services.getInstance().updateUserWaterService(cadastre, Context.getBean("userSystem", UserSystem.class));
		}

		
	}
}
