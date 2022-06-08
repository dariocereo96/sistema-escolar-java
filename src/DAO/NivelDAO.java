package DAO;

import DTO.MateriaDTO;
import DTO.NivelDTO;
import DTO.PensumDTO;
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

public class NivelDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(NivelDTO a) throws ErrorDB {
        String sql = "{call insertarNivel(?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getOrden());
            cs.setString(2, a.getGrado());
            cs.setString(3, a.getEspecialidad());
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

    public int modificar(NivelDTO a) throws ErrorDB {
        String sql = "{call modificarNivel(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdNivel());
            cs.setInt(2, a.getOrden());
            cs.setString(3, a.getGrado());
            cs.setString(4, a.getEspecialidad());
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

    public int eliminar(int id) throws ErrorDB {
        String sql = "{call eliminarNivel(?)}";
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

    public NivelDTO conseguir(int id) throws ErrorDB {
        NivelDTO nivel = new NivelDTO();
        String sql = "{call conseguirNivel(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {

                nivel.setIdNivel(rs.getInt("ID"));
                nivel.setOrden(rs.getInt("ORDEN"));
                nivel.setGrado(rs.getString("GRADO"));
                nivel.setEspecialidad(rs.getString("ESPECIALIDAD"));
                nivel.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return nivel;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<NivelDTO> listar() throws ErrorDB {
        List<NivelDTO> lista = new ArrayList<>();
        NivelDTO nivel;
        String sql = "select * from mostrarNiveles";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                nivel = new NivelDTO();
                nivel.setIdNivel(rs.getInt("ID"));
                nivel.setDescripcion(rs.getString("DESCRIPCION"));
                nivel.setOrden(rs.getInt("ORDEN"));
                nivel.setGrado(rs.getString("GRADO"));
                nivel.setEspecialidad(rs.getString("ESPECIALIDAD"));
                nivel.setEstado(rs.getString("ESTADO"));
                lista.add(nivel);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<NivelDTO> listar(String campo, String valor) throws ErrorDB {

        List<NivelDTO> lista = new ArrayList<>();
        NivelDTO nivel;
        String sql = "select * from mostrarNiveles where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                nivel=new NivelDTO();
                nivel.setIdNivel(rs.getInt("ID"));
                nivel.setDescripcion(rs.getString("DESCRIPCION"));
                nivel.setOrden(rs.getInt("ORDEN"));
                nivel.setGrado(rs.getString("GRADO"));
                nivel.setEspecialidad(rs.getString("ESPECIALIDAD"));
                nivel.setEstado(rs.getString("ESTADO"));
                lista.add(nivel);
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
        String sql = "select count(*) from niveles where id_nivel=?";

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

    public int guardar(NivelDTO nivel) throws ErrorDB {

        if (existe(nivel.getIdNivel())) {
            return modificar(nivel);
        } else {
            return insertar(nivel);
        }
    }


    public int insertarMateria(int idNivel, int idMateria) throws ErrorDB {
        String sql = "{call insertarPensum(?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, idNivel);
            cs.setInt(2, idMateria);

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

    public PensumDTO conseguirPensum(int idNivel) throws ErrorDB {
        List<MateriaDTO> lista = new ArrayList<>();
        MateriaDTO materia;
        String sql = "{call conseguirPensum(?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, idNivel);
            rs = cs.executeQuery();

            while (rs.next()) {
                materia = new MateriaDTO();
                materia.setIdMateria(rs.getInt("ID_MATERIA"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setArea(rs.getString("AREA"));
                materia.setHorasSemanales(rs.getInt("HORAS_SEMANALES"));
                materia.setEstado(rs.getString("ESTADO"));
                lista.add(materia);
            }
            PensumDTO pensum = new PensumDTO(idNivel, lista);

            conDB.close();
            cs.close();
            rs.close();

            return pensum;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int eliminarMateria(int idNivel, int idMateria) throws ErrorDB {
        String sql = "{call eliminarPensum(?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, idMateria);
            cs.setInt(2, idNivel);

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
    
}
