package manager;

import action.common.Constrants;
import dao.AdminDAO;
import dao.TenantDAO;
import data.Tenant;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import org.MtConnector.Session.MtResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mk
 * Manager 返回de数据都是List
 */
public class AdminManager extends AbstractManager
{


    private AdminDAO adminDAO=new AdminDAO();
    private TenantDAO tenantDAO=new TenantDAO();

    public  AdminManager()
    {
        adminDAO.init();
        tenantDAO.init();
    }

    /**
     * 获得tenant的列表
     * @return 一个tenant对象的list
     */
    public final List<Tenant> getTenant()
    {
        List<Tenant> list=new ArrayList<Tenant>();

        ResultSet set=tenantDAO.queryAll();
        try
        {
            while (set.next())
            {
                Tenant tenant=new Tenant();
                tenant.setNo(set.getInt(1));
                tenant.setName(set.getString(2));
                tenant.setPassword(set.getString(3));

                list.add(tenant);
            }
            getLogger().info("got tenant.");
        }
        catch(SQLException e)
        {
            getLogger().error("Error in get tenant.",e);
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * 获得tenant的数量
     */
    public final int getTenantNumber()
    {
        int number;

        try
        {
            ResultSet set=tenantDAO.count();
            set.next();
            number=set.getInt(1);
            getLogger().info("got tenant number.");
        }
        catch (SQLException e)
        {
            getLogger().error("Error in next()",e);
            throw new RuntimeException();
        }

        return number;
    }

    /**
     * 获得admin的数量
     */
    public final int getAdminNumber()
    {
        int number;

        try
        {
            ResultSet set=adminDAO.count();
            set.next();
            number=set.getInt(1);
            getLogger().info("got admin number.");
        }
        catch (SQLException e)
        {
            getLogger().error("Error in next()",e);
            throw new RuntimeException();
        }

        return number;
    }

    /**
     * 获得登录者的身份
     * 处理log部分
     * @return 身份
     */
    public final String getIdentity(String name,String password)
    {
        if (isAdmin(name,password))
        {
            Log4admin log4admin=new Log4admin();
            log4admin.log(name+" 登录了.");
            return Constrants.ADMIN;
        }
        else if(isTenant(name,password))
        {
            Log4tenant log4tenant=new Log4tenant(name);
            log4tenant.log("登录了.");
            return Constrants.TENANT;
        }
        else
        {
            getLogger().info(name+" try to use wrong password,refused.");
            return Constrants.NOBODY;
        }
    }

    /**
     *是否是超级管理员
     */
    private boolean isAdmin(String name,String password)
    {
        ResultSet set=adminDAO.query("Name",name,"Password",password);

        boolean returnValue;
        try
        {
            returnValue=set.next();
        }
        catch (SQLException e)
        {
            getLogger().error("Error in next()",e);
            throw new RuntimeException();
        }
        return returnValue;
    }

    /**
     * 是否是租户
     */
    private boolean isTenant(String name,String password)
    {
        ResultSet set=tenantDAO.query("Name",name,"Password",password);

        boolean returnValue;
        try
        {
            returnValue=set.next();
        }
        catch (SQLException e)
        {
            getLogger().error("Error in next()",e);
            throw new RuntimeException();
        }
        return returnValue;
    }

    /**
     * 关闭
     */
    public final void close()
    {
        adminDAO.close();
        tenantDAO.close();
    }


}
