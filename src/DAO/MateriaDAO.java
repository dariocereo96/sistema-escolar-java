package DAO;

import DTO.MateriaDTO;
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

public class MateriaDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(MateriaDTO materia) throws ErrorDB {
        String sql = "{call insertarMateria(?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setString(1, materia.getNombre());
            cs.setString(2, materia.getArea());
            cs.setInt(3, materia.getHorasSemanales());
            cs.setString(4, materia.getEstado());

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

    public int modificar(MateriaDTO materia) throws ErrorDB {
        String sql = "{call modificarMateria(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, materia.getIdMateria());
            cs.setString(2, materia.getNombre());
            cs.setString(3, materia.getArea());
            cs.setInt(4, materia.getHorasSemanales());
            cs.setString(5, materia.getEstado());

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
        String sql = "{call eliminarMateria(?)}";
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

    public MateriaDTO conseguir(int id) throws ErrorDB {
        MateriaDTO materia = new MateriaDTO();
        String sql = "{call conseguirMateria(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                materia.setIdMateria(rs.getInt("ID"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setArea(rs.getString("AREA"));
                materia.setHorasSemanales(rs.getInt("HORAS_SEMANALES"));
                materia.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return materia;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<MateriaDTO> listar() throws ErrorDB {
        List<MateriaDTO> lista = new ArrayList<>();
        MateriaDTO materia;
        String sql = "select * from mostrarMaterias";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                materia = new MateriaDTO();
                materia.setIdMateria(rs.getInt("ID"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setArea(rs.getString("AREA"));
                materia.setHorasSemanales(rs.getInt("HORAS_SEMANALES"));
                materia.setEstado(rs.getString("ESTADO"));
                lista.add(materia);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<MateriaDTO> listar(String campo, String valor) throws ErrorDB {
        List<MateriaDTO> lista = new ArrayList<>();
        MateriaDTO materia;
        String sql = "select * from mostrarMaterias " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                materia = new MateriaDTO();
                materia.setIdMateria(rs.getInt("ID"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setArea(rs.getString("AREA"));
                materia.setHorasSemanales(rs.getInt("HORAS_SEMANALES"));
                materia.setEstado(rs.getString("ESTADO"));
                lista.add(materia);
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
        String sql = "select count(*) from materias where id_materia=?";

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

    public int guardar(MateriaDTO materia) throws ErrorDB {

        if (existe(materia.getIdMateria())) {
            return modificar(materia);
        } else {
            return insertar(materia);
        }
    }

}
