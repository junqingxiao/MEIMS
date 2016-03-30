package manager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mk
 */
public class AdminManagerTest
{
    @Test
    public void test()
    {
        AdminManager adminManager=new AdminManager();

        adminManager.updateTenant("c","ccc","cc","cccc");

        adminManager.close();
    }
}