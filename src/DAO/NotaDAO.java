package DAO;

import DTO.NotaDTO;
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
public class NotaDAO {

    private Connection conDB = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<NotaDTO> conseguirNotasCurso(int idCurso, int idMateria, int idQuimestre, int idPeriodo) throws ErrorDB {

        NotaDTO nota;
        List<NotaDTO> lista = new ArrayList<>();
        String sql = "{call conseguirNotaCurso(?,?,?,?)}";

        try {
            conDB = new Conexion().getConexion();
            cs = conDB.prepareCall(sql);
            cs.setInt(1, idCurso);
            cs.setInt(2, idMateria);
            cs.setInt(3, idQuimestre);
            cs.setInt(4, idPeriodo);
            rs = cs.executeQuery();

            while (rs.next()) {
                nota = new NotaDTO();
                nota.setIdMatricula(rs.getInt("ID_MATRICULA"));
                nota.setNombres(rs.getString("NOMBRES"));
                nota.setMateria(rs.getString("MATERIA"));
                //nota.setQuimestre(rs.getString("QUIMESTRE"));
                nota.setNotaP1(rs.getDouble("PRIMER_PARCIAL"));
                nota.setNotaP2(rs.getDouble("SEGUNDO_PARCIAL"));
                nota.setNotaP3(rs.getDouble("TERCER_PARCIAL"));

                //nota.setPromedio(rs.getDouble("PROMEDIO"));
                nota.setPeriodo("PERIODO");

                lista.add(nota);

            }
            conDB.close();
            cs.close();
            rs.close();
            return lista;

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            throw new ErrorDB("Error en la conexion: " + ex.getMessage());
        }
    }

    public int registrarNota(NotaDTO nota) throws ErrorDB {
        String sql = "insert into notas values(null,?,?,?,?,?,?,?,?,?)";
        try {
            conDB = new Conexion().getConexion();
            ps = conDB.prepareStatement(sql);
            ps.setInt(1, nota.getIdMateria());
            ps.setInt(2, nota.getIdMatricula());
            ps.setInt(3, nota.getIdPeriodo());
            ps.setDouble(4, nota.getNotaP1());
            ps.setDouble(5, nota.getNotaP2());
            ps.setDouble(6, nota.getNotaP3());
            ps.setDouble(7, nota.getNotaP1Q());
            ps.setDouble(8, nota.getNotaP2Q());
            ps.setDouble(9, nota.getNotaP3Q());

            int res = ps.executeUpdate();
            conDB.commit();
            conDB.close();
            ps.close();
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
