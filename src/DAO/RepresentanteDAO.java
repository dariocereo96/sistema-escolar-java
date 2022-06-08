/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.RepresentanteDTO;
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

public class RepresentanteDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(RepresentanteDTO a) throws ErrorDB {
        String sql = "{call insertarRepresentante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
            cs.setString(13, a.getOcupacion());
            cs.setString(14, a.getLugarTrabajo());
            cs.setString(15, a.getTelefonoTrabajo());
            cs.setString(16, a.getEstadoCivil());
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

    public int modificar(RepresentanteDTO a) throws ErrorDB {
        String sql = "{call modificarRepresentante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, a.getIdRepresentante());
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
            cs.setString(14, a.getOcupacion());
            cs.setString(15, a.getLugarTrabajo());
            cs.setString(16, a.getTelefonoTrabajo());
            cs.setString(17, a.getEstadoCivil());
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
        String sql = "{call eliminarRepresentante(?)}";
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

    public RepresentanteDTO conseguir(int id) throws ErrorDB {
        RepresentanteDTO representante = new RepresentanteDTO();
        String sql = "{call conseguirRepresentante(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {

                representante.setIdRepresentante(rs.getInt("ID"));
                representante.setNombres(rs.getString("NOMBRES"));
                representante.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                representante.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                representante.setCedula(rs.getString("CEDULA"));
                representante.setGenero(rs.getString("GENERO"));
                representante.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                representante.setTelefono(rs.getString("TELEFONO"));
                representante.setCorreo(rs.getString("CORREO"));
                representante.setProvincia(rs.getString("PROVINCIA"));
                representante.setCanton(rs.getString("CANTON"));
                representante.setParroquia(rs.getString("PARROQUIA"));
                representante.setDireccion(rs.getString("DIRECCION"));
                representante.setOcupacion(rs.getString("OCUPACION"));
                representante.setLugarTrabajo(rs.getString("LUGAR_TRABAJO"));
                representante.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                representante.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                representante.setEstado(rs.getString("ESTADO"));

            }

            conDB.close();
            cs.close();
            rs.close();

            return representante;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<RepresentanteDTO> listar() throws ErrorDB {
        List<RepresentanteDTO> lista = new ArrayList<>();
        RepresentanteDTO representante;
        String sql = "select * from mostrarRepresentantes";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                representante = new RepresentanteDTO();

                representante.setIdRepresentante(rs.getInt("ID"));
                representante.setNombres(rs.getString("NOMBRES"));
                representante.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                representante.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                representante.setCedula(rs.getString("CEDULA"));
                representante.setGenero(rs.getString("GENERO"));
                representante.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                representante.setTelefono(rs.getString("TELEFONO"));
                representante.setCorreo(rs.getString("CORREO"));
                representante.setProvincia(rs.getString("PROVINCIA"));
                representante.setCanton(rs.getString("CANTON"));
                representante.setParroquia(rs.getString("PARROQUIA"));
                representante.setDireccion(rs.getString("DIRECCION"));
                representante.setOcupacion(rs.getString("OCUPACION"));
                representante.setLugarTrabajo(rs.getString("LUGAR_TRABAJO"));
                representante.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                representante.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                representante.setEstado(rs.getString("ESTADO"));

                lista.add(representante);

            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }

    }

    public List<RepresentanteDTO> listar(String campo, String valor) throws ErrorDB {
        List<RepresentanteDTO> lista = new ArrayList<>();
        RepresentanteDTO representante;
        String sql = "select * from mostrarRepresentantes where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                representante = new RepresentanteDTO();

                representante.setIdRepresentante(rs.getInt("ID"));
                representante.setNombres(rs.getString("NOMBRES"));
                representante.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                representante.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                representante.setCedula(rs.getString("CEDULA"));
                representante.setGenero(rs.getString("GENERO"));
                representante.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                representante.setTelefono(rs.getString("TELEFONO"));
                representante.setCorreo(rs.getString("CORREO"));
                representante.setProvincia(rs.getString("PROVINCIA"));
                representante.setCanton(rs.getString("CANTON"));
                representante.setParroquia(rs.getString("PARROQUIA"));
                representante.setDireccion(rs.getString("DIRECCION"));
                representante.setOcupacion(rs.getString("OCUPACION"));
                representante.setLugarTrabajo(rs.getString("LUGAR_TRABAJO"));
                representante.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                representante.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                representante.setEstado(rs.getString("ESTADO"));

                lista.add(representante);

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
        String sql = "select count(*) from representantes where id_representante=?";

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

    public int guardar(RepresentanteDTO representante) throws ErrorDB {

        if (existe(representante.getIdRepresentante())) {
            return modificar(representante);
        } else {
            return insertar(representante);
        }
    }

}
