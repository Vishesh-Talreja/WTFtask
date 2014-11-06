/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author visheshtalreja
 */
@org.junit.runner.RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({Check_EmailTest.class, Check_UsernameTest.class, Validate_AssigneeTest.class, Complete_TaskTest.class, LoginTest.class, Add_TaskTest.class, Add_FriendTest.class, New_friendTest.class, RegistrationTest.class, DisplayChartTest.class, SearchTest.class, LogoutTest.class})
public class RootSuite {

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
    
}
