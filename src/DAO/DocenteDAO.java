package DAO;

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

public class DocenteDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(DocenteDTO a) throws ErrorDB {
        String sql = "{call insertarDocente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
            cs.setString(14, a.getTitulo());
            cs.setString(15, a.getTipoContrato());
            cs.setString(16, a.getAnteriorInstitucion());
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

    public int modificar(DocenteDTO a) throws ErrorDB {
        String sql = "{call modificarDocente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdDocente());
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
            cs.setString(15, a.getTitulo());
            cs.setString(16, a.getTipoContrato());
            cs.setString(17, a.getAnteriorInstitucion());
            cs.setString(18, a.getFechaIngreso());
            cs.setString(19, a.getEstado());

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
        String sql = "{call eliminarDocente(?)}";
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

    public DocenteDTO conseguir(int id) throws ErrorDB {
        DocenteDTO docente = new DocenteDTO();
        String sql = "{call conseguirDocente(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {

                docente.setIdDocente(rs.getInt("ID"));
                docente.setNombres(rs.getString("NOMBRES"));
                docente.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                docente.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                docente.setCedula(rs.getString("CEDULA"));
                docente.setGenero(rs.getString("GENERO"));
                docente.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                docente.setTelefono(rs.getString("TELEFONO"));
                docente.setCorreo(rs.getString("CORREO"));
                docente.setProvincia(rs.getString("PROVINCIA"));
                docente.setCanton(rs.getString("CANTON"));
                docente.setParroquia(rs.getString("PARROQUIA"));
                docente.setDireccion(rs.getString("DIRECCION"));
                docente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                docente.setTitulo(rs.getString("TITULO"));
                docente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
                docente.setAnteriorInstitucion(rs.getString("ANTERIOR_INSTITUCION"));
                docente.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                docente.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return docente;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<DocenteDTO> listar() throws ErrorDB {
        List<DocenteDTO> lista = new ArrayList<>();
        DocenteDTO docente;
        String sql = "select * from mostrarDocentes";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                docente = new DocenteDTO();
                docente.setIdDocente(rs.getInt("ID"));
                docente.setNombres(rs.getString("NOMBRES"));
                docente.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                docente.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                docente.setCedula(rs.getString("CEDULA"));
                docente.setGenero(rs.getString("GENERO"));
                docente.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                docente.setTelefono(rs.getString("TELEFONO"));
                docente.setCorreo(rs.getString("CORREO"));
                docente.setProvincia(rs.getString("PROVINCIA"));
                docente.setCanton(rs.getString("CANTON"));
                docente.setParroquia(rs.getString("PARROQUIA"));
                docente.setDireccion(rs.getString("DIRECCION"));
                docente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                docente.setTitulo(rs.getString("TITULO"));
                docente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
                docente.setAnteriorInstitucion(rs.getString("ANTERIOR_INSTITUCION"));
                docente.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                docente.setEstado(rs.getString("ESTADO"));
                lista.add(docente);
            }
            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<DocenteDTO> listar(String campo, String valor) throws ErrorDB {
        List<DocenteDTO> lista = new ArrayList<>();
        DocenteDTO docente;
        String sql = "select * from mostrarDocentes where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                docente = new DocenteDTO();
                docente.setIdDocente(rs.getInt("ID"));
                docente.setNombres(rs.getString("NOMBRES"));
                docente.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                docente.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                docente.setCedula(rs.getString("CEDULA"));
                docente.setGenero(rs.getString("GENERO"));
                docente.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                docente.setTelefono(rs.getString("TELEFONO"));
                docente.setCorreo(rs.getString("CORREO"));
                docente.setProvincia(rs.getString("PROVINCIA"));
                docente.setCanton(rs.getString("CANTON"));
                docente.setParroquia(rs.getString("PARROQUIA"));
                docente.setDireccion(rs.getString("DIRECCION"));
                docente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                docente.setTitulo(rs.getString("TITULO"));
                docente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
                docente.setAnteriorInstitucion(rs.getString("ANTERIOR_INSTITUCION"));
                docente.setFechaIngreso(rs.getString("FECHA_INGRESO"));
                docente.setEstado(rs.getString("ESTADO"));
                lista.add(docente);
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
        String sql = "select count(*) from docentes where id_docente=?";

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

    public int guardar(DocenteDTO docente) throws ErrorDB {

        if (existe(docente.getIdDocente())) {
            return modificar(docente);
        } else {
            return insertar(docente);
        }
    }

}
