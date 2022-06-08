package vistas;

import DAO.CursoDAO;
import DAO.NivelDAO;
import DTO.CursoDTO;
import DTO.NivelDTO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utilidades.ErrorDB;

/**
 *
 * @author PABLO
 */
public class FrmCursos extends javax.swing.JInternalFrame {

    private static FrmCursos instancia;
    private final CursoDAO cursoDAO = new CursoDAO();
    private CursoDTO curso = new CursoDTO();
    private NivelDAO nivelDAO = new NivelDAO();
    private NivelDTO nivel = new NivelDTO();

    public FrmCursos() {
        initComponents();
        try {
            rellenarComboNiveles();
            rellenarComboBusqueda();
            cargarTabla(cursoDAO.listar());
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar los datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);

        }
    }

    public static FrmCursos getInstancia() {
        if (instancia == null) {
            instancia = new FrmCursos();
        }
        return instancia;
    }

    public static boolean isInstanciado() {
        return instancia != null;
    }

    private void activarControles() {
        this.cbNiveles.setEnabled(true);
        this.cbEstado.setEnabled(true);
        this.cbJornadas.setEnabled(true);
        this.txtParalelo.setEnabled(true);
        this.txtCapacidad.setEnabled(true);
        this.btnGuardar.setEnabled(true);
    }

    private void desactivarControles() {

        this.cbNiveles.setEnabled(false);
        this.cbEstado.setEnabled(false);
        this.cbJornadas.setEnabled(false);
        this.txtParalelo.setEnabled(false);
        this.txtCapacidad.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
    }

    private void limpiarControles() {
        this.txtCapacidad.setText("");
        this.txtParalelo.setText("");
    }

    private boolean validarCampos() {
        if (this.txtCapacidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la capacidad del curso", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCapacidad.requestFocus();
        } else if (this.txtParalelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el paralelo del curso", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtParalelo.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private void cargarTabla(List<CursoDTO> lista) {

        String columnas[] = new String[]{"ID", "DESCRIPCION", "PARALELO", "JORNADA", "CAPACIDAD"};

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.setColumnIdentifiers(columnas);

        for (CursoDTO lista1 : lista) {
            String fila[] = new String[columnas.length];

            fila[0] = String.valueOf(lista1.getIdCurso());
            fila[1] = lista1.getDescripcion();
            fila[2] = lista1.getParalelo();
            fila[3] = lista1.getJornada();
            fila[4] = String.valueOf(lista1.getCapacidad());
            modelo.addRow(fila);

        }
        this.tabla.setModel(modelo);
        columnasTamaño(this.tabla);
    }

    private void columnasTamaño(JTable tabla) {
        tabla.getColumnModel().getColumn(1).setPreferredWidth(280);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(130);

    }

    private void insertar() {
        if (validarCampos()) {

            nivel = (NivelDTO) this.cbNiveles.getSelectedItem();
            curso.setIdNivel(nivel.getIdNivel());

            curso.setCapacidad(Integer.parseInt(this.txtCapacidad.getText()));
            curso.setParalelo(this.txtParalelo.getText());
            curso.setJornada(this.cbJornadas.getSelectedItem().toString());
            curso.setEstado(this.cbEstado.getSelectedItem().toString());

            try {
                int res = cursoDAO.guardar(curso);
                if (res == 1) {
                    limpiarControles();
                    desactivarControles();
                    cargarTabla(cursoDAO.listar());
                    JOptionPane.showMessageDialog(this, "Datos registrados correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar los datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar() {
        int fila = this.tabla.getSelectedRow();
        if (fila >= 0) {
            try {
                int id = Integer.parseInt(this.tabla.getValueAt(fila, 0).toString());
                int opc = JOptionPane.showConfirmDialog(this, "Desea eliminar el curso", "Sistema de gestion escolar", JOptionPane.YES_NO_OPTION);

                if (opc == JOptionPane.YES_OPTION) {
                    if (cursoDAO.eliminar(id) == 1) {
                        limpiarControles();
                        desactivarControles();
                        this.btnEliminar.setEnabled(false);
                        cargarTabla(cursoDAO.listar());
                        JOptionPane.showMessageDialog(this, "Curso eliminado correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void rellenarComboNiveles() throws ErrorDB {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<NivelDTO> listado = nivelDAO.listar();

        for (int i = 0; i < listado.size(); i++) {
            modelo.addElement(listado.get(i));
        }
        this.cbNiveles.setModel(modelo);
    }

    private void rellenarComboBusqueda() throws ErrorDB {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<NivelDTO> listado = nivelDAO.listar();

        modelo.addElement("MOSTRAR TODOS");
        for (int i = 0; i < listado.size(); i++) {
            modelo.addElement(listado.get(i));
        }
        this.cbBusqueda.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbJornadas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbNiveles = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtParalelo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbBusqueda = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion de cursos");
        setToolTipText("");
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        cbJornadas.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbJornadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MATUTINO", "VESPERTINO" }));
        cbJornadas.setEnabled(false);
        cbJornadas.setPreferredSize(new java.awt.Dimension(59, 23));
        cbJornadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJornadasActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Nivel");
        jLabel4.setPreferredSize(new java.awt.Dimension(130, 24));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Jornada");
        jLabel5.setPreferredSize(new java.awt.Dimension(130, 24));

        cbNiveles.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbNiveles.setEnabled(false);
        cbNiveles.setPreferredSize(new java.awt.Dimension(59, 23));
        cbNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNivelesActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Estado");
        jLabel8.setPreferredSize(new java.awt.Dimension(130, 24));

        cbEstado.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbEstado.setEnabled(false);
        cbEstado.setPreferredSize(new java.awt.Dimension(59, 23));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Capacidad");
        jLabel6.setPreferredSize(new java.awt.Dimension(130, 24));

        txtCapacidad.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCapacidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCapacidad.setEnabled(false);
        txtCapacidad.setPreferredSize(new java.awt.Dimension(130, 23));
        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Paralelo");
        jLabel7.setPreferredSize(new java.awt.Dimension(130, 24));

        txtParalelo.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtParalelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtParalelo.setEnabled(false);
        txtParalelo.setPreferredSize(new java.awt.Dimension(130, 23));
        txtParalelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParaleloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, 242, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNiveles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbJornadas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCapacidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParalelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtParalelo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbJornadas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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

        btnEliminar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Registro de cursos");
        jLabel3.setPreferredSize(new java.awt.Dimension(130, 24));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Filtrar");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 22));

        cbBusqueda.setPreferredSize(new java.awt.Dimension(56, 22));
        cbBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbBusquedaFocusGained(evt);
            }
        });
        cbBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbBusquedaMouseClicked(evt);
            }
        });
        cbBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbJornadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJornadasActionPerformed

    }//GEN-LAST:event_cbJornadasActionPerformed

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        try {
            if (evt.getClickCount() > 1) {
                curso = cursoDAO.conseguir(Integer.parseInt(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString()));
                this.cbNiveles.setSelectedItem(nivelDAO.conseguir(curso.getIdNivel()));
                this.txtCapacidad.setText(String.valueOf(curso.getCapacidad()));
                this.txtParalelo.setText(curso.getParalelo());
                this.cbJornadas.setSelectedItem(curso.getJornada());
                this.cbEstado.setSelectedItem(curso.getEstado());
                activarControles();
                this.btnEliminar.setEnabled(true);
            }
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tablaMouseClicked

    private void cbNivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNivelesActionPerformed

    }//GEN-LAST:event_cbNivelesActionPerformed

    private void txtParaleloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParaleloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParaleloActionPerformed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        instancia = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarControles();
        activarControles();
        curso.setIdCurso(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cbBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbBusquedaFocusGained

    }//GEN-LAST:event_cbBusquedaFocusGained

    private void cbBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBusquedaMouseClicked

    }//GEN-LAST:event_cbBusquedaMouseClicked

    private void cbBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBusquedaActionPerformed
        try {
            if (this.cbBusqueda.getSelectedIndex() > 0) {
                nivel = (NivelDTO) this.cbBusqueda.getSelectedItem();
                cargarTabla(cursoDAO.listar("ID_NIVEL", String.valueOf(nivel.getIdNivel())));

            } else {
                cargarTabla(cursoDAO.listar());
            }
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar los datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cbBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbBusqueda;
    public javax.swing.JComboBox<String> cbEstado;
    public javax.swing.JComboBox<String> cbJornadas;
    public javax.swing.JComboBox<String> cbNiveles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField txtCapacidad;
    public javax.swing.JTextField txtParalelo;
    // End of variables declaration//GEN-END:variables
}
