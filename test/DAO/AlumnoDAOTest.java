/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AlumnoDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author FAFILAB1
 */
public class AlumnoDAOTest {
    
    public AlumnoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of eliminar method, of class AlumnoDAO.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        int id = 0;
        AlumnoDAO instance = new AlumnoDAO();
        int expResult = 0;
        int result = instance.eliminar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conseguir method, of class AlumnoDAO.
     */
    @Test
    public void testConseguir() throws Exception {
        System.out.println("conseguir");
        int id = 0;
        AlumnoDAO instance = new AlumnoDAO();
        AlumnoDTO expResult = null;
        AlumnoDTO result = instance.conseguir(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class AlumnoDAO.
     */
    @Test
    public void testListar_0args() throws Exception {
        System.out.println("listar");
        AlumnoDAO instance = new AlumnoDAO();
        List<AlumnoDTO> expResult = null;
        List<AlumnoDTO> result = instance.listar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class AlumnoDAO.
     */
    @Test
    public void testListar_String_String() throws Exception {
        System.out.println("listar");
        String campo = "";
        String valor = "";
        AlumnoDAO instance = new AlumnoDAO();
        List<AlumnoDTO> expResult = null;
        List<AlumnoDTO> result = instance.listar(campo, valor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardar method, of class AlumnoDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        AlumnoDTO alumno = new AlumnoDTO();
        alumno.setNombres("PABLO");
        alumno.setIdRepresentante(3);
        alumno.setApellMaterno("ROSA");
        alumno.setApellPaterno("CASTRO");
        alumno.setFechaNacimiento("1990-11-11");
        alumno.setCedula("13232323");
        alumno.setGenero("MASCULINO");
        alumno.setEstado("ACTIVO");
        
        AlumnoDAO instance = new AlumnoDAO();
        int expResult = 1;
        int result = instance.guardar(alumno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("NO SE PUDO REGISTRAR");
    }

    /**
     * Test of main method, of class AlumnoDAO.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AlumnoDAO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
