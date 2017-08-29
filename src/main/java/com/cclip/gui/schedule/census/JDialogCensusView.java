package com.cclip.gui.schedule.census;

import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.JDialog;

public class JDialogCensusView extends JDialog {

	protected JPanelCensusViewCustom jPanelCensusViewCustom;
	private String scheduleCensusId;

	public JDialogCensusView(Frame parent, String scheduleCensusId) {
		super(parent, true);
		this.scheduleCensusId = scheduleCensusId;
		initComponents();
		
	}

	private void initComponents() {

		jPanelCensusViewCustom = new JPanelCensusViewCustom();
		jPanelCensusViewCustom.setScheduleCensusId(scheduleCensusId);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanelCensusViewCustom, GroupLayout.DEFAULT_SIZE, 1321, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanelCensusViewCustom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

}
