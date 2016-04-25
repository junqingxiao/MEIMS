package action.app.admin;

import action.common.CommonAction;
import data.Tenant;
import filter.log.Log4admin;
import manager.AdminManager;

import java.util.List;

/**
 * @author mk
 */
public class RegisterOkAction extends CommonAction
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
        adminManager.registerOk(name,password);
        //这里要为新注册的用户新建表
        adminManager.createTenantSchema(adminManager.getTenantId(name,password));

        list=adminManager.getRegisterTenant();

        Log4admin log4admin=new Log4admin();
        log4admin.log("同意新用户注册.账号:"+name+",密码:"+password);

        getLogger().info("accepted new tenant.name:"+name+",password:"+password);
        return SUCCESS;
    }
}
