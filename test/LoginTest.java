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
public class LoginTest {
    

    /**
     * Test of doGet method, of class Login.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        boolean flag=false;
        Login instance = new Login();
        flag = instance.JUNIT(flag);
        assertEquals(true,flag);
    }

    
}
