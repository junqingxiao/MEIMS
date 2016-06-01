package manager;

import action.common.Constrants;
import dao.AdminDAO;
import dao.LogTimeDAO;
import dao.TenantCheckDAO;
import dao.TenantDAO;
import data.Tenant;
import data.TenantTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    private TenantCheckDAO tenantCheckDAO=new TenantCheckDAO();
    private LogTimeDAO logTimeDAO=new LogTimeDAO();

    public  AdminManager()
    {
        adminDAO.init();
        tenantDAO.init();
        tenantCheckDAO.init();
        logTimeDAO.init();
    }

    /**
     * 由name查询登入登出记录 最近的在最前面
     * @param name
     * @return
     */
    public List<TenantTime> showTenentTime(String name)
    {
        List<TenantTime> list=new ArrayList<TenantTime>();
        ResultSet set=logTimeDAO.query("TNo",tenantDAO.getId(name));
        try
        {
            while (set.next())
            {
                TenantTime tenantTime=new TenantTime();
                tenantTime.setLoginTime(set.getTimestamp(3));
                tenantTime.setLogoutTime(set.getTimestamp(4));
                list.add(0,tenantTime);
            }
            getLogger().info("got tenantTime.");
        }
        catch (SQLException e)
        {
            getLogger().error("Error in get got tenantTime.",e);
            throw new RuntimeException();
        }
        return list;
    }

    /**
     *插入登出时间 将一个最近的Constrants.ORIGINTIME替换
     */
    public void insertLogoutTime(int TNo,Timestamp logoutTime)
    {
        logTimeDAO.insertLogoutTime(TNo, logoutTime);
    }

    /**
     * 插入登录时间 等出时间暂时由Constrants.ORIGINTIME代替
     */
    public void insertLoginTime(int TNo, Timestamp loginTime)
    {
        logTimeDAO.insertLoginTime(TNo,loginTime);
    }

    /**
     * 不同意注册
     */
    public final void registerRemove(String name,String password)
    {
        tenantCheckDAO.delete(tenantCheckDAO.getId(name,password));
    }

    /**
     * 同意注册
     */
    public final void registerOk(String name,String password)
    {
        tenantDAO.insert(name,password);

        tenantCheckDAO.delete(tenantCheckDAO.getId(name,password));
    }

    /**
     * 用户注册
     */
    public final void registerTenant(String name,String password)
    {
        tenantCheckDAO.insert(name,password);
    }

    /**
     * 修改 admin 的密码
     */
    public final void updateAdminPassword(String oldName,String password)
    {
        adminDAO.update("password",password,adminDAO.getId(oldName));
    }

    /**
     * 修改 tenant 的密码
     */
    public final void updateTenantPassword(String oldName,String password)
    {
        tenantDAO.update("password",password,tenantDAO.getId(oldName));
    }

    /**
     * 修改 admin 的账户
     * 先查看是否重名
     */
    public final boolean updateAdminAccount(String oldName,String name)
    {
        if(isTenant(name) || isAdmin(name) || oldName.equals(name))
        {
            //新改的名字是已经存在的,这里加上了admin的判断
            return true;
        }
        else
        {
            adminDAO.update("name",name,adminDAO.getId(oldName));
        }
        return false;
    }

    /**
     * 修改 tenant 的账户
     * 先查看是否重名
     */
    public final boolean updateTenantAccount(String oldName,String name)
    {
        if(isTenant(name) || isAdmin(name) || oldName.equals(name))
        {
            //新改的名字是已经存在的,这里加上了admin的判断
            return true;
        }
        else
        {
            tenantDAO.update("name",name,tenantDAO.getId(oldName));
        }
        return false;
    }

    /**
     * 获得id
     */
    public final int getTenantId(String name,String password)
    {
        return tenantDAO.getId(name,password);
    }

    /**
     * 根据id新建schema
     */
    public final void createTenantSchema(int id)
    {
        tenantDAO.createSchema(id);
    }

    /**
     * 删除schema
     */
    public final void dropTenantSchema(int id)
    {
        tenantDAO.dropScheme(id);
    }

    /**
     * 删除id的登录登出记录
     * @param id
     */
    public final void dropTenantLogtime(int id)
    {
        logTimeDAO.delete("TNo",id);
    }

    /**
     *先判断name是否存在 再添加
     * @return 新的name是否已存在
     */
    public final boolean addTenant(String name,String password)
    {
        if (isTenant(name) || isAdmin(name))
        {//新改的名字是已经存在的,这里加上了admin的判断
            return true;
        }
        else
        {
            tenantDAO.insert(name,password);
        }
        return false;
    }
    /**
     *根据旧的name password更新
     * @return 新的name是否已存在
     */
    public final boolean updateTenant(String oldName,String oldPassword,String name,String password)
    {
        if ((isTenant(name) || isAdmin(name))&& !oldName.equals(name))
        {//新改的名字是已经存在的,这里加上了admin的判断
            return true;
        }
        else
        {
            tenantDAO.update("name",name,"password",password,tenantDAO.getId(oldName,oldPassword));
        }
        return false;
    }
    /**
     * 根据name,password删除tenant
     */
    public final void deleteTenant(String name,String password)
    {
        tenantDAO.delete("name",name,"password",password);
    }

    /**
     * 获得注册的 tenant的列表
     * @return 一个tenant对象的list
     * 这里按主键倒序排列 1在最后面
     */
    public final List<Tenant> getRegisterTenant()
    {
        List<Tenant> list=new ArrayList<Tenant>();

        ResultSet set=tenantCheckDAO.queryAll();
        try
        {
            while (set.next())
            {
                Tenant tenant=new Tenant();
                tenant.setPassword(set.getString(3));
                tenant.setNo(set.getInt(1));
                tenant.setName(set.getString(2));

                list.add(0,tenant);
            }
            getLogger().info("got register tenant.");
        }
        catch(SQLException e)
        {
            getLogger().error("Error in get register tenant.",e);
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * 获得tenant的列表
     * @return 一个tenant对象的list
     * 这里按主键倒序排列 1在最后面
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

                list.add(0,tenant);
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
     * @return 身份
     */
    public final String getIdentity(String name,String password)
    {
        if (isAdmin(name,password))
        {
            return Constrants.ADMIN;
        }
        else if(isTenant(name,password))
        {
            return Constrants.TENANT;
        }
        else
        {
            getLogger().info(name+" try to use wrong password,refused.");
            return Constrants.NOBODY;
        }
    }

    /**
     * 获得登录者的身份
     * @return 身份
     */
    public final String getIdentity(String name)
    {
        if (isAdmin(name))
        {
            return Constrants.ADMIN;
        }
        else if(isTenant(name))
        {
            return Constrants.TENANT;
        }
        else if (isRegisterTenant(name))
        {
              return Constrants.TENANT;
        }
        else
        {
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
     *是否是超级管理员
     */
    private boolean isAdmin(String name)
    {
        ResultSet set=adminDAO.query("Name",name);

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
    private boolean isTenant(String name)
    {
        ResultSet set=tenantDAO.query("Name",name);

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
    private boolean isRegisterTenant(String name)
    {
        ResultSet set=tenantCheckDAO.query("Name",name);

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
        tenantCheckDAO.close();
        logTimeDAO.close();
    }
}
