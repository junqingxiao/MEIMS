package action.app.admin;

import action.common.CommonAction;
import filter.log.Log4admin;
import manager.AdminManager;

/**
 * @author mk
 */
public class TenantAddAction extends CommonAction
{
    private String name;
    private String password;

    private String message;//返回的错误信息

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        if (adminManager.addTenant(name,password))
        {//如果已经存在
            getLogger().info("the tenant that "+getSessionName()+" wanted to add is existed,refused.");
            message=name+"已经存在,请换一个名字";
            adminManager.close();
            return ERROR;
        }
        else
        {
            //新建一个schema
            adminManager.createTenantSchema(adminManager.getTenantId(name,password));

            getLogger().info(getSessionName()+" insert a tenant.");

            //应用日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log(getSessionName()+"添加了一个租户:"+name+".");
            adminManager.close();
            return SUCCESS;
        }

    }
}
