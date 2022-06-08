/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PeriodoDTO;
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
public class PeriodoDAOTest {
    
    public PeriodoDAOTest() {
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
     * Test of insertar method, of class PeriodoDAO.
     */
    @Test
    public void testInsertar() throws Exception {
        System.out.println("insertar");
        PeriodoDTO a = new PeriodoDTO();
        a.setDescripcion("PERIODO 2018");
        a.setEstado("ACTIVO");
        a.setFechaInicio("2016-11-11");
        a.setFechaFin("2018-11-11");
        PeriodoDAO instance = new PeriodoDAO();
        int expResult = 1;
        int result = instance.insertar(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("No se registro el periodo");
    }
    
}
