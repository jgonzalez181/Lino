package com.cclip.gui.schedule.census;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JTable;

import com.cclip.gui.geo.cadastre.JDialogInmAddressCustom;
import com.cclip.model.schedule.census.ScheduleCensus;
import com.cclip.services.Services;

public class JPanelCensusViewCustom extends JPanelCensusView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3928454400142500058L;

	private ScheduleCensus scheduleCensus;

	public JPanelCensusViewCustom() {
		super();
		printClear();
		jPanelView.setVisible(false);

		jPanelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editJDialogCensusHeaderForm();
				}
			}
		});
		
		jPanelGeneralBuildong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editJDialogConstructionWorkForm();
				}
			}
		});

	}

	public JPanelCensusViewCustom(ScheduleCensus scheduleCensus) {
		super();
		printClear();

		this.scheduleCensus = scheduleCensus;
		this.setScheduleCensus(this.scheduleCensus);

	}

	public void setDto(Object dto, JTable jTable, Integer row) {
		jPanelView.setVisible(false);
		if (dto == null) {
			return;
		}

		Object[] objArray = (Object[]) dto;

		if (objArray == null) {
			jPanelView.setVisible(false);

			return;
		}

		if (objArray[0] == null) {
			jPanelView.setVisible(false);

			return;
		}

		jPanelView.setVisible(false);

		setScheduleCensusId(objArray[0].toString());

		if (scheduleCensus == null || scheduleCensus.getId() == null) {
			jPanelView.setVisible(false);

			return;
		}

		jPanelView.setVisible(true);
	}

	public void setScheduleCensusId(String scheduleCensusId) {
		this.setScheduleCensus(Services.getInstance().findScheduleCensusById(
				scheduleCensusId));
	}

	public void setScheduleCensus(ScheduleCensus scheduleCensus) {
		this.scheduleCensus = scheduleCensus;
		if (scheduleCensus == null || scheduleCensus.getId() == null) {
			jPanelView.setVisible(false);

			return;
		}

		jPanelView.setVisible(true);
		print();
		this.jPanelCadastreView.setCadastreCensusId(this.scheduleCensus
				.getCadastreCensus().getId());
	}

	private void print() {

		printClear();

		if (this.scheduleCensus == null) {
			return;
		}

		if (scheduleCensus.getInsertCadastre() != null
				&& scheduleCensus.getInsertCadastre() == true) {
			jLabelType.setText("I");
		} else if (scheduleCensus.getDeleteCadastre() != null
				&& scheduleCensus.getDeleteCadastre() == true) {
			jLabelType.setText("D");
		} else if (scheduleCensus.getUpdateCadastre() != null
				&& scheduleCensus.getUpdateCadastre() == true) {
			jLabelType.setText("M");
		} else {
			jLabelType.setText("");
		}

		if (scheduleCensus.getCensused() != null) {
			jLabelCensused.setText(new SimpleDateFormat("dd/MM/yyyy")
					.format(scheduleCensus.getCensused()));
		} else {
			jLabelCensused.setText("");
		}

		if (scheduleCensus.getNumberCensus() != null) {
			jLabelNumberCensus.setText(scheduleCensus.getNumberCensus()
					.toString());
		} else {
			jLabelNumberCensus.setText("");
		}

		if (scheduleCensus.getSchedule() != null) {
			jLabelSchedule.setText(scheduleCensus.getSchedule().toString());
		} else {
			jLabelSchedule.setText("");
		}

		if (scheduleCensus.getScheduleCensusResult() != null) {
			jLabelResult.setText(scheduleCensus.getScheduleCensusResult()
					.toString());
		} else {
			jLabelResult.setText("");
		}

		if (scheduleCensus.getScheduleBatch() != null) {
			jLabelScheduleBatch.setText(scheduleCensus.getScheduleBatch()
					.toString());
		} else {
			jLabelScheduleBatch.setText("");
		}

		if (scheduleCensus.getCensusTaker() != null) {
			jLabelCensusTaker.setText(scheduleCensus.getCensusTaker()
					.toString());
		} else {
			jLabelCensusTaker.setText("");
		}

		if (scheduleCensus.getComment() != null) {
			jTextPaneComment.setText(scheduleCensus.getComment());
		} else {
			jTextPaneComment.setText("");
		}

		String tmp = "";

		if (scheduleCensus.getDniCensused() != null) {
			tmp += "(" + scheduleCensus.getDniCensused() + ")";
		}

		if (scheduleCensus.getNameCensused() != null) {
			tmp += " " + scheduleCensus.getNameCensused();
		}

		if (scheduleCensus.getLastNameCensused() != null) {
			tmp += " " + scheduleCensus.getLastNameCensused();
		}

		jLabelCensusPerson.setText(tmp.trim());

		if (scheduleCensus.getSignatureCensused() != null
				&& scheduleCensus.getSignatureCensused() == true) {
			jLabelSigned.setText("Si");
		} else if (scheduleCensus.getSignatureCensused() != null
				&& scheduleCensus.getSignatureCensused() == false) {
			jLabelSigned.setText("No");
		} else {
			jLabelSigned.setText("  ");
		}

		if (scheduleCensus.getM2GeneralBuilding() != null) {
			jLabelM2GeneralBuilding.setText(scheduleCensus
					.getM2GeneralBuilding().toString());
		} else {
			jLabelM2GeneralBuilding.setText("   ");
		}

		if (scheduleCensus.getM2BuildingsPublicEntertainment() != null) {
			jLabelM2BuildingsPublicEntertainment.setText(scheduleCensus
					.getM2BuildingsPublicEntertainment().toString());
		} else {
			jLabelM2BuildingsPublicEntertainment.setText("   ");
		}

		if (scheduleCensus.getM2ProgressConstruction() != null) {
			jLabelM2ProgressConstruction.setText(scheduleCensus
					.getM2ProgressConstruction().toString());
		} else {
			jLabelM2ProgressConstruction.setText("   ");
		}

		if (scheduleCensus.getM2ShedsGeneral() != null) {
			jLabelM2ShedsGeneral.setText(scheduleCensus.getM2ShedsGeneral()
					.toString());
		} else {
			jLabelM2ShedsGeneral.setText("   ");
		}

	}

	private void printClear() {

		jLabelType.setText("");

		jLabelCensused.setText("");

		jLabelNumberCensus.setText("");

		jLabelSchedule.setText("");

		jLabelResult.setText("");

		jLabelScheduleBatch.setText("");

		jLabelCensusTaker.setText("");

		jTextPaneComment.setText("");

		jLabelCensusPerson.setText("");

		jLabelSigned.setText("  ");

		jLabelM2GeneralBuilding.setText("   ");

		jLabelM2BuildingsPublicEntertainment.setText("   ");

		jLabelM2ProgressConstruction.setText("   ");

		jLabelM2ShedsGeneral.setText("   ");

	}

	private void editJDialogCensusHeaderForm() {
		new JDialogCensusHeaderFormCustom(scheduleCensus, this);
	}
	
	private void editJDialogConstructionWorkForm() {
		new JDialogConstructionWorkFormCustom(scheduleCensus, this);
	}
	
	

}
