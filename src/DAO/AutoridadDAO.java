package DAO;

import DTO.AutoridadDTO;
import DTO.DocenteDTO;
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

public class AutoridadDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(AutoridadDTO a) throws ErrorDB {
        String sql = "{call insertarAutoridad(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setString(1, a.getNombres());
            cs.setString(2, a.getApellPaterno());
            cs.setString(3, a.getApellMaterno());
            cs.setString(4, a.getCedula());
            cs.setString(5, a.getGenero());
            cs.setString(6, a.getFechaNacimiento());
            cs.setString(7, a.getTelefono());
            cs.setString(8, a.getCorreo());
            cs.setString(9, a.getProvincia());
            cs.setString(10, a.getCanton());
            cs.setString(11, a.getParroquia());
            cs.setString(12, a.getDireccion());
            cs.setString(13, a.getEstadoCivil());
            cs.setString(14, a.getProfesion());
            cs.setString(15, a.getCargo());
            cs.setString(16, a.getFechaIngreso());
            cs.setString(17, a.getEstado());

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

    public int modificar(AutoridadDTO a) throws ErrorDB {
        String sql = "{call modificarAutoridad(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdAutoridad());
            cs.setString(2, a.getNombres());
            cs.setString(3, a.getApellPaterno());
            cs.setString(4, a.getApellMaterno());
            cs.setString(5, a.getCedula());
            cs.setString(6, a.getGenero());
            cs.setString(7, a.getFechaNacimiento());
            cs.setString(8, a.getTelefono());
            cs.setString(9, a.getCorreo());
            cs.setString(10, a.getProvincia());
            cs.setString(11, a.getCanton());
            cs.setString(12, a.getParroquia());
            cs.setString(13, a.getDireccion());
            cs.setString(14, a.getEstadoCivil());
            cs.setString(15, a.getProfesion());
            cs.setString(16, a.getCargo());
            cs.setString(17, a.getFechaIngreso());
            cs.setString(18, a.getEstado());

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
        String sql = "{call eliminarAutoridad(?)}";
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

    public AutoridadDTO conseguir(int id) throws ErrorDB {
        AutoridadDTO autoridad = new AutoridadDTO();
        String sql = "{call conseguirAutoridad(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                autoridad.setIdAutoridad(rs.getInt("ID"));
                autoridad.setNombres(rs.getString("NOMBRES"));
                autoridad.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                autoridad.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                autoridad.setCedula(rs.getString("CEDULA"));
                autoridad.setGenero(rs.getString("GENERO"));
                autoridad.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                autoridad.setTelefono(rs.getString("TELEFONO"));
                autoridad.setCorreo(rs.getString("CORREO"));
                autoridad.setProvincia(rs.getString("PROVINCIA"));
                autoridad.setCanton(rs.getString("CANTON"));
                autoridad.setParroquia(rs.getString("PARROQUIA"));
                autoridad.setDireccion(rs.getString("DIRECCION"));
                autoridad.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                autoridad.setProfesion(rs.getString("PROFESION"));
                autoridad.setCargo(rs.getString("CARGO"));
                autoridad.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                autoridad.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return autoridad;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<AutoridadDTO> listar() throws ErrorDB {
        List<AutoridadDTO> lista = new ArrayList<>();
        AutoridadDTO autoridad;
        String sql = "select * from mostrarAutoridades";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                autoridad = new AutoridadDTO();
                autoridad.setIdAutoridad(rs.getInt("ID"));
                autoridad.setNombres(rs.getString("NOMBRES"));
                autoridad.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                autoridad.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                autoridad.setCedula(rs.getString("CEDULA"));
                autoridad.setGenero(rs.getString("GENERO"));
                autoridad.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                autoridad.setTelefono(rs.getString("TELEFONO"));
                autoridad.setCorreo(rs.getString("CORREO"));
                autoridad.setProvincia(rs.getString("PROVINCIA"));
                autoridad.setCanton(rs.getString("CANTON"));
                autoridad.setParroquia(rs.getString("PARROQUIA"));
                autoridad.setDireccion(rs.getString("DIRECCION"));
                autoridad.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                autoridad.setProfesion(rs.getString("PROFESION"));
                autoridad.setCargo(rs.getString("CARGO"));
                autoridad.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                autoridad.setEstado(rs.getString("ESTADO"));
                lista.add(autoridad);
            }
            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<AutoridadDTO> listar(String campo, String valor) throws ErrorDB {
        List<AutoridadDTO> lista = new ArrayList<>();
        AutoridadDTO autoridad;
        String sql = "select * from mostrarAutoridades where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                 autoridad = new AutoridadDTO();
                autoridad.setIdAutoridad(rs.getInt("ID"));
                autoridad.setNombres(rs.getString("NOMBRES"));
                autoridad.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                autoridad.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                autoridad.setCedula(rs.getString("CEDULA"));
                autoridad.setGenero(rs.getString("GENERO"));
                autoridad.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                autoridad.setTelefono(rs.getString("TELEFONO"));
                autoridad.setCorreo(rs.getString("CORREO"));
                autoridad.setProvincia(rs.getString("PROVINCIA"));
                autoridad.setCanton(rs.getString("CANTON"));
                autoridad.setParroquia(rs.getString("PARROQUIA"));
                autoridad.setDireccion(rs.getString("DIRECCION"));
                autoridad.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                autoridad.setProfesion(rs.getString("PROFESION"));
                autoridad.setCargo(rs.getString("CARGO"));
                autoridad.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                autoridad.setEstado(rs.getString("ESTADO"));
                lista.add(autoridad);
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
        String sql = "select count(*) from autoridades where id_autoridad=?";

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

    public int guardar(AutoridadDTO autoridad) throws ErrorDB {

        if (existe(autoridad.getIdAutoridad())) {
            return modificar(autoridad);
        } else {
            return insertar(autoridad);
        }
    }

}
