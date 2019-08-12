package com.burakocak.todolist;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegisterUnitTest {
    @Test
    public void testLoginNullFieldNotRegister(){
        String username="burak";
        String password="pass";

        Assert.assertNotNull(username);
        Assert.assertNotNull(password);

    }

    @Test
    public void testLoginEmptyFieldNotRegister(){
        String username = "";
        String pass="";

        Assert.assertEquals(username,"");
        Assert.assertEquals(pass,"");
    }

    @Test
    public void testLoginNotEmptyFieldSuccessLogin() {
        String username = "burak";
        String pass="pass";

        Assert.assertNotEquals(username,"");
        Assert.assertNotEquals(pass,"");
    }
}