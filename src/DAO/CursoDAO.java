package DAO;

import DTO.CursoDTO;
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
public class CursoDAO{

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(CursoDTO a) throws ErrorDB {
        String sql = "{call insertarCurso(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdNivel());
            cs.setInt(2, a.getCapacidad());
            cs.setString(3, a.getParalelo());
            cs.setString(4, a.getJornada());
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

    public int modificar(CursoDTO a) throws ErrorDB {
        String sql = "{call modificarCurso(?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdCurso());
            cs.setInt(2, a.getIdNivel());
            cs.setInt(3, a.getCapacidad());
            cs.setString(4, a.getParalelo());
            cs.setString(5, a.getJornada());
            cs.setString(6, a.getEstado());

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
        String sql = "{call eliminarCurso(?)}";
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

    public CursoDTO conseguir(int id) throws ErrorDB {
        CursoDTO curso = new CursoDTO();
        String sql = "{call conseguirCurso(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {

                curso.setIdCurso(rs.getInt("ID"));
                curso.setIdNivel(rs.getInt("ID_NIVEL"));
                curso.setCapacidad(rs.getInt("CAPACIDAD"));
                curso.setParalelo(rs.getString("PARALELO"));
                curso.setJornada(rs.getString("JORNADA"));
                curso.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return curso;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<CursoDTO> listar() throws ErrorDB {
        List<CursoDTO> lista = new ArrayList<>();
        CursoDTO curso;
        String sql = "select * from mostrarCursos";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                curso = new CursoDTO();
                curso.setIdCurso(rs.getInt("ID"));
                curso.setDescripcion(rs.getString("DESCRIPCION"));
                curso.setIdNivel(rs.getInt("ID_NIVEL"));
                curso.setCapacidad(rs.getInt("CAPACIDAD"));
                curso.setParalelo(rs.getString("PARALELO"));
                curso.setJornada(rs.getString("JORNADA"));
                lista.add(curso);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<CursoDTO> listar(String campo, String valor) throws ErrorDB {
        List<CursoDTO> lista = new ArrayList<>();
        CursoDTO curso;
        String sql = "select * from mostrarCursos where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                curso = new CursoDTO();
                curso.setIdCurso(rs.getInt("ID"));
                curso.setDescripcion(rs.getString("DESCRIPCION"));
                curso.setIdNivel(rs.getInt("ID_NIVEL"));
                curso.setCapacidad(rs.getInt("CAPACIDAD"));
                curso.setParalelo(rs.getString("PARALELO"));
                curso.setJornada(rs.getString("JORNADA"));
                lista.add(curso);
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
        String sql = "select count(*) from cursos where id_curso=?";

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

    public int guardar(CursoDTO curso) throws ErrorDB {

        if (existe(curso.getIdCurso())) {
            return modificar(curso);
        } else {
            return insertar(curso);
        }
    }

}
