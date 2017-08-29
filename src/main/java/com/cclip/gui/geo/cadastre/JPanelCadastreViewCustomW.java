package com.cclip.gui.geo.cadastre;

import java.awt.BorderLayout;

import javax.swing.JTable;

import com.cclip.gui.util.JPanelForm;

public class JPanelCadastreViewCustomW extends JPanelForm {

	private static final long serialVersionUID = -6126573501501775859L;

	private JPanelCadastreViewCustom jPanelCadastreViewCustom;

	public JPanelCadastreViewCustomW() {
		super(new BorderLayout());
		this.jPanelCadastreViewCustom = new JPanelCadastreViewCustom(this);
		this.jPanelCadastreViewCustom.setVisible(false);

		add(jPanelCadastreViewCustom, BorderLayout.NORTH);

	}

	public void setDto(Object dto, JTable jTable, Integer row) {

		jPanelCadastreViewCustom.setVisible(false);

		if (dto == null) {
			return;
		}

		Object[] objArray = (Object[]) dto;

		if (objArray[0] == null) {
			return;
		}

		jPanelCadastreViewCustom.setDto(dto, jTable, row);

		if (jPanelCadastreViewCustom.getCadastre() == null || jPanelCadastreViewCustom.getCadastre().getId() == null) {
			jPanelCadastreViewCustom.setVisible(false);

			return;
		}

		jPanelCadastreViewCustom.setVisible(true);

	}

	public void setCadastreCensusId(String cadastreCensusId) {

		jPanelCadastreViewCustom.setVisible(false);

		if (cadastreCensusId == null) {
			return;
		}

		jPanelCadastreViewCustom.setCadastreCensusId(cadastreCensusId);

		if (jPanelCadastreViewCustom.getCadastre() == null || jPanelCadastreViewCustom.getCadastre().getId() == null) {
			jPanelCadastreViewCustom.setVisible(false);

			return;
		}

		jPanelCadastreViewCustom.setVisible(true);
	}

//	public void refresh(String cadastreCensusId) {
//		setCadastreCensusId(cadastreCensusId);
//	}

}
