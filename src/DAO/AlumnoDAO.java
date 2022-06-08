package DAO;

import DTO.AlumnoDTO;
import conexiones.Conexion;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.ErrorDB;

public class AlumnoDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private int insertar(AlumnoDTO a) throws ErrorDB {

        String sql = "{call insertarAlumno(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
            cs.setString(14, a.getEstado());

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

    private int modificar(AlumnoDTO alumno) throws ErrorDB {
        String sql = "{call modificarAlumno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, alumno.getIdAlumno());
            cs.setInt(2, alumno.getIdRepresentante());
            cs.setString(3, alumno.getNombres());
            cs.setString(4, alumno.getApellPaterno());
            cs.setString(5, alumno.getApellMaterno());
            cs.setString(6, alumno.getCedula());
            cs.setString(7, alumno.getGenero());
            cs.setString(8, alumno.getFechaNacimiento());
            cs.setString(9, alumno.getTelefono());
            cs.setString(10, alumno.getCorreo());
            cs.setString(11, alumno.getProvincia());
            cs.setString(12, alumno.getCanton());
            cs.setString(13, alumno.getParroquia());
            cs.setString(14, alumno.getDireccion());
            cs.setString(15, alumno.getEstado());

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
        String sql = "{call eliminarAlumno(?)}";
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

    public AlumnoDTO conseguir(int id) throws ErrorDB {
        AlumnoDTO alumno = new AlumnoDTO();
        String sql = "{call conseguirAlumno(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                alumno.setIdAlumno(rs.getInt("ID"));
                alumno.setIdRepresentante(rs.getInt("ID_REPRESENTANTE"));
                alumno.setNombres(rs.getString("NOMBRES"));
                alumno.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                alumno.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                alumno.setCedula(rs.getString("CEDULA"));
                alumno.setGenero(rs.getString("GENERO"));
                alumno.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                alumno.setTelefono(rs.getString("TELEFONO"));
                alumno.setCorreo(rs.getString("CORREO"));
                alumno.setProvincia(rs.getString("PROVINCIA"));
                alumno.setCanton(rs.getString("CANTON"));
                alumno.setParroquia(rs.getString("PARROQUIA"));
                alumno.setDireccion(rs.getString("DIRECCION"));
                alumno.setEstado(rs.getString("ESTADO"));
                alumno.setNomRepresentantes(rs.getString("NOMBRES_REPRESENTANTE"));
                alumno.setApellRepresentante(rs.getString("APELLIDOS_REPRESENTANTE"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return alumno;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<AlumnoDTO> listar() throws ErrorDB {
        List<AlumnoDTO> lista = new ArrayList<>();
        AlumnoDTO alumno;
        String sql = "select * from mostrarAlumnos";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno = new AlumnoDTO();

                alumno.setIdAlumno(rs.getInt("ID"));
                alumno.setIdRepresentante(rs.getInt("ID_REPRESENTANTE"));
                alumno.setNombres(rs.getString("NOMBRES"));
                alumno.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                alumno.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                alumno.setCedula(rs.getString("CEDULA"));
                alumno.setGenero(rs.getString("GENERO"));
                alumno.setEdad(rs.getInt("EDAD"));
                alumno.setTelefono(rs.getString("TELEFONO"));
                alumno.setCorreo(rs.getString("CORREO"));
                alumno.setProvincia(rs.getString("PROVINCIA"));
                alumno.setCanton(rs.getString("CANTON"));
                alumno.setParroquia(rs.getString("PARROQUIA"));
                alumno.setDireccion(rs.getString("DIRECCION"));
                alumno.setEstado(rs.getString("ESTADO"));
                alumno.setNomRepresentantes(rs.getString("NOMBRES_REPRESENTANTE"));
                alumno.setApellRepresentante(rs.getString("APELLIDOS_REPRESENTANTE"));
                lista.add(alumno);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }

    }

    public List<AlumnoDTO> listar(String campo, String valor) throws ErrorDB {
        List<AlumnoDTO> lista = new ArrayList<>();
        AlumnoDTO alumno;
        String sql = "select * from mostrarAlumnos where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno = new AlumnoDTO();

                alumno.setIdAlumno(rs.getInt("ID"));
                alumno.setIdRepresentante(rs.getInt("ID_REPRESENTANTE"));
                alumno.setNombres(rs.getString("NOMBRES"));
                alumno.setApellPaterno(rs.getString("APELLIDO_PATERNO"));
                alumno.setApellMaterno(rs.getString("APELLIDO_MATERNO"));
                alumno.setCedula(rs.getString("CEDULA"));
                alumno.setGenero(rs.getString("GENERO"));
                alumno.setEdad(rs.getInt("EDAD"));
                alumno.setTelefono(rs.getString("TELEFONO"));
                alumno.setCorreo(rs.getString("CORREO"));
                alumno.setProvincia(rs.getString("PROVINCIA"));
                alumno.setCanton(rs.getString("CANTON"));
                alumno.setParroquia(rs.getString("PARROQUIA"));
                alumno.setDireccion(rs.getString("DIRECCION"));
                alumno.setEstado(rs.getString("ESTADO"));
                alumno.setNomRepresentantes(rs.getString("NOMBRES_REPRESENTANTE"));
                alumno.setApellRepresentante(rs.getString("APELLIDOS_REPRESENTANTE"));
                lista.add(alumno);
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
        String sql = "select count(*) from alumnos where id_alumno=?";
        
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

    public int guardar(AlumnoDTO alumno) throws ErrorDB {

        if (existe(alumno.getIdAlumno())) {
            return modificar(alumno);
        } else {
            return insertar(alumno);
        }
    }
    
    public static void main(String[] args) {
       AlumnoDTO alumno = new AlumnoDTO();
        try {
            System.out.print(new AlumnoDAO().existe(alumno.getIdAlumno()));
        } catch (ErrorDB ex) {
            System.out.print("Error: "+ex.getMessage());
        }
    }
    

}
