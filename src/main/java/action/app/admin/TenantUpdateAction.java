package action.app.admin;

/**
 * @author mk
 */

import action.common.CommonAction;
import filter.log.Log4admin;
import manager.AdminManager;

public class TenantUpdateAction extends CommonAction
{
    private String name;
    private String password;

    private String oldName;
    private String oldPassword;

    private String message;//返回的错误信息

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getOldName() {
        return oldName;
    }

    public String getOldPassword() {
        return oldPassword;
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

    public void setOldName(String oldName) {this.oldName = oldName;}

    public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}

    public String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        if (adminManager.updateTenant(oldName,oldPassword,name,password))
        {//如果已经存在
            getLogger().info("the tenant that "+getSessionName()+" wanted to update is existed,refused.");
            message=name+"已经存在,请换一个名字";
            adminManager.close();
            return ERROR;
        }
        else
        {
            getLogger().info(getSessionName()+" update a tenant.");

            //应用日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log(getSessionName()+"更新了一个租户.");
            adminManager.close();
            return SUCCESS;
        }

    }
}

