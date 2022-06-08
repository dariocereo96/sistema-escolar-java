/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import DAO.CursoDAO;
import DAO.MatriculaDAO;
import DAO.NivelDAO;
import DAO.NotaDAO;
import DAO.PeriodoDAO;
import DTO.CursoDTO;
import DTO.MateriaDTO;
import DTO.MatriculaDTO;
import DTO.NotaDTO;
import DTO.PeriodoDTO;
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
public class FrmNotas extends javax.swing.JInternalFrame {

    private static FrmNotas instancia;
    private final NotaDAO notaDAO = new NotaDAO();
    private NotaDTO nota = new NotaDTO();

    public FrmNotas() {
        initComponents();
        try {
            rellenarComboCursos();
            rellenarComboPeriodos();
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static FrmNotas getInstancia() {
        if (instancia == null) {
            instancia = new FrmNotas();
        }
        return instancia;
    }

    public static boolean isInstanciado() {
        return instancia != null;
    }

    private void activarControles() {
        this.txtP1.setEnabled(true);
        this.txtP2.setEnabled(true);
        this.txtP3.setEnabled(true);
        this.btnGuardar.setEnabled(true);

        this.txtP1Q.setEnabled(true);
        this.txtP2Q.setEnabled(true);
        this.txtP3Q.setEnabled(true);

        this.cbCursos.setEnabled(true);
        this.cbMaterias.setEnabled(true);
        this.cbPeriodos.setEnabled(true);
    }

    private void desactivarControles() {
        this.txtP1.setEnabled(false);
        this.txtP2.setEnabled(false);
        this.txtP3.setEnabled(false);
        this.btnGuardar.setEnabled(false);

        this.txtP1Q.setEnabled(false);
        this.txtP2Q.setEnabled(false);
        this.txtP3Q.setEnabled(false);

        this.cbCursos.setEnabled(false);
        this.cbMaterias.setEnabled(false);
        this.cbPeriodos.setEnabled(false);
    }

    private void limpiarControles() {
        this.txtP1.setText("");
        this.txtP2.setText("");
        this.txtP3.setText("");

        this.txtP1Q.setText("");
        this.txtP2Q.setText("");
        this.txtP3Q.setText("");
    }

    private void rellenarComboMaterias(int idNivel) {
        try {
            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            List<MateriaDTO> listado = new NivelDAO().conseguirPensum(idNivel).getMaterias();

            for (int i = 0; i < listado.size(); i++) {
                modelo.addElement(listado.get(i));
            }
            this.cbMaterias.setModel(modelo);

        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rellenarComboPeriodos() throws ErrorDB {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<PeriodoDTO> listado = new PeriodoDAO().listar();

        for (int i = 0; i < listado.size(); i++) {
            modelo.addElement(listado.get(i));
        }
        this.cbPeriodos.setModel(modelo);
    }

    private void rellenarComboCursos() throws ErrorDB {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<CursoDTO> listado = new CursoDAO().listar();

        modelo.addElement("SELECCIONE UN CURSO");
        for (int i = 0; i < listado.size(); i++) {
            modelo.addElement(listado.get(i));
        }
        this.cbCursos.setModel(modelo);
    }

    private boolean validarCampos() {
        if (this.tabla.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else if (this.cbCursos.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else if (this.txtP1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese nota del primer parcial", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else {
            return true;
        }
        return false;
    }

    private void cargarTabla(List<MatriculaDTO> lista) {

        String columnas[] = new String[]{"N°", "NOMBRES", "CURSO", "PERIODO", "FECHA MATRICULA"};

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.setColumnIdentifiers(columnas);

        for (MatriculaDTO lista1 : lista) {
            String fila[] = new String[columnas.length];

            fila[0] = String.valueOf(lista1.getIdMatricula());
            fila[1] = lista1.getNombreCompletos();
            fila[2] = lista1.getCurso();
            fila[3] = lista1.getPeriodo();
            fila[4] = lista1.getFechaMatricula();
            modelo.addRow(fila);

        }
        this.tabla.setModel(modelo);
        columnasTamaño(tabla);

    }

    private void columnasTamaño(JTable tabla) {
        for (int i = 1; i <= tabla.getColumnCount() - 1; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(240);
        }
    }

    private void insertar() {

        if (validarCampos()) {
            nota = new NotaDTO();
            CursoDTO curso = (CursoDTO) this.cbCursos.getSelectedItem();
            PeriodoDTO periodo = (PeriodoDTO) this.cbPeriodos.getSelectedItem();
            MateriaDTO materia = (MateriaDTO) this.cbMaterias.getSelectedItem();

            int codMat = Integer.parseInt(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString());

            nota.setIdMatricula(codMat);
            nota.setIdMateria(materia.getIdMateria());
            nota.setIdPeriodo(periodo.getIdPeriodo());
            nota.setNotaP1(Double.parseDouble(this.txtP1.getText()));

            if (!this.txtP2.getText().isEmpty()) {
                nota.setNotaP2(Double.parseDouble(this.txtP2.getText()));
            }

            if (!this.txtP3.getText().isEmpty()) {
                nota.setNotaP3(Double.parseDouble(this.txtP3.getText()));
            }

            if (!this.txtP1Q.getText().isEmpty()) {
                nota.setNotaP1Q(Double.parseDouble(this.txtP1Q.getText()));
            }

            if (!this.txtP2Q.getText().isEmpty()) {
                nota.setNotaP2Q(Double.parseDouble(this.txtP2Q.getText()));
            }

            if (!this.txtP3Q.getText().isEmpty()) {
                nota.setNotaP3Q(Double.parseDouble(this.txtP3Q.getText()));
            }

            try {
                int res = notaDAO.registrarNota(nota);
                if (res == 1) {
                    limpiarControles();
                    desactivarControles();
                    JOptionPane.showMessageDialog(this, "Nota registrada correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtP1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtP2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtP3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbMaterias = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbPeriodos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtP1Q = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtP2Q = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtP3Q = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Administracion de notas");
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("1° Parcial");
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP1.setEnabled(false);
        txtP1.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP1ActionPerformed(evt);
            }
        });
        txtP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP1KeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("2° Parcial");
        jLabel4.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP2.setEnabled(false);
        txtP2.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP2ActionPerformed(evt);
            }
        });
        txtP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP2KeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("3° Parcial");
        jLabel5.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP3.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP3.setEnabled(false);
        txtP3.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP3ActionPerformed(evt);
            }
        });
        txtP3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP3KeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Materia");
        jLabel6.setPreferredSize(new java.awt.Dimension(130, 24));

        cbMaterias.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbMaterias.setEnabled(false);
        cbMaterias.setPreferredSize(new java.awt.Dimension(59, 23));
        cbMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMateriasActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Periodo");
        jLabel8.setPreferredSize(new java.awt.Dimension(130, 24));

        cbPeriodos.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbPeriodos.setEnabled(false);
        cbPeriodos.setPreferredSize(new java.awt.Dimension(59, 23));
        cbPeriodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPeriodosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Curso");
        jLabel7.setPreferredSize(new java.awt.Dimension(130, 24));

        cbCursos.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbCursos.setEnabled(false);
        cbCursos.setPreferredSize(new java.awt.Dimension(59, 23));
        cbCursos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCursosFocusGained(evt);
            }
        });
        cbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursosActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Primer Quimestre");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 24));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("1° Parcial");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP1Q.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP1Q.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP1Q.setEnabled(false);
        txtP1Q.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP1Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP1QActionPerformed(evt);
            }
        });
        txtP1Q.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP1QKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("2° Parcial");
        jLabel12.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP2Q.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP2Q.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP2Q.setEnabled(false);
        txtP2Q.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP2Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP2QActionPerformed(evt);
            }
        });
        txtP2Q.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP2QKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 153));
        jLabel13.setText("3° Parcial");
        jLabel13.setPreferredSize(new java.awt.Dimension(130, 24));

        txtP3Q.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtP3Q.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtP3Q.setEnabled(false);
        txtP3Q.setPreferredSize(new java.awt.Dimension(130, 23));
        txtP3Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP3QActionPerformed(evt);
            }
        });
        txtP3Q.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtP3QKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Segundo Quimestre");
        jLabel14.setPreferredSize(new java.awt.Dimension(130, 24));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtP2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtP3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtP2Q, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtP3Q, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(txtP1Q, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbCursos, javax.swing.GroupLayout.Alignment.LEADING, 0, 322, Short.MAX_VALUE)
                        .addComponent(cbMaterias, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtP3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtP1Q, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtP2Q, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtP3Q, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Registro de notas");
        jLabel10.setPreferredSize(new java.awt.Dimension(130, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(350, 350, 350))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        instancia = null;
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarControles();
        activarControles();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        instancia = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void cbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursosActionPerformed
        if (this.cbCursos.getSelectedIndex() > 0) {
            CursoDTO curso = (CursoDTO) this.cbCursos.getSelectedItem();
            rellenarComboMaterias(curso.getIdNivel());

            rellenarComboMaterias(curso.getIdNivel());
            try {
                cargarTabla(new MatriculaDAO().listar("ID_CURSO", String.valueOf(curso.getIdCurso())));

            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbCursosActionPerformed

    private void cbMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMateriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMateriasActionPerformed

    private void txtP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP1ActionPerformed

    private void txtP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP1KeyTyped

    private void txtP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP2ActionPerformed

    private void txtP2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP2KeyTyped

    private void txtP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP3ActionPerformed

    private void txtP3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP3KeyTyped

    private void cbPeriodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeriodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPeriodosActionPerformed

    private void txtP1QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP1QActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP1QActionPerformed

    private void txtP1QKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP1QKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP1QKeyTyped

    private void txtP2QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP2QActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP2QActionPerformed

    private void txtP2QKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP2QKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP2QKeyTyped

    private void txtP3QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP3QActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP3QActionPerformed

    private void txtP3QKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtP3QKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP3QKeyTyped

    private void cbCursosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCursosFocusGained
        if (this.cbCursos.getSelectedIndex() > 0) {
            CursoDTO curso = (CursoDTO) this.cbCursos.getSelectedItem();
            rellenarComboMaterias(curso.getIdNivel());
            try {
                cargarTabla(new MatriculaDAO().listar("ID_CURSO", String.valueOf(curso.getIdCurso())));

            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbCursosFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbCursos;
    public javax.swing.JComboBox<String> cbMaterias;
    public javax.swing.JComboBox<String> cbPeriodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField txtP1;
    public javax.swing.JTextField txtP1Q;
    public javax.swing.JTextField txtP2;
    public javax.swing.JTextField txtP2Q;
    public javax.swing.JTextField txtP3;
    public javax.swing.JTextField txtP3Q;
    // End of variables declaration//GEN-END:variables
}
