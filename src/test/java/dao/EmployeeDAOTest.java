package dao;

import java.sql.Date;

import org.MtConnector.Session.MtResultSet;
import org.junit.Test;

/**
 * @author mk
 */
public class EmployeeDAOTest
{
    @Test
    public void test()
    {
        EmployeeDAO employeeDAO=new EmployeeDAO();
        employeeDAO.init("tenant_1");

        Date time= new java.sql.Date(new java.util.Date().getTime());

        MtResultSet set=employeeDAO.query(3);
        while (set.next()) {
            System.out.println(set.getInt(1) + "\t" + set.getString(2));
        }

        employeeDAO.close();
    }
}