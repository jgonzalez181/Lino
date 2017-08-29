package com.cclip.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.cclip.Context;
import com.cclip.model.person.UserSystem;
import com.cclip.services.Services;
import com.cclip.util.LinoProperties;
import com.cclip.util.UtilComponent;

/**
 *
 * @author java
 */
public class JDialogLogin extends javax.swing.JDialog implements ActionListener {

	private JFrameMainGui jFrameMainGui = null;

	/**
	 * Creates new form JDialogLogin
	 */
	public JDialogLogin(java.awt.Frame parent) {
		super(parent, true);

		jFrameMainGui = (JFrameMainGui) parent;

		initComponents();
		jPasswordField1.setText("");

		LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);
		jTextField1.setText(linoProperties.getCuilLogin());

		jTextField1.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jButton1ActionPerformed(null);
				}

			}
		});

		jPasswordField1.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jButton1ActionPerformed(null);
				}

			}
		});

		this.setTitle("Sistema Lino - Login");
		this.setIconImage(new ImageIcon(JFrameMainGui.iconPath + "cclip.jpg").getImage());
		this.setModal(true);
		this.setMinimumSize(new Dimension(800, 340));
		this.setPreferredSize(new Dimension(800, 340));
		this.setSize(800, 340);
		this.setResizable(false);
		// setUndecorated(false);

		// this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setSize(700, 400);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 700) / 2);
		int y = (int) ((dimension.getHeight() - 400) / 2);
		setLocation(x, y);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		pack();

		setVisible(true);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		try {

//			CustomUserSystemBo bo = Context.getBean("customUserSystemBo", CustomUserSystemBo.class);
//
//			UserSystem userSystemLogin = bo.findByUserPass(jTextField1.getText(), new String(jPasswordField1.getPassword()));
			
			UserSystem userSystemLogin = Services.getInstance().findUserSystemByUserPass(jTextField1.getText(), new String(jPasswordField1.getPassword()));

			if (userSystemLogin != null) {
				jFrameMainGui.login = true;

				UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

				userSystem.setId(userSystemLogin.getId());
				userSystem.setErased(userSystemLogin.getErased());
				userSystem.setCuil(userSystemLogin.getCuil());
				userSystem.setDni(userSystemLogin.getDni());
				userSystem.setGivenName(userSystemLogin.getGivenName());
				userSystem.setMiddleName(userSystemLogin.getMiddleName());
				userSystem.setFamilyName(userSystemLogin.getFamilyName());

				setVisible(true);
				dispose();

			} else {
				jFrameMainGui.login = false;
				JOptionPane.showMessageDialog(this, "CUIL y/o Contraseña incorrectos ! ", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	public void actionPerformed(ActionEvent e) {

		jFrameMainGui.login = false;

		setVisible(false);
		dispose();

	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jPasswordField1 = new javax.swing.JPasswordField();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jLabel1.setIcon(new javax.swing.ImageIcon(JFrameMainGui.iconPath + "logo.png")); // NOI18N
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(0, 102, 153));
		jLabel2.setText("CUIL:");

		jTextField1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

		jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(0, 102, 153));
		jLabel3.setText("Contraseña:");

		jPasswordField1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
		jPasswordField1.setText("jPasswordField1");

		jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jButton1.setText("Ingresar");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout
						.createSequentialGroup()
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(
								jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												jPanel1Layout
														.createSequentialGroup()
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(
																jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2)
																		.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(
																jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField1)
																		.addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(108, 108, 108).addComponent(jButton1))).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel1).addGap(0, 13, Short.MAX_VALUE))
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addGap(101, 101, 101)
								.addGroup(
										jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
												.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
												.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(47, 47, 47).addComponent(jButton1).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration
}
