/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MatriculaDTO;
import conexiones.Conexion;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.ErrorDB;

/**
 *
 * @author PABLO
 */
public class MatriculaDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(MatriculaDTO matricula) throws ErrorDB {
        String sql = "{call insertarMatricula(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, matricula.getIdAlumno());
            cs.setInt(2, matricula.getIdPeriodo());
            cs.setInt(3, matricula.getIdCurso());
            cs.setInt(4, matricula.getIdNivel());
            cs.setInt(5, matricula.getIdPension());

            int res = cs.executeUpdate();
            conDB.commit();
            conDB.close();
            cs.close();
            return res;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            try {
                conDB.rollback();
            } catch (SQLException ex1) {
                throw new ErrorDB("Error en la conexion: " + ex1.getMessage());
            }
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int modificar(MatriculaDTO matricula) throws ErrorDB {
        String sql = "{call modificarMatricula(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, matricula.getIdAlumno());
            cs.setInt(2, matricula.getIdPeriodo());
            cs.setInt(3, matricula.getIdCurso());
            cs.setInt(4, matricula.getIdNivel());
            cs.setInt(5, matricula.getIdPension());

            int res = cs.executeUpdate();
            conDB.commit();
            conDB.close();
            cs.close();
            return res;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            try {
                conDB.rollback();
            } catch (SQLException ex1) {
                throw new ErrorDB("Error en la conexion: " + ex1.getMessage());
            }
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int eliminar(int id) throws ErrorDB {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MatriculaDTO conseguir(int id) throws ErrorDB {
        MatriculaDTO matricula = new MatriculaDTO();
        String sql = "{call conseguirMatricula(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                matricula.setIdMatricula(rs.getInt("ID"));
                matricula.setIdAlumno(rs.getInt("ID_ALUMNO"));
                matricula.setIdPeriodo(rs.getInt("ID_PERIODO"));
                matricula.setIdCurso(rs.getInt("ID_CURSO"));
                matricula.setIdNivel(rs.getInt("ID_NIVEL"));
                matricula.setIdPension(rs.getInt("ID_PENSION"));
                matricula.setFechaMatricula("FECHA_MATRICULA");
                matricula.setNombreCompletos(rs.getString("NOMBRE_COMPLETO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return matricula;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<MatriculaDTO> listar() throws ErrorDB {
        List<MatriculaDTO> lista = new ArrayList<>();
        MatriculaDTO matricula;
        String sql = "select * from mostrarMatriculas";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                matricula = new MatriculaDTO();
                matricula.setIdMatricula(rs.getInt("ID"));
                matricula.setIdAlumno(rs.getInt("ID_ALUMNO"));
                matricula.setIdPeriodo(rs.getInt("ID_PERIODO"));
                matricula.setIdCurso(rs.getInt("ID_CURSO"));
                matricula.setIdNivel(rs.getInt("ID_NIVEL"));
                matricula.setIdPension(rs.getInt("ID_PENSION"));
                matricula.setFechaMatricula(rs.getString("FECHA_MATRICULA"));
                matricula.setNombreCompletos(rs.getString("NOMBRE_COMPLETO"));
                matricula.setCurso(rs.getString("CURSO"));
                matricula.setPeriodo(rs.getString("PERIODO"));
                lista.add(matricula);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<MatriculaDTO> listar(String campo, String valor) throws ErrorDB {
        List<MatriculaDTO> lista = new ArrayList<>();
        MatriculaDTO matricula;
        String sql = "select * from mostrarMatriculas where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                matricula = new MatriculaDTO();
                matricula.setIdMatricula(rs.getInt("ID"));
                matricula.setIdAlumno(rs.getInt("ID_ALUMNO"));
                matricula.setIdPeriodo(rs.getInt("ID_PERIODO"));
                matricula.setIdCurso(rs.getInt("ID_CURSO"));
                matricula.setIdNivel(rs.getInt("ID_NIVEL"));
                matricula.setIdPension(rs.getInt("ID_PENSION"));
                matricula.setFechaMatricula(rs.getString("FECHA_MATRICULA"));
                matricula.setNombreCompletos(rs.getString("NOMBRE_COMPLETO"));
                matricula.setCurso(rs.getString("CURSO"));
                matricula.setPeriodo(rs.getString("PERIODO"));
                lista.add(matricula);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }
    
      private boolean existe(int idAlumno) throws ErrorDB {
        String sql = "select count(*) from matriculas where id_alumno=?";
        
        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();
            rs.next();
            
            return rs.getInt(1)>0;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int guardar(MatriculaDTO matricula) throws ErrorDB {

        if (existe(matricula.getIdAlumno())) {
            return modificar(matricula);
        } else {
            return insertar(matricula);
        }
    }

}
