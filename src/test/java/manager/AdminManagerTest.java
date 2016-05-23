package manager;

import action.common.Constrants;
import org.junit.Test;

import java.sql.Timestamp;
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

        adminManager.insertLogoutTime(27, Timestamp.valueOf("2002-01-01 00:00:00"));

        adminManager.close();
    }
}