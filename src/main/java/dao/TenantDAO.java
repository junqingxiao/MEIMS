package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mk
 */
public class TenantDAO extends AbstractAdminDAO
{
    @Override
    String getClassName()
    {
        return "tenant";
    }

    /**
     * 按账户密码插入纪录
     * @param name 租户账号
     * @param password 租户密码
     */
    public final void insert(String name,String password)
    {
        insert("Name",name,"Password",password);
    }

    /**
     * 获得id 应该保证在查询是否存在后使用
     */
    public final int getId(String name,String password)
    {
        ResultSet set=query("name",name,"password",password);

        try
        {
            set.next();
            return set.getInt(1);

        }
        catch (SQLException e)
        {
            getLogger().error("Error in getId.",e);
            throw new RuntimeException();
        }
    }

    /**
     * 获得id 应该保证在查询是否存在后使用
     */
    public final int getId(String name)
    {
        ResultSet set=query("name",name);

        try
        {
            set.next();
            return set.getInt(1);

        }
        catch (SQLException e)
        {
            getLogger().error("Error in getId...",e);
            throw new RuntimeException();
        }
    }
}
