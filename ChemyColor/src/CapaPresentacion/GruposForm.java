/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacion;

import CapaDatos.*;
import CapaNegocios.GruposJpaController;
import CapaNegocios.LineasJpaController;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amaya
 */
public class GruposForm extends javax.swing.JFrame {
    CapaNegocios.GruposJpaController tabla = new CapaNegocios.GruposJpaController(entityMain.getInstance());
    LineasJpaController Control_Lineas = new LineasJpaController(entityMain.getInstance());
    GruposJpaController Control_Grupos = new GruposJpaController(entityMain.getInstance());
    
    boolean estado = true;
    
    /**
     * Creates new form GruposForm
     */
    public GruposForm() {
        initComponents();
        verTabla();
        Llenar_Tabla();
        verTablaSub();
        Llenar_TablaSub();
        Llenar_Combo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtGrupo = new javax.swing.JTextField();
        txtComision = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGrupos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        txtSub = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSub = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtIds = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setText("Grupos");

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtGrupo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        txtComision.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Comisión");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Grupo:");

        tablaGrupos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaGrupos);

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton2.setText("Nuevo");

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton3.setText("Editar");

        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton4.setText("Eliminar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton5.setText("Guardar");

        txtSub.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Sub-grupo:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Grupo:");

        tablaSub.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaSub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaSub);

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton6.setText("Nuevo");

        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton7.setText("Editar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton8.setText("Eliminar");

        jCombo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("ID");

        txtIds.setEditable(false);
        txtIds.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIds, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSub)
                        .addComponent(jCombo, 0, 109, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtIds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel6.setText("Sub-Grupos");

        jButton9.setText("Salir");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    
    private void Guardar() {
        if (txtGrupo.getText().length() != 0) {
            String grupo = this.txtGrupo.getText();
            String comi = this.txtComision.getText();
             double dnum =Double.parseDouble(comi) ;
            GruposJpaController t = new GruposJpaController(entityMain.getInstance());
            Grupos tp = new Grupos();
            tp.setNombregrupo(grupo);
            tp.setComision(BigDecimal.valueOf(dnum));

            try {
                t.create(tp);
                Llenar_Tabla();
                JOptionPane.showMessageDialog(null, "Datos registrados correctamente");
               // Llenar_Tabla();
                txtGrupo.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un error. " + e.toString(),
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha ingresado el tipo de proveedor.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static DefaultTableModel tbpro;

    private void verTabla() {
        try {
            tbpro = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Grupo", "Comision"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                        java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false
                };

                @Override
                public Class getColumnClass(int columbIndex) {
                    return types[columbIndex];
                }

                @Override
                public boolean isCellEditable(int rowIdex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tablaGrupos.setModel(tbpro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error");
        }
    }
     private void Llenar_Tabla() {

        try {

            Object A[] = null;
            List<Grupos> ListaGrupos;
            ListaGrupos = tabla.findGruposEntities();
            for (int i = 0; i < ListaGrupos.size(); i++) {
                tbpro.addRow(A);
                tbpro.setValueAt(ListaGrupos.get(i).getCodigogrupo(), i, 0);
                tbpro.setValueAt(ListaGrupos.get(i).getNombregrupo(), i, 1);
                tbpro.setValueAt(ListaGrupos.get(i).getComision(), i, 2);
            
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.toString());

        }

     }
    
     
     //PARA SUB GRUPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
     
      public void GuardarSub() {
        String subGrupo = this.txtSub.getText();
       
        
        int tipoProv = EncontrarGrupo(this.jCombo.getSelectedItem().toString());
   
        Lineas type = new Lineas();
        
        LineasJpaController t = new LineasJpaController(entityMain.getInstance());
        CapaDatos.Lineas pro = new CapaDatos.Lineas();
        BigDecimal idNewSubGrupo = t.findidNewSubGrupo();

        type.setCodigolinea(BigDecimal.valueOf(tipoProv));
        type.setCodigolinea(BigDecimal.valueOf(Double.parseDouble(this.jCombo.getSelectedItem().toString())));
        
        int idG = 1;
        BigDecimal idGrupo = BigDecimal.valueOf(idG);
        CapaDatos.Grupos g = new CapaDatos.Grupos();
        g.setCodigogrupo(idGrupo);
        
        pro.setCodigolinea(idNewSubGrupo);
        pro.setCodigogrupo(g);
        pro.setNombrelineas(subGrupo);
        
      

        if (estado == true) {
           
        } else {
            int idP = Integer.parseInt(this.txtIds.getText());
            pro.setCodigolinea(BigDecimal.valueOf(idP));

            try {
                t.edit(pro);
               
                Llenar_TablaSub();
                JOptionPane.showMessageDialog(null, "Proveedor editado exitosamente.");
                LimpiarControles();
                estado = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un error al editar. " + e.toString());
            }
        }

    }

   

    private void EliminarSub() {

        try {
            int indice = this.tablaSub.getSelectedRow();
            int codigoP = Integer.parseInt(tablaSub.getModel().getValueAt(indice, 0).toString());
            String proveedor = tablaSub.getModel().getValueAt(indice, 1).toString();
            LineasJpaController t = new LineasJpaController(entityMain.getInstance());
           

            int r = JOptionPane.showConfirmDialog(null, "¿Desea eliminar a " + proveedor + "?", "Acción de Eliminar",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                //Primero eliminamos los telefonos
              
                
                try {
                   
                } catch (Exception e){
                    
                }
                //Luego eliminamos el proveedor
                try {
                    t.destroy(BigDecimal.valueOf(codigoP));
                    verTablaSub();
                    Llenar_TablaSub();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito.");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar." + e.toString());
                }

            } else if (r == JOptionPane.NO_OPTION) {

            } else if (r == JOptionPane.CLOSED_OPTION) {

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Es necesario primero seleccionar una fila para eliminar.");
            estado = true;
        }
    }

    private void EditarSub() {
        try {
            int indice = this.tablaSub.getSelectedRow();
            LimpiarControles();
            this.txtIds.setText(tablaSub.getModel().getValueAt(indice, 0).toString());
            this.txtSub.setText(tablaSub.getModel().getValueAt(indice, 1).toString());
            this.jCombo.setSelectedItem(tablaSub.getModel().getValueAt(indice, 2).toString());
            
            estado = false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Es necesario primero seleccionar una fila para editar.");
            estado = true;
        }
    }

    private int EncontrarGrupo(String prov) {
        int retorno = 0;
        GruposJpaController t = new GruposJpaController(entityMain.getInstance());
        CapaDatos.Grupos pro = new CapaDatos.Grupos();
        double num = Double.parseDouble(prov);
        try {
            pro = t.findGrupos(BigDecimal.valueOf(num));
            retorno = Integer.parseInt(pro.getCodigogrupo().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al encontrar el id del proveedor. " + e.toString());
        }

        return retorno;
    }

   

    DefaultComboBoxModel dc = new DefaultComboBoxModel();

    private void Llenar_Combo()
          {
              jCombo.setModel(dc);
              List<Grupos> ListaGrupos;
            ListaGrupos = Control_Grupos.findGruposEntities();
              for (int i = 0; i < ListaGrupos.size(); i++) {
                  dc.addElement(ListaGrupos.get(i).getNombregrupo()+"");
              }
          }

    public void LimpiarControles() {
        this.txtIds.setText("");
        this.txtSub.setText("");
        //this.txtCtaPorPagar.setText("");
        
    }

    public static DefaultTableModel tbproS;

     private void verTablaSub() {
        try {
            tbproS = (new DefaultTableModel(
                    null, new String[]{
                        "Codigo_Linea", "Codigo_Grupo", "SubGrupo"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                        java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false
                };

                @Override
                public Class getColumnClass(int columbIndex) {
                    return types[columbIndex];
                }

                @Override
                public boolean isCellEditable(int rowIdex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tablaSub.setModel(tbproS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error");
        }
    }
     private void Llenar_TablaSub() {

        try {

            Object B[] = null;
            List<Lineas> ListaLineas;
            ListaLineas = Control_Lineas.findLineasEntities();
            for (int i = 0; i < ListaLineas.size(); i++) {
                tbproS.addRow(B);
                tbproS.setValueAt(ListaLineas.get(i).getCodigolinea(), i, 0);
                tbproS.setValueAt(ListaLineas.get(i).getCodigogrupo().getNombregrupo(), i, 1);
                tbproS.setValueAt(ListaLineas.get(i).getNombrelineas(), i, 2);
            
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Murio");

        }
     }
     
     
     
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
            java.util.logging.Logger.getLogger(GruposForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GruposForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GruposForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GruposForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GruposForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaGrupos;
    private javax.swing.JTable tablaSub;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtIds;
    private javax.swing.JTextField txtSub;
    // End of variables declaration//GEN-END:variables
}
