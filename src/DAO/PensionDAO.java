package DAO;

import DTO.PensionDTO;
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
public class PensionDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertar(PensionDTO pension) throws ErrorDB {
        String sql = "{call insertarPension(?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setString(1, pension.getDescripcion());
            cs.setDouble(2, pension.getValorMatricula());
            cs.setDouble(3, pension.getValorMensual());
            cs.setInt(4, pension.getIdPeriodo());
            cs.setString(5, pension.getEstado());

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
            throw new ErrorDB("Error en la conexion insertar: " + ex.getMessage());
        }
    }

    public int modificar(PensionDTO pension) throws ErrorDB {
        String sql = "{call modificarPension(?,?,?,?,?,?)}";
        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, pension.getIdPension());
            cs.setString(2, pension.getDescripcion());
            cs.setDouble(3, pension.getValorMatricula());
            cs.setDouble(4, pension.getValorMensual());
            cs.setInt(5, pension.getIdPeriodo());
            cs.setString(6, pension.getEstado());

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
            throw new ErrorDB("Error en la conexion modificar: " + ex.getMessage());
        }
    }

    public int eliminar(int id) throws ErrorDB {
        String sql = "{call eliminarPension(?)}";
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

    public PensionDTO conseguir(int id) throws ErrorDB {
        PensionDTO pension = new PensionDTO();
        String sql = "{call conseguirPension(?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            while (rs.next()) {
                pension.setIdPension(rs.getInt("ID"));
                pension.setDescripcion(rs.getString("DESCRIPCION"));
                pension.setValorMatricula(rs.getDouble("VALOR_MATRICULA"));
                pension.setValorMensual(rs.getDouble("VALOR_MENSUAL"));
                pension.setIdPeriodo(rs.getInt("ID_PERIODO"));
                pension.setEstado(rs.getString("ESTADO"));
            }

            conDB.close();
            cs.close();
            rs.close();

            return pension;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<PensionDTO> listar() throws ErrorDB {
        List<PensionDTO> lista = new ArrayList<>();
        PensionDTO pension;
        String sql = "select * from mostrarPensiones";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                pension = new PensionDTO();
                pension.setIdPension(rs.getInt("ID"));
                pension.setDescripcion(rs.getString("DESCRIPCION"));
                pension.setValorMatricula(rs.getDouble("VALOR_MATRICULA"));
                pension.setValorMensual(rs.getDouble("VALOR_MENSUAL"));
                pension.setPeriodo(rs.getString("PERIODO"));
                pension.setEstado(rs.getString("ESTADO"));
                lista.add(pension);
            }

            conDB.close();
            ps.close();
            rs.close();

            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public List<PensionDTO> listar(String campo, String valor) throws ErrorDB {
        List<PensionDTO> lista = new ArrayList<>();
        PensionDTO pension;
        String sql = "select * from mostrarPensiones where " + campo + " LIKE ?";

        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setString(1, valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                pension = new PensionDTO();
                pension.setIdPension(rs.getInt("ID"));
                pension.setDescripcion(rs.getString("DESCRIPCION"));
                pension.setValorMatricula(rs.getDouble("VALOR_MATRICULA"));
                pension.setValorMensual(rs.getDouble("VALOR_MENSUAL"));
                pension.setPeriodo(rs.getString("PERIODO"));
                pension.setEstado(rs.getString("ESTADO"));
                lista.add(pension);
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
        String sql = "select count(*) from pensiones where id_pension=?";

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

    public int guardar(PensionDTO pension) throws ErrorDB {

        if (existe(pension.getIdPension())) {
            return modificar(pension);
        } else {
            return insertar(pension);
        }
    }
    
    public static void main(String[] args) {
        try{
            PensionDTO pension = new PensionDTO();
            pension.setIdPension(5);
            pension.setDescripcion("PENSION ENERO");
            pension.setValorMatricula(20);
            pension.setValorMensual(24);
            pension.setIdPeriodo(30);
            pension.setEstado("ACTIVO");
            System.out.print(""+new PensionDAO().modificar(pension));
            
        }catch(ErrorDB ex){
            System.out.print(ex.getMessage());
        }
    }

}
