/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cclip.gui.geo.cadastre;

/**
 *
 * @author java
 */
public class JDialogCadastreBlock extends javax.swing.JDialog {

    /**
     * Creates new form JDialogInmAddress
     */
    public JDialogCadastreBlock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableG = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableI = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableC = new javax.swing.JTable();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Domicilio del Inmueble");
        setMaximumSize(new java.awt.Dimension(1412, 2147483647));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)), "Bloques", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 12), java.awt.Color.white)); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1412, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(1412, 0));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1412, 32767));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1412, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1412, 1007));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setForeground(new java.awt.Color(0, 102, 153));
        jPanel4.setMaximumSize(new java.awt.Dimension(1412, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(1412, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(1412, 1004));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTableG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Año", "2001", null, null, null, null, null, null},
                {"Mes", "Noviembre", null, null, null, null, null, null},
                {"Superficie", "300", null, null, null, null, null, null},
                {"Fachada", "2", null, null, null, null, null, null},
                {"Techos/Estructura", "2", null, null, null, null, null, null},
                {"Pisos", "22", null, null, null, null, null, null},
                {"Muros interiores", "22", null, null, null, null, null, null},
                {"Cielorrasos", "2", null, null, null, null, null, null},
                {"Cocina", "22", null, null, null, null, null, null},
                {"Baños", "2", null, null, null, null, null, null},
                {"Hall de ingreso", "2", null, null, null, null, null, null},
                {"Instalaciones", "222", null, null, null, null, null, null},
                {"Carpintería", "222", null, null, null, null, null, null},
                {"Puntos", null, null, null, null, null, null, null},
                {"APC", null, null, null, null, null, null, null},
                {"APC Comentario", null, null, null, null, null, null, null},
                {"Tarifa", null, null, null, null, null, null, null},
                {"Tipo", null, null, null, null, null, null, null},
                {"Actividad", null, null, null, null, null, null, null}
            },
            new String [] {
                "General", "1", "2", "3", "4", "5", "6", "7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableG.setMaximumSize(new java.awt.Dimension(1412, 304));
        jTableG.setPreferredSize(new java.awt.Dimension(1412, 304));
        jTableG.setShowHorizontalLines(false);
        jTableG.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTableG);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTableI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Año", "2001", null, null, null, null, null, null},
                {"Mes", "Noviembre", null, null, null, null, null, null},
                {"Superficie", "300", null, null, null, null, null, null},
                {"Fachada", "2", null, null, null, null, null, null},
                {"Est/Cubierta", "2", null, null, null, null, null, null},
                {"Pisos", "22", null, null, null, null, null, null},
                {"Muros interiores", "22", null, null, null, null, null, null},
                {"Cocina", "22", null, null, null, null, null, null},
                {"Baños", "2", null, null, null, null, null, null},
                {"Instalaciones", "222", null, null, null, null, null, null},
                {"Carpintería", "222", null, null, null, null, null, null},
                {"Puntos", null, null, null, null, null, null, null},
                {"APC", null, null, null, null, null, null, null},
                {"APC Comentario", null, null, null, null, null, null, null},
                {"Tarifa", null, null, null, null, null, null, null},
                {"T", null, null, null, null, null, null, null},
                {"Acti", null, null, null, null, null, null, null}
            },
            new String [] {
                "Industria", "1", "2", "3", "4", "5", "6", "7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableI.setMaximumSize(new java.awt.Dimension(1412, 272));
        jTableI.setPreferredSize(new java.awt.Dimension(1412, 272));
        jScrollPane3.setViewportView(jTableI);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Año", null, null, null, null, null, null},
                {"Mes", null, null, null, null, null, null},
                {"Superficie", null, null, null, null, null, null},
                {"Fachada", null, null, null, null, null, null},
                {"Est/Cubierta", null, null, null, null, null, null},
                {"Pisos", null, null, null, null, null, null},
                {"Muros interiores", null, null, null, null, null, null},
                {"Cielorrasos", null, null, null, null, null, null},
                {"Ornamentación", null, null, null, null, null, null},
                {"Vidrieras/Iluminacion", null, null, null, null, null, null},
                {"Comedor", null, null, null, null, null, null},
                {"Baños", null, null, null, null, null, null},
                {"Instalaciones", null, null, null, null, null, null},
                {"Carpintería", null, null, null, null, null, null},
                {"Puntos", null, null, null, null, null, null},
                {"APC", null, null, null, null, null, null},
                {"APC Comentario", null, null, null, null, null, null},
                {"Tarifa", null, null, null, null, null, null},
                {"Tipo", null, null, null, null, null, null},
                {"Actividad", null, null, null, null, null, null}
            },
            new String [] {
                "Comercio", "2", "3", "4", "5", "6", "7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableC.setMaximumSize(new java.awt.Dimension(1412, 320));
        jTableC.setPreferredSize(new java.awt.Dimension(1412, 320));
        jScrollPane4.setViewportView(jTableC);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1412, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1412, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jScrollPane1.setViewportView(jPanel4);

        jButtonSave.setText("Guardar");

        jButtonCancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastreBlock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastreBlock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastreBlock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastreBlock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogCadastreBlock dialog = new JDialogCadastreBlock(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    protected javax.swing.JButton jButtonCancel;
    protected javax.swing.JButton jButtonSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    protected javax.swing.JTable jTableC;
    protected javax.swing.JTable jTableG;
    protected javax.swing.JTable jTableI;
    // End of variables declaration                   
}
