package vistas;

import DAO.PeriodoDAO;
import DTO.PeriodoDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utilidades.ErrorDB;

/**
 *
 * @author PABLO
 */
public class FrmPeriodos extends javax.swing.JInternalFrame {

    private static FrmPeriodos instancia;
    private final PeriodoDAO periodoDAO = new PeriodoDAO();
    private PeriodoDTO periodo = new PeriodoDTO();

    public FrmPeriodos() {
        initComponents();
        try {
            cargarTabla(periodoDAO.listar());
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static FrmPeriodos getInstancia() {
        if (instancia == null) {
            instancia = new FrmPeriodos();
        }
        return instancia;
    }

    public static boolean isInstanciado() {
        return instancia != null;
    }

    private void activarControles() {
        this.txtDescripcion.setEnabled(true);
        this.fechaInicio.setEnabled(true);
        this.fechaFin.setEnabled(true);
        this.txtDescripcion.requestFocus();
        this.btnGuardar.setEnabled(true);
       
    }

    private void desactivarControles() {
        this.txtDescripcion.setEnabled(false);
        this.fechaInicio.setEnabled(false);
        this.fechaFin.setEnabled(false);
        this.btnActivar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
    }

    private void limpiarControles() {
        this.txtDescripcion.setText("");
        this.fechaInicio.setDate(null);
        this.fechaFin.setDate(null);
    }

    private boolean validarCampos() {
        if (this.txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la descripcion del periodo", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtDescripcion.requestFocus();
        } else if (this.fechaInicio.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese la fecha de inicio del periodo", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else if (this.fechaFin.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese la fecha de finalizacion del periodo", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else {
            return true;
        }
        return false;
    }

    private void cargarTabla(List<PeriodoDTO> lista) {
        String columnas[] = new String[]{"ID", "DESCRIPCION", "FECHA DE INICIO", "FECHA FINALIZACION", "ESTADO"};

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.setColumnIdentifiers(columnas);

        for (PeriodoDTO lista1 : lista) {
            String fila[] = new String[columnas.length];

            fila[0] = String.valueOf(lista1.getIdPeriodo());
            fila[1] = lista1.getDescripcion();
            fila[2] = lista1.getFechaInicio();
            fila[3] = lista1.getFechaFin();
            fila[4] = lista1.getEstado();
            modelo.addRow(fila);
        }
        this.tabla.setModel(modelo);
        columnasTamaño(this.tabla);
    }

    private void columnasTamaño(JTable tabla) {
        for (int i = 1; i <= tabla.getColumnCount() - 1; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(185);
        }
    }

    private void insertar() {
        if (validarCampos()) {

            int anioInicio = this.fechaInicio.getCalendar().get(Calendar.YEAR);
            int anioFin = this.fechaFin.getCalendar().get(Calendar.YEAR);

            if (anioFin == anioInicio || anioFin < anioInicio) {
                JOptionPane.showMessageDialog(this, "El año de finalizacion debe ser superior al año de inicio", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);

            } else {
                periodo.setDescripcion(this.txtDescripcion.getText());
                periodo.setFechaInicio(new SimpleDateFormat("yyyy/MM/dd").format(this.fechaInicio.getDate()));
                periodo.setFechaFin(new SimpleDateFormat("yyyy/MM/dd").format(this.fechaFin.getDate()));
                periodo.setEstado(this.txtEstado.getText());

                try {
                    int res = periodoDAO.guardar(periodo);
                    if (res == 1) {
                        limpiarControles();
                        desactivarControles();
                        cargarTabla(periodoDAO.listar());
                        JOptionPane.showMessageDialog(this, "Datos del periodo registrados correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ErrorDB ex) {
                    JOptionPane.showMessageDialog(this, "Error al registrar datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void activar() {
        int fila = this.tabla.getSelectedRow();
        if (fila >= 0) {
            try {
                int id = Integer.parseInt(this.tabla.getValueAt(fila, 0).toString());
                int opc = JOptionPane.showConfirmDialog(this, "Seleccionar como periodo actual de trabajo", "Sistema de gestion escolar", JOptionPane.YES_NO_OPTION);

                if (opc == JOptionPane.YES_OPTION) {
                    if (periodoDAO.activar(id) >= 1) {
                        limpiarControles();
                        desactivarControles();
                        cargarTabla(periodoDAO.listar());
                        JOptionPane.showMessageDialog(this, "Periodo seleccionado", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un periodo", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        txtDescripcion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion de periodos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Descripcion");
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 24));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Fecha de Inicio ");
        jLabel7.setPreferredSize(new java.awt.Dimension(130, 23));

        fechaInicio.setDateFormatString("yyyy/MM/dd");
        fechaInicio.setEnabled(false);
        fechaInicio.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        fechaInicio.setPreferredSize(new java.awt.Dimension(0, 22));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Fecha de Finalizacion ");
        jLabel8.setPreferredSize(new java.awt.Dimension(130, 23));

        fechaFin.setDateFormatString("yyyy/MM/dd");
        fechaFin.setEnabled(false);
        fechaFin.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        fechaFin.setPreferredSize(new java.awt.Dimension(22, 22));

        txtDescripcion.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDescripcion.setEnabled(false);
        txtDescripcion.setPreferredSize(new java.awt.Dimension(130, 24));
        txtDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDescripcionMouseClicked(evt);
            }
        });
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Estado");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 24));

        txtEstado.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtEstado.setText("INACTIVO");
        txtEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEstado.setEnabled(false);
        txtEstado.setPreferredSize(new java.awt.Dimension(130, 24));
        txtEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEstadoMouseClicked(evt);
            }
        });
        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });
        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(fechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        tabla.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setRowHeight(22);
        tabla.setSelectionBackground(new java.awt.Color(61, 212, 225));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        btnCerrar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnActivar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar.png"))); // NOI18N
        btnActivar.setText("Activar");
        btnActivar.setEnabled(false);
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Registro de periodo");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        instancia = null;
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        activar();
    }//GEN-LAST:event_btnActivarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() > 1) {
            try {
                periodo = periodoDAO.conseguir(Integer.parseInt(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString()));
                this.txtDescripcion.setText(periodo.getDescripcion());
                this.fechaInicio.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getFechaInicio()));
                this.fechaFin.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getFechaFin()));
                this.txtEstado.setText(periodo.getEstado());
                this.btnActivar.setEnabled(true);
                activarControles();
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error en la fecha " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        instancia = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarControles();
        activarControles();
        this.txtEstado.setText("INACTIVO");
        periodo.setIdPeriodo(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtDescripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDescripcionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionMouseClicked

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        if (this.txtDescripcion.getText().length() > 30) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoMouseClicked

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActivar;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public com.toedter.calendar.JDateChooser fechaFin;
    public com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}
