package dao;

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
    public void insert(String name,String password)
    {
        insert("Name",name,"Password",password);
    }
}
