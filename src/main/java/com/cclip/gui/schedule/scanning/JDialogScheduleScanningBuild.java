package com.cclip.gui.schedule.scanning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.cclip.gui.JFrameMainGui;

public class JDialogScheduleScanningBuild extends JDialog implements ActionListener {

	private static final long serialVersionUID = -5060223437056879018L;

	public JButton button;

	public JDialogScheduleScanningBuild(JPanelScheduleScanning jPanelScheduleScanning) {

		this.setTitle("Generar Planillas de Barrido de Zona");
		this.setIconImage(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "cclip.jpg").getImage());
		this.setModal(true);
		this.setMinimumSize(new Dimension(700, 400));
		this.setPreferredSize(new Dimension(700, 400));
		this.setSize(700, 400);
		this.setResizable(false);
		setUndecorated(false);

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		button = new JButton("Salir");
		button.setIcon(new ImageIcon((JFrameMainGui.iconPath + "application-exit.png"))); // NOI18N

		JPanelScheduleScanningBuild jPanelScheduleScanningBuild = new JPanelScheduleScanningBuild(jPanelScheduleScanning, this);

		setSize(700, 400);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 700) / 2);
		int y = (int) ((dimension.getHeight() - 400) / 2);
		setLocation(x, y);

		getContentPane().add(jPanelScheduleScanningBuild);

		JPanel buttonPane = new JPanel();

		buttonPane.add(button);

		button.addActionListener(this);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		pack();

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		setVisible(false);
		dispose();
	}

}
