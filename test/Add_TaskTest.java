/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visheshtalreja
 */
public class Add_TaskTest {
    
    public Add_TaskTest() {
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
     * Test of processRequest method, of class Registration.
     */
    @org.junit.Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        Add_Task instance = new Add_Task();
        boolean flag = false;
        flag = instance.JUNIT(flag);
        assertEquals(true,flag);
    }

    
}
