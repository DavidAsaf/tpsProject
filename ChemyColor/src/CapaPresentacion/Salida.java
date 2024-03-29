/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacion;

import CapaNegocios.ArticulosJpaController;
import CapaNegocios.BodegasJpaController;
import CapaNegocios.HistorialfacturasJpaController;
import CapaNegocios.ProveedoresJpaController;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author gerso
 */
public class Salida extends javax.swing.JFrame {

    BodegasJpaController bod = new BodegasJpaController(entityMain.getInstance());

    /**
     * Creates new form Salida
     */
    public Salida() {
        initComponents();
        llenarComboBodegas();
        AutoCompleteDecorator.decorate(comboBodega);
        AutoCompleteDecorator.decorate(comboProductos);
        this.comboDias.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        comboBodega = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rbContado = new javax.swing.JRadioButton();
        rbCredito = new javax.swing.JRadioButton();
        lblCantDias = new javax.swing.JLabel();
        comboDias = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtCostoTotal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Salida de productos");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Nº de Factura:");

        txtNumFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumFacturaActionPerformed(evt);
            }
        });

        jLabel16.setText("Fecha");

        txtFecha.setDateFormatString("dd-MM-yyyy");

        jLabel14.setText("Buscar en bodega:");

        comboBodega.setEditable(true);
        comboBodega.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBodegaItemStateChanged(evt);
            }
        });
        comboBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboBodegaKeyReleased(evt);
            }
        });

        jLabel15.setText("Producto a cargar:");

        comboProductos.setEditable(true);
        comboProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProductosItemStateChanged(evt);
            }
        });

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        jLabel12.setText("Existencia:");

        txtExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExistenciaActionPerformed(evt);
            }
        });
        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyReleased(evt);
            }
        });

        jLabel10.setText("Precio unitario:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboBodega, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNumFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(rbContado);
        rbContado.setSelected(true);
        rbContado.setText("Al contado");

        buttonGroup1.add(rbCredito);
        rbCredito.setText("Al credito");
        rbCredito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbCreditoItemStateChanged(evt);
            }
        });

        lblCantDias.setText("Cantidad de días ");

        comboDias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15", "30", "60", "90" }));
        comboDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDiasActionPerformed(evt);
            }
        });

        jLabel13.setText("Forma de pago: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbContado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantDias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCredito)
                    .addComponent(lblCantDias)
                    .addComponent(rbContado)
                    .addComponent(comboDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setText("Cantidad:");

        jLabel11.setText("Costo Total:");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        txtCostoTotal.setEditable(false);

        jLabel18.setText("Cliente:");

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(333, 333, 333))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(375, 375, 375))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumFacturaActionPerformed

    private void comboBodegaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBodegaItemStateChanged
        String bodega = this.comboBodega.getSelectedItem().toString();
        BigDecimal idBodega;
        BodegasJpaController b = new BodegasJpaController(entityMain.getInstance());

        try {
            idBodega = b.findIdBodega(bodega);
            llenarComboProductos(idBodega);
            this.comboProductos.setEnabled(true);
        } catch (Exception e) {
            //System.out.println("Error al llenar combo de productos." + e.toString());
            this.comboProductos.setEnabled(false);
        }
    }//GEN-LAST:event_comboBodegaItemStateChanged

    private void llenarComboProductos(BigDecimal bodega) {
        //this.comboProductos.removeAllItems();
        ArticulosJpaController art = new ArticulosJpaController(entityMain.getInstance());
        String val = bodega.toString();
        art.fillCombo(this.comboProductos, val);
        this.comboProductos.setSelectedIndex(0);
        precioArticulo();
    }

    DefaultComboBoxModel dc = new DefaultComboBoxModel();

    private void llenarComboBodegas() {
        comboBodega.setModel(dc);
        List<CapaDatos.Bodegas> Listatipo;
        Listatipo = bod.findBodegasEntities();
        for (int i = 0; i < Listatipo.size(); i++) {
            dc.addElement(Listatipo.get(i).getNombrebodega());
        }
    }

    private void comboBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboBodegaKeyReleased

    }//GEN-LAST:event_comboBodegaKeyReleased

    private void comboProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProductosItemStateChanged

    }//GEN-LAST:event_comboProductosItemStateChanged

    private void precioArticulo() {
        ArticulosJpaController a = new ArticulosJpaController(entityMain.getInstance());
        BodegasJpaController b = new BodegasJpaController(entityMain.getInstance());

        try {
            int art = a.findIdArticulos(this.comboProductos.getSelectedItem().toString());
            int bod = b.findIdBodegaInteger(this.comboBodega.getSelectedItem().toString());

            Double precio = a.findPrecioArticulo(art, bod);
            int existencia = a.findExistenciaArticulo(art, bod);

            this.txtPrecio.setText(String.valueOf(precio));
            this.txtExistencia.setText(String.valueOf(existencia));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error. " + e.toString());
        }
    }

    private void rbCreditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbCreditoItemStateChanged
        if (rbCredito.isSelected()) {
            this.comboDias.setEnabled(true);
        } else {
            this.comboDias.setEnabled(false);
        }
    }//GEN-LAST:event_rbCreditoItemStateChanged

    private void Calculo() {
        try {
            float v1 = Float.parseFloat(this.txtCantidad.getText());
            float v2 = Float.parseFloat(this.txtPrecio.getText());
            float total = v2 * v1;
            this.txtCostoTotal.setText("$ " + String.valueOf(total));
        } catch (Exception e) {

        }
    }


    private void comboDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDiasActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        this.txtCostoTotal.setText("");
        Calculo();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        Calculo();
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtExistenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExistenciaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean est = Validaciones();

        if (est == true) {
            try {
                int existencia = Integer.parseInt(this.txtExistencia.getText().toString());
                int cantidad = Integer.parseInt(this.txtCantidad.getText().toString());

                if (cantidad <= existencia) {
                    Guardar();
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad de salida no puede ser mayor que la existencia.");
                }
            } catch (Exception e) {
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Uno de los campos requeridos está vacío.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean Validaciones() {
        boolean retorno = false;

        if (this.txtNumFactura.getText().toString().length() != 0 || this.txtCantidad.getText().toString().length() != 0
                || this.txtCliente.getText().toString().length() != 0 || this.txtFecha.getDate().toString().length() != 0) {
            retorno = true;
        }

        return retorno;
    }

    private void Guardar() {

        BodegasJpaController b = new BodegasJpaController(entityMain.getInstance());
        ArticulosJpaController a = new ArticulosJpaController(entityMain.getInstance());
        HistorialfacturasJpaController h = new HistorialfacturasJpaController(entityMain.getInstance());
        String numFactura = this.txtNumFactura.getText();
        int estadoCompra = 0;
        if (rbCredito.isSelected() == true) {
            estadoCompra = 1;
        }
        int cantidadDias = 0;

        if (rbCredito.isSelected() == true) {
            cantidadDias = Integer.parseInt(this.comboDias.getSelectedItem().toString());
        }

        int idBodega = b.findIdBodegaInteger(this.comboBodega.getSelectedItem().toString());
        int articulo = a.findIdArticulos(this.comboProductos.getSelectedItem().toString());
        Date fecha = txtFecha.getDate();
        int cantidad = Integer.parseInt(this.txtCantidad.getText());
        String cliente = this.txtCliente.getText().toString();

        try {
            int codigo = h.findNextId();
            h.SalidaFactura(codigo, numFactura, estadoCompra, cantidadDias, idBodega, articulo, fecha, cantidad, cliente);

            JOptionPane.showMessageDialog(null, "Salida guardada con éxito. ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error. " + e.toString());
        }

        /*int codigo, String numFactura, int contOcredit, int cantDiasCredito, int codigoBodega,
            int codigoArticulo, Date fecha, int oUnidad, String cliente*/
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
            java.util.logging.Logger.getLogger(Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBodega;
    private javax.swing.JComboBox comboDias;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblCantDias;
    private javax.swing.JRadioButton rbContado;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtExistencia;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
