/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PeriodoDTO;
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
public class PeriodoDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(PeriodoDTO a) throws ErrorDB {
        String sql = "{call insertarPeriodo(?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setString(1, a.getDescripcion());
            cs.setString(2, a.getFechaInicio());
            cs.setString(3, a.getFechaFin());
            cs.setString(4, a.getEstado());

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
    
    public int modificar(PeriodoDTO a) throws ErrorDB {
        String sql = "{call modificarPeriodo(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdPeriodo());
            cs.setString(2, a.getDescripcion());
            cs.setString(3, a.getFechaInicio());
            cs.setString(4, a.getFechaFin());
            cs.setString(5, a.getEstado());

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

    public int activar(int id) throws ErrorDB {
        String sql = "{call activarPeriodo(?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);

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

    public PeriodoDTO conseguir(int id) throws ErrorDB {
        PeriodoDTO periodo = new PeriodoDTO();
        String sql = "{call conseguirPeriodo(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {

                periodo.setIdPeriodo(rs.getInt("ID"));
                periodo.setDescripcion(rs.getString("DESCRIPCION"));
                periodo.setFechaInicio(rs.getString("FECHA_INICIO"));
                periodo.setFechaFin(rs.getString("FECHA_FIN"));
                periodo.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return periodo;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<PeriodoDTO> listar() throws ErrorDB {
        List<PeriodoDTO> lista = new ArrayList<>();
        PeriodoDTO periodo;
        String sql = "select * from mostrarPeriodos";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                periodo = new PeriodoDTO();
                periodo.setIdPeriodo(rs.getInt("ID"));
                periodo.setDescripcion(rs.getString("DESCRIPCION"));
                periodo.setFechaInicio(rs.getString("FECHA_INICIO"));
                periodo.setFechaFin(rs.getString("FECHA_FIN"));
                periodo.setEstado(rs.getString("ESTADO"));
                lista.add(periodo);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<PeriodoDTO> listar(String campo, String valor) throws ErrorDB {
        List<PeriodoDTO> lista = new ArrayList<>();
        PeriodoDTO periodo;
        String sql = "select * from mostrarPeriodos where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                periodo = new PeriodoDTO();
                periodo.setIdPeriodo(rs.getInt("ID"));
                periodo.setDescripcion(rs.getString("DESCRIPCION"));
                periodo.setFechaInicio(rs.getString("FECHA_INICIO"));
                periodo.setFechaFin(rs.getString("FECHA_FIN"));
                periodo.setEstado(rs.getString("ESTADO"));
                lista.add(periodo);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    private boolean existe(int id) throws ErrorDB {
        String sql = "select count(*) from periodos where id_periodo=?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1) > 0;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int guardar(PeriodoDTO periodo) throws ErrorDB {

        if (existe(periodo.getIdPeriodo())) {
            return modificar(periodo);
        } else {
            return insertar(periodo);
        }
    }

}
