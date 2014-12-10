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
 * @author vinay
 */
public class ReuseTaskTest {
    
    public ReuseTaskTest() {
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
     * Test of doGet method, of class ReuseTask.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        ReuseTask instance = new ReuseTask();   
        boolean flag=true;
       flag= instance.JUNIT(3);
       System.out.println(flag); 
       assertEquals(true,flag);
        
    }

   
    
}
