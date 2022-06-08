/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author PABLO
 */
public class Conexion {

    private String url = "";
    private String usuario = "";
    private String contrasena = "";
    private String driver = "";
    private Connection conexion = null;
    Properties prop = new Properties();

    public Connection getConexion() throws SQLException, IOException, ClassNotFoundException {

        prop.load(this.getClass().getClassLoader().getResourceAsStream("conexiones/datos.properties"));

        url = prop.getProperty("url");
        usuario = prop.getProperty("usuario");
        contrasena = prop.getProperty("contrasena");
        driver = prop.getProperty("driver");

        Class.forName(driver);
        conexion = DriverManager.getConnection(url, usuario, contrasena);
        conexion.setAutoCommit(false);
        return conexion;

    }

}
