package action.app.admin;

import action.common.CommonAction;
import data.Tenant;
import filter.log.Log4admin;
import manager.AdminManager;

import java.util.List;

/**
 * @author mk
 */
public class RegisterRemoveAction extends CommonAction
{
    private String name;
    private String password;

    private List<Tenant> list;

    public List<Tenant> getList() {
        return list;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public final String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        adminManager.registerRemove(name,password);

        list=adminManager.getRegisterTenant();

        Log4admin log4admin=new Log4admin();
        log4admin.log("拒绝新用户注册.账号:"+name+",密码:"+password);
        return SUCCESS;
    }
}
