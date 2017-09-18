package com.cclip.gui.schedule.census;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JDialog;


import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;

import com.cclip.model.person.UserSystem;

import com.cclip.model.schedule.census.ScheduleCensus;

import com.cclip.services.Services;

public class JDialogConstructionWorkFormCustom extends JDialogConstructionWorkForm implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;

	private ScheduleCensus scheduleCensus;
	private JPanelCensusViewCustom jPanelCensusViewCustom;
	
	public JDialogConstructionWorkFormCustom(Frame parent, boolean modal) {
		super(parent, modal);
		// TODO Auto-generated constructor stub
	}
	
	public JDialogConstructionWorkFormCustom(ScheduleCensus scheduleCensus,
			JPanelCensusViewCustom jPanelCensusViewCustom) {
		super(null, true);

		this.scheduleCensus = scheduleCensus;
		this.jPanelCensusViewCustom = jPanelCensusViewCustom;

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		jButtonSave.setIcon(new ImageIcon(
				(JFrameMainGui.iconPath + "document-save.png"))); // NOI18N
		jButtonCancel.setIcon(new ImageIcon(
				(JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		jButtonSave.addActionListener(this);
		jButtonCancel.addActionListener(this);

		

		printClear();
		print();

		pack();

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(jButtonSave)) {

			if (validateForm() == false) {
				return;
			}

			try {
				formToDto();
				update();
				if (scheduleCensus != null) {
					jPanelCensusViewCustom.setScheduleCensusId(scheduleCensus
							.getId());
				}
			} catch (Exception e1) {
				return;
			}
		}

		setVisible(false);
		dispose();
		
	}
	
	private void printClear() {
		jM2ShedsGral.setText("");
		jM2GralBuilding.setText("");
		jM2BuildPublic.setText("");
		jM2Progress.setText("");
		

	}

	private void print() {
		if (scheduleCensus == null) {
			return;
		}

		if (scheduleCensus.getM2ShedsGeneral() != null) {
			jM2ShedsGral.setText(scheduleCensus.getM2ShedsGeneral().toString());
		} else {
			jM2ShedsGral.setText("");
		}
		
		if (scheduleCensus.getM2GeneralBuilding() != null) {
			jM2GralBuilding.setText(scheduleCensus.getM2GeneralBuilding().toString());
		} else {
			jM2GralBuilding.setText("");
		}
		
		if (scheduleCensus.getM2BuildingsPublicEntertainment() != null) {
			jM2BuildPublic.setText(scheduleCensus.getM2BuildingsPublicEntertainment().toString());
		} else {
			jM2BuildPublic.setText("");
		}
		
		if (scheduleCensus.getM2ProgressConstruction() != null) {
			jM2Progress.setText(scheduleCensus.getM2ProgressConstruction().toString());
		} else {
			jM2Progress.setText("");
		}

	}
	
	private boolean validateForm() {

		
		return true;
	}
	
	private void update() {
		
		Services.getInstance().updateScheduleCensusConstruccionWork(scheduleCensus, Context.getBean("userSystem", UserSystem.class));
			
	}

	private void formToDto() throws Exception {

		if (scheduleCensus == null) {
			return;
		}
		//M2ShedsGeneral
		if (jM2ShedsGral.getText()!=null && jM2ShedsGral.getText().length()>0) {
			
			scheduleCensus.setM2ShedsGeneral(new Double(jM2ShedsGral.getText().toString()));;
		} else {
			scheduleCensus.setM2ShedsGeneral(null);
		}
		
		//M2GeneralBuilding
		if (jM2GralBuilding.getText()!=null && jM2GralBuilding.getText().length()>0) {
			
			scheduleCensus.setM2GeneralBuilding(new Double(jM2GralBuilding.getText().toString()));;
		} else {
			scheduleCensus.setM2GeneralBuilding(null);
		}
		
		//M2BuildingsPublicEntertainment
		if (jM2BuildPublic.getText()!=null && jM2BuildPublic.getText().length()>0) {
			
			scheduleCensus.setM2BuildingsPublicEntertainment(new Double(jM2BuildPublic.getText().toString()));;
		} else {
			scheduleCensus.setM2BuildingsPublicEntertainment(null);
		}
		
		//M2ProgressConstruction
		if (jM2Progress.getText()!=null && jM2Progress.getText().length()>0) {
			
			scheduleCensus.setM2ProgressConstruction(new Double(jM2Progress.getText().toString()));;
		} else {
			scheduleCensus.setM2ProgressConstruction(null);
		}
		

	}

}

