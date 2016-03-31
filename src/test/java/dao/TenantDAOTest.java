package dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mk
 */
public class TenantDAOTest
{
    @Test
    public void test()
    {
        TenantDAO tenantDAO=new TenantDAO();
        tenantDAO.init();
        tenantDAO.createSchema(3);
        tenantDAO.close();
    }
}