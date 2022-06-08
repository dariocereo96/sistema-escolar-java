/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conexiones.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utilidades.FondoImagen;

/**
 *
 * @author PABLO
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     *
     */
    public FrmPrincipal() {
        initComponents();
        estilos();
    }

    private void estilos() {
        this.escritorio.setBorder(new FondoImagen());
        this.setIconImage(new ImageIcon(this.getClass().getResource("/iconos/acess.png")).getImage());
        this.setVisible(true);
        this.setExtendedState(FrmPrincipal.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        panelEstado = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        BarraMenu = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        itemAlumno = new javax.swing.JMenuItem();
        itemRepresentante = new javax.swing.JMenuItem();
        menuConfig1 = new javax.swing.JMenu();
        itemMatriculas = new javax.swing.JMenuItem();
        item = new javax.swing.JMenu();
        itemPensiones = new javax.swing.JMenuItem();
        itemPagos = new javax.swing.JMenuItem();
        menuGestion = new javax.swing.JMenu();
        itemCursos = new javax.swing.JMenuItem();
        itemMateria = new javax.swing.JMenuItem();
        itemNotas = new javax.swing.JMenuItem();
        itemNivel = new javax.swing.JMenuItem();
        itemDistribucion = new javax.swing.JMenuItem();
        itemPeriodos = new javax.swing.JMenuItem();
        menuDocentes = new javax.swing.JMenu();
        itemDocente = new javax.swing.JMenuItem();
        itemAutoridades = new javax.swing.JMenuItem();
        itemUsuarios = new javax.swing.JMenuItem();
        menuInformes = new javax.swing.JMenu();
        itemReporteMatricula = new javax.swing.JMenuItem();
        menuSesion = new javax.swing.JMenu();
        itemClose = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE GESTION ESCOLAR");

        panelEstado.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        panelEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 25, 5));

        lblUsuario.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        lblUsuario.setText("USUARIO:");
        panelEstado.add(lblUsuario);

        lblTipo.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        lblTipo.setText("TIPO:");
        panelEstado.add(lblTipo);

        escritorio.setLayer(panelEstado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addGap(0, 429, Short.MAX_VALUE)
                .addComponent(panelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        BarraMenu.setBackground(new java.awt.Color(255, 255, 255));

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/student.png"))); // NOI18N
        jMenu3.setText("Alumnos");
        jMenu3.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemAlumno.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/estudiante.png"))); // NOI18N
        itemAlumno.setText("Gestion de alumnos");
        itemAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAlumnoActionPerformed(evt);
            }
        });
        jMenu3.add(itemAlumno);

        itemRepresentante.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemRepresentante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/representantes.png"))); // NOI18N
        itemRepresentante.setText("Gestion de representantes");
        itemRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRepresentanteActionPerformed(evt);
            }
        });
        jMenu3.add(itemRepresentante);

        BarraMenu.add(jMenu3);

        menuConfig1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/bussines.png"))); // NOI18N
        menuConfig1.setText("Matriculas");
        menuConfig1.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemMatriculas.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemMatriculas.setText("Gestion de matriculas");
        itemMatriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMatriculasActionPerformed(evt);
            }
        });
        menuConfig1.add(itemMatriculas);

        BarraMenu.add(menuConfig1);

        item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/money.png"))); // NOI18N
        item.setText("Pensiones");
        item.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemPensiones.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemPensiones.setText("Gestion de pensiones");
        itemPensiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPensionesActionPerformed(evt);
            }
        });
        item.add(itemPensiones);

        itemPagos.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemPagos.setText("Registro de pagos");
        itemPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPagosActionPerformed(evt);
            }
        });
        item.add(itemPagos);

        BarraMenu.add(item);

        menuGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/folder_office_22603.png"))); // NOI18N
        menuGestion.setText("Gestion Academica");
        menuGestion.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemCursos.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cursos.png"))); // NOI18N
        itemCursos.setText("Cursos");
        itemCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCursosActionPerformed(evt);
            }
        });
        menuGestion.add(itemCursos);

        itemMateria.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/materias.png"))); // NOI18N
        itemMateria.setText("Materias");
        itemMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMateriaActionPerformed(evt);
            }
        });
        menuGestion.add(itemMateria);

        itemNotas.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemNotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/notas.png"))); // NOI18N
        itemNotas.setText("Notas");
        itemNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNotasActionPerformed(evt);
            }
        });
        menuGestion.add(itemNotas);

        itemNivel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/niveles.png"))); // NOI18N
        itemNivel.setText("Niveles");
        itemNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNivelActionPerformed(evt);
            }
        });
        menuGestion.add(itemNivel);

        itemDistribucion.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemDistribucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/distribucion.png"))); // NOI18N
        itemDistribucion.setText("Distribucion");
        itemDistribucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDistribucionActionPerformed(evt);
            }
        });
        menuGestion.add(itemDistribucion);

        itemPeriodos.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemPeriodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calendar.png"))); // NOI18N
        itemPeriodos.setText("Periodos");
        itemPeriodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPeriodosActionPerformed(evt);
            }
        });
        menuGestion.add(itemPeriodos);

        BarraMenu.add(menuGestion);

        menuDocentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/people.png"))); // NOI18N
        menuDocentes.setText("Administracion");
        menuDocentes.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemDocente.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/teacher.png"))); // NOI18N
        itemDocente.setText("Gestion de docentes");
        itemDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDocenteActionPerformed(evt);
            }
        });
        menuDocentes.add(itemDocente);

        itemAutoridades.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemAutoridades.setText("Gestion de autoridades");
        itemAutoridades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAutoridadesActionPerformed(evt);
            }
        });
        menuDocentes.add(itemAutoridades);

        itemUsuarios.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemUsuarios.setText("Gestion de usuarios");
        itemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuariosActionPerformed(evt);
            }
        });
        menuDocentes.add(itemUsuarios);

        BarraMenu.add(menuDocentes);

        menuInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/informes.png"))); // NOI18N
        menuInformes.setText("Reportes");
        menuInformes.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemReporteMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemReporteMatricula.setText("Reporte de matriculas");
        itemReporteMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReporteMatriculaActionPerformed(evt);
            }
        });
        menuInformes.add(itemReporteMatricula);

        BarraMenu.add(menuInformes);

        menuSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config.png"))); // NOI18N
        menuSesion.setText("Opciones");
        menuSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        itemClose.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/close.png"))); // NOI18N
        itemClose.setText("Cerrar Sesion");
        itemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCloseActionPerformed(evt);
            }
        });
        menuSesion.add(itemClose);

        BarraMenu.add(menuSesion);

        setJMenuBar(BarraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCloseActionPerformed
        new FrmLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemCloseActionPerformed

    private void itemAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlumnoActionPerformed
        if (!FrmAlumnos.isInstanciado()) {
            this.escritorio.add(FrmAlumnos.getInstancia());
            FrmAlumnos.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemAlumnoActionPerformed

    private void itemRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRepresentanteActionPerformed
        if (!FrmRepresentantes.isInstanciado()) {
            this.escritorio.add(FrmRepresentantes.getInstancia());
            FrmRepresentantes.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemRepresentanteActionPerformed

    private void itemDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDocenteActionPerformed
        if (!FrmDocentes.isInstanciado()) {
            this.escritorio.add(FrmDocentes.getInstancia());
            FrmDocentes.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemDocenteActionPerformed

    private void itemPeriodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPeriodosActionPerformed
        if (!FrmPeriodos.isInstanciado()) {
            this.escritorio.add(FrmPeriodos.getInstancia());
            FrmPeriodos.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemPeriodosActionPerformed

    private void itemNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNivelActionPerformed
        if (!FrmNiveles.isInstanciado()) {
            this.escritorio.add(FrmNiveles.getInstancia());
            FrmNiveles.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemNivelActionPerformed

    private void itemCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCursosActionPerformed
        if (!FrmCursos.isInstanciado()) {
            this.escritorio.add(FrmCursos.getInstancia());
            FrmCursos.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemCursosActionPerformed

    private void itemPensionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPensionesActionPerformed
        if (!FrmPensiones.isInstanciado()) {
            this.escritorio.add(FrmPensiones.getInstancia());
            FrmPensiones.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemPensionesActionPerformed

    private void itemMatriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMatriculasActionPerformed
        if (!FrmMatriculas.isInstanciado()) {
            this.escritorio.add(FrmMatriculas.getInstancia());
            FrmMatriculas.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemMatriculasActionPerformed

    private void itemPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPagosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPagosActionPerformed

    private void itemMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMateriaActionPerformed
        if (!FrmMaterias.isInstanciado()) {
            this.escritorio.add(FrmMaterias.getInstancia());
            FrmMaterias.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemMateriaActionPerformed

    private void itemAutoridadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAutoridadesActionPerformed

        if (!FrmAutoridades.isInstanciado()) {
            this.escritorio.add(FrmAutoridades.getInstancia());
            FrmAutoridades.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemAutoridadesActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuariosActionPerformed

        if (!FrmUsuarios.isInstanciado()) {
            this.escritorio.add(FrmUsuarios.getInstancia());
            FrmUsuarios.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemUsuariosActionPerformed

    private void itemReporteMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReporteMatriculaActionPerformed
        try {
            Connection con = new Conexion().getConexion();
            JasperReport reporte = null;
            String path = "src\\reportes\\alumnos.jasper";
           
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el archivo de conexion: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la clase: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el reporte: " + ex.getMessage(), "Sistema de gestion escolar", JOptionPane.ERROR_MESSAGE);
        }*/
    }//GEN-LAST:event_itemReporteMatriculaActionPerformed

    private void itemDistribucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDistribucionActionPerformed
        if (!FrmDistribuciones.isInstanciado()) {
            this.escritorio.add(FrmDistribuciones.getInstancia());
            FrmDistribuciones.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemDistribucionActionPerformed

    private void itemNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNotasActionPerformed
        if (!FrmNotas.isInstanciado()) {
            this.escritorio.add(FrmNotas.getInstancia());
            FrmNotas.getInstancia().setVisible(true);
        }
    }//GEN-LAST:event_itemNotasActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar BarraMenu;
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu item;
    public javax.swing.JMenuItem itemAlumno;
    public javax.swing.JMenuItem itemAutoridades;
    public javax.swing.JMenuItem itemClose;
    public javax.swing.JMenuItem itemCursos;
    public javax.swing.JMenuItem itemDistribucion;
    public javax.swing.JMenuItem itemDocente;
    public javax.swing.JMenuItem itemMateria;
    public javax.swing.JMenuItem itemMatriculas;
    public javax.swing.JMenuItem itemNivel;
    public javax.swing.JMenuItem itemNotas;
    public javax.swing.JMenuItem itemPagos;
    public javax.swing.JMenuItem itemPensiones;
    public javax.swing.JMenuItem itemPeriodos;
    public javax.swing.JMenuItem itemReporteMatricula;
    public javax.swing.JMenuItem itemRepresentante;
    public javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JMenu jMenu3;
    public static javax.swing.JLabel lblTipo;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menuConfig1;
    private javax.swing.JMenu menuDocentes;
    private javax.swing.JMenu menuGestion;
    private javax.swing.JMenu menuInformes;
    private javax.swing.JMenu menuSesion;
    private javax.swing.JPanel panelEstado;
    // End of variables declaration//GEN-END:variables
}
