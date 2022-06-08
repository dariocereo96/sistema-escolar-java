
package DAO;

import DTO.UsuarioDTO;
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
public class UsuarioDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public UsuarioDTO conseguir(String name, String password) throws ErrorDB {

        try {
            UsuarioDTO usuario = new UsuarioDTO();
            String sql = "{call buscarUsuario(?,?)}";

            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setString(1, name);
            cs.setString(2, password);
            rs = cs.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setIdAutoridad(rs.getInt(2));
                usuario.setName(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setAutoridad(rs.getString(5));
                usuario.setTipo(rs.getString(6));
                usuario.setEstado(rs.getString(7));

            } else {
                throw new ErrorDB("Error en el usuario o la contraseña");
            }
            return usuario;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int insertar(UsuarioDTO a) throws ErrorDB {
        String sql = "{call insertarUsuario(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdAutoridad());
            cs.setString(2, a.getName());
            cs.setString(3, a.getPassword());
            cs.setString(4, a.getTipo());
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

    public int modificar(UsuarioDTO a) throws ErrorDB {
        String sql = "{call modificarUsuario(?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdUsuario());
            cs.setInt(2, a.getIdAutoridad());
            cs.setString(3, a.getName());
            cs.setString(4, a.getPassword());
            cs.setString(5, a.getTipo());
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
        String sql = "{call eliminarUsuario(?)}";
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

    public UsuarioDTO conseguir(int id) throws ErrorDB {
        UsuarioDTO usuario = new UsuarioDTO();
        String sql = "{call conseguirUsuario(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setIdAutoridad(rs.getInt(2));
                usuario.setName(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setTipo(rs.getString(5));
                usuario.setEstado(rs.getString(6));

            }

            conDB.close();
            cs.close();
            rs.close();

            return usuario;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<UsuarioDTO> listar() throws ErrorDB {
        List<UsuarioDTO> lista = new ArrayList<>();
        UsuarioDTO usuario;
        String sql = "select * from mostrarUsuarios";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setIdAutoridad(rs.getInt(2));
                usuario.setName(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setAutoridad(rs.getString(5));
                usuario.setTipo(rs.getString(6));
                usuario.setEstado(rs.getString(7));
                lista.add(usuario);
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
        String sql = "select count(*) from usuarios where id_usuario=?";

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

    public int guardar(UsuarioDTO usuario) throws ErrorDB {

        if (existe(usuario.getIdUsuario())) {
            return modificar(usuario);
        } else {
            return insertar(usuario);
        }
    }


}
