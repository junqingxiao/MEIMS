package dao;

/**
 * @author mk
 */
public class AdminDAO extends AbstractAdminDAO
{
    @Override
    String getClassName()
    {
        return "admin";
    }

    /**
     * 按账户密码插入纪录
     * @param name 超级管理员账号
     * @param password 超级管理员密码
     */
    public void insert(String name,String password)
    {
        insert("Name",name,"Password",password);
    }
}
