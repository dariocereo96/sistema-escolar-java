package vistas;

import DAO.AlumnoDAO;
import DTO.AlumnoDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utilidades.ErrorDB;
import utilidades.ValidacionText;

public class FrmAlumnos extends javax.swing.JInternalFrame {

    private static FrmAlumnos instancia;
    private final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private AlumnoDTO alumno = new AlumnoDTO();

    public FrmAlumnos() {
        initComponents();
        try {
            cargarTabla(alumnoDAO.listar());
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(rootPane, "No se pudo cargar los datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static FrmAlumnos getInstancia() {
        if (instancia == null) {
            instancia = new FrmAlumnos();
        }
        return instancia;
    }

    public static boolean isInstanciado() {
        return instancia != null;
    }

    private void activarControles() {
        txtNombres.setEnabled(true);
        txtNombres.requestFocus();
        txtApellMat.setEnabled(true);
        txtApellPat.setEnabled(true);
        txtCedula.setEnabled(true);
        cbGenero.setEnabled(true);
        txtTelefono.setEnabled(true);
        fechaNac.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtIdrepresentante.setEnabled(true);
        txtNomRepr.setEnabled(true);
        txtApellRepr.setEnabled(true);
        btnBuscar.setEnabled(true);
        txtProvincia.setEnabled(true);
        txtCanton.setEnabled(true);
        txtParroquia.setEnabled(true);
        txtNombres.setEnabled(true);
        txtDireccion.setEnabled(true);
        cbEstado.setEnabled(true);
        btnGuardar.setEnabled(true);
    }

    private void desactivarControles() {
        txtNombres.setEnabled(false);
        txtApellMat.setEnabled(false);
        txtApellPat.setEnabled(false);
        txtCedula.setEnabled(false);
        cbGenero.setEnabled(false);
        txtTelefono.setEnabled(false);
        this.fechaNac.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtIdrepresentante.setEnabled(false);
        txtNomRepr.setEnabled(false);
        txtApellRepr.setEnabled(false);
        btnBuscar.setEnabled(false);
        txtProvincia.setEnabled(false);
        txtCanton.setEnabled(false);
        txtParroquia.setEnabled(false);
        txtDireccion.setEnabled(false);
        cbEstado.setEnabled(false);
        btnGuardar.setEnabled(false);
    }

    private void limpiarControles() {
        txtNombres.setText("");
        txtApellMat.setText("");
        txtApellPat.setText("");
        txtCedula.setText("");
        fechaNac.setDate(null);
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtProvincia.setText("");
        txtCanton.setText("");
        txtParroquia.setText("");
        txtDireccion.setText("");
        txtIdrepresentante.setText("");
        txtNomRepr.setText("");
        txtApellRepr.setText("");
    }

    private boolean validarCampos() {
        if (this.txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtNombres.requestFocus();
        } else if (this.txtApellPat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido paterno", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtApellPat.requestFocus();
        } else if (this.txtApellMat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido materno", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtApellMat.requestFocus();
        } else if (this.txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el numero de cedula", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCedula.requestFocus();
        } else if (this.txtCedula.getText().length() < 10) {
            JOptionPane.showMessageDialog(this, "Numero de cedula incompleto " + this.txtCedula.getText().length(), "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.txtCedula.requestFocus();
        } else if (this.fechaNac.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese la fecha de nacimiento", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.fechaNac.requestFocus();
        } else if (FrmAlumnos.txtIdrepresentante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione el representante del alumno", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
            this.btnGuardar.requestFocus();
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

    private void cargarTabla(List<AlumnoDTO> lista) {
        String columnas[] = new String[]{"ID", "NOMBRES", "APELLIDOS", "CEDULA", "GENERO", "EDAD",
            "TELEFONO", "CORREO", "NOMBRE REPRESENTANTE", "APELLIDO REPRESENTANTE"};

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        modelo.setColumnIdentifiers(columnas);

        for (AlumnoDTO lista1 : lista) {
            String fila[] = new String[columnas.length];

            fila[0] = String.valueOf(lista1.getIdAlumno());
            fila[1] = lista1.getNombres();
            fila[2] = lista1.getApellPaterno() + " " + lista1.getApellMaterno();
            fila[3] = lista1.getCedula();
            fila[4] = lista1.getGenero();
            fila[5] = String.valueOf(lista1.getEdad());
            fila[6] = lista1.getTelefono();
            fila[7] = lista1.getCorreo();
            fila[8] = lista1.getNomRepresentantes();
            fila[9] = lista1.getApellRepresentante();
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
            alumno.setNombres(this.txtNombres.getText());
            alumno.setApellPaterno(this.txtApellPat.getText());
            alumno.setApellMaterno(this.txtApellMat.getText());
            alumno.setCedula(this.txtCedula.getText());
            alumno.setGenero(this.cbGenero.getSelectedItem().toString());
            alumno.setFechaNacimiento(new SimpleDateFormat("yyyy/MM/dd").format(this.fechaNac.getDate()));
            alumno.setTelefono(this.txtTelefono.getText());
            alumno.setCorreo(this.txtCorreo.getText());
            alumno.setProvincia(this.txtProvincia.getText());
            alumno.setCanton(this.txtCanton.getText());
            alumno.setParroquia(this.txtParroquia.getText());
            alumno.setDireccion(this.txtDireccion.getText());
            alumno.setIdRepresentante(Integer.parseInt(FrmAlumnos.txtIdrepresentante.getText()));
            alumno.setEstado(this.cbEstado.getSelectedItem().toString());

            try {
                int res = alumnoDAO.guardar(alumno);
                if (res == 1) {
                    limpiarControles();
                    desactivarControles();
                    cargarTabla(alumnoDAO.listar());
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
                int opc = JOptionPane.showConfirmDialog(this, "Desea eliminar al alumno", "Sistema de gestion escolar", JOptionPane.YES_NO_OPTION);

                if (opc == JOptionPane.YES_OPTION) {
                    if (alumnoDAO.eliminar(id) == 1) {
                        cargarTabla(alumnoDAO.listar());
                        limpiarControles();
                        desactivarControles();
                        JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente", "Sistema de gestion escolar", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, "Error el eliminar: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno", "Sistema de gestion escolar", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TablePanel = new javax.swing.JTabbedPane();
        panelRegistrar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellPat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellMat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        txtCanton = new javax.swing.JTextField();
        txtParroquia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtIdrepresentante = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtNomRepr = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtApellRepr = new javax.swing.JTextField();
        fechaNac = new com.toedter.calendar.JDateChooser();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCorreo = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
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

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion de alumnos");
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

        panelRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        panelRegistrar.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Nombres");
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 22));

        txtNombres.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNombres.setEnabled(false);
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Apellido paterno");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 22));

        txtApellPat.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtApellPat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtApellPat.setEnabled(false);
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

        cbGenero.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        cbGenero.setEnabled(false);
        cbGenero.setPreferredSize(new java.awt.Dimension(59, 23));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Fecha de nacimiento");
        jLabel7.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Telefono");
        jLabel8.setPreferredSize(new java.awt.Dimension(130, 22));

        txtTelefono.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTelefono.setEnabled(false);
        txtTelefono.setPreferredSize(new java.awt.Dimension(59, 22));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Correo");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 22));

        lblFoto.setBackground(new java.awt.Color(153, 153, 153));
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/adduser.png"))); // NOI18N
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 153));
        jLabel26.setText("Provincia");
        jLabel26.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 153));
        jLabel27.setText("Canton");
        jLabel27.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 153, 153));
        jLabel29.setText("Parroquia");
        jLabel29.setPreferredSize(new java.awt.Dimension(130, 22));

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 153, 153));
        jLabel28.setText("Direccion");
        jLabel28.setPreferredSize(new java.awt.Dimension(130, 22));

        txtProvincia.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtProvincia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtProvincia.setEnabled(false);
        txtProvincia.setPreferredSize(new java.awt.Dimension(59, 22));
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyTyped(evt);
            }
        });

        txtCanton.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCanton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCanton.setEnabled(false);
        txtCanton.setPreferredSize(new java.awt.Dimension(59, 22));
        txtCanton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantonKeyTyped(evt);
            }
        });

        txtParroquia.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtParroquia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtParroquia.setEnabled(false);
        txtParroquia.setPreferredSize(new java.awt.Dimension(59, 22));
        txtParroquia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtParroquiaKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 153));
        jLabel22.setText("ID representante");
        jLabel22.setPreferredSize(new java.awt.Dimension(130, 22));

        txtIdrepresentante.setEditable(false);
        txtIdrepresentante.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtIdrepresentante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIdrepresentante.setEnabled(false);
        txtIdrepresentante.setPreferredSize(new java.awt.Dimension(59, 22));
        txtIdrepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdrepresentanteActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscar.setEnabled(false);
        btnBuscar.setPreferredSize(new java.awt.Dimension(59, 23));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 153, 153));
        jLabel23.setText("Nombres del representante");
        jLabel23.setPreferredSize(new java.awt.Dimension(130, 22));

        txtNomRepr.setEditable(false);
        txtNomRepr.setBackground(new java.awt.Color(255, 255, 255));
        txtNomRepr.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtNomRepr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNomRepr.setEnabled(false);
        txtNomRepr.setPreferredSize(new java.awt.Dimension(59, 22));
        txtNomRepr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomReprActionPerformed(evt);
            }
        });
        txtNomRepr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomReprKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 153));
        jLabel24.setText("Apellidos del representante");
        jLabel24.setPreferredSize(new java.awt.Dimension(130, 22));

        txtApellRepr.setEditable(false);
        txtApellRepr.setBackground(new java.awt.Color(255, 255, 255));
        txtApellRepr.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtApellRepr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtApellRepr.setEnabled(false);
        txtApellRepr.setPreferredSize(new java.awt.Dimension(59, 22));

        fechaNac.setDateFormatString("yyyy/MM/dd");
        fechaNac.setEnabled(false);
        fechaNac.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        cbEstado.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbEstado.setEnabled(false);
        cbEstado.setPreferredSize(new java.awt.Dimension(59, 23));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Estado");
        jLabel12.setPreferredSize(new java.awt.Dimension(130, 24));

        txtCorreo.setColumns(20);
        txtCorreo.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtCorreo.setLineWrap(true);
        txtCorreo.setRows(5);
        txtCorreo.setEnabled(false);
        jScrollPane2.setViewportView(txtCorreo);

        txtDireccion.setColumns(20);
        txtDireccion.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        txtDireccion.setLineWrap(true);
        txtDireccion.setRows(5);
        txtDireccion.setEnabled(false);
        jScrollPane3.setViewportView(txtDireccion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellPat, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(txtNombres)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtApellMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbGenero, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaNac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIdrepresentante, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomRepr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApellRepr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtParroquia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCanton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtApellPat, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNomRepr, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtApellRepr, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIdrepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(74, 74, 74))))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCanton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(70, 70, 70))
        );

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Datos personales");

        btnCerrar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorderPainted(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorderPainted(false);
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
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelRegistrarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar)
                        .addGap(15, 15, 15)
                        .addComponent(btnCerrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrarLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        panelRegistrarLayout.setVerticalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        TablePanel.addTab("Registrar alumno", null, panelRegistrar, "");
        panelRegistrar.getAccessibleContext().setAccessibleName("");

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

        cbBusqueda.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
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
                .addContainerGap(448, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblregistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        TablePanel.addTab("Listado de alumnos", panelLista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TablePanel)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGeneroActionPerformed

    private void txtApellPatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellPatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellPatActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        instancia = null;
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        new FrmListaRepresentantes(null, true).setVisible(true);

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        instancia = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtNombresKeyTyped

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

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtCantonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantonKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtCantonKeyTyped

    private void txtNomReprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomReprActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomReprActionPerformed

    private void txtIdrepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdrepresentanteActionPerformed

    }//GEN-LAST:event_txtIdrepresentanteActionPerformed

    private void txtParroquiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParroquiaKeyTyped
        ValidacionText.soloLetras(evt, 30);
    }//GEN-LAST:event_txtParroquiaKeyTyped

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        try {
            cargarTabla(alumnoDAO.listar(this.cbBusqueda.getSelectedItem().toString(), this.txtBusqueda.getText()));
        } catch (ErrorDB ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
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

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        instancia = null;
        dispose();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNomReprKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomReprKeyTyped

    }//GEN-LAST:event_txtNomReprKeyTyped

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() > 1) {
            try {
                alumno = alumnoDAO.conseguir(Integer.parseInt(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString()));
                this.txtNombres.setText(alumno.getNombres());
                this.txtApellMat.setText(alumno.getApellMaterno());
                this.txtApellPat.setText(alumno.getApellPaterno());
                this.txtCedula.setText(alumno.getCedula());
                this.cbGenero.setSelectedItem(alumno.getGenero());
                this.fechaNac.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(alumno.getFechaNacimiento()));
                this.txtTelefono.setText(alumno.getTelefono());
                this.txtCorreo.setText(alumno.getCorreo());
                this.txtProvincia.setText(alumno.getProvincia());
                this.txtCanton.setText(alumno.getCanton());
                this.txtParroquia.setText(alumno.getParroquia());
                this.txtDireccion.setText(alumno.getDireccion());
                FrmAlumnos.txtIdrepresentante.setText(String.valueOf(alumno.getIdRepresentante()));
                FrmAlumnos.txtNomRepr.setText(alumno.getNomRepresentantes());
                FrmAlumnos.txtApellRepr.setText(alumno.getApellRepresentante());
                this.cbEstado.setSelectedItem(alumno.getEstado());
                activarControles();
                this.TablePanel.setSelectedComponent(this.panelRegistrar);

            } catch (ErrorDB ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error en la fecha " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_tablaMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarControles();
        activarControles();
        alumno.setIdAlumno(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane TablePanel;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCancelar2;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbBusqueda;
    public javax.swing.JComboBox<String> cbEstado;
    public javax.swing.JComboBox<String> cbGenero;
    public com.toedter.calendar.JDateChooser fechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFoto;
    public javax.swing.JLabel lblregistros;
    public javax.swing.JPanel panelLista;
    public javax.swing.JPanel panelRegistrar;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField txtApellMat;
    public javax.swing.JTextField txtApellPat;
    public static javax.swing.JTextField txtApellRepr;
    public javax.swing.JTextField txtBusqueda;
    public javax.swing.JTextField txtCanton;
    public javax.swing.JTextField txtCedula;
    private javax.swing.JTextArea txtCorreo;
    private javax.swing.JTextArea txtDireccion;
    public static javax.swing.JTextField txtIdrepresentante;
    public static javax.swing.JTextField txtNomRepr;
    public javax.swing.JTextField txtNombres;
    public javax.swing.JTextField txtParroquia;
    public javax.swing.JTextField txtProvincia;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
