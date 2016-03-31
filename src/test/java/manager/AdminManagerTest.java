package manager;

import org.junit.Test;

import java.util.List;

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

        List list= adminManager.getTenant();

        adminManager.close();
    }
}