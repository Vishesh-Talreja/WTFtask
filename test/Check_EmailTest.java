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
public class Check_EmailTest {
    
    public Check_EmailTest() {
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
     * Test of doGet method, of class Check_Email.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        boolean flag = false;
        Check_Email instance = new Check_Email();
        //flag = instance.JUNIT(flag);
        assertEquals(true,flag);
    }

    
}
