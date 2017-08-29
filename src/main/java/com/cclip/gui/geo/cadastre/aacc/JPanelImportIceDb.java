package com.cclip.gui.geo.cadastre.aacc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.cclip.Context;
import com.cclip.gui.JFrameMainGui;
import com.cclip.gui.aacc.JDialogImportIceDbProgress;
import com.cclip.model.person.UserSystem;
import com.cclip.util.LinoProperties;
import com.cclip.util.UtilComponent;

public class JPanelImportIceDb extends JPanel {

	private static final long serialVersionUID = -8375662604055751799L;

	private JFileChooser jFileChooser1;

	private File fileUnidad;
	private File fileInmueble;
	private File fileSuperficie;
	private File fileSubcuenta;
	private File fileDatosPost;

	public JFrame jFrame;

	private void importUnl() {
		try {
			
			jLabel1.setText("/home/java/jaspe/cclip/migracion/ejemplos_import_aacc/ejemplo_bases_congeladas/base_congelada_mayo_16/cat_39086_uni_a_2016061_00.unl");
			jLabel2.setText("/home/java/jaspe/cclip/migracion/ejemplos_import_aacc/ejemplo_bases_congeladas/base_congelada_mayo_16/cat_39086_inm_a_2016061_00.unl");
			jLabel3.setText("/home/java/jaspe/cclip/migracion/ejemplos_import_aacc/ejemplo_bases_congeladas/base_congelada_mayo_16/cat_39086_sup_a_2016061_00.unl");
			jLabel4.setText("/home/java/jaspe/cclip/migracion/ejemplos_import_aacc/ejemplo_bases_congeladas/base_congelada_mayo_16/cat_39086_sta_a_2016061_00.unl");
			jLabel5.setText("/home/java/jaspe/cclip/migracion/ejemplos_import_aacc/ejemplo_bases_congeladas/base_congelada_mayo_16/cat_39086_usu_a_2016061_00.unl");

			if (jLabel1.getText().trim().length() == 0 || jLabel2.getText().trim().length() == 0 || jLabel3.getText().trim().length() == 0 || jLabel4.getText().trim().length() == 0
					|| jLabel5.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar los archivos .UNL .", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(JFrameMainGui.iconPath + "dialog-error.png"));
				return;
			}

			LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

			if (jRadioButton2.isSelected()) {
				jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
			} else {
				jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
			}

			// jLabel7.setText("Importando...");
			// jButton6.setText("Importando ..");

			UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

			JDialogImportIceDbProgress gui = new JDialogImportIceDbProgress(jFrame, userSystem, fileUnidad, fileInmueble, fileSuperficie, fileSubcuenta, fileDatosPost);
			gui.show();

			// if (jRadioButton2.isSelected()) {
			// Services.getInstance().importUnlNov(gui, userSystem, fileUnidad, fileInmueble, fileSuperficie, fileSubcuenta);
			// } else {
			// Services.getInstance().importUnlDbIce(gui, userSystem, fileUnidad, fileInmueble, fileSuperficie, fileSubcuenta, fileDatosPost);
			// }

			JOptionPane.showMessageDialog(this, "Importación concluida con éxsito", "Fin de Importación", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
					(JFrameMainGui.iconPath + "dialog-information.png")));

//			jLabel7.setText("");
//			jButton6.setText("Importar Archivos");

//			jLabel1.setText("");
//			jLabel2.setText("");
//			jLabel4.setText("");
//			jLabel5.setText("");
//			jLabel3.setText("");

		} catch (Exception e) {

			UtilComponent.logger(e);

			JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);

			jLabel7.setText("");
			jButton6.setText("Importar Archivos");
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton4.setVisible(jRadioButton1.isSelected());
		jLabel5.setVisible(jRadioButton1.isSelected());

		if (jRadioButton2.isSelected()) {
			jRadioButton2.setForeground(new java.awt.Color(255, 255, 153));
			jRadioButton1.setForeground(Color.WHITE);

			jButton1.setText("UNIDAD_NOVEDADES.UNL");
			jButton2.setText("INMUEBLE_NOVEDADES.UNL");
			jButton3.setText("SUBCUENTA_NOVEDADES.UNL");
			jButton4.setText("DATOS_POSTALES_NOVEDADES.UNL");
			jButton5.setText("SUPERFICIE_NOVEDADES.UNL");

			jLabel8.setBackground(new Color(0, 176, 80));

		} else {
			jRadioButton1.setForeground(new java.awt.Color(255, 255, 153));
			jRadioButton2.setForeground(Color.WHITE);

			jButton1.setText("UNIDAD_BASE_CONGELADA.UNL");
			jButton2.setText("INMUEBLE_BASE_CONGELADA.UNL");
			jButton3.setText("SUBCUENTA_BASE_CONGELADA.UNL");
			jButton4.setText("DATOS_POSTALES_BASE_CONGELADA.UNL");
			jButton5.setText("SUPERFICIE_BASE_CONGELADA.UNL");

			jLabel8.setBackground(new Color(192, 0, 0));
		}

	}

	private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton4.setVisible(!jRadioButton2.isSelected());
		jLabel5.setVisible(!jRadioButton2.isSelected());

		if (jRadioButton2.isSelected()) {
			jRadioButton2.setForeground(new java.awt.Color(255, 255, 153));
			jRadioButton1.setForeground(Color.WHITE);

			jButton1.setText("UNIDAD_NOVEDADES.UNL");
			jButton2.setText("INMUEBLE_NOVEDADES.UNL");
			jButton3.setText("SUBCUENTA_NOVEDADES.UNL");
			jButton4.setText("DATOS_POSTALES_NOVEDADES.UNL");
			jButton5.setText("SUPERFICIE_NOVEDADES.UNL");

			jLabel8.setBackground(new Color(0, 176, 80));

		} else {
			jRadioButton1.setForeground(new java.awt.Color(255, 255, 153));
			jRadioButton2.setForeground(Color.WHITE);

			jButton1.setText("UNIDAD_BASE_CONGELADA.UNL");
			jButton2.setText("INMUEBLE_BASE_CONGELADA.UNL");
			jButton3.setText("SUBCUENTA_BASE_CONGELADA.UNL");
			jButton4.setText("DATOS_POSTALES_BASE_CONGELADA.UNL");
			jButton5.setText("SUPERFICIE_BASE_CONGELADA.UNL");

			jLabel8.setBackground(new Color(192, 0, 0));
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		importUnl();
	}

	public JPanelImportIceDb() {
		initComponents();

		jLabel8.setOpaque(true);
		jLabel8.setBackground(new Color(192, 0, 0));
		jLabel8.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel7.setOpaque(false);

		jButton1.setText("UNIDAD_BASE_CONGELADA.UNL");
		jButton2.setText("INMUEBLE_BASE_CONGELADA.UNL");
		jButton3.setText("SUBCUENTA_BASE_CONGELADA.UNL");
		jButton4.setText("DATOS_POSTALES_BASE_CONGELADA.UNL");
		jButton5.setText("SUPERFICIE_BASE_CONGELADA.UNL");

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

				if (jRadioButton2.isSelected()) {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
				} else {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
				}

				int returnVal = jFileChooser1.showOpenDialog(JPanelImportIceDb.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileUnidad = jFileChooser1.getSelectedFile();
					jLabel1.setText(fileUnidad.getPath());
				} else {
					jLabel1.setText("");
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

				if (jRadioButton2.isSelected()) {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
				} else {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
				}

				int returnVal = jFileChooser1.showOpenDialog(JPanelImportIceDb.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileInmueble = jFileChooser1.getSelectedFile();
					jLabel2.setText(fileInmueble.getPath());
				} else {
					jLabel2.setText("");
				}
			}
		});

		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

				if (jRadioButton2.isSelected()) {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
				} else {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
				}

				int returnVal = jFileChooser1.showOpenDialog(JPanelImportIceDb.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileSubcuenta = jFileChooser1.getSelectedFile();
					jLabel4.setText(fileSubcuenta.getPath());
				} else {
					jLabel4.setText("");
				}
			}
		});

		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

				if (jRadioButton2.isSelected()) {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
				} else {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
				}

				int returnVal = jFileChooser1.showOpenDialog(JPanelImportIceDb.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileDatosPost = jFileChooser1.getSelectedFile();
					jLabel5.setText(fileDatosPost.getPath());
				} else {
					jLabel5.setText("");
				}
			}
		});

		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

				if (jRadioButton2.isSelected()) {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlNovFrom()));
				} else {
					jFileChooser1 = new JFileChooser(new File(linoProperties.getUrlUnlDbIceFrom()));
				}

				int returnVal = jFileChooser1.showOpenDialog(JPanelImportIceDb.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileSuperficie = jFileChooser1.getSelectedFile();
					jLabel3.setText(fileSuperficie.getPath());
				} else {
					jLabel3.setText("");
				}
			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jRadioButton1 = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		jButton6 = new javax.swing.JButton();

		setBackground(new java.awt.Color(102, 102, 102));
		setPreferredSize(new java.awt.Dimension(1024, 768));

		jLabel1.setBackground(new java.awt.Color(255, 255, 255));
		jLabel1.setForeground(new java.awt.Color(0, 102, 153));
		jLabel1.setOpaque(true);

		jLabel2.setBackground(new java.awt.Color(255, 255, 255));
		jLabel2.setForeground(new java.awt.Color(0, 102, 153));
		jLabel2.setOpaque(true);

		jLabel3.setBackground(new java.awt.Color(255, 255, 255));
		jLabel3.setForeground(new java.awt.Color(0, 102, 153));
		jLabel3.setOpaque(true);

		jLabel4.setBackground(new java.awt.Color(255, 255, 255));
		jLabel4.setForeground(new java.awt.Color(0, 102, 153));
		jLabel4.setOpaque(true);

		jLabel5.setBackground(new java.awt.Color(255, 255, 255));
		jLabel5.setForeground(new java.awt.Color(0, 102, 153));
		jLabel5.setOpaque(true);

		jButton1.setText("Unidad");

		jButton2.setText("Inmueble");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Subcuenta");

		jButton4.setText("Datos Postales");

		jButton5.setText("Superfice");

		jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		jLabel6.setForeground(new java.awt.Color(255, 255, 255));
		jLabel6.setText("Seleccione los archivos a importar:");

		jLabel7.setBackground(new java.awt.Color(102, 102, 102));
		jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(255, 255, 255));
		jLabel7.setOpaque(true);

		jLabel8.setBackground(new java.awt.Color(255, 0, 0));
		jLabel8.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
		jLabel8.setForeground(new java.awt.Color(255, 255, 255));
		jLabel8.setText("Importar Archivos .UNL");
		jLabel8.setOpaque(true);

		jRadioButton1.setBackground(new java.awt.Color(102, 102, 102));
		buttonGroup1.add(jRadioButton1);
		jRadioButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jRadioButton1.setForeground(new java.awt.Color(255, 255, 153));
		jRadioButton1.setSelected(true);
		jRadioButton1.setText("Base de Datos Congelada");
		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}
		});

		jRadioButton2.setBackground(new java.awt.Color(102, 102, 102));
		buttonGroup1.add(jRadioButton2);
		jRadioButton2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
		jRadioButton2.setText("Novedades");
		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton2ActionPerformed(evt);
			}
		});

		jButton6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jButton6.setText("Importar Archivos");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel6))
												.addGroup(layout.createSequentialGroup().addGap(124, 124, 124).addComponent(jRadioButton1).addGap(42, 42, 42).addComponent(jRadioButton2)))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
												.addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup().addComponent(jButton6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 28, Short.MAX_VALUE))
												.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel8)
						.addGap(34, 34, 34)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jRadioButton1).addComponent(jRadioButton2))
						.addGap(34, 34, 34)
						.addComponent(jLabel6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
										.addGroup(
												layout.createSequentialGroup().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(7, 7, 7).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(6, 6, 6)
														.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(6, 6, 6)
														.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(6, 6, 6)
														.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup().addComponent(jButton1).addGap(7, 7, 7).addComponent(jButton2).addGap(6, 6, 6).addComponent(jButton5).addGap(6, 6, 6)
														.addComponent(jButton3).addGap(6, 6, 6).addComponent(jButton4)))
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addGap(210, 210, 210)
														.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(193, 193, 193).addComponent(jButton6))).addContainerGap()));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	// End of variables declaration
}
