/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import DAO.DocenteDAO;
import DTO.DocenteDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utilidades.ErrorDB;
import utilidades.ValidacionText;

/**
 *
 * @author PABLO
 */
public class FrmDocentes extends javax.swing.JInternalFrame {

    private static FrmDocentes instancia;
    private final DocenteDAO docenteDAO = new DocenteDAO();
    private DocenteDTO docente = new DocenteDTO();

    public FrmDocentes() {
        initComponents();
         try {
            cargarTabla(docenteDAO.listar());
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(rootPane, "No se pudo cargar los datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static FrmDocentes getInstancia() {
        if (instancia == null) {
            instancia = new FrmDocentes();
        }
        return instancia;
    }

    public static boolean isInstanciado() {
        return instancia != null;
    }

    private void activarControles() {
        this.txtNom.setEnabled(true);
        this.txtNom.requestFocus();
        this.txtApellMat.setEnabled(true);
        this.txtApellPat.setEnabled(true);
        this.txtCedula.setEnabled(true);
        this.txtTelefono.setEnabled(true);
        this.txtDireccion.setEnabled(true);
        this.txtTitulo.setEnabled(true);
        this.txtAnterior.setEnabled(true);
        this.txtCorreo.setEnabled(true);
        this.txtProvincia.setEnabled(true);
        this.txtCanton.setEnabled(true);
        this.txtParroquia.setEnabled(true);
        this.txtDireccion.setEnabled(true);
        this.cbEstado.setEnabled(true);
        this.cbEstadoCivil.setEnabled(true);
        this.cbGenero.setEnabled(true);
        this.cbTipoContrato.setEnabled(true);
        this.fechaNac.setEnabled(true);
        this.fechaIngreso.setEnabled(true);
        this.btnGuardar.setEnabled(true);
    }

    private void desactivarControles() {
        this.txtNom.setEnabled(false);
        this.txtApellMat.setEnabled(false);
        this.txtApellPat.setEnabled(false);
        this.txtCedula.setEnabled(false);
        this.txtTelefono.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.txtTitulo.setEnabled(false);
        this.txtAnterior.setEnabled(false);
        this.txtCorreo.setEnabled(false);
        this.txtProvincia.setEnabled(false);
        this.txtCanton.setEnabled(false);
        this.txtParroquia.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.cbEstado.setEnabled(false);
        this.cbEstadoCivil.setEnabled(false);
        this.cbGenero.setEnabled(false);
        this.cbTipoContrato.setEnabled(false);
        this.fechaNac.setEnabled(false);
        this.fechaIngreso.setEnabled(false);
        this.btnGuardar.setEnabled(false);
    }

    private void limpiarControles() {
        this.txtNom.setText("");
        this.txtApellMat.setText("");
        this.txtApellPat.setText("");
        this.txtCedula.setText("");
        this.fechaNac.setDate(null);
        this.fechaIngreso.setDate(null);
        this.txtTelefono.setText("");
        this.txtDireccion.setText("");
        this.txtProvincia.setText("");
        this.txtCanton.setText("");
        this.txtParroquia.setText("");
        this.txtDireccion.setText("");
        this.txtTitulo.setText("");
        this.txtAnterior.setText("");
        this.txtCorreo.setText("");

    }

    private boolean validarCampos() {
        if (this.txtNom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        } else if (this.txtApellPat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido paterno del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtApellPat.requestFocus();
        } else if (this.txtApellMat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido materno del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtApellMat.requestFocus();
        } else if (this.txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el numero de cedula del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCedula.requestFocus();
        } else if (this.txtCedula.getText().length() < 10) {
            JOptionPane.showMessageDialog(this, "Numero de cedula incompleto " + this.txtCedula.getText().length(), "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCedula.requestFocus();
        } else if (this.fechaNac.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese la fecha de nacimiento del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.fechaNac.requestFocus();
        } else if (this.txtTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el titulo del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtTitulo.requestFocus();
        } else if (this.fechaIngreso.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese la fecha de ingreso del docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.fechaIngreso.requestFocus();
        } else if (this.txtProvincia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la provincia", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtProvincia.requestFocus();
        } else if (this.txtCanton.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el canton", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCanton.requestFocus();
        } else if (this.txtParroquia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la parroquia", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtParroquia.requestFocus();
        } else if (this.txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la direccion", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCanton.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private void cargarTabla(List<DocenteDTO> lista) {
        String columnas[] = new String[]{"ID", "NOMBRES", "APELLIDOS", "CEDULA", "GENERO",
            "TELEFONO", "CORREO", "TITULO", "TIPO CONTRATO", "FECHA INGRESO ", "ANTERIOR INSTITUCION"};

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.setColumnIdentifiers(columnas);

        for (DocenteDTO lista1 : lista) {
            String fila[] = new String[columnas.length];

            fila[0] = String.valueOf(lista1.getIdDocente());
            fila[1] = lista1.getNombres();
            fila[2] = lista1.getApellPaterno() + " " + lista1.getApellMaterno();
            fila[3] = lista1.getCedula();
            fila[4] = lista1.getGenero();
            fila[5] = lista1.getTelefono();
            fila[6] = lista1.getCorreo();
            fila[7] = lista1.getTitulo();
            fila[8] = lista1.getTipoContrato();
            fila[9] = lista1.getFechaIngreso();
            fila[10] = lista1.getAnteriorInstitucion();
            modelo.addRow(fila);
        }
        this.tabla.setModel(modelo);
        columnasTamaño(this.tabla);
        this.lblregistros.setText("Registos: " + lista.size());
    }

    private void columnasTamaño(JTable tabla) {
        for (int i = 1; i <= tabla.getColumnCount() - 1; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(175);
        }
    }

    private void insertar() {

        if (validarCampos()) {
            docente.setNombres(this.txtNom.getText());
            docente.setApellPaterno(this.txtApellPat.getText());
            docente.setApellMaterno(this.txtApellMat.getText());
            docente.setCedula(this.txtCedula.getText());
            docente.setGenero(this.cbGenero.getSelectedItem().toString());
            docente.setFechaNacimiento(new SimpleDateFormat("yyyy/MM/dd").format(this.fechaNac.getDate()));
            docente.setTelefono(this.txtTelefono.getText());
            docente.setCorreo(this.txtCorreo.getText());
            docente.setProvincia(this.txtProvincia.getText());
            docente.setCanton(this.txtCanton.getText());
            docente.setParroquia(this.txtParroquia.getText());
            docente.setDireccion(this.txtDireccion.getText());
            docente.setEstadoCivil(this.cbEstadoCivil.getSelectedItem().toString());
            docente.setTitulo(this.txtTitulo.getText());
            docente.setTipoContrato(this.cbTipoContrato.getSelectedItem().toString());
            docente.setAnteriorInstitucion(this.txtAnterior.getText());
            docente.setFechaIngreso(new SimpleDateFormat("yyyy/MM/dd").format(this.fechaIngreso.getDate()));
            docente.setEstado(this.cbEstado.getSelectedItem().toString());

            try {
                int res = docenteDAO.guardar(docente);
                if (res == 1) {
                    limpiarControles();
                    desactivarControles();
                    cargarTabla(docenteDAO.listar());
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
                int opc = JOptionPane.showConfirmDialog(this, "Desea eliminar al docente", "Sistema de gestion escolar", JOptionPane.YES_NO_OPTION);

                if (opc == JOptionPane.YES_OPTION) {
                    if (docenteDAO.eliminar(id) == 1) {
                        limpiarControles();
                        desactivarControles();
                        cargarTabla(docenteDAO.listar());
                        JOptionPane.showMessageDialog(this, "Docente eliminado correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un docente", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TablePanel = new javax.swing.JTabbedPane();
        panelRegistrar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellPat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellMat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fechaNac = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblFoto1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCanton = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtParroquia = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        cbTipoContrato = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        fechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnterior = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCorreo = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        cbEstadoCivil = new javax.swing.JComboBox<>();
        btnCerrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        panelLista = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        cbBusqueda = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblregistros = new javax.swing.JLabel();
        btnCancelar2 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setTitle("Gestion de docentes");

        panelRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        panelRegistrar.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Datos personales");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Nombres");
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 22));

        txtNom.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtNom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNom.setEnabled(false);
        txtNom.setPreferredSize(new java.awt.Dimension(65, 22));
        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Apellido paterno");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 22));

        txtApellPat.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtApellPat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtApellPat.setEnabled(false);
        txtApellPat.setPreferredSize(new java.awt.Dimension(65, 22));
        txtApellPat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellPatActionPerformed(evt);
            }
        });
        txtApellPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellPatKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Apellido materno");
        jLabel3.setPreferredSize(new java.awt.Dimension(130, 22));

        txtApellMat.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtApellMat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtApellMat.setEnabled(false);
        txtApellMat.setPreferredSize(new java.awt.Dimension(65, 22));
        txtApellMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellMatKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Cedula");
        jLabel4.setPreferredSize(new java.awt.Dimension(130, 22));

        txtCedula.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCedula.setEnabled(false);
        txtCedula.setPreferredSize(new java.awt.Dimension(59, 22));
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Genero");
        jLabel5.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Fecha de nacimiento");
        jLabel7.setPreferredSize(new java.awt.Dimension(130, 22));

        fechaNac.setDateFormatString("yyyy/MM/dd");
        fechaNac.setEnabled(false);
        fechaNac.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        fechaNac.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Correo");
        jLabel8.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Telefono ");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 22));

        txtTelefono.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTelefono.setEnabled(false);
        txtTelefono.setPreferredSize(new java.awt.Dimension(59, 22));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lblFoto1.setBackground(new java.awt.Color(153, 153, 153));
        lblFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/representante.png"))); // NOI18N
        lblFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setText("Estado Civil");
        jLabel14.setPreferredSize(new java.awt.Dimension(130, 22));

        cbGenero.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        cbGenero.setEnabled(false);
        cbGenero.setPreferredSize(new java.awt.Dimension(59, 23));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 153));
        jLabel26.setText("Provincia");
        jLabel26.setPreferredSize(new java.awt.Dimension(130, 22));

        txtProvincia.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtProvincia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtProvincia.setEnabled(false);
        txtProvincia.setPreferredSize(new java.awt.Dimension(59, 22));
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 153));
        jLabel27.setText("Canton");
        jLabel27.setPreferredSize(new java.awt.Dimension(130, 22));

        txtCanton.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCanton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCanton.setEnabled(false);
        txtCanton.setPreferredSize(new java.awt.Dimension(59, 22));
        txtCanton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantonKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 153, 153));
        jLabel29.setText("Parroquia");
        jLabel29.setPreferredSize(new java.awt.Dimension(130, 22));

        txtParroquia.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtParroquia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtParroquia.setEnabled(false);
        txtParroquia.setPreferredSize(new java.awt.Dimension(59, 22));
        txtParroquia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtParroquiaKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 153, 153));
        jLabel28.setText("Direccion");
        jLabel28.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 153, 153));
        jLabel25.setText("Titulo");
        jLabel25.setPreferredSize(new java.awt.Dimension(130, 22));

        txtTitulo.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTitulo.setEnabled(false);
        txtTitulo.setPreferredSize(new java.awt.Dimension(59, 22));
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        txtTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTituloKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 153, 153));
        jLabel30.setText("Tipo de contrato");
        jLabel30.setPreferredSize(new java.awt.Dimension(130, 22));

        cbTipoContrato.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbTipoContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEDIO TIEMPO", "JORNADA COMPLETA" }));
        cbTipoContrato.setEnabled(false);
        cbTipoContrato.setPreferredSize(new java.awt.Dimension(59, 23));
        cbTipoContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoContratoActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 153));
        jLabel31.setText("Fecha de ingreso");
        jLabel31.setPreferredSize(new java.awt.Dimension(130, 22));

        fechaIngreso.setDateFormatString("yyyy/MM/dd");
        fechaIngreso.setEnabled(false);
        fechaIngreso.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        fechaIngreso.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 153));
        jLabel32.setText("Anterior Institucion");
        jLabel32.setPreferredSize(new java.awt.Dimension(130, 22));

        txtAnterior.setColumns(20);
        txtAnterior.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtAnterior.setLineWrap(true);
        txtAnterior.setRows(5);
        txtAnterior.setWrapStyleWord(true);
        txtAnterior.setEnabled(false);
        txtAnterior.setPreferredSize(new java.awt.Dimension(224, 44));
        txtAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnteriorKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtAnterior);

        txtCorreo.setColumns(20);
        txtCorreo.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCorreo.setLineWrap(true);
        txtCorreo.setRows(5);
        txtCorreo.setWrapStyleWord(true);
        txtCorreo.setEnabled(false);
        txtCorreo.setPreferredSize(new java.awt.Dimension(224, 44));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txtCorreo);

        txtDireccion.setColumns(20);
        txtDireccion.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtDireccion.setLineWrap(true);
        txtDireccion.setRows(5);
        txtDireccion.setWrapStyleWord(true);
        txtDireccion.setEnabled(false);
        txtDireccion.setPreferredSize(new java.awt.Dimension(224, 44));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(txtDireccion);

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Estado");
        jLabel12.setPreferredSize(new java.awt.Dimension(130, 24));

        cbEstado.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbEstado.setEnabled(false);
        cbEstado.setPreferredSize(new java.awt.Dimension(59, 23));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        cbEstadoCivil.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLTERO", "CASADO", "UNION LIBRE" }));
        cbEstadoCivil.setEnabled(false);
        cbEstadoCivil.setPreferredSize(new java.awt.Dimension(59, 23));
        cbEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoCivilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbEstadoCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellMat, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(cbGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(txtApellPat, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(fechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(24, 24, 24))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtProvincia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txtCanton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtParroquia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(cbTipoContrato, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtApellPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(cbTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(fechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtCanton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout panelRegistrarLayout = new javax.swing.GroupLayout(panelRegistrar);
        panelRegistrar.setLayout(panelRegistrarLayout);
        panelRegistrarLayout.setHorizontalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createSequentialGroup()
                        .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrar)
                        .addGap(15, 15, 15))))
        );
        panelRegistrarLayout.setVerticalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        TablePanel.addTab("Registrar docente", panelRegistrar);

        panelLista.setBackground(new java.awt.Color(255, 255, 255));
        panelLista.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBusqueda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtBusqueda.setPreferredSize(new java.awt.Dimension(59, 23));
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        cbBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRES", "APELLIDOS", "CEDULA" }));
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(cbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cbBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Busqueda");

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
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        lblregistros.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        lblregistros.setText("Registros: ");

        btnCancelar2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnCancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancel.png"))); // NOI18N
        btnCancelar2.setText("Cerrar");
        btnCancelar2.setPreferredSize(new java.awt.Dimension(107, 45));
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelListaLayout.createSequentialGroup()
                        .addComponent(lblregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblregistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        TablePanel.addTab("Listado de docentes", panelLista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtNomKeyTyped

    private void txtApellPatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellPatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellPatActionPerformed

    private void txtApellPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellPatKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtApellPatKeyTyped

    private void txtApellMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellMatKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtApellMatKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        ValidacionText.soloNumeros(evt, 10);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        ValidacionText.soloNumeros(evt, 10);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGeneroActionPerformed

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtCantonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantonKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtCantonKeyTyped

    private void txtParroquiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParroquiaKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtParroquiaKeyTyped

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void txtTituloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtTituloKeyTyped

    private void cbTipoContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoContratoActionPerformed

    private void txtAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnteriorKeyTyped
        if (this.txtAnterior.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAnteriorKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        if (this.txtCorreo.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (this.txtDireccion.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

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
        docente.setIdDocente(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if (evt.getSource() == this.txtBusqueda) {
            try {
                cargarTabla(docenteDAO.listar(this.cbBusqueda.getSelectedItem().toString(), this.txtBusqueda.getText()));
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void cbBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbBusquedaFocusGained
        this.txtBusqueda.setText("");
    }//GEN-LAST:event_cbBusquedaFocusGained

    private void cbBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBusquedaMouseClicked

    }//GEN-LAST:event_cbBusquedaMouseClicked

    private void cbBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBusquedaActionPerformed
        this.txtBusqueda.requestFocus();
    }//GEN-LAST:event_cbBusquedaActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() > 1) {
            try {
                docente = docenteDAO.conseguir(Integer.parseInt(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString()));
                this.txtNom.setText(docente.getNombres());
                this.txtApellPat.setText(docente.getApellPaterno());
                this.txtApellMat.setText(docente.getApellMaterno());
                this.txtCedula.setText(docente.getCedula());
                this.cbGenero.setSelectedItem(docente.getGenero());
                this.fechaNac.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(docente.getFechaNacimiento()));
                this.txtTelefono.setText(docente.getTelefono());
                this.txtCorreo.setText(docente.getCorreo());
                this.txtProvincia.setText(docente.getProvincia());
                this.txtCanton.setText(docente.getCanton());
                this.txtParroquia.setText(docente.getParroquia());
                this.txtDireccion.setText(docente.getDireccion());
                this.cbEstadoCivil.setSelectedItem(docente.getEstadoCivil());
                this.txtTitulo.setText(docente.getTitulo());
                this.cbTipoContrato.setSelectedItem(docente.getTipoContrato());
                this.txtAnterior.setText(docente.getAnteriorInstitucion());
                this.fechaIngreso.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(docente.getFechaIngreso()));
                this.cbEstado.setSelectedItem(docente.getEstado());

                activarControles();
                this.TablePanel.setSelectedComponent(this.panelRegistrar);

            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error en la fecha " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        instancia = null;
        dispose();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void cbEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoCivilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane TablePanel;
    public javax.swing.JButton btnCancelar2;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbBusqueda;
    public javax.swing.JComboBox<String> cbEstado;
    public javax.swing.JComboBox<String> cbEstadoCivil;
    public javax.swing.JComboBox<String> cbGenero;
    public javax.swing.JComboBox<String> cbTipoContrato;
    public com.toedter.calendar.JDateChooser fechaIngreso;
    public com.toedter.calendar.JDateChooser fechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblFoto1;
    public javax.swing.JLabel lblregistros;
    public javax.swing.JPanel panelLista;
    public javax.swing.JPanel panelRegistrar;
    public javax.swing.JTable tabla;
    public javax.swing.JTextArea txtAnterior;
    public javax.swing.JTextField txtApellMat;
    public javax.swing.JTextField txtApellPat;
    public javax.swing.JTextField txtBusqueda;
    public javax.swing.JTextField txtCanton;
    public javax.swing.JTextField txtCedula;
    public javax.swing.JTextArea txtCorreo;
    public javax.swing.JTextArea txtDireccion;
    public javax.swing.JTextField txtNom;
    public javax.swing.JTextField txtParroquia;
    public javax.swing.JTextField txtProvincia;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
